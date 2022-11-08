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

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Determines whether a given graph contains a Hamiltonian cycle or not.If it contains, returns a list of the vertices
 * forming the cycle. Else, it returns the empty list.
 * <p>
 * Hamiltonian path in an undirected graph is a path that visits each vertex exactly once. A Hamiltonian cycle (or
 * Hamiltonian circuit) is a Hamiltonian Path such that there is an edge in the graph from the last vertex to the
 * first vertex of the Hamiltonian Path.
 *
 * @author Javier Marrero
 * @param <V>
 */
public class HamiltonianCycleDetection<V> extends AbstractAlgorithm<List<Integer>>
{

    private final Graph<V> graph;
    private final int V;
    private final ArrayList<Integer> vertices;

    public HamiltonianCycleDetection(Graph<V> graph)
    {
        super(new LinkedList<>());

        // Input parameters
        this.graph = graph;
        this.V = graph.size();
        this.vertices = new ArrayList<>(V);
    }

    @Override
    public Algorithm<List<Integer>> apply()
    {
        List<Integer> path = new ArrayList<>();

        // Vertices
        GraphIterator<V> bfs = (GraphIterator<V>) graph.breadthFirstSearchIterator(false);
        while (bfs.hasNext())
        {
            vertices.add(bfs.getLabel());
            bfs.next();
        }

        // Add the vertex zero
        path.add(vertices.get(0));

        // Start adding other vertices
        if (walk(path, 1) == false)
        {
            getResult().clear();
        }
        else
        {
            setResult(path);
        }

        // This return statement is mandated by the interface
        return this;
    }

    private boolean isSafe(int v, List<Integer> path, int pos)
    {
        // Check if the vertex is adjacent to the previously added vertex
        if (graph.isVertexAdjacent(path.get(pos - 1), v) == false)
        {
            return false;
        }

        // Check if the vertex has already been included
        // If not, return true, else, false
        return !path.contains(v);
    }

    private boolean walk(List<Integer> path, int pos)
    {
        if (pos == V)
        {
            return graph.isVertexAdjacent(path.get(pos - 1), path.get(0));
        }
        else
        {
            for (int v = 1; v < V; ++v)
            {
                if (isSafe(v, path, pos))
                {
                    path.add(pos, v);

                    if (walk(path, pos + 1))
                    {
                        return true;
                    }

                    path.remove(pos);
                }
            }
        }
        return false;
    }

}
