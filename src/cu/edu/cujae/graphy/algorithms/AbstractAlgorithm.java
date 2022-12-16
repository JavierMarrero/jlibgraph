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
package cu.edu.cujae.graphy.algorithms;

/**
 * This class implements some utility functions useful when implementing algorithms.It has no other utility whatsoever.
 *
 * @author Javier Marrero
 * @param <T>
 */
public abstract class AbstractAlgorithm<T> implements Algorithm<T>
{

    private T result;

    protected AbstractAlgorithm(T result)
    {
        this.result = result;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T get()
    {
        return getResult();
    }

    /**
     * @return the result
     */
    protected T getResult()
    {
        return result;
    }

    /**
     * @param result the result to set
     */
    protected void setResult(T result)
    {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "jlibgraph's algorithm: " + getClass().toGenericString();
    }

}
