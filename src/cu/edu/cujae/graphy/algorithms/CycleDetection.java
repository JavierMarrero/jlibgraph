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
package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.GraphIterator;
import cu.edu.cujae.graphy.core.Node;
import java.util.Set;
import java.util.TreeSet;

/**
 * This algorithm checks if there are nodes in both directed and undirected graphs.
 *
 * @author Javier Marrero
 */
public class CycleDetection<V> extends AbstractAlgorithm<Boolean>
{

    private GraphIterator<V> current;
    private Graph<V> graph;

    public CycleDetection(Graph<V> graph)
    {
        super(Boolean.FALSE);

        /* Create the graph iterator */
        this.current = (GraphIterator<V>) graph.iterator();
        this.graph = graph;
    }

    @Override
    public Algorithm<Boolean> apply()
    {
        Set<Integer> visited = new TreeSet<>();
        if (!graph.isDirected())
        {
            setResult(walkUndirected(current.getLabel(), visited, -1));
        }

        /* This is mandated by the interface */
        return this;
    }

    private boolean walkUndirected(int v, Set<Integer> visited, int parent)
    {
        // Mark the current node as visited
        visited.add(v);
        int i;

        // Recur for all the vertices adjacent to this node
        for (Edge e : current.getAdjacentEdges())
        {
            @SuppressWarnings ("unchecked")
            Node<V> end = (Node<V>) e.getFinalNode();
            i = end.getLabel();

            // If the adjacent node is not visited
            // recur for that adjacent
            if (!visited.contains(i))
            {
                current.next(end);

                if (walkUndirected(i, visited, v))
                {
                    return true;
                }
            }

            // If an adjacent is visited
            // and not parent of current
            // vertex, then there is a cycle.
            else if (i != parent)
            {
                return true;
            }
        }
        return false;
    }

}
