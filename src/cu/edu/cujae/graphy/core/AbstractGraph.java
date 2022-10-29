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
package cu.edu.cujae.graphy.core;

import java.util.*;

/**
 * This class eases the implementation of the {@link Graph} interface by offering some default implementations for
 * commonly used methods. However, any required specialization may be performed by descendant classes.
 * <p>
 *
 * @see Graph
 *
 * @author Javier Marrero
 * @param <T>
 */
public abstract class AbstractGraph<T> implements Graph<T>
{

    private class RandomAccessIterator extends AbstractGraphIterator<T> implements GraphIterator<T>
    {

        private final Graph<T> graph;

        public RandomAccessIterator(Graph<T> graph, Node<T> node)
        {
            super(node);

            this.graph = graph;
        }

        @Override
        public boolean hasNext()
        {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public T next(Node<T> target)
        {
            T result = getCurrent().get();
            setCurrent(target);
            return result;
        }

        @Override
        @SuppressWarnings ("unchecked")
        public T next()
        {
            Iterator<Edge> children = getAdjacentEdges().iterator();
            Node<?> target = children.next().getFinalNode();
            while (target.equals(getCurrent()))
            {
                target = children.next().getFinalNode();
            }
            return next((Node<T>) target);
        }

    }

    private class DepthFirstSearchIterator extends AbstractGraphIterator<T> implements GraphIterator<T>
    {

        private final Iterator<Node<T>> iter;
        private final List<Node<T>> dfsList;

        public DepthFirstSearchIterator(Node<T> start)
        {
            super(start);

            // Create the list
            dfsList = new LinkedList<>();

            // Populate the list
            Set<Integer> visited = new HashSet<>();
            walk(start, visited);

            // Walk and add disconnected vertices
            for (Node<T> node : getNodes())
            {
                if (!visited.contains(node.getLabel()))
                {
                    dfsList.add(node);
                }
            }

            // This was a test!
            // System.out.println(dfsList);

            // Create the iterator
            iter = dfsList.iterator();
        }

        @Override
        public boolean hasNext()
        {
            return iter.hasNext();
        }

        @Override
        public T next()
        {
            return iter.next().get();
        }

        private void walk(Node<T> v, Set<Integer> visited)
        {
            // Mark the current node as visited
            visited.add(v.getLabel());

            // Add the node to the list
            dfsList.add(v);

            // Recur for all the vertices adjacent to this vertex
            Iterator<Edge> edges = v.getConnectedEdges().iterator();
            while (edges.hasNext())
            {
                @SuppressWarnings ("unchecked")
                Node<T> n = (Node<T>) edges.next().getFinalNode();
                if (!visited.contains(n.getLabel()))
                {
                    walk(n, visited);
                }
            }
        }

    }

    private final Set<Integer> allocatedLabels;
    private EdgeFactory edgeFactory;
    private int lastAllocated;

    /**
     * Default constructor for abstract graphs.
     */
    protected AbstractGraph()
    {
        this.allocatedLabels = new TreeSet<>();
        this.lastAllocated = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T data)
    {
        return add(allocateLabel(), data);
    }

    /**
     * Internally allocates a label for a node, and handles conflicts.
     *
     * @return an integer representing the next allocatable label.
     */
    protected int allocateLabel()
    {
        int result = lastAllocated++;
        while (allocatedLabels.contains(result))
        {
            result = lastAllocated++;
        }
        allocatedLabels.add(result);
        return result;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean connect(int u, int v)
    {
        return connect(findNodeByLabel(u), findNodeByLabel(v));
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean connect(Node<T> u, Node<T> v)
    {
        return u.addEdge(getEdgeFactory().build(u, v));
    }

    /**
     * De-allocates a label and makes it available for use.
     *
     * @param label
     */
    protected void deallocateLabel(int label)
    {
        allocatedLabels.remove(label);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> depthFirstSearchIterator(Node<T> start)
    {
        return new DepthFirstSearchIterator(start);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> depthFirstSearchIterator(int v)
    {
        return new DepthFirstSearchIterator(findNodeByLabel(v));
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> depthFirstSearchIterator()
    {
        return new DepthFirstSearchIterator(getNodes().iterator().next());
    }

    /**
     * Returns a collection holding all the nodes within this graph.
     *
     * @return a {@link Collection} of {@link Node}
     */
    protected abstract Collection<Node<T>> getNodes();

    /**
     * This abstract method should return a node with the identifier passed as argument. If the node is not present
     * on the graph it is allowed to return null, or throw an {@link IllegalStateException}.
     *
     * @param label
     *
     * @return the found {@link Node}.
     */
    protected abstract Node<T> findNodeByLabel(int label);

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator()
    {
        return new RandomAccessIterator(this, getNodes().iterator().next());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public GraphIterator<T> iterator(int v)
    {
        return new RandomAccessIterator(this, findNodeByLabel(v));
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void registerEdgeFactory(EdgeFactory factory)
    {
        this.setEdgeFactory(factory);
    }

    /**
     * Prints this graph as a series of nodes with their corresponding connections. This shall return a string
     * containing all of the graph's vertex in a human-readable format.
     *
     * @return
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("[");
        for (Node<T> node : getNodes())
        {
            builder.append(node.toString());
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * @return the edgeFactory
     */
    protected EdgeFactory getEdgeFactory()
    {
        return edgeFactory;
    }

    /**
     * @param edgeFactory the edgeFactory to set
     */
    protected void setEdgeFactory(EdgeFactory edgeFactory)
    {
        this.edgeFactory = edgeFactory;
    }

}
