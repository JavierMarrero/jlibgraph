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

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Javier Marrero
 */
public class DefaultNode<T> implements Node<T>
{

    private Set<Edge> connections;
    private T data;

    /**
     * Default public constructor.
     *
     * @param data
     */
    public DefaultNode(T data)
    {
        this.connections = new LinkedHashSet<>(5);
        this.data = data;
    }

    /**
     * @@inheritDoc
     */
    @Override
    public boolean attach(Node<T> node)
    {
        boolean result = false;

        /// TODO: Perform the creation of the edges
        Edge connection = null;

        return connections.add(connection);
    }

    /**
     * @@inheritDoc
     */
    @Override
    public T get()
    {
        return data;
    }

    /**
     * @@inheritDoc
     */
    @Override
    public Set<Edge> getConnectedEdges()
    {
        return Collections.unmodifiableSet(connections);
    }

    /**
     * @@inheritDoc
     */
    @Override
    public void set(T data)
    {
        this.data = data;
    }

}
