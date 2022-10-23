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
 * This interface defines factories for {@link Edge}. This allows to implement custom {@link Edge} creation
 * functionality on top of an arbitrary graph; separating concerns of building the {@link Edge} objects from actually
 * using these objects.
 * <p>
 * There will be concrete implementations of this interface on each graph.
 *
 * @author Javier Marrero
 */
public interface EdgeFactory
{

    /**
     * This method should build a new {@link Edge} instance given start and final nodes.If the node can't be
     * constructed by some particular reason this method should return <code>null</code>.Neither the initial nor the
     * final nodes may be <code>null</code>, but the weight can, in which case the result is an unweighted edge.
     *
     * @param label
     * @param u
     * @param v
     * @param w
     *
     * @return a new {@link Edge} connecting <code>u</code> and <code>v</code>
     */
    public Edge build(Object label, Node<?> u, Node<?> v, Weight<?> w);

    /**
     * A method similar to {@link EdgeFactory#build} but that does not accept a weight.It is provided as a convenience
     * method for unweighted graphs, avoiding the explicit pass of the <code>null</code> parameter for the weight.
     *
     * @param label
     * @param u
     * @param v
     *
     * @return
     */
    public Edge build(Object label, Node<?> u, Node<?> v);

    /**
     * A method similar to {@link  EdgeFactory#build} not taking the label parameter nor the weight.
     *
     * @param u
     * @param v
     *
     * @return
     */
    public Edge build(Node<?> u, Node<?> v);
}
