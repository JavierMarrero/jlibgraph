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

import java.util.Collection;
import java.util.Iterator;

/**
 * A graph iterator is a special kind of iterator that allows random traversal of a graph.It extends the functionality
 * found in {@link Iterator}, but does not replace it. One can still use a <code>GraphIterator</code> as a regular
 * {@link Iterator}.
 *
 * @author Javier Marrero
 * @param <T>
 */
public interface GraphIterator<T> extends Iterator<T>
{

    /**
     * This method should return the edges that depart from the pointed node.
     *
     * @return a collection of edges
     */
    public Collection<Edge> getAdjacentEdges();

    /**
     * Jumps to the specified {@link Node}.
     *
     * @param target
     *
     * @return
     */
    public T next(Node<T> target);
}
