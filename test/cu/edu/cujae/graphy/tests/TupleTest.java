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
package cu.edu.cujae.graphy.tests;

import cu.edu.cujae.graphy.utils.HashTuple;
import cu.edu.cujae.graphy.utils.Tuple;

/**
 *
 * @author Javier Marrero
 */
public class TupleTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Tuple<Integer> tuple = new HashTuple<>();
        for (int i = 0; i < 5; ++i)
        {
            tuple.add(i);
        }
        tuple.add(10);
        tuple.add(7);
        tuple.add(5);
        tuple.add(57);
        tuple.add(43);

        tuple.freeze();
        System.out.println(tuple);

        System.out.println("Tuple size: " + tuple.size());
        System.out.println("Tuple is empty?: " + tuple.isEmpty());
    }

}
