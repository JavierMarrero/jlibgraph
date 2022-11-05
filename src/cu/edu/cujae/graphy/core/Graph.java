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

import java.util.Iterator;

/**
 * The <code>Graph</code> interface represents a graph as an abstract
 * data structure.The graph does not make a distinction with the data
 * type it holds, it just presents an abstract model of a graph.
 *
 * @author Javier Marrero
 * @param <T>
 */
public interface Graph<T> extends Iterable<T>
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
    public boolean add(T data);

    /**
     * Returns a BFS iterator to the selected node.
     *
     * @param node
     *
     * @return a {@link Iterator} instance that is a BFS iterator.
     */
    public Iterator<T> breadthFirstSearchIterator(Node<T> node);

    /**
     * The same as {@link Graph#breadthFirstSearchIterator(cu.edu.cujae.graphy.core.Node) } but with the integer label
     * of the node.
     *
     * @see Graph#breadthFirstSearchIterator(cu.edu.cujae.graphy.core.Node)
     * @param v
     *
     * @return a {@link Iterator} instance.
     */
    public Iterator<T> breadthFirstSearchIterator(int v);

    /**
     * Returns a BFS iterator to a random node in the graph.
     *
     * @return a {@link Iterator}
     */
    public Iterator<T> breadthFirstSearchIterator();

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
     * Generates an iterator that performs a depth first search. Depth first traversal for a graph is similar to depth
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
     *
     * @return a new {@link Iterator}
     */
    public Iterator<T> depthFirstSearchIterator(Node<T> start);

    /**
     * @see Graph#depthFirstSearchIterator(cu.edu.cujae.graphy.core.Node)
     *
     * @param v the label of the node.
     *
     * @return
     */
    public Iterator<T> depthFirstSearchIterator(int v);

    /**
     * Generates an iterator that performs a depth first search, grabbing a random node as the root.
     *
     * @see Graph#depthFirstSearchIterator(cu.edu.cujae.graphy.core.Node)
     * @return a new {@link Iterator}
     */
    public Iterator<T> depthFirstSearchIterator();

    /**
     * Returns if the graph is a directed graph or not.
     *
     * @return true if the graph is a directed graph, false if otherwise.
     */
    public boolean isDirected();

    /**
     * Returns if the graph is weighted or not.
     *
     * @return <code>true</code> if the graph is weighted, <code>false</code> if otherwise.
     */
    public boolean isWeighted();

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
     * Returns a new {@link Iterator} for this graph. Order of iteration is not guaranteed, it may be insertion order or
     * BSF or DSF.
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
     * Registers an {@link EdgeFactory} instance to this class. This allows to vary the behavior of the graph as long as
     * {@link Edge} creation refers.
     *
     * @param factory
     */
    public void registerEdgeFactory(EdgeFactory factory);

    /**
     * Returns the count of nodes in the graph.
     *
     * @return
     */
    public int size();

}
