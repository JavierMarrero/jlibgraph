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

import cu.edu.cujae.graphy.algorithms.FordFulkersonAlgorithm;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;

/**
 *
 * @author Amanda Mendez
 */
public class FordFulkersonTest
{

    public static void main(String[] args) throws CloneNotSupportedException
    {
        WeightedGraph<Integer> graph = GraphBuilders.makeSimpleWeightedGraph(false);

        for (int i = 0; i < 5; ++i)
        {
            graph.add(i);
        }

        graph.connect(0, 1, Weights.makeWeight(5.3f));
        graph.connect(0, 2, Weights.makeWeight(10.2f));
        graph.connect(0, 3, Weights.makeWeight(12.9f));
        graph.connect(1, 3, Weights.makeWeight(7.4f));
        graph.connect(1, 4, Weights.makeWeight(16.2f));
        graph.connect(2, 1, Weights.makeWeight(1.4f));
        graph.connect(2, 3, Weights.makeWeight(2.3f));
        graph.connect(3, 4, Weights.makeWeight(8.5f));
        graph.connect(4, 2, Weights.makeWeight(2.7f));
        graph.connect(4, 1, Weights.makeWeight(13.7f));

        System.out.println(graph);

        System.out.println("Ford Fulkerson: ");
        System.out.println(new FordFulkersonAlgorithm<>(graph, graph.iterator(0), graph.iterator(4)).apply().get());
    }
}
