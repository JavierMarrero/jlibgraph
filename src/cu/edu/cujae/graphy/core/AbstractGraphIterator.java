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

import java.util.Collection;

/**
 * Esta clase define un iterador abstracto.
 *
 * @author Javier Marrero
 * @param <T>
 */
public abstract class AbstractGraphIterator<T> implements GraphIterator<T>
{

    private Graph<T> graph;
    private Node<T> current;

    /**
     * Construye un nuevo objeto iterador abstracto.
     *
     * @param current
     */
    protected AbstractGraphIterator(Graph<T> graph, Node<T> current)
    {
        this.graph = graph;
        this.current = current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Edge> getAdjacentEdges()
    {
        return getCurrent().getConnectedEdges();
    }

    /**
     * @return the current
     */
    protected Node<T> getCurrent()
    {
        return current;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getLabel()
    {
        return current.getLabel();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isAdjacent(Node<T> node)
    {
        return current.isAdjacent(node);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isAdjacent(GraphIterator<T> it)
    {
        return graph.isVertexAdjacent(current.getLabel(), it.getLabel());
    }

    /**
     * {@inheritDoc }
     * <p>
     * This particular implementation is a stub and produces an exception whenever someone tries to random access a
     * node.
     */
    @Override
    public T next(Node<T> target)
    {
        throw new UnsupportedOperationException("This operation is not supported by this particular iterator");
    }

    /**
     * @param current the current to set
     */
    protected void setCurrent(Node<T> current)
    {
        this.current = current;
    }

}
