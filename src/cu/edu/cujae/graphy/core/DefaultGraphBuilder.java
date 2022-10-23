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
 * This is a default concrete implementation of the builder pattern for graphs.
 *
 * @author Javier Marrero
 */
public class DefaultGraphBuilder<T> implements GraphBuilder<T>
{

    private Graph<T> instance;

    /**
     * Constructs a new instance of this class.
     */
    public DefaultGraphBuilder()
    {
        instance = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphBuilder<T> buildGraph()
    {
        instance = new DefaultSimpleGraph<>();
        return this;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public GraphBuilder<T> directed(boolean directed)
    {
        ///TODO: Change the null
        instance.registerEdgeFactory(directed ? new DefaultDirectedEdgeFactory() : null);
        return this;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Graph<T> get()
    {
        return instance;
    }

}
