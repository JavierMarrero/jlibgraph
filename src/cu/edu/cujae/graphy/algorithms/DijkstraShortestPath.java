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
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Given a graph and a source vertex in the graph, find the shortest paths from the source to all vertices in the given
 * graph.
 * <p>
 * The name of the algorithm refers to the Dutch computation scientist <i>Edsger Dijkstra</i>, who described it in 1959.
 * The algorithm solves the <b>single-source shortest path problem</b> for a weighted graph. This algorithm is greedy
 * and keeps track of the weights of the edges for finding the path that minimizes the total distance.
 * <p>
 * The time complexity of this algorithm is at most <code>O(n<sup>2</sup>)</code> but on the average case it may
 * achieve <code>O(V + E log(V))</code>. Dijkstra has several advantages such as its time complexity, that it is useful
 * in finding the shortest distance quite fast. However it is unable to handle negative weights and, as every greedy
 * algorithm it may not be optimal for certain conditions.
 * <p>
 * Dijkstra's algorithm fails on negative weights because since Dijkstra follows a greedy approach, once a node is
 * marked as visited it cannot be reconsidered even if there is another path with less cost or distance. This issue
 * arises only if there exists a negative weight or edge in the graph. If negative weights are needed, see the
 * Bellman-Ford algorithm. In this implementation, whenever a graph with negative weights is encountered it may throw
 * a {@link cu.edu.cujae.graphy.core.exceptions.InvalidOperationException}.
 *
 * @author Javier Marrero
 */
public class DijkstraShortestPath extends AbstractAlgorithm<Map<Integer, Pair<Integer, List<Integer>>>>
{

    private final Map<Integer, Integer> distances;
    private final WeightedGraph<?> G;
    private final GraphIterator<?> it;
    private final Map<Integer, Integer> previous;
    private final int s;
    private final PriorityQueue<Integer> Q;
    private final int V;

    public DijkstraShortestPath(WeightedGraph<?> graph, GraphIterator<?> iter)
    {
        super(new HashMap<>(graph.size()));
        if (!graph.isWeighted())
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Dijkstra Shortest Path algorithm to an unweighted graph.");
        }

        // Initialize the fields of the classes
        this.distances = new HashMap<>(graph.size(), 0.25f);
        this.G = graph;
        this.it = iter;
        this.previous = new TreeMap<>();
        this.s = iter.getLabel();
        this.Q = new PriorityQueue<>(graph.size(), (Integer u, Integer v) -> 
                             {
                                 int du = distances.get(u);
                                 int dv = distances.get(v);

                                 return du - dv;
                             });
        this.V = graph.size();

        // Get a set of all the integer vertices
        int[] vertices = new int[V];
        int k = 0;

        GraphIterator<?> depthFirstSearchIterator = (GraphIterator<?>) graph.depthFirstSearchIterator(false);
        while (depthFirstSearchIterator.hasNext())
        {
            depthFirstSearchIterator.next();
            vertices[k++] = depthFirstSearchIterator.getLabel();
        }

        // Initialize the distances
        for (int v : vertices)
        {
            if (G.isVertexAdjacent(s, v))
            {
                distances.put(v, (Integer) iter.getAdjacentEdge(v).getWeight().getValue());
                previous.put(v, iter.getLabel());
            }
            else
            {
                distances.put(v, Integer.MAX_VALUE);
                previous.put(v, null);
            }

            Q.add(v);
        }

        // System.err.println(Q);
        // Initialize the initial distances
        distances.put(iter.getLabel(), 0);
    }

    @Override
    public Algorithm<Map<Integer, Pair<Integer, List<Integer>>>> apply()
    {
        // Code
        while (!Q.isEmpty())
        {
            // System.err.println(Q);

            int u = Q.poll();
            it.next(u);

            for (Edge edge : it.getAllAdjacentEdges())
            {
                int v;
                if (it.getEdgesDepartingSelf().contains(edge))
                {
                    v = edge.getFinalNode().getLabel();
                }
                else
                {
                    v = edge.getStartNode().getLabel();
                }

                if (Q.contains(v))
                {
                    int alt = distances.get(u) + (int) edge.getWeight().getValue();
                    if (alt <= distances.get(v))
                    {
                        distances.put(v, alt);
                        previous.put(v, u);
                    }
                }
            }
        }

        // Create the shortest path sequence
        // Create the final result
        Map<Integer, Pair<Integer, List<Integer>>> result = getResult();
        for (int k : distances.keySet())
        {
            result.put(k, new Pair<>(distances.get(k), makeShortestPathSequence(s, k)));
        }

        // Mandated by the specification
        return this;
    }

    private List<Integer> makeShortestPathSequence(int source, int target)
    {
        LinkedList<Integer> S = new LinkedList<>();
        int u = target;

        if (previous.get(u) != null)
        {
            while (previous.get(u) != null)
            {
                S.push(u);
                u = previous.get(u);
            }
        }
        S.push(source);
        return S;
    }
}
