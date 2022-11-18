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
package cu.edu.cujae.graphy.core.defaults;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.Weight;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.abstractions.AdjacencyListGraph;

/**
 * This is a default implementation of a {@link WeightedGraph}.
 *
 * @author Javier Marrero
 * @param <T>
 */
public class DefaultWeightedGraph<T> extends AdjacencyListGraph<T> implements WeightedGraph<T>
{

    public DefaultWeightedGraph()
    {
        super(false);
    }

    public DefaultWeightedGraph(boolean directed)
    {
        super(directed);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean connect(int u, int v,
                           Weight<?> w)
    {
        return connect(findNodeByLabel(u), findNodeByLabel(v), w);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean connect(Node<T> u, Node<T> v, Weight<?> w)
    {
        if (u == null || v == null || w == null)
        {
            throw new IllegalArgumentException("Passed null parameters to connection method: (u: " + u + ", v: " + v
                                                       + ", w: " + w + ")");
        }
        return u.addEdge(getEdgeFactory().build(w, u, v, w));
    }

    @Override
    public Graph<T> duplicate() throws CloneNotSupportedException
    {
        DefaultWeightedGraph<T> graph = (DefaultWeightedGraph<T>) new DefaultWeightedGraphBuilder<T>().buildGraph().
                directed(isDirected()).get();
        for (Node<T> node : duplicateInternalNodes())
        {
            graph.addNode(node);
        }
        return graph;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isWeighted()
    {
        return true;
    }

}
