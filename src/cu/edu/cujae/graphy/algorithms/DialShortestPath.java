/*
 * Copyright (C) 2022 Ananda.
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
import java.util.Set;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * El algoritmo de Dial, es decir, Dijkstra optimizado para pesos de rango
 * pequeño, emplea una nueva estructura denominada cubo y posee una complejidad
 * de tiempo O(E+WV), donde W es el peso máximo en cualquier borde del
 * gráfico.La distancia máxima entre dos nodos puede tener un máximo de w(V-1).
 *
 *
 * @author Ananda
 */
public class DialShortestPath extends AbstractAlgorithm<Map<Integer, Pair<Integer, List<Integer>>>> {

    private final static int INFINITY = Integer.MAX_VALUE;

    private final Map<Integer, Deque<Integer>> buckets;
    private final Map<Integer, Integer> distances;
    private final WeightedGraph<?> G;
    private final Map<Integer, Integer> previous;
    private final int V;
    private final int W;
    private GraphIterator<?> s;

    public DialShortestPath(WeightedGraph<?> graph, GraphIterator<?> s, int maxW) {
        super(new HashMap<>(graph.size()));

        //debo verificar que ninguna arista posea un peso mayor que maxWeight!!!
        if (!graph.isWeighted()) {
            throw new IllegalArgumentException(
                    "Attempted to apply Dial algorithm to an unweighted graph.");
        }

        // Initialize the class's fields
        this.buckets = new HashMap<>((int) maxW * graph.size() + 1);
        this.distances = new TreeMap<>();
        this.G = graph;
        this.previous = new TreeMap<>();
        this.V = graph.size();
        this.W = maxW;
        this.s = s;

        // Initialize all distances to infinity
        for (int v : graph.getLabels()) {
            distances.put(v, INFINITY);
            previous.put(v, null);
        }

        // Initialize the buckets
        for (int i = 0; i < (maxW * V + 1); ++i)
        {
            buckets.put(i, new LinkedList<>());
        }
        
        // Set the distance to the source to 0
        // and put it in the 0 bucket
        distances.put(s.getLabel(), 0);
        buckets.get(0).push(s.getLabel());        

//        System.out.println(distances);
//        System.out.println(buckets);
    }

    @Override
    public Algorithm<Map<Integer, Pair<Integer, List<Integer>>>> apply() {

        int idx = 0;
        while (true)
        {
            while (buckets.get(idx).isEmpty() && idx < W * V)
                idx++;
            
            if (idx == W * V)
                break;
            
            GraphIterator<?> u = G.iterator(buckets.get(idx).pop());
            
            // Process all adjacents of extracted vertex 'u'
            // and update their distances if required.
            for (Edge e : u.getAllAdjacentEdges())
            {
                int v = e.getFinalNode().getLabel();
                int weight = (int) e.getWeight().getValue();
                
                int du = distances.get(u.getLabel());
                int dv = distances.get(v);
                
                // If there is shorted path to v through u.
                if (dv > du + weight)
                {
                    distances.put(v, du + weight);
                    dv = distances.get(v);
                    
                    previous.put(v, u.getLabel());
                    
                    // Push vertex v into updated distances bucket
                    buckets.get(dv).push(v);
                }
            }
        }
        
        for (int v : distances.keySet())
        {
            getResult().put(v, new Pair<>(distances.get(v), makeShortestPathSequence(s.getLabel(), v)));
        }
            
        // This is mandated by the interface
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
