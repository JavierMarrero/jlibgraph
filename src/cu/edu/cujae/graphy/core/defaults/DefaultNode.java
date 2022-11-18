/*
 * Copyright (C) 2022 CUJAE.
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
package cu.edu.cujae.graphy.core.defaults;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.exceptions.InvalidKeyException;
import cu.edu.cujae.graphy.core.exceptions.InvalidOperationException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This is the default implementation of the {@link Node} interface.It provides some default operations for nodes.
 *
 * @author Javier Marrero
 * @param <T>
 */
public class DefaultNode<T> implements Node<T>
{

    private final Map<Object, Object> attributes;
    private Map<Node<T>, Edge> connectionsFromVertex;
    private Map<Node<T>, Edge> connectionsToVertex;
    private T data;
    private final int label;

    /**
     * Default public constructor.
     *
     * @param label
     * @param data
     */
    public DefaultNode(int label, T data)
    {
        this.attributes = new HashMap<>(5);
        this.connectionsFromVertex = new LinkedHashMap<>(5);
        this.connectionsToVertex = new LinkedHashMap<>(5);
        this.data = data;
        this.label = label;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @SuppressWarnings ("unchecked")
    public boolean addEdge(Edge edge)
    {
        boolean result = (connectionsFromVertex.putIfAbsent((Node<T>) edge.getFinalNode(), edge) == null);
        if (edge.getFinalNode() instanceof DefaultNode)
        {
            DefaultNode<T> u = (DefaultNode<T>) edge.getFinalNode();
            result &= (u.connectionsToVertex.putIfAbsent(this, edge) == null);
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        @SuppressWarnings ("unchecked")
        DefaultNode<T> clone = (DefaultNode<T>) super.clone();

        clone.connectionsFromVertex = new LinkedHashMap<>(connectionsFromVertex);
        clone.connectionsToVertex = new LinkedHashMap<>(connectionsToVertex);

        // The resulting node is returned disconnected
        clone.disconnect();

        return clone;
    }

    @Override
    public int degree()
    {
        return connectionsFromVertex.size() + connectionsToVertex.size();
    }

    @Override
    public void disconnect()
    {
        for (Edge e : getEdgesDepartingSelf())
        {
            removeEdge(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get()
    {
        return data;
    }

    @Override
    public Edge getAdjacentEdge(Node<T> v)
    {
        Edge result = connectionsFromVertex.containsKey(v) ? connectionsFromVertex.get(v) : connectionsToVertex.get(v);

        // If the result is still null
        // throw an exception
        if (result == null)
        {
            throw new InvalidOperationException(v.getLabel() + " is not connected to " + label
                                                        + ", they are not adjacent.");
        }
        return result;
    }

    @Override
    public Collection<Integer> getAllAdjacentVertices()
    {
        Collection<Integer> nodes = new LinkedHashSet<>(getConnectionsFromVertex().size() + getConnectionsToVertex().
                size());
        for (Edge e : getEdgesArrivingSelf())
        {
            nodes.add(e.getStartNode().getLabel());
        }
        for (Edge e : getEdgesDepartingSelf())
        {
            nodes.add(e.getFinalNode().getLabel());
        }

        return Collections.unmodifiableCollection(nodes);
    }

    @Override
    public Collection<Integer> getAllVerticesArrivingSelf()
    {
        Collection<Integer> vertices = new LinkedList<>();
        for (Edge e : getEdgesArrivingSelf())
        {
            vertices.add(e.getStartNode().getLabel());
        }
        return vertices;
    }

    @Override
    public Collection<Integer> getAllVerticesDepartingSelf()
    {
        Collection<Integer> vertices = new LinkedList<>();
        for (Edge e : getEdgesDepartingSelf())
        {
            vertices.add(e.getFinalNode().getLabel());
        }
        return vertices;
    }

    @Override
    public Object getAttribute(Object key) throws InvalidKeyException
    {
        if (!attributes.containsKey(key))
        {
            throw new InvalidKeyException(attributes, key);
        }
        return attributes.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Edge> getEdgesArrivingSelf()
    {
        return Collections.unmodifiableSet(new CopyOnWriteArraySet<>(getConnectionsToVertex().values()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Edge> getEdgesDepartingSelf()
    {
        return Collections.unmodifiableSet(new CopyOnWriteArraySet<>(getConnectionsFromVertex().values()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLabel()
    {
        return label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Object, Object> getNodeAttributes()
    {
        return Collections.unmodifiableMap(attributes);
    }

    @Override
    public boolean isAdjacent(Node<T> v)
    {
        return getConnectionsFromVertex().containsKey(v) || getConnectionsToVertex().containsKey(v);
    }

    @Override
    public boolean isAdjacentAndArriving(Node<T> v)
    {
        return getConnectionsFromVertex().containsKey(v);
    }

    @Override
    public boolean isAdjacentAndDeparting(Node<T> v)
    {
        return getConnectionsToVertex().containsKey(v);
    }

    @Override
    public Object removeAttribute(Object key)
    {
        return attributes.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(Edge edge)
    {
        boolean result = connectionsFromVertex.remove(edge.getFinalNode(), edge);
        if (edge.getFinalNode() instanceof DefaultNode)
        {
            @SuppressWarnings ("unchecked")
            DefaultNode<T> v = (DefaultNode<T>) edge.getFinalNode();
            result &= (v.connectionsToVertex.remove(this) != null);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(T data)
    {
        this.data = data;
    }

    @Override
    public Object setAttribute(Object key, Object value)
    {
        return attributes.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("<" + label + ":" + data.toString() + "> (");
        for (Iterator<Edge> it = getConnectionsFromVertex().values().iterator(); it.hasNext();)
        {
            Edge edge = it.next();
            if (edge.getFinalNode() != null)
            {
                builder.append(edge.getFinalNode().getLabel());
            }
            else
            {
                builder.append("<null>");
            }

            if (edge.isWeighted())
            {
                builder.append(" <").append(edge.getWeight().toString()).append(">");
            }

            if (it.hasNext())
            {
                builder.append(", ");
            }
        }
        return builder.append(")").toString();
    }

    /**
     * @return the connectionsFromVertex
     */
    protected Map<Node<T>, Edge> getConnectionsFromVertex()
    {
        return Collections.unmodifiableMap(connectionsFromVertex);
    }

    /**
     * @return the connectionsToVertex
     */
    protected Map<Node<T>, Edge> getConnectionsToVertex()
    {
        return Collections.unmodifiableMap(connectionsToVertex);
    }
}
