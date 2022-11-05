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
 * Simple implementation of the builder pattern and the {@link GraphBuilder} interface for weighted graphs.
 *
 * @author Javier Marrero
 * @param <T>
 */
public class DefaultWeightedGraphBuilder<T> implements GraphBuilder<T>
{

    private DefaultWeightedGraph<T> instance;

    @Override
    public GraphBuilder<T> buildGraph()
    {
        instance = new DefaultWeightedGraph<>();
        return this;
    }

    @Override
    public GraphBuilder<T> directed(boolean directed)
    {
        instance.setDirected(true);
        instance.registerEdgeFactory((directed) ? (new DefaultDirectedEdgeFactory())
                                             : (new DefaultNotDirectedEdgeFactory()));
        return this;
    }

    @Override
    public Graph<T> get()
    {
        return instance;
    }

}
