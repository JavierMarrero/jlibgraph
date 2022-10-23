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

import java.util.Set;

/**
 * The node interface
 *
 * @author Javier Marrero
 * @param <T> a parametrized type.
 */
public interface Node<T>
{

    /**
     * Attach this node to another. Self referencing is allowed. This creates a cycle.
     *
     * @param node
     *
     * @return a truth value representing if insertion was successful or not.
     */
    public boolean attach(Node<T> node);

    /**
     * Returns the data this node holds.
     *
     * @return a reference to the data that holds this node.
     */
    public T get();

    /**
     * Returns the set of edges connected to this node. If the node is isolated, it should return the empty set. This
     * set is an unmodifiable view of the {@link Node}'s internal container.
     *
     * @return the set of connected edges.
     */
    public Set<Edge> getConnectedEdges();

    /**
     * Sets this node's data attribute.
     *
     * @param data
     */
    public void set(T data);

}
