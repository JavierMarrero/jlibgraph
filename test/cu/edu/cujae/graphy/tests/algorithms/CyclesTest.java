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
package cu.edu.cujae.graphy.tests.algorithms;

import cu.edu.cujae.graphy.algorithms.CycleDetection;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.defaults.DefaultGraphBuilder;

/**
 * This class tests the different cycle detectors.
 *
 * @author Javier Marrero
 */
public class CyclesTest
{

    public static void checkSimpleCycle(Graph<Integer> graph)
    {
        System.out.println("Contains cycle?: " + (new CycleDetection<>(graph).apply().get()));
    }

    public static void main(String[] args)
    {
        Graph<Integer> g1 = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();
        Graph<Integer> g2 = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();

        // Populate the graph
        for (int i = 0; i < 5; ++i)
        {
            g1.add(i);
            if (i < 3)
            {
                g2.add(i);
            }
        }

        // Connect G1
        g1.connect(0, 1);
        g1.connect(0, 2);
        g1.connect(2, 1);
        g1.connect(0, 3);
        g1.connect(3, 4);

        System.out.println("Built g1...");

        // Connect G2
        g2.connect(0, 1);
        g2.connect(1, 2);

        System.out.println("Built g2...");

        // Contains cycle?
        checkSimpleCycle(g1);
        checkSimpleCycle(g2);
    }
}
