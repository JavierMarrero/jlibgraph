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
 * Utility implementation of a factory method for {@link Weight} interfaces.
 *
 * @author Javier Marrero
 */
public class Weights
{

    public static <E> Weight<E> makeWeight(E data)
    {
        return new AbstractWeight<E>(data)
        {
            @Override
            @SuppressWarnings ("unchecked")
            public int compareTo(E o)
            {
                if (!(data instanceof Comparable))
                {
                    throw new ClassCastException("The data is not a comparable object.");
                }

                return ((Comparable<E>) data).compareTo(o);
            }
        };
    }
}
