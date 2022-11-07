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
package cu.edu.cujae.graphy.core.abstractions;

import cu.edu.cujae.graphy.core.Weight;

/**
 * Default abstract {@link Weight} implementation.
 *
 * @author Javier Marrero
 * @param <T>
 */
public abstract class AbstractWeight<T> implements Weight<T>
{

    private T value;

    public AbstractWeight(T value)
    {
        this.value = value;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T getValue()
    {
        return value;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setValue(T value)
    {
        this.value = value;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString()
    {
        return value.toString();
    }

}