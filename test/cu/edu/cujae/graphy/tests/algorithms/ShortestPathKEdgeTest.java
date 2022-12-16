/*
 * Copyright (C) 2022 Jose.
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

import cu.edu.cujae.graphy.algorithms.ExistSimpleRoad;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;

/**
 *
 * @author Jose
 */
public class ShortestPathKEdgeTest
{

    public static void checkIfExistASimpleRoad(WeightedGraph<Integer> graph, GraphIterator<Integer> iteratorNI,
                                               GraphIterator<Integer> iteratorNF, int k, int pesoMayor)
    {
        System.out.println("Exist simple road?: "
                                   + (new ExistSimpleRoad<>(graph, iteratorNI, iteratorNF, k, pesoMayor)).apply().get());
    }

    public static void main(String[] args)
    {
        WeightedGraph<Integer> graph = GraphBuilders.makeSimpleWeightedGraph(true);

        // Create the nodes
        for (int i = 0; i < 9; ++i)
        {
            graph.add(i);
        }

        // Connect
        graph.connect(0, 1, Weights.makeWeight(3));
        graph.connect(0, 2, Weights.makeWeight(3));
        graph.connect(1, 3, Weights.makeWeight(7));
        graph.connect(1, 4, Weights.makeWeight(4));
        graph.connect(2, 3, Weights.makeWeight(4));
        graph.connect(3, 5, Weights.makeWeight(2));
        graph.connect(2, 6, Weights.makeWeight(2));
        graph.connect(3, 7, Weights.makeWeight(11));
        graph.connect(7, 8, Weights.makeWeight(8));
        graph.connect(5, 8, Weights.makeWeight(10));

        System.out.println(graph);

        checkIfExistASimpleRoad(graph, graph.iterator(0), graph.iterator(5), 3, 11);
    }

}
