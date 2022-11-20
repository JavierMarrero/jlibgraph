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

import cu.edu.cujae.graphy.algorithms.IsolatedVertices;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.defaults.DefaultGraphBuilder;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Jose
 */
public class IsolatedVerticesTest
{

    public static void getIsolatedVertices(Graph<Integer> graph)
    {
        LinkedList<Integer> aux = (LinkedList<Integer>) (new IsolatedVertices(graph)).apply().get();
        Iterator<Integer> iter = aux.iterator();

        if (!aux.isEmpty())
        {
            while (iter.hasNext())
            {
                Integer in = iter.next();
                System.out.println(in);
            }
        }
        else
        {
            System.out.println("No existen vértices aislados en el  grafo");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Juego de datos para cuando existen vertices aislados
        Graph<Integer> graph = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();

        for (int i = 0; i < 10; i++)
        {
            graph.add(i, i);
        }

        graph.connect(0, 1);
        graph.connect(0, 2);
        graph.connect(2, 3);
        graph.connect(2, 4);
        graph.connect(4, 5);
        graph.connect(5, 6);
        graph.connect(1, 9);

        getIsolatedVertices(graph);

        //Juego de datos para cuando no existen vertices aislados
        Graph<Integer> graph2 = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();

        for (int i = 0; i < 10; i++)
        {
            graph2.add(i, i);
        }

        graph2.connect(0, 1);
        graph2.connect(0, 2);
        graph2.connect(2, 3);
        graph2.connect(2, 4);
        graph2.connect(4, 5);
        graph2.connect(5, 6);
        graph2.connect(1, 9);
        graph2.connect(7, 8);

        getIsolatedVertices(graph2);
        
        //Juego de datos para cuando existen vertices aislados en un grafo dirigido
        //en este caso están aislados 7,8 y el nodo 0 que no tiene aristas apuntando hacia él
        
        Graph<Integer> graph3 = new DefaultGraphBuilder<Integer>().buildGraph().directed(true).get();

        for (int i = 0; i < 10; i++)
        {
            graph3.add(i, i);
        }

        graph3.connect(0, 1);
        graph3.connect(0, 2);
        graph3.connect(2, 3);
        graph3.connect(2, 4);
        graph3.connect(4, 5);
        graph3.connect(5, 6);
        graph3.connect(1, 9);

        getIsolatedVertices(graph3);
    }

}
