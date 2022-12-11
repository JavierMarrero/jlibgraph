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
package cu.edu.cujae.graphy.core.abstractions;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.defaults.DefaultNode;
import cu.edu.cujae.graphy.core.exceptions.InvalidOperationException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * An implementation of a graph using an <i>adjacency list</i> to keep track of the nodes.
 *
 * @author Javier Marrero
 * @param <T>
 */
public abstract class AdjacencyListGraph<T> extends AbstractGraph<T> implements Graph<T>, Cloneable
{

    private final Map<Integer, Node<T>> nodes;

    protected AdjacencyListGraph(boolean directed)
    {
        super(directed);

        nodes = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(int label, T data)
    {
        return nodes.putIfAbsent(label, new DefaultNode<>(label, data)) == null;
    }

    @Override
    protected boolean addNode(Node<T> node)
    {
        return nodes.putIfAbsent(node.getLabel(), node) == null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Node<T> findNodeByLabel(int label)
    {
        if (nodes.containsKey(label) == false)
        {
            throw new InvalidOperationException("Attempted to access node " + label
                                                        + " in a graph that does not contains it.");
        }
        return nodes.get(label);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Collection<Integer> getLabels()
    {
        return nodes.keySet();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected Collection<Node<T>> getNodes()
    {
        return nodes.values();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isVertexAdjacent(int u, int v)
    {
        return findNodeByLabel(u).isAdjacent(findNodeByLabel(v));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T remove(Node<T> node)
    {
        if (!nodes.containsKey(node.getLabel()))
        {
            throw new IllegalArgumentException("The node to remove is not present in this graph.");
        }

        // Remove all the edges from the graph that ends in or departs from this node
        for (Edge edge : node.getEdgesDepartingSelf())
        {
            node.removeEdge(edge);
        }

        // Remove all the edges that arrives to the node
        for (Edge edge : node.getEdgesArrivingSelf())
        {
            edge.getStartNode().removeEdge(edge);
        }

        // Remove the node
        nodes.remove(node.getLabel());

        return node.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeAt(int u)
    {
        return remove(findNodeByLabel(u));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("[\n");
        for (Iterator<Node<T>> it = nodes.values().iterator(); it.hasNext();)
        {
            Node<T> node = it.next();
            builder.append(node.toString());
            if (it.hasNext())
            {
                builder.append(",\n");
            }
        }
        return builder.append("\n]").toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int size()
    {
        return nodes.size();
    }

}
