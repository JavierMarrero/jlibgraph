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
package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * This algorithm checks if there are nodes in both directed and undirected graphs.
 *
 * @author Javier Marrero
 * @param <V>
 */
public class CycleDetection<V> extends AbstractAlgorithm<Boolean>
{

    private final GraphIterator<V> iterator;

    public CycleDetection(Graph<V> graph)
    {
        super(Boolean.FALSE);

        /* Create the graph iterator */
        this.iterator = (GraphIterator<V>) graph.depthFirstSearchIterator(true);
    }

    @Override
    public Algorithm<Boolean> apply()
    {
        Set<Integer> visited = new TreeSet<>();
        while (iterator.hasNext() && (Objects.equals(getResult(), Boolean.FALSE)))
        {
            // Step to the next element
            iterator.next();

            // Mark the node as visited
            if (!visited.add(iterator.getLabel()))
            {
//                System.out.println("FOUND CYCLE!");
                setResult(Boolean.TRUE);
            }

            // Debug... may removeAt later
//            System.out.println("Visiting node: " + iterator.getLabel() + " | visited: " + visited.toString());
            // Now for each adjacent node check if the node was visited
            for (Edge e : iterator.getEdgesDepartingSelf())
            {
                // Debug
                //System.out.println("checking edge: " + e.getFinalNode().getLabel());

                if (visited.contains(e.getFinalNode().getLabel()))
                {
                    // DEBUG
//                    System.out.println("FOUND CYCLE!");

                    setResult(Boolean.TRUE);
                }
            }
        }

        /* This is mandated by the interface */
        return this;
    }
}
