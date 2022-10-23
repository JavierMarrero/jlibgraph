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
 * An edge is an ordered pair of the form <i>(u, v)</i>. The pair must be ordered because <i>(u, v)</i> is not the same
 * as <i>(v, u)</i> in the case of a directed graph <i>(di-graph)</i>. The pair of the form <i>(u, v)</i> indicates that
 * there is an edge from vertex <i>u</i> to vertex <i>v</i>. The edges may contain an attribute known as weight. This
 * attribute is arbitrary, but must be comparable; or in other words, a value.
 *
 * @author Javier Marrero
 */
public interface Edge extends Comparable<Edge>
{

    /**
     * Returns a reference to the edge's final node.
     *
     * @return a reference to the edge's final node.
     */
    public Node<?> getFinalNode();

    /**
     * Returns a reference to the edge's initial node.
     *
     * @return a reference to the edge's initial node.
     */
    public Node<?> getStartNode();

    /**
     * Returns the weight of this edge. It may return a null value in the case the edge is unweighted.
     *
     * @return the value of the weight.
     */
    public Comparable<?> getWeight();

    /**
     * Returns true if the graph edge is directed. This will be true only if the containing graph is also directed.
     * This method is established here as a convenience.
     *
     * @return if the graph's edge is directed.
     */
    public boolean isDirected();

    /**
     * Returns true if the edge is weighted.
     *
     * @return if the edge is weighted or unweighted.
     */
    public boolean isWeighted();
}
