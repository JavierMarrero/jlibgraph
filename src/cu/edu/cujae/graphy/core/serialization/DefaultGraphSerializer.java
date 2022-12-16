/*
 * Copyright (C) 2022 Javier Marrero.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package cu.edu.cujae.graphy.core.serialization;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.Weight;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Javier Marrero
 */
public class DefaultGraphSerializer implements GraphSerializer
{

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphSerializer.class);

    private static class EdgeDescriptor
    {

        public EdgeDescriptor(int u, int v,
                              Weight<?> weight)
        {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public int u;
        public int v;
        public Weight<?> weight;

    }

    public DefaultGraphSerializer()
    {
        LOGGER.debug("Created a new serialization class...");
    }

    @Override
    @SuppressWarnings ("unchecked")
    public Graph<?> deserialize(InputStream origin, CustomSerializer<Object> customSerializer) throws IOException,
                                                                                                      ClassNotFoundException
    {
        try ( DataInputStream stream = new DataInputStream(origin))
        {
            byte[] magic_number = stream.readNBytes(4);
            if (!GraphSerializer.matchesMagicNumber(magic_number))
            {
                throw new IOException("invalid magic number!! wrong file format.");
            }

            boolean directed = stream.readByte() == 1;
            boolean weighted = stream.readByte() == 1;

            Graph<?> graph;
            if (weighted)
            {
                graph = GraphBuilders.makeSimpleWeightedGraph(directed);
            }
            else
            {
                graph = GraphBuilders.makeSimpleGraph(directed);
            }

            List<EdgeDescriptor> edges = new ArrayList<>();

            int number_of_nodes = stream.readInt();
            while (number_of_nodes-- > 0)
            {
                edges.addAll(readNode((Graph<Object>) graph, stream, customSerializer));
            }

            for (EdgeDescriptor edgeDescriptor : edges)
            {
                if (weighted)
                {
                    ((WeightedGraph<?>) graph).connect(edgeDescriptor.u, edgeDescriptor.v, edgeDescriptor.weight);
                }
                else
                {
                    graph.connect(edgeDescriptor.u, edgeDescriptor.v);
                }
            }
            return graph;
        }
    }

    private List<EdgeDescriptor> readNode(Graph<Object> graph, DataInputStream stream,
                                          CustomSerializer<Object> customSerializer) throws
            IOException, ClassNotFoundException
    {
        List<EdgeDescriptor> result = new ArrayList<>();

        int label = stream.readInt();
        int sfl = stream.readInt();

        byte[] pack = stream.readNBytes(sfl);
        int attributesSize = stream.readInt();

        Object data = customSerializer.deserialize(pack);

        LOGGER.info("Read node with label {} and serialized size {} (data {}). ({} attributes)",
                    label, sfl, data, attributesSize);

        graph.add(label, data);

        for (int i = 0; i < attributesSize; ++i)
        {
            // Read an attribute
        }

        // Go with every edge
        int edgeCount = stream.readInt();
        LOGGER.info("{} edges departing node {}", edgeCount, label);

        for (int i = 0; i < edgeCount; ++i)
        {
            int u = stream.readInt();
            int v = stream.readInt();
            boolean wp = stream.readBoolean();

            int size = stream.readInt();

            LOGGER.info("Read edge descriptor ({} -> {}), weight present? {}", u, v, wp);
            Weight<?> w = null;

            if (wp)
            {
                byte[] serializedWeight = stream.readNBytes(size);
                w = (Weight<?>) deserializeObject(serializedWeight);
            }
            result.add(new EdgeDescriptor(u, v, w));
        }

        return result;
    }

    /**
     *
     * @param destination
     * @param graph
     * @param customSerializer
     *
     * @throws IOException
     */
    @Override
    @SuppressWarnings ("unchecked")
    public void serialize(OutputStream destination,
                          Graph<?> graph, CustomSerializer<Object> customSerializer) throws IOException
    {
        // Write the file header
        try ( DataOutputStream stream = new DataOutputStream(destination))
        {
            // Write the file header
            stream.write(MAGIC_NUMBER);

            // Header
            stream.write(graph.isDirected() ? 1 : 0);   // 1B - directed
            stream.write(graph.isWeighted() ? 1 : 0);   // 1B - weighted
            stream.writeInt(graph.size());              // 4B - node quantity

            // Go with every node
            GraphIterator<?> iterator = (GraphIterator<?>) graph.iterator();
            while (iterator.hasNext())
            {
                iterator.next();
                writeNode(stream, (GraphIterator<Object>) iterator, customSerializer);

                // Now with every edge of that node
                // stream.writeInt(0xFF00FF00); // Magic number
                stream.writeInt(iterator.getEdgesDepartingSelf().size()); // 4B - edge quantity for node
                for (Edge e : iterator.getEdgesDepartingSelf())
                {
                    int u = e.getStartNode().getLabel();
                    int v = e.getFinalNode().getLabel();
                    Weight<?> w = e.getWeight();

                    stream.writeInt(u);
                    stream.writeInt(v);

                    stream.writeBoolean(w != null);
                    if (w != null)
                    {
                        byte[] bw = serializeObject(w);

                        // Write
                        stream.writeInt(bw.length);
                        stream.write(bw);
                    }
                    else
                    {
                        stream.writeInt(0);
                    }
                }
            }
        }
    }

    private void writeNode(DataOutputStream stream, GraphIterator<Object> iterator, CustomSerializer<Object> serializer)
            throws IOException
    {
        byte[] serializedForm = serializer.serialize(iterator.get());

        stream.writeInt(iterator.getLabel());
        stream.writeInt(serializedForm.length);
        stream.write(serializedForm);
        stream.writeInt(iterator.getAllAttributes().size());

        for (Entry<Object, Object> entry : iterator.getAllAttributes().entrySet())
        {
            writeAttribute(stream, entry.getKey(), entry.getValue());
        }
    }

    private void writeAttribute(DataOutputStream stream, Object key, Object value) throws IOException
    {
        byte[] bKey = serializeObject(key);
        byte[] bVal = serializeObject(value);

        stream.writeInt(bKey.length);
        stream.write(bKey);
        stream.writeInt(bVal.length);
        stream.write(bVal);
    }

    private byte[] serializeObject(Object object) throws IOException
    {
        byte[] result;
        try ( ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();  ObjectOutputStream oos
                                                                                                                 = new ObjectOutputStream(
                     arrayOutputStream))
        {
            oos.writeObject(object);
            result = arrayOutputStream.toByteArray();
        }

        return result;
    }

    private Object deserializeObject(byte[] memory) throws IOException, ClassNotFoundException
    {
        Object result;
        try ( ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(memory);  ObjectInputStream ois
                                                                                                                   = new ObjectInputStream(
                     arrayInputStream))
        {

            result = ois.readObject();
        }

        return result;
    }

}
