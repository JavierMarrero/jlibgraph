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
 * A pair is a class holding two elements.
 *
 * @author Javier Marrero
 * @param <U>
 * @param <V>
 */
public class Pair<U, V>
{

    /**
     * Creates a new pair object.
     *
     * @param <E1>  Parametrized type for the pair object.
     * @param <E2>  Parametrized type for the pair object.s
     * @param first
     * @param last
     *
     * @return
     */
    public static <E1, E2> Pair<E1, E2> makePair(E1 first, E2 last)
    {
        return new Pair<>(first, last);
    }

    private U first;
    private V last;

    public Pair()
    {
        this(null, null);
    }

    /**
     * Constructs a new instance of a {@link Pair}
     *
     * @param first
     * @param last
     */
    public Pair(U first, V last)
    {
        this.first = first;
        this.last = last;
    }

    /**
     * @return the first
     */
    public U getFirst()
    {
        return first;
    }

    /**
     * @return the last
     */
    public V getLast()
    {
        return last;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(U first)
    {
        this.first = first;
    }

    /**
     * @param last the last to set
     */
    public void setLast(V last)
    {
        this.last = last;
    }

    @Override
    public String toString()
    {
        return "<" + first + " : " + last + ">";
    }

}
