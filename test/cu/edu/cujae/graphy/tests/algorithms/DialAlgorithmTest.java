/*
 * Copyright (C) 2022 Ananda.
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

import cu.edu.cujae.graphy.algorithms.DialShortestPath;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;

/**
 *
 * @author Ananda
 */
public class DialAlgorithmTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        WeightedGraph<Integer> graph = GraphBuilders.makeSimpleWeightedGraph(false);

        // Create the nodes
        for (int i = 0; i < 9; ++i)
        {
            graph.add(i);
        }

        // Connect
        graph.connect(0, 1, Weights.makeWeight(4));
        graph.connect(0, 7, Weights.makeWeight(8));
        graph.connect(1, 2, Weights.makeWeight(8));
        graph.connect(1, 7, Weights.makeWeight(11));
        graph.connect(2, 3, Weights.makeWeight(7));
        graph.connect(2, 8, Weights.makeWeight(2));
        graph.connect(2, 5, Weights.makeWeight(4));
        graph.connect(3, 4, Weights.makeWeight(9));
        graph.connect(3, 5, Weights.makeWeight(14));
        graph.connect(4, 5, Weights.makeWeight(10));
        graph.connect(5, 6, Weights.makeWeight(2));
        graph.connect(6, 7, Weights.makeWeight(1));
        graph.connect(6, 8, Weights.makeWeight(6));
        graph.connect(7, 8, Weights.makeWeight(7));

        System.out.println(graph);
        System.out.println("Dial: ");
        System.out.println(new DialShortestPath(graph, graph.iterator(0), 14).apply().get());
    }

}
