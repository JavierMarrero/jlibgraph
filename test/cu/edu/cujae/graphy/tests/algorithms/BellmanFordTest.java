/*
 * Copyright (C) 2022 Asus.
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

import cu.edu.cujae.graphy.algorithms.BellmanFordShortestPath;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;

/**
 *
 * @author Amanda Mendez
 */
public class BellmanFordTest
{

    public static void main(String[] args)
    {
        WeightedGraph<Integer> graph = GraphBuilders.makeSimpleWeightedGraph(false);

        for (int i = 0; i < 5; ++i)
        {
            graph.add(i);
        }

        graph.connect(0, 1, Weights.makeWeight(-1));
        graph.connect(0, 2, Weights.makeWeight(4));
        graph.connect(1, 2, Weights.makeWeight(3));
        graph.connect(1, 3, Weights.makeWeight(2));
        graph.connect(1, 4, Weights.makeWeight(2));
        graph.connect(3, 2, Weights.makeWeight(5));
        graph.connect(3, 1, Weights.makeWeight(1));
        graph.connect(4, 3, Weights.makeWeight(-3));

        System.out.println(graph);

        System.out.println("Bellman-Ford: ");
        System.out.println(new BellmanFordShortestPath<>(graph, graph.iterator(0)).apply().get());
    }
}
