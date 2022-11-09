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
package cu.edu.cujae.graphy.core.utility;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.defaults.DefaultWeightedGraphBuilder;
import cu.edu.cujae.graphy.core.defaults.DefaultGraphBuilder;

/**
 * This is an utility class that simplifies the creation of different
 * graphs.However, being this an abstraction over the normal building process of
 * graphs, it is much more limited in scope.For custom graphs, implement and use
 * the builder pattern ({@link GraphBuilder}).
 *
 * @author Javier Marrero
 */
public class GraphBuilders
{

    /**
     * Creates a new simple undirected and unweighted graph.
     *
     * @param <T>
     *
     * @return
     */
    public static <T> Graph<T> makeSimpleUndirectedGraph()
    {
        return new DefaultGraphBuilder<T>().buildGraph().directed(false).get();
    }

    public static <T> WeightedGraph<T> makeSimpleWeightedGraph(boolean directed)
    {
        return (WeightedGraph<T>) new DefaultWeightedGraphBuilder<T>().buildGraph().directed(false).get();
    }
}
