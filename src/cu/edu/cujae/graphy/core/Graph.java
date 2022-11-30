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
package cu.edu.cujae.graphy.core;

import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.Collection;
import java.util.Iterator;

/**
 * The <code>Graph</code> interface represents a graph as an abstract
 * data structure.The graph does not make a distinction with the data
 * type it holds, it just presents an abstract model of a graph.
 *
 * @author Javier Marrero
 * @param <T>
 */
public interface Graph<T> extends Iterable<T>, Collection<T>
{

    /**
     * Adds a new node to this graph with the specified index.
     *
     * @param label
     * @param data
     *
     * @return a truth value representing wether insertion was successful.
     */
    public boolean add(int label, T data);

    /**
     * Adds a new node to this graph with a default allocated index.
     *
     * @param data
     *
     * @return a truth value representing wether insertion was successful.
     */
    @Override
    public boolean add(T data);

    /**
     * Adds all the elements of the collection c to the graph.
     *
     * @param c
     *
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends T> c);

    /**
     * Returns a BFS iterator to the selected node.
     *
     * @param node
     * @param includeDisconnected
     *
     * @return a {@link Iterator} instance that is a BFS iterator.
     */
    public Iterator<T> breadthFirstSearchIterator(Node<T> node, boolean includeDisconnected);

    /**
     * The same as {@link cu.edu.cujae.graphy.core.Graph#breadthFirstSearchIterator(cu.edu.cujae.graphy.core.Node) } but
     * with the integer label of the node.
     *
     * @param includeDisconnected
     *
     * @see Graph#breadthFirstSearchIterator(cu.edu.cujae.graphy.core.Node)
     * @param v
     *
     * @return a {@link Iterator} instance.
     */
    public Iterator<T> breadthFirstSearchIterator(int v, boolean includeDisconnected);

    /**
     * Returns a BFS iterator to a random node in the graph.
     *
     * @param includeDisconnected
     *
     * @return a {@link Iterator}
     */
    public Iterator<T> breadthFirstSearchIterator(boolean includeDisconnected);

    /**
     * Clears the contents of this collection, effectively removing all the nodes in the graph.
     */
    @Override
    public void clear();

    /**
     * Connects two nodes in this graph. This method should return true if the connection was successful and false
     * otherwise. The two input parameters are the labels of the nodes within the graph.
     *
     * @param u
     * @param v
     *
     * @return
     */
    public boolean connect(int u, int v);

    /**
     * Connects two nodes within the graph. This version of the method uses the nodes directly.
     *
     * @param u
     * @param v
     *
     * @return
     */
    public boolean connect(Node<T> u, Node<T> v);

    /**
     * Generates an iterator that performs a depth first search.Depth first traversal for a graph is similar to depth
     * first traversal of a tree, the only catch being that, unlike trees, graphs may contain cycles (a node may be
     * visited twice). This iterator avoids processing a node more than once. A graph may have more than one DFS
     * traversal.
     * <p>
     * Depth-first search is an algorithm for traversing or searching a graph. The algorithm starts at the root node
     * (some arbitrary node in this case, passed as a parameter) and explores as far as possible along each branch
     * before backtracking.
     * <p>
     * The basic idea is to start from the root or any arbitrary node, and move to the next adjacent unmarked node.
     *
     * @param start
     * @param includeDisconnected
     *
     * @return a new {@link Iterator}
     */
    public Iterator<T> depthFirstSearchIterator(Node<T> start, boolean includeDisconnected);

    /**
     * @param includeDisconnected
     *
     * @see Graph#depthFirstSearchIterator(cu.edu.cujae.graphy.core.Node)
     *
     * @param v                   the label of the node.
     *
     * @return
     */
    public Iterator<T> depthFirstSearchIterator(int v, boolean includeDisconnected);

    /**
     * Generates an iterator that performs a depth first search, grabbing a random node as the root.
     *
     * @param includeDisconnected
     *
     * @see Graph#depthFirstSearchIterator(cu.edu.cujae.graphy.core.Node, boolean)
     * @return a new {@link Iterator}
     */
    public Iterator<T> depthFirstSearchIterator(boolean includeDisconnected);

