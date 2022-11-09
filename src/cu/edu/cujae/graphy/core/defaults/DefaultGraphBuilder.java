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
import cu.edu.cujae.graphy.core.GraphBuilder;

/**
 * This is a default implementation of the {@link GraphBuilder} interface.
 *
 * @author Javier Marrero
 * @param <T>
 */
public class DefaultGraphBuilder<T> implements GraphBuilder<T>
{

    private DefaultSimpleGraph<T> instance;

    public DefaultGraphBuilder()
    {
        this.instance = null;
    }

    @Override
    public GraphBuilder<T> buildGraph()
    {
        // Create the instance
        instance = new DefaultSimpleGraph<>();
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
