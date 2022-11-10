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
package cu.edu.cujae.graphy.tests;

import cu.edu.cujae.graphy.algorithms.HamiltonianCycleDetection;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;
import java.util.Iterator;
import java.util.Random;

/**
 * Simple test for weighted graphs.
 *
 * @author Javier Marrero
 */
public class WeightedGraphTest
{

    public static void main(String[] args)
    {
        WeightedGraph<Integer> wg = GraphBuilders.makeSimpleWeightedGraph(true);
        Random random = new Random();

        /* Add vertices */
        for (int i = 0; i < 10; ++i)
        {
            wg.add(i);
        }

        for (int i = 0; i < 20; ++i)
        {
            wg.connect(random.nextInt(10), random.nextInt(10), Weights.makeWeight(random.nextInt(50)));
        }

        /* Print */
        System.out.println(wg.toString());

        /* Dfs */
        Iterator<Integer> dfs = wg.depthFirstSearchIterator(true);
        while (dfs.hasNext())
        {
            System.out.print(dfs.next() + " ");
        }
        System.out.print('\n');

        /* Bfs */
        Iterator<Integer> bfs = wg.breadthFirstSearchIterator(true);
        while (bfs.hasNext())
        {
            System.out.print(bfs.next() + " ");
        }
        System.out.println();

        /* Hamiltonian cycle */
        System.out.println("Hamilton tour: " + (new HamiltonianCycleDetection<>(wg).apply().get()).toString());
    }
}
