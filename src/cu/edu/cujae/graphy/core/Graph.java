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

/**
 * The <code>Graph</code> interface represents a graph as an abstract
 * data structure.The graph does not make a distinction with the data
 * type it holds, it just presents an abstract model of a graph.
 *
 * @author Javier Marrero
 * @param <T>
 */
public interface Graph<T>
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
     * Returns if the graph is a directed graph or not.
     *
     * @return true if the graph is a directed graph, false if otherwise.
     */
    public boolean isDirected();

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
