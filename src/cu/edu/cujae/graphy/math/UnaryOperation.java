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
package cu.edu.cujae.graphy.math;

/**
 * This interface embodies the concept of unary operations.These are operations performed on a data type that receives
 * only the data as argument.
 *
 * @author Javier Marrero
 * @param <ResultType>
 * @param <ValueType>
 */
@FunctionalInterface
public interface UnaryOperation<ResultType, ValueType>
{

    /**
     * Applies the unary operation and returns a result.
     *
     * @param data
     *
     * @return
     */
    ResultType apply(ValueType data);
}
