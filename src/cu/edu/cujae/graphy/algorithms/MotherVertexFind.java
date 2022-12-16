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

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.TreeSet;

/**
 * A mother vertex in a graph <code>G = (V, E)</code> is a vertex <i>v</i> such that all other vertices in G can be
 * reached by a path from <i>v</i>.
 *
 * @author Javier Marrero
 * @param <T>
 */
public class MotherVertexFind<T> extends AbstractAlgorithm<Integer>
{

    private final Graph<T> G;
    private final GraphIterator<T> dfsIterator;
    private final TreeSet<Object> visited;

    public MotherVertexFind(Graph<T> graph)
    {
        super(null);

        // Graphs
        this.G = graph;
        this.dfsIterator = (GraphIterator<T>) graph.depthFirstSearchIterator(true);
        this.visited = new TreeSet<>();
    }

    @Override
    public Algorithm<Integer> apply()
    {
        // Store the last finished vertex
        // (or mother vertex)
        int v = -1;

        while (dfsIterator.hasNext())
        {
            // Step to the next vertex
            dfsIterator.next();

            // Add this to the list of visited
            if (!isVisited(dfsIterator.getLabel()))
            {

                v = dfsIterator.getLabel();
            }
        }

        return this;
    }

    private boolean isVisited(int v)
    {
        return visited.contains(v);
    }
}
