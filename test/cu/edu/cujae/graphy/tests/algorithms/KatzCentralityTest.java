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

import cu.edu.cujae.graphy.algorithms.KatzCentrality;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.defaults.DefaultGraphBuilder;

/**
 *
 * @author Jose
 */
public class KatzCentralityTest
{

    public static void getKatz(Graph<Integer> graph, double alpha)
    {
        System.out.println("Katz Centrality" + new KatzCentrality(graph, alpha).apply().get());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Juego de datos para cuando existen vertices aislados
        Graph<Integer> graph = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();

        for (int i = 0; i < 9; i++)
        {
            graph.add(i, i);
        }

        graph.connect(0, 1);
        graph.connect(0, 2);
        graph.connect(0, 5);
        graph.connect(1, 5);
        graph.connect(1, 6);
        graph.connect(2, 5);
        graph.connect(2, 3);
        graph.connect(3, 4);
        graph.connect(4, 5);
        graph.connect(4, 8);
        graph.connect(5, 7);
        graph.connect(6, 7);
        graph.connect(7, 8);

        getKatz(graph, 1.5);
    }

}
