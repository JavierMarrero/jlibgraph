/*
 * Copyright (C) 2022 CUJAE.
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
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import java.util.Iterator;

/**
 *
 * @author Javier Marrero
 */
public class SimpleGraphTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Graph<Integer> graph = GraphBuilders.makeSimpleGraph(false);

        /* Add some nodes */
        for (int i = 0; i < 4; ++i)
        {
            graph.add(i);
        }

        /* Connect these nodes */
        graph.connect(0, 1);
        graph.connect(0, 2);
        graph.connect(1, 2);
        graph.connect(2, 0);
        graph.connect(2, 3);
        graph.connect(3, 3);

        /* Print the graph */
        System.out.println(graph.toString());

        /* Iterate depth first */
        Iterator<Integer> dfs = graph.depthFirstSearchIterator(2, true);
        while (dfs.hasNext())
        {
            System.out.print(dfs.next() + " ");
        }
        System.out.println();

        /* Iterate breadth first */
        Iterator<Integer> bfs = graph.breadthFirstSearchIterator(2, true);
        while (bfs.hasNext())
        {
            System.out.print(bfs.next() + " ");
        }
        System.out.println();
    }

}
