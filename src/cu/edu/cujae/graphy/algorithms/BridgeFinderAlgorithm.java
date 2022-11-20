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
import cu.edu.cujae.graphy.utils.Pair;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Given an undirected graph finds all the bridges. Like articulation points, bridges represent vulnerabilities in a
 * connected network and are useful for designing reliable systems. A bridge is an edge which removing increases the
 * number of disconnected components.
 * <p>
 * The time complexity of this algorithm is <code>O(V + E)</code> and <code>O(B<sup>M</sup>)</code> where M is the
 * maximum branching of the DFS tree.
 *
 * @author Javier Marrero
 */
public class BridgeFinderAlgorithm extends AbstractAlgorithm<List<Pair<Integer, Integer>>>
{

    private static final int NIL = Integer.MIN_VALUE;

    private final Graph<?> G;
    private int time;

    public BridgeFinderAlgorithm(Graph<?> graph)
    {
        super(new LinkedList<>());
        if (graph.isDirected())
        {
            throw new IllegalArgumentException("the graph must be undirected");
        }

        // Initialize the parameters
        this.G = graph;
        this.time = 0;
    }

    @Override
    public Algorithm<List<Pair<Integer, Integer>>> apply()
    {
        // Initialize the algorithm parameters
        this.time = 0;
        Set<Integer> visited = new TreeSet<>();
        Map<Integer, Integer> disc = new TreeMap<>();
        Map<Integer, Integer> low = new TreeMap<>();
        Map<Integer, Integer> parent = new TreeMap<>();

        // Initialize the parent map
        for (int i : G.getLabels())
        {
            disc.put(i, 0);
            low.put(i, 0);
            parent.put(i, NIL);
        }

        // Call the recursive vertex function to find all the bridges in the DFS tree
        for (int i : G.getLabels())
        {
            if (!visited.contains(i))
            {
                bridgeUtil(i, visited, disc, low, parent);
            }
        }

        // This is mandated by the interface
        return this;
    }

    private void bridgeUtil(int u, Set<Integer> visited, Map<Integer, Integer> disc, Map<Integer, Integer> low,
                            Map<Integer, Integer> parent)
    {
        // Mark the current node as visited
        visited.add(u);

        // Initialize discovery time and low value
        low.put(u, ++time);
        disc.put(u, low.get(u));

        // Iterate for all the adjacent vertices
        GraphIterator<?> i = G.iterator(u);
        for (Edge e : i.getAllAdjacentEdges())
        {
            int v = (i.getEdgesDepartingSelf().contains(e)) ? e.getFinalNode().getLabel() : e.getStartNode().getLabel();

            if (visited.contains(v) == false)
            {
                parent.put(v, u);
                bridgeUtil(v, visited, disc, low, parent);

                // Check if the subtree rooted with v has a
                // connection to one of the ancestors of u
                low.put(u, Math.min(low.get(u), low.get(v)));

                // If the lowest vertex reachable from subtree
                // under v is below u in DFS tree, then u-v is
                // a bridge
                if (low.get(v) > disc.get(u))
                {
                    getResult().add(new Pair<>(u, v));
                }
            }

            // Update the low value of u for parent function calls
            else if (v != parent.get(u))
            {
                low.put(u, Math.min(low.get(u), disc.get(v)));
            }
        }
    }

}
