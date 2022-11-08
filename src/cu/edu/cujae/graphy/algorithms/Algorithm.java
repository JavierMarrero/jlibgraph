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
 * An <b>algorithm</b> is a process or set of rules to be followed in calculations or other problem-solving operations,
 * especially by a computer.This interface encapsulates the concept of algorithm for its use on this library. Algorithms
 * are implemented as classes, taking an arbitrary set of input parameters but producing a well defined
 * output. This output is defined by the parametrized type that this class takes. The user then after applying the
 * argument can retrieve the result using the method <code>get</code>.
 * <p>
 * Algorithms must be implemented in a way that they do not depend on the particular implementation of the graphs,
 * therefore, heavy use of <i>iterator</i> classes is recommended.
 *
 * @author Javier Marrero
 * @param <T>
 */
public interface Algorithm<T>
{

    /**
     * Applies the algorithm using some arbitrary input parameters (that must be provided elsewhere) producing an output
     * that is latter recoverable.
     *
     * @return a reference to <i>this</i>, to be able to implement <i>call chains</i>
     */
    public Algorithm<T> apply();

    /**
     * Returns the result of the last execution of the algorithm.
     *
     * @return a value of the type that parametrizes this algorithm.
     */
    public T get();
}
