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
package cu.edu.cujae.graphy.tests;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

/**
 *
 * @author Javier Marrero
 */
public class RemovalTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException
    {
        Graph<Integer> graph = GraphBuilders.makeSimpleGraph(false);
        for (int i = 0; i < 5; ++i)
        {
            graph.add(i);
        }

        graph.connect(0, 1);
        graph.connect(0, 2);
        graph.connect(1, 2);
        graph.connect(1, 3);
        graph.connect(2, 3);
        graph.connect(2, 4);
        graph.connect(3, 4);
        graph.connect(4, 0);

        System.out.println(graph + "\n");
        {
            printAll(graph);
        }

        // Remove
        graph.removeAt(1);
        graph.removeAt(3);

        System.out.println(graph + "\n");
        {
            printAll(graph);
        }

        // Clone the graph
        Graph<Integer> clone = graph.duplicate();
        clone.add(1, 1);
        clone.add(3, 3);

        clone.connect(1, 2);
        clone.connect(1, 3);
        clone.connect(2, 3);
        clone.connect(3, 4);

        System.out.println(clone + "\n");
        {
            printAll(clone);
        }

        // Remove
        clone.removeAt(1);
        clone.removeAt(3);

        System.out.println(clone + "\n");
        {
            printAll(clone);
        }
    }

    private static void printAll(Graph<Integer> graph)
    {
        GraphIterator<Integer> dfs = (GraphIterator<Integer>) graph.depthFirstSearchIterator(false);
        while (dfs.hasNext())
        {
            System.out.println(dfs.next() + " " + dfs.getAllAdjacentEdges());
        }
        System.out.println("\n");
    }

}
