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

import java.util.Collection;
import java.util.Set;

/**
 * This interface represents <i>nodes</i> or <i>vertices</i> in a graph.
 * <p>
 * Members of this class must be cloneable in order to implement graph duplication.
 *
 * @author Javier Marrero
 * @param <T> a parametrized type.
 */
public interface Node<T> extends Cloneable
{

    /**
     * Creates a new connection given an edge.
     *
     * @param edge
     *
     * @return true if the insertion was successful, false if not.
     */
    public boolean addEdge(Edge edge);

    /**
     * {@inheritDoc }
     */
    public Object clone() throws CloneNotSupportedException;

    /**
     * Disconnects this node, clearing all the edges incoming or outgoing.
     */
    public void disconnect();

    /**
     * Returns the data this node holds.
     *
     * @return a reference to the data that holds this node.
     */
    public T get();

    /**
     * Nodes inserted within a graph get access to a label that uniquely identifies them within a graph. This could be
     * their index in the adjacency matrix or their index within the adjacency list.
     * <p>
     * It is not correct to assume that this number is any of the things described above, but it can be used to
     * uniquely identify the node within the graph, such as no other node will have the same label. Label allocation
     * is responsibility of the {@link Graph} class.
     *
     * @return the integer label of this node
     */
    public int getLabel();

    /**
     * Returns the edge connecting this node and v.
     *
     * @param v
     *
     * @return the {@link Edge} connecting this node and v, or null.
     */
    public Edge getAdjacentEdge(Node<T> v);

    /**
     * Returns a {@link Collection} containing all the adjacent vertices to this node.
     *
     * @return
     */
    public Collection<Integer> getAllAdjacentVertices();

    /**
     * Returns the set of edges connected to this node. If the node is isolated, it should return the empty set. This
     * set is an unmodifiable view of the {@link Node}'s internal container.
     *
     * @return the set of connected edges.
     */
    public Set<Edge> getEdgesDepartingSelf();

    /**
     * Returns the set of edges that have this node as the destination node. If the node is isolated should return the
     * empty set. The set is an unmodifiable view of the {@link Node}'s internal container.
     *
     * @return
     */
    public Set<Edge> getEdgesArrivingSelf();

    /**
     * Returns true if this node is adjacent to another node in a graph.
     *
     * @param v
     *
     * @return
     */
    public boolean isAdjacent(Node<T> v);

    /**
     * Removes an edge from this node's adjacent edges.
     *
     * @param edge
     *
     * @return true if the deletion was successful
     */
    public boolean removeEdge(Edge edge);

    /**
     * Sets this node's data attribute.
     *
     * @param data
     */
    public void set(T data);

}
