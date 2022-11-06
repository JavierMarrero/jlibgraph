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
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.Weight;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

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
 * a {@link InvalidOperationException}.
 *
 * @author Javier Marrero
 */
public class DijkstraShortestPath extends AbstractAlgorithm<Map<Integer, Pair<Integer, List<Integer>>>>
{

    private final WeightedGraph<Object> G;
    private final GraphIterator<Object> s;
    private final Map<Integer, Integer> distance;
    private final List<Integer> path;
    private final Set<Integer> visited;

    @SuppressWarnings (
            {
                "unchecked", "unchecked"
            })
    public DijkstraShortestPath(WeightedGraph<?> graph, GraphIterator<?> iter)
    {
        super(new HashMap<>(graph.size()));
        if (!graph.isWeighted())
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Dijkstra Shortest Path algorithm to an unweighted graph.");
        }

        this.G = (WeightedGraph<Object>) graph;
        this.s = (GraphIterator<Object>) iter;
        this.distance = new HashMap<>(graph.size());
        this.path = new ArrayList<>(graph.size() - 1);
        this.visited = new TreeSet<>();

        // Initialize the algorithm
        // Set all the distances to infinity
        List<Integer> vertices = new ArrayList<>(graph.size());
        GraphIterator<?> dfs = (GraphIterator<?>) graph.depthFirstSearchIterator();
        while (dfs.hasNext())
        {
            dfs.next();
            vertices.add(dfs.getLabel());
        }

        for (int w : vertices)
        {
            if (w != s.getLabel())
            {
                if (graph.isVertexAdjacent(s.getLabel(), w))
                {
                    setDistance(w, (int) s.getAdjacentEdge(w).getWeight().getValue());
                }
                else
                {
                    setDistance(w, Integer.MAX_VALUE);
                }
            }
        }

        // Set the distance to S as zero
        setDistance(label(s), 0);
        setVisited(label(s));

        // Just another debug statement
        // System.out.println(distance);
    }

    @Override
    public Algorithm<Map<Integer, Pair<Integer, List<Integer>>>> apply()
    {
        Map<Integer, List<Integer>> paths = new TreeMap<>();

        while (visited.size() != G.size())
        {
            
            // Choose the next node and mark it as visited       
            Node<Object> chosen = chooseNextNode(s.getEdgesDepartingSelf(), s.getEdgesArrivingSelf());
            if (chosen == null)
            {
                throw new IllegalStateException("Dijkstra could not choose a correct node to proceed.");
            }

            s.next(chosen);
            setVisited(label(s));
            
            // System.out.println("visiting node: " + s.getLabel());
            // Add the vertices to the path
            paths.put(label(s), new ArrayList<>(path));

            // Create the collection of possible jumpable nodes
            Set<Edge> possibleEdges = new CopyOnWriteArraySet<>(s.getEdgesDepartingSelf());
            possibleEdges.addAll(s.getEdgesArrivingSelf());

            for (Edge w : possibleEdges)
            {
                Node<?> n = (s.getEdgesDepartingSelf().contains(w)) ? w.getFinalNode() : w.getStartNode();
                int distancia_w = getDistance(n.getLabel());
                int distancia_v = getDistance(s.getLabel());
                int peso_w = (int) w.getWeight().getValue();

                if (distancia_w > distancia_v + peso_w)
                {
                    setDistance(n.getLabel(), distancia_v + peso_w);
                }
            }
            
            // Get the label of this visited node
            int labelOfLastVisited = label(s);
            path.add(labelOfLastVisited);
        }

        // End, now output the final results
        for (int v : distance.keySet())
        {
            List<Integer> currentPath = paths.get(v);
            if (currentPath == null)
            {
                currentPath = new ArrayList<>();
            }
            getResult().put(v, Pair.makePair(distance.get(v), currentPath));
        }

        // Mandated by the specification
        return this;
    }

    @SuppressWarnings ("unchecked")
    private void checkEdgeCompliesWithDijkstra(Edge edge)
    {
        if (!edge.isWeighted() || (((Weight<Integer>) edge.getWeight()).getValue() < 0))
        {
            throw new IllegalStateException(
                    "Dijkstra applied with incorrect parameters but not detected on the class constructor.");
        }
    }

    @SuppressWarnings ("unchecked")
    private Node<Object> chooseNextNode(Collection<Edge> edgesDeparting, Collection<Edge> edgesArriving)
    {
        Node<Object> result = null;
        Weight<Integer> lesser = null;

        for (Edge edge : edgesDeparting)
        {
            // Security check
            checkEdgeCompliesWithDijkstra(edge);

            // This should execute only the first time
            if (lesser == null || (compareEdgesLesserWeight(edge, lesser)
                                   && (!isVisited(edge.getFinalNode().getLabel()))))
            {
                lesser = (Weight<Integer>) edge.getWeight();
                result = (Node<Object>) edge.getFinalNode();
            }
        }
        for (Edge edge : edgesArriving)
        {
            checkEdgeCompliesWithDijkstra(edge);

            if (lesser == null || (compareEdgesLesserWeight(edge, lesser)
                                   && (!isVisited(edge.getStartNode().getLabel()))))
            {
                lesser = (Weight<Integer>) edge.getWeight();
                result = (Node<Object>) edge.getStartNode();
            }
        }

        return result;
    }

    @SuppressWarnings ("unchecked")
    private boolean compareEdgesLesserWeight(Edge lhs, Weight<Integer> rhs)
    {
        return ((Comparable<Integer>) lhs.getWeight()).compareTo(rhs.getValue()) < 0;
    }

    /**
     * Filter the algorithm results to obtain the shortest path to a particular node.
     *
     * @param v
     *
     * @return
     */
    public Pair<Integer, List<Integer>> filter(int v)
    {
        return getResult().get(v);
    }

    private boolean isVisited(int u)
    {
        return visited.contains(u);
    }

    private void setDistance(int v, int d)
    {
        distance.put(v, d);
    }

    private int getDistance(int v)
    {
        return distance.getOrDefault(v, 0);
    }

    private int label(GraphIterator<?> it)
    {
        return it.getLabel();
    }

    private void setVisited(int v)
    {
        visited.add(v);
    }
}
