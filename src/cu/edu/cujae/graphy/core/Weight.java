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
package cu.edu.cujae.graphy.core;

/**
 * This interface is used to represents weights in a graph.It is mostly a wrapper over the {@link Comparable}
 * interface.
 *
 * @author Javier Marrero
 * @param <T>
 */
public interface Weight<T extends Number> extends Comparable<T>, Cloneable
{

    /**
     * {@inheritDoc }
     */
    public Object clone() throws CloneNotSupportedException;

    /**
     * Returns the actual value of the weight.
     *
     * @return the actual value of the weight.
     */
    public T getValue();

    /**
     * Sets the actual value of the weight object.
     *
     * @param value
     */
    public void setValue(T value);
}