    /**
     * Disconnects the {@link Edge} passed as argument.
     *
     * @param u
     * @param v
     *
     * @return
     */
    public boolean disconnect(Node<T> u, Node<T> v);

    /**
     * Disconnects the edge running between u and v.
     *
     * @param u
     * @param v
     *
     * @return
     */
    public boolean disconnect(int u, int v);

    /**
     * This method should be, at some extent, similar to the Java cloning mechanism in that returns a deep copy of this
     * object.The returned graph is not a view of this graph but two independent objects. Changes are not guaranteed
     * to persists across clone instances as they don't share data.
     * <p>
     * While the two graphs does not share any internal representation, they both point to the same data, so any <b>
     * data manipulation</b> will be seen in the original graph. If deep cloning of the internal data is desired,
     * perform a manual copy or rather use the utility methods provided by the class <code>Graphs</code>.
     * <p>
     * If by any means the duplication cannot be made, a {@link CloneNotSupportedException} will be thrown.
     *
     * @return
     *
     * @throws java.lang.CloneNotSupportedException
     */
    public Graph<T> duplicate() throws CloneNotSupportedException;

    /**
     * Tests if exists and {@link Edge} <code>e</code> such that there's a connection between nodes <code>u</code> and
     * <code>v</code> where <b>u</b> is the departing node and <b>v</b> is the arrival node.
     *
     * @param u
     * @param v
     *
     * @return
     */
    public boolean existsEdgeWithDirection(int u, int v);

    /**
     * Returns all the node labels of this graph.
     *
     * @return a {@link Collection} of integers representing node labels.
     */
    public Collection<Integer> getLabels();

    /**
     * Returns if the graph is a directed graph or not.
     *
     * @return true if the graph is a directed graph, false if otherwise.
     */
    public boolean isDirected();

    /**
     * Returns true if the graph is an empty graph.
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * Returns true wether u and v are adjacent vertex in the graph.
     *
     * @param u
     * @param v
     *
     * @return
     */
    public boolean isVertexAdjacent(int u, int v);

    /**
     * Returns if the graph is weighted or not.
     *
     * @return <code>true</code> if the graph is weighted, <code>false</code> if otherwise.
     */
    public boolean isWeighted();

    /**
     * Returns a new {@link Iterator} for this graph. Order of iteration is not guaranteed, it may be insertion order or
     * BSF or DSF. Most implementations will just return a {@link GraphIterator} to some random node. However, it is
     * not guaranteed.
     * <p>
     * Most probable result is a sequential iterator that iterates over all the nodes of the graph in a random unsorted
     * order. In this sense, it is similar to the random access iterator, just that it jumps off accordingly.
     *
     * @return a new {@link Iterator}
     */
    @Override
    public Iterator<T> iterator();

    /**
     * Returns a new {@link GraphIterator} for this node in the graph. Order of iteration is not guaranteed, commonly it
     * will be a random access iterator, but this is implementation dependent. This method takes as a parameter the
     * label of a particular node.
     *
     * @param v
     *
     * @return a new {@link GraphIterator}
     */
    public GraphIterator<T> iterator(int v);

    /**
     * Similar to the <code>iterator()</code> method but it guarantees a random access iterator to some randomly
     * selected node.
     *
     * @return a new {@link GraphIterator}
     */
    public GraphIterator<T> randomIterator();

    /**
     * Registers an {@link EdgeFactory} instance to this class. This allows to vary the behavior of the graph as long as
     * {@link Edge} creation refers.
     *
     * @param factory
     */
    public void registerEdgeFactory(EdgeFactory factory);

    /**
     * Removes a node from the graph. May throw a {@link IllegalArgumentException} if the node is not present in the
     * graph.
     *
     * @param node the node to be removed.
     *
     * @return the value of the removed node.
     */
    public T remove(Node<T> node);

    /**
     * Removes a node from the graph.
     *
     * @see Graph#remove(cu.edu.cujae.graphy.core.Node)
     * @param u the node to be removed
     *
     * @return the value of the removed node
     */
    public T remove(int u);

    /**
     * Reverses this graph's edges.If an edge was (u, v) then after this will be (v, u). Weights are preserved.
     */
    public void reverse();

    /**
     * Returns the count of nodes in the graph.
     *
     * @return
     */
    public int size();

}
