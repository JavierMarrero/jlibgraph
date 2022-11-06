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
package cu.edu.cujae.graphy.tests.algorithms;

import cu.edu.cujae.graphy.algorithms.HamiltonianCycleDetection;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

/**
 * Hamiltonian cycle test.
 *
 * @author Javier Marrero
 */
public class HamiltonCycleTest
{

    public static void main(String[] args)
    {
        Graph<Integer> graph = GraphBuilders.makeSimpleUndirectedGraph();

        // Create the hamiltonian path
        for (int i = 0; i < 5; ++i)
        {
            graph.add(i);
        }

        graph.connect(0, 1);
        graph.connect(0, 3);
        graph.connect(1, 2);
        graph.connect(1, 4);
        graph.connect(2, 4);
        graph.connect(4, 3);

        // Test the hamiltonian cycle
        System.out.println("Hamilton cycle?: " + (new HamiltonianCycleDetection<>(graph).apply().get()));
    }
}
