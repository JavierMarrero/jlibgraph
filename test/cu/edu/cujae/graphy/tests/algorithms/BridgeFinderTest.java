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

import cu.edu.cujae.graphy.algorithms.BridgeFinderAlgorithm;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

/**
 *
 * @author Javier Marrero
 */
public class BridgeFinderTest
{

    private static void bridges(Graph<?> graph)
    {
        System.out.println(new BridgeFinderAlgorithm(graph).apply().get());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Bridges in the first graph:");
        Graph<Integer> g1 = GraphBuilders.makeSimpleGraph(false);
        for (int i = 0; i < 5; ++i)
        {
            g1.add(i);
        }

        g1.connect(1, 0);
        g1.connect(0, 2);
        g1.connect(2, 1);
        g1.connect(0, 3);
        g1.connect(3, 4);
        bridges(g1);

        // SECOND GRAPH
        System.out.println("Bridges in the second graph:");
        Graph<Integer> g2 = GraphBuilders.makeSimpleGraph(false);
        for (int i = 0; i < 4; ++i)
        {
            g2.add(i);
        }

        g2.connect(0, 1);
        g2.connect(1, 2);
        g2.connect(2, 3);
        bridges(g2);

        // THIRD GRAPH
        System.out.println("Bridges in the third graph:");
        Graph<Integer> g3 = GraphBuilders.makeSimpleGraph(false);
        for (int i = 0; i < 7; ++i)
        {
            g3.add(i);
        }

        g3.connect(0, 1);
        g3.connect(1, 2);
        g3.connect(2, 0);
        g3.connect(1, 3);
        g3.connect(1, 4);
        g3.connect(1, 6);
        g3.connect(3, 5);
        g3.connect(4, 5);
        bridges(g3);

    }

}
