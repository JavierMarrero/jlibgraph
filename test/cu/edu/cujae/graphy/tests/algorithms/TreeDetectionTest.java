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
import cu.edu.cujae.graphy.core.defaults.DefaultGraphBuilder;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;

/**
 *
 * @author Jose
 */
public class TreeDetectionTest
{

    public static void checkIsATree(Graph<Integer> graph)
    {
        System.out.println("Is a tree?: " + (new TreeDetection<>(graph, (GraphIterator<Integer>) graph.
                                                                 depthFirstSearchIterator(0, Boolean.FALSE)).apply().
                                             get()));
    }

    public static void main(String[] args)
    {
        /*Juego de datos para cuando un grafo es un árbol*/
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

        /*Juego de datos para cuando un grafo no es un árbol, posee un ciclo*/
        Graph<Integer> graph2 = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();
        for (int i = 0; i < 10; i++)
        {
            graph2.add(i, i);
        }
        graph2.connect(0, 1);
        graph2.connect(0, 2);
        graph2.connect(1, 2);
        graph2.connect(1, 4);
        graph2.connect(2, 5);
        graph2.connect(5, 7);
        graph2.connect(2, 3);
        graph2.connect(3, 6);
        graph2.connect(6, 8);
        graph2.connect(3, 9);

        /*Juego de datos para cuando un grafo no es un árbol, posee un nodo desconectado, label=9*/
        Graph<Integer> graph3 = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();
        for (int i = 0; i < 10; i++)
        {
            graph3.add(i, i);
        }
        graph3.connect(0, 1);
        graph3.connect(0, 2);
        graph3.connect(1, 4);
        graph3.connect(2, 5);
        graph3.connect(5, 7);
        graph3.connect(2, 3);
        graph3.connect(3, 6);
        graph3.connect(6, 8);

        /*Is a tree*/
        checkIsATree(graph);
        checkIsATree(graph2);
        checkIsATree(graph3);

    }
}
