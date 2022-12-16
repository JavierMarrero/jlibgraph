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

import cu.edu.cujae.graphy.algorithms.KosarajuAlgorithm;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

/**
 *
 * @author Ananda
 */
public class KosarajuTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        Graph<Integer> graph = GraphBuilders.makeSimpleGraph(true);
        for (int i = 0; i < 5; i++)
        {
            graph.add(i);
        }

        graph.connect(0, 3);
        graph.connect(0, 2);
        graph.connect(1, 0);
        graph.connect(2, 1);
        graph.connect(3, 4);

        System.out.println(graph);
        System.out.println("Kosaraju?:");
        System.out.println(new KosarajuAlgorithm<>(graph, graph.iterator(0)).apply().get());
    }

}
