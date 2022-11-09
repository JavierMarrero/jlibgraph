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
package cu.edu.cujae.graphy.core.iterators;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Node;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * A graph iterator is a special kind of iterator that allows random traversal
 * of a graph.It extends the functionality found in {@link Iterator}, but does
 * not replace it. One can still use a <code>GraphIterator</code> as a regular
 * {@link Iterator}.
 *
 * @author Javier Marrero
 * @param <T>
 */
public interface GraphIterator<T> extends Iterator<T>
{

    /**
     * Returns to the last node left before stepping to this node.
     *
     * @return the data currently held by the iterator
     */
    public T back();

    /**
     * Jumps to the specified node.
     *
     * @param target
     *
     * @return the data currently being held by the iterator
     */
    public T back(Node<T> target);

    /**
     * Returns all the {@link Edge}s departing or arriving to this node.
     *
     * @return
     */
    public Collection<Edge> getAllAdjacentEdges();

    /**
     * This method should return the edges that depart from the pointed node.
     *
     * @return a collection of edges
     */
    public Collection<Edge> getEdgesDepartingSelf();

    /**
     * Returns the {@link Edge} connecting this iterator and the vertex with
     * label <i>v</i>.
     *
     * @param v
     *
     * @return edge, or null if no edge connects this and v.
     */
    public Edge getAdjacentEdge(int v);

    /**
     * Returns the {@link Set} of edges that have the vertex pointed by this
     * iterator as destination.
     *
     * @return a {@link Collection} of edges having this vertex as destination.
     */
    public Collection<Edge> getEdgesArrivingSelf();

    /**
     * Each node in a graph is somehow labeled. In this library, labels are
     * integer indices that are unique to each node in the graph. This method
     * should return the node's label of the node it is currently standing over.
     *
     * @return the label of the node that is the current iterating node
     */
    public int getLabel();

    /**
     * Returns true if this iterator is adjacent to some specified {@link Node}
     *
     * @param node
     *
     * @return
     */
    public boolean isAdjacent(Node<T> node);

    /**
     * Returns true if this iterator is adjacent to some node pointed by the
     * specified {@link GraphIterator}
     *
     * @param it
     *
     * @return
     */
    public boolean isAdjacent(GraphIterator<T> it);

    /**
     * Jumps to the specified {@link Node}.
     *
     * @param target
     *
     * @return
     */
    public T next(Node<T> target);

    /**
     * Jumps to the node specified by this integer label.
     *
     * @param u
     *
     * @return
     */
    public T next(int u);
}
