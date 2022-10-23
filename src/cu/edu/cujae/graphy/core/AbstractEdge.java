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
 * This class simplifies the construction of {@link Edge} objects, by providing a bridge to common functionality.
 *
 * @author Javier Marrero
 */
public abstract class AbstractEdge implements Edge
{

    protected boolean directed;
    protected Node<?> finish;
    protected Node<?> start;
    protected Comparable<?> weight;

    /**
     * Construct a new abstract edge from a set of initial parameters.
     *
     * @param start
     * @param finish
     * @param weight
     * @param directed
     */
    protected AbstractEdge(Node<?> start, Node<?> finish, Comparable<?> weight, boolean directed)
    {
        this.directed = directed;
        this.finish = finish;
        this.start = start;
        this.weight = weight;
    }

    /**
     * Two edges are considered equals <i>if and only if</i> their start nodes match and their final nodes match too.
     * In the case that the edge is not directed, the correspondence may be given regardless of the order of comparison;
     * this meaning that: <code>(u, v) == (v, u)</code>.
     *
     * @param obj
     *
     * @return a boolean value telling wether the two edges are equal or not.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Edge)
        {
            if (isDirected())
            {
                return (getStartNode().equals(((Edge) obj).getStartNode()))
                               && (getFinalNode().equals(((Edge) obj).getFinalNode()));
            }
            else
            {
                Edge rhs = (Edge) obj;
                return (getStartNode().equals(rhs.getStartNode()) || getStartNode().equals(rhs.getFinalNode()))
                               && (getFinalNode().equals(rhs.getStartNode()) || getFinalNode().
                                   equals(rhs.getFinalNode()));
            }
        }
        throw new IllegalArgumentException("attempted to compare an edge to something that is not an edge.");
    }

    /**
     * @@inheritDoc
     */
    @Override
    public Node<?> getFinalNode()
    {
        return finish;
    }

    /**
     * @@inheritDoc
     */
    @Override
    public Node<?> getStartNode()
    {
        return start;
    }

    /**
     * @@inheritDoc
     */
    @Override
    public Comparable<?> getWeight()
    {
        return weight;
    }

    /**
     * @@inheritDoc
     */
    @Override
    public boolean isDirected()
    {
        return directed;
    }

    /**
     * @@inheritDoc
     */
    @Override
    public boolean isWeighted()
    {
        return weight != null;
    }
}
