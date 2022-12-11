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
package cu.edu.cujae.graphy.core.utility;

import cu.edu.cujae.graphy.core.EdgeFactory;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Utility class to perform some operations on graphs.
 *
 * @author Javier Marrero
 */
public class Graphs
{

    private static class ImmutableGraph<T> implements Graph<T>
    {

        private Graph<T> graph;

        public ImmutableGraph(Graph<T> graph)
        {
            if (graph == null)
            {
                throw new IllegalArgumentException("unable to make a 'null' graph immutable.");
            }
            this.graph = graph;
        }

        @Override
        public boolean add(int label, T data)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public boolean add(T data)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public boolean addAll(Collection<? extends T> c)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public Iterator<T> breadthFirstSearchIterator(Node<T> node, boolean includeDisconnected)
        {
            return graph.breadthFirstSearchIterator(node, includeDisconnected);
        }

        @Override
        public Iterator<T> breadthFirstSearchIterator(int v, boolean includeDisconnected)
        {
            return graph.breadthFirstSearchIterator(v, includeDisconnected);
        }

        @Override
        public Iterator<T> breadthFirstSearchIterator(boolean includeDisconnected)
        {
            return graph.breadthFirstSearchIterator(includeDisconnected);
        }

        @Override
        public void clear()
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public boolean connect(int u, int v)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public boolean connect(Node<T> u, Node<T> v)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public boolean contains(Object o)
        {
            return graph.contains(o);
        }

        @Override
        public boolean containsAll(
                Collection<?> c)
        {
            return graph.containsAll(c);
        }

        @Override
        public Iterator<T> depthFirstSearchIterator(Node<T> start, boolean includeDisconnected)
        {
            return graph.depthFirstSearchIterator(start, includeDisconnected);
        }

        @Override
        public Iterator<T> depthFirstSearchIterator(int v, boolean includeDisconnected)
        {
            return graph.depthFirstSearchIterator(v, includeDisconnected);
        }

        @Override
        public Iterator<T> depthFirstSearchIterator(boolean includeDisconnected)
        {
            return graph.depthFirstSearchIterator(includeDisconnected);
        }

        @Override
        public boolean disconnect(Node<T> u, Node<T> v)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public boolean disconnect(int u, int v)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public Graph<T> duplicate() throws CloneNotSupportedException
        {
            throw new CloneNotSupportedException();
        }

        @Override
        public boolean existsEdgeWithDirection(int u, int v)
        {
            return graph.existsEdgeWithDirection(u, v);
        }

        @Override
        public Collection<Integer> getLabels()
        {
            return Collections.unmodifiableCollection(graph.getLabels());
        }

        @Override
        public boolean isDirected()
        {
            return graph.isDirected();
        }

        @Override
        public boolean isEmpty()
        {
            return graph.isEmpty();
        }

        @Override
        public boolean isVertexAdjacent(int u, int v)
        {
            return graph.isVertexAdjacent(u, v);
        }

        @Override
        public boolean isWeighted()
        {
            return graph.isWeighted();
        }

        @Override
        public Iterator<T> iterator()
        {
            return graph.iterator();
        }

        @Override
        public GraphIterator<T> iterator(int v)
        {
            return graph.iterator(v);
        }

        @Override
        public GraphIterator<T> randomIterator()
        {
            return graph.randomIterator();
        }

        @Override
        public void registerEdgeFactory(EdgeFactory factory)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public T remove(Node<T> node)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public T removeAt(int u)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public boolean remove(Object o)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public boolean removeAll(
                Collection<?> c)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public boolean retainAll(
                Collection<?> c)
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public void reverse()
        {
            throw new UnsupportedOperationException("This graph instance is immutable.");
        }

        @Override
        public int size()
        {
            return graph.size();
        }

        @Override
        public Object[] toArray()
        {
            return graph.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a)
        {
            return graph.toArray(a);
        }
    }

    /**
     * Returns a new graph view that is immutable, meaning that no changes to the graph's structure can be made. The
     * returned graph is a view of the original graph, in the sense that any change to the original graph gets reflected
     * in the returned graph.
     * <p>
     * If one attempts to modify the graph via its standard methods, a {@link UnsupportedOperationException} will be
     * thrown.
     *
     * @param <T>
     * @param graph
     *
     * @return
     */
    public static <T> Graph<T> makeImmutableGraph(Graph<T> graph)
    {
        return new ImmutableGraph<>(graph);
    }
}
