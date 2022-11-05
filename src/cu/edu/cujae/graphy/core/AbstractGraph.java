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
    
    private boolean directed;
    
    private class RandomAccessIterator extends AbstractGraphIterator<T> implements GraphIterator<T>
    {
        
        public RandomAccessIterator(Graph<T> graph, Node<T> node)
        {
            super(graph, node);
        }
        
        @Override
        public T back(Node<T> target)
        {
            T result = getCurrent().get();
            setCurrent(target);
            return result;
        }
        
        @Override
        public boolean hasNext()
        {
            return !getAdjacentEdges().isEmpty();
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
        
        private final ListIterator<Node<T>> iter;
        private final List<Node<T>> dfsList;
        
        public DepthFirstSearchIterator(Graph<T> graph, Node<T> start)
        {
            super(graph, start);

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
            iter = dfsList.listIterator();
        }
        
        @Override
        public T back()
        {
            T data = getCurrent().get();
            setCurrent(iter.previous());
            return data;
        }
        
        @Override
        public T back(Node<T> target)
        {
            throw new InvalidOperationException("This is not a random access iterator.");
        }
        
        @Override
        public boolean hasNext()
        {
            return iter.hasNext();
        }
        
        @Override
        public T next()
        {
            /* Next node */
            setCurrent(iter.next());

            /* Return the data within the node */
            return getCurrent().get();
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
    
    private class BreadthFirstSearchIterator extends AbstractGraphIterator<T> implements GraphIterator<T>
    {
        
        private final ListIterator<Node<T>> iter;
        private final List<Node<T>> bfsList;
        
        public BreadthFirstSearchIterator(Graph<T> graph, Node<T> start)
        {
            super(graph, start);

            /* Create the list and populate it */
            bfsList = new LinkedList<>();

            /* Populate the list */
            Set<Integer> visited = new TreeSet<>();
            Queue<Node<T>> queue = new LinkedList<>();

            // Insert the starting vertex
            queue.add(start);

            // Initialize the visited set and mark the starting vertex as visited
            visited.add(start.getLabel());
            
            while (!queue.isEmpty())
            {
                Node<T> s = queue.poll();
                bfsList.add(s);

                // Get all adjacent vertices of the dequeued vertex s
                // if an adjacent has not been visited, then mark it visited
                // and enqueue it
                Iterator<Edge> i = s.getConnectedEdges().iterator();
                while (i.hasNext())
                {
                    @SuppressWarnings ("unchecked")
                    Node<T> n = (Node<T>) i.next().getFinalNode();
                    if (!visited.contains(n.getLabel()))
                    {
                        visited.add(n.getLabel());
                        queue.add(n);
                    }
                }
            }

            // Add the disconnected nodes
            for (Node<T> node : getNodes())
            {
                if (!visited.contains(node.getLabel()))
                {
                    bfsList.add(node);
                }
            }

            /* Create the iterator */
            iter = bfsList.listIterator();
        }
        
        @Override
        public T back(Node<T> target)
        {
            throw new InvalidOperationException("This is not a random access iterator.");
        }
        
        @Override
        public T back()
        {
            T data = getCurrent().get();
            setCurrent(iter.previous());
            return data;
        }
        
        @Override
        public boolean hasNext()
        {
            return iter.hasNext();
        }
        
        @Override
        public T next()
        {
            setCurrent(iter.next());
            return getCurrent().get();
        }
        
    }
    
    private final Set<Integer> allocatedLabels;
    private EdgeFactory edgeFactory;
    private int lastAllocated;

    /**
     * Default constructor for abstract graphs.
     *
     * @param directed
     */
    protected AbstractGraph(boolean directed)
    {
        this.allocatedLabels = new TreeSet<>();
        this.directed = directed;
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
    public Iterator<T> breadthFirstSearchIterator()
    {
        return depthFirstSearchIterator(getNodes().iterator().next());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> breadthFirstSearchIterator(Node<T> node)
    {
        return new BreadthFirstSearchIterator(this, node);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> breadthFirstSearchIterator(int v)
    {
        return breadthFirstSearchIterator(findNodeByLabel(v));
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
        return new DepthFirstSearchIterator(this, start);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> depthFirstSearchIterator(int v)
    {
        return new DepthFirstSearchIterator(this, findNodeByLabel(v));
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> depthFirstSearchIterator()
    {
        return new DepthFirstSearchIterator(this, getNodes().iterator().next());
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
    public boolean isDirected()
    {
        return directed;
    }

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
     * Makes a graph directed.
     */
    public void makeDirected()
    {
        setDirected(true);
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

    /**
     * @param directed the directed to set
     */
    protected void setDirected(boolean directed)
    {
        this.directed = directed;
    }
    
}
