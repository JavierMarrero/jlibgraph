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
package cu.edu.cujae.graphy.tests;

import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.serialization.CustomSerializer;
import cu.edu.cujae.graphy.core.serialization.DefaultCustomSerializers;
import cu.edu.cujae.graphy.core.serialization.DefaultGraphSerializer;
import cu.edu.cujae.graphy.core.serialization.GraphSerializer;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Javier Marrero
 */
public class SerializationTest
{

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings ("unchecked")
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        File file = new File("serialized.txt");
        try ( FileOutputStream stream = new FileOutputStream(file))
        {
            WeightedGraph<Integer> graph = GraphBuilders.makeSimpleWeightedGraph(true);
            graph.add(1, 1);
            graph.add(2, 2);

            graph.connect(1, 2, Weights.makeWeight(5));

            GraphSerializer serializer = new DefaultGraphSerializer();
            serializer.serialize(stream, graph, (CustomSerializer<Object>) DefaultCustomSerializers.
                                 getIntegerSerializer());

            System.out.println(graph);
        }

        try ( FileInputStream stream = new FileInputStream(file))
        {
            WeightedGraph<Integer> graph = (WeightedGraph<Integer>) new DefaultGraphSerializer().deserialize(stream,
                                                                                                             (CustomSerializer<Object>) DefaultCustomSerializers.
                                                                                                                     getIntegerSerializer());
            System.out.println(graph);
        }
    }

}
