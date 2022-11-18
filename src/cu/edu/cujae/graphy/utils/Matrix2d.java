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
package cu.edu.cujae.graphy.utils;

/**
 * This class represents a typesafe bidimensional matrix.
 *
 * @author Javier Marrero
 * @param <T>
 */
public class Matrix2d<T>
{

    private final int xDimension;
    private final int yDimension;
    private final Object[][] array;

    public Matrix2d(int xDimension, int yDimension)
    {
        array = new Object[xDimension][yDimension];

        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    /**
     * Equivalent to the matrix operation <i>A<sub>ij</sub></i>
     *
     * @param i
     * @param j
     *
     * @return
     */
    @SuppressWarnings ("unchecked")
    public T get(int i, int j)
    {
        checkIndexExclusive(i, j);
        return (T) array[i][j];
    }

    /**
     * Returns the i<sup>th</sup> row.
     *
     * @param i
     *
     * @return
     */
    @SuppressWarnings ("unchecked")
    public T[] get(int i)
    {
        return (T[]) array[i];
    }

    /**
     * Equivalent to the assignment to the result of the matrix operation <i>A</sub>ij</sub></i>.
     *
     * @param i
     * @param j
     * @param value
     *
     * @return
     */
    public T set(int i, int j, T value)
    {
        checkIndexExclusive(i, j);
        T result = get(i, j);
        array[i][j] = value;

        return result;
    }

    private void checkIndexExclusive(int i, int j)
    {
        if ((i < 0 || i > yDimension) || (j < 0 || j > xDimension))
        {
            throw new IndexOutOfBoundsException("Attempted to access index <" + i + "," + j + "> on a matrix.");
        }
    }
}
