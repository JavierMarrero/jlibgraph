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

import cu.edu.cujae.graphy.algorithms.GirvanNewmanAlgorithm;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;
import java.util.Random;

/**
 *
 * @author Javier Marrero
 */
public class GivanNewmanAlgorithmTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Random random = new Random();

        WeightedGraph<Integer> graph = GraphBuilders.makeSimpleWeightedGraph(false);
        for (int i = 0; i < 200; ++i)
        {
            graph.add(i);
        }

        // Make connections
        for (int i = 0; i < 200; ++i)
        {
            graph.connect(i, (2 * i + 1) % 200, Weights.makeWeight(random.nextInt(15) + 1));
            if (i % 7 == 0 || i % 11 == 0 || i % 9 == 0 || i % 4 == 0)
            {
                graph.connect(i, (i + 3) % 200, Weights.makeWeight(random.nextInt(12) + 1));
                graph.connect((i + 3) % 200, i, Weights.makeWeight(random.nextInt(17) + 1));
            }
        }

        // Print the graph
        System.out.println(graph);

        System.out.println("Givan-Newman algorithm for communities yielded this value:");
        System.out.println(new GirvanNewmanAlgorithm(graph).apply().get());
    }

}
