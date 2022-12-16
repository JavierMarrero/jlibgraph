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

import cu.edu.cujae.graphy.algorithms.FloydWarshall;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;
import cu.edu.cujae.graphy.utils.MapBiArray;
import cu.edu.cujae.graphy.utils.Pair;

/**
 *
 * @author Jose
 */
public class FloydWarshallTest
{

    public static void getMatrix(WeightedGraph<Integer> graph)
    {
        @SuppressWarnings ("unchecked")
        Pair<MapBiArray<Integer, Integer, Integer>, MapBiArray<Integer, Integer, Integer>> aux
                                                                                                   = (Pair<MapBiArray<Integer, Integer, Integer>, MapBiArray<Integer, Integer, Integer>>) new FloydWarshall<>(
                        graph).apply().get();
        System.out.println("Floyd-Warshall");
        System.out.println("Matriz de distancia");
        System.out.println("   0  1  2  3  4");
        for (int i = 0; i < 5; i++)
        {
            System.out.print(i + "  ");
            for (int j = 0; j < 5; j++)
            {
                System.out.
                        print((aux.getFirst().get(i, j) != Integer.MAX_VALUE ? aux.getFirst().get(i, j) : "-") + "  ");
            }
            System.out.println();
        }

        System.out.println("Floyd-Warshall");
        System.out.println("Matriz de recorrido");
        System.out.println("   0  1  2  3  4");
        for (int i = 0; i < 5; i++)
        {
            System.out.print(i + "  ");
            for (int j = 0; j < 5; j++)
            {
                System.out.print((aux.getLast().get(i, j) != Integer.MAX_VALUE ? aux.getLast().get(i, j) : "-") + "  ");
            }
            System.out.println();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        WeightedGraph<Integer> graph = GraphBuilders.makeSimpleWeightedGraph(true);

        // Create the nodes
        for (int i = 0; i < 5; ++i)
        {
            graph.add(i);
        }

        // Connect
        graph.connect(0, 1, Weights.makeWeight(5));
        graph.connect(1, 4, Weights.makeWeight(3));
        graph.connect(4, 0, Weights.makeWeight(6));
        graph.connect(0, 3, Weights.makeWeight(1));
        graph.connect(3, 2, Weights.makeWeight(2));
        graph.connect(4, 2, Weights.makeWeight(4));

        getMatrix(graph);

    }

}
