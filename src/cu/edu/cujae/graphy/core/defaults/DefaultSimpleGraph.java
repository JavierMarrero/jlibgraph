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
package cu.edu.cujae.graphy.core.defaults;

import cu.edu.cujae.graphy.core.abstractions.AdjacencyListGraph;
import cu.edu.cujae.graphy.core.Graph;

/**
 * This is a default implementation of the {@link Graph} interface.It is a
 * simple graph (not simple in the mathematical sense of a <i>"simple
 * graph"</i>, but in the sense of the most basic implementation you will find
 * in this library). It does not aim to be the ultimate representation of a
 * graph, but rather a default implementation that uncaring users may rely upon.
 * <p>
 * For more complex or more specific needs, the entire library is customizable
 * via design patterns and the use of this library's API.
 *
 * @author Javier Marrero
 * @param <T>
 */
public class DefaultSimpleGraph<T> extends AdjacencyListGraph<T> implements Graph<T>
{

    public DefaultSimpleGraph()
    {
        super(false);
    }

    public DefaultSimpleGraph(boolean directed)
    {
        super(directed);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isWeighted()
    {
        return false;
    }

}
