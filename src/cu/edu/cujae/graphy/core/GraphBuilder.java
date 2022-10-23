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
 * This is an implementation of the <i>builder</i> pattern for constructing graphs. Graphs are complex data structures
 * that may present several flavors. Therefore, here we separate the concerns of building graphs via the <i>builder</i>
 * pattern.
 * <p>
 *
 * @author Javier Marrero
 */
public interface GraphBuilder<T>
{

    /**
     * Constructs a new instance of an object that implements the {@link Graph} interface.
     *
     * @return a reference to this builder
     */
    GraphBuilder<T> buildGraph();

    /**
     * Constructs a directed or undirected graph depending on the boolean parameter.
     *
     * @param directed
     *
     * @return a reference to this builder
     */
    GraphBuilder<T> directed(boolean directed);

    /**
     * Returns the previously built graph.
     *
     * @return a new {@link Graph} instance.
     */
    Graph<T> get();

}
