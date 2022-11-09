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

/**
 * A <i>weighted graph</i> is a {@link Graph} in which each branch is given a
 * numerical weight.A weighted graph is therefore a special type of labeled
 * graph. Weighted graphs are implemented in this library by adding a special
 * property to existing graphs. Graphs can be labeled and weighted, for
 * whatsoever the practical applications this may have.
 *
 * @author Javier Marrero
 * @param <T>
 */
public interface WeightedGraph<T> extends Graph<T>
{

    /**
     * Connects two vertices in the graph using a weight as label.
     *
     * @param u
     * @param v
     * @param w
     *
     * @return true if the connection was possible, false otherwise.
     */
    public boolean connect(int u, int v, Weight<?> w);

    /**
     * Connects two vertices in the graph using a weight as label. However, this
     * version of the method uses the node handles directly.
     *
     * @param u
     * @param v
     * @param w
     *
     * @return true if the connection was possible, false otherwise.
     */
    public boolean connect(Node<T> u, Node<T> v, Weight<?> w);
}
