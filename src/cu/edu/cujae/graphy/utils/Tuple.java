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

import java.util.Collection;

/**
 * This interface represents the mathematical concept of a tuple. A tuple is a mathematical entity that defines a
 * <i>n-sequence</i> of values that is immutable.
 * <p>
 * Tuples have the requirement of being O(1) for random access to their elements, so whatever the data structure used
 * as a subjacent data structure must provide these guarantees.
 * <p>
 * Tuples cannot be used as a general container. On construction time, they may be added a certain amount of elements
 * and the tuple is allowed to resize. When finished the tuple construction, the method freeze must be called to make
 * the tuple immutable.
 * <p>
 * <b>Once a tuple is frozen it may never be modified again</b>
 *
 * @author Javier Marrero
 * @param <E>
 */
public interface Tuple<E> extends Iterable<E>, Collection<E>
{

    /**
     * Makes this tuple immutable.
     */
    public void freeze();

    /**
     * Retrieves the n<sup>th</sup> element of this tuple.
     *
     * @param n
     *
     * @return
     */
    public E get(int n);
}
