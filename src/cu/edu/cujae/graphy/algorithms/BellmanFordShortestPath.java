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
import cu.edu.cujae.graphy.core.exceptions.InvalidOperationException;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * El algoritmo Bellman-Ford genera el camino mínimo desde un vértice al resto
 * de vértices en un digrafo (con posibles pesos negativos en algunas de las
 * aristas). El algoritmo DijkstraShortestPath (cu.edu.cujae.graphy.algorithms)
 * resulve este mismo problema en un menor tiempo pero requiere que los pesos de
 * las aristas no sean negativos, salvo que el grafo sea dirigido y sin ciclos.
 * Tiene una compejidad algorítmica de O(VE).
 *
 * Este algoritmo fue desarrollado por Richard Bellman, Samuel End y Lester
 * Ford.
 *
 * @author Amanda Mendez
 * @param <T>
 */
public class BellmanFordShortestPath<T> extends AbstractAlgorithm<Map<Integer, Pair<Integer, List<Integer>>>>
{

    private final WeightedGraph<T> G;
    private final GraphIterator<T> it;
    private final int V;
    private final Map<Integer, Integer> distances;
    private final Map<Integer, Integer> parents;
    private final Queue<Integer> queue;
    private final int[] vertices;
    private final Set<Edge> edges;

    public BellmanFordShortestPath(WeightedGraph<T> graph, GraphIterator<T> iter) 
    {

        super(new HashMap<>(graph.size()));

        if (!graph.isWeighted()) {

            throw new IllegalArgumentException(
                "Attempted to apply Dijkstra Shortest Path algorithm to an unweighted graph.");

        }

        // Inicializar campos de la clase
        this.edges = new HashSet<Edge>();
        this.distances = new HashMap<>(graph.size(), 0.25f);
        this.parents = new TreeMap<>();
        this.vertices = new int[graph.size()];
        this.queue = new ArrayDeque<Integer>();
        this.G = graph;
        this.it = iter;
        this.V = iter.getLabel();

        //Obtener vértices del grafo 
        int i = 0;

        GraphIterator<T> dfs = (GraphIterator<T>) graph.depthFirstSearchIterator(false);

        while (dfs.hasNext()) 
        {

            dfs.next();
            vertices[i++] = dfs.getLabel();
            edges.addAll(dfs.getAllAdjacentEdges());

        }

        //Inicializar las distancias de los vértices a MAX_VALUE
        for (int v : vertices) 
        {

            distances.put(v, Integer.MAX_VALUE);
            parents.put(v, null);

        }

        // Inicializar las distancias inicales
        distances.put(V, 0);

        //Inicializar cola
        queue.offer(V);

    }

    @Override
    public Algorithm<Map<Integer, Pair<Integer, List<Integer>>>> apply() 
    {

        for(int i= 1;i<vertices.length;++i)
        {
           
            for(Edge e: edges)
            {
                int u = e.getStartNode().getLabel();
                int v = e.getFinalNode().getLabel();
                int weight = (int) e.getWeight().getValue();
                
                int d = distances.get(u) + weight;
                if (distances.get(u) != Integer.MAX_VALUE && d < distances.get(v))
                {
                    distances.put(v, d);
                    parents.put(v, u);
                }
            }
        }
        
        for(Edge edge: edges)
        {
            int u = edge.getStartNode().getLabel();
            int v = edge.getFinalNode().getLabel();
            int w = (int) edge.getWeight().getValue();
            
            int alt = distances.get(u) + w;
            if(distances.get(u) != Integer.MAX_VALUE && alt < distances.get(v))
            {
                throw new InvalidOperationException("The graph contains a negative weight cycle");
            }
        }
        
        Map<Integer, Pair<Integer, List<Integer>>> result = getResult();
        for (int k : distances.keySet()) 
        {
            result.put(k, new Pair<>(distances.get(k), makeShortestPathSequence(V, k)));
        }

        return this;

    }
    
    private List<Integer> makeShortestPathSequence(int source, int target)
    {
        LinkedList<Integer> S = new LinkedList<>();
        int u = target;

        if (parents.get(u) != null)
        {
            while (parents.get(u) != null)
            {
                S.push(u);
                u = parents.get(u);
            }
        }
        S.push(source);
        return S;
    }

}
