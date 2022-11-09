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

import cu.edu.cujae.graphy.algorithms.TreeDetection;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.defaults.DefaultGraphBuilder;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;

/**
 *
 * @author Jose
 */
public class SimpleRoadTest
{

    public static void checkIfExistASimpleRoad(Graph<Integer> graph)
    {
        // System.out.println("Exist simple road?: " + (new SimpleRoadTest<>(graph, (GraphIterator<Integer>) graph.depthFirstSearchIterator(0, Boolean.FALSE),4).apply().get()));
    }

    public static void main(String[] args)
    {
        /*Juego de datos para cuando existe el camino simple*/
        Graph<Integer> graph = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();
        for (int i = 0; i < 10; i++)
        {
            graph.add(i, i);
        }
        graph.connect(0, 1);
        graph.connect(0, 2);
        graph.connect(1, 4);
        graph.connect(2, 5);
        graph.connect(5, 7);
        graph.connect(2, 3);
        graph.connect(3, 6);
        graph.connect(6, 8);
        graph.connect(3, 9);
    }

}
