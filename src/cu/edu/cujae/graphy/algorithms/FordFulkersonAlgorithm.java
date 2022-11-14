/*
 * Copyright (C) 2022 Asus.
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
import cu.edu.cujae.graphy.core.Weight;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.*;

/**
 * El algoritmo de Ford-Fulkerson resuelve el problema de flujo máximo. Dado un
 * grafo que representa una red de flujo donde toda arista tiene una capacidad,
 * y dados dos vértices: fuente (s) y vertedero (t) en el grafo, se encuentra
 * el flujo máximo posible desde s hasta t, teniendo en cuenta las siguientes
 * restriciones:
 * <ul>
 * <li>El flujo en un vértice no puede superar la capacidad máxima de dicho
 * vértice.</li>
 * <li>El flujo de entrada debe ser igual al flujo de salida para cada vértice
 * excepto s y t.</li>
 * </ul>
 * <p>
 * Su nombre viene dado por sus creadores , L.R. Ford, Jr. y D.R. Fulkerson.
 *
 * @author Amanda Mendez
 * @param <T>
 */
public class FordFulkersonAlgorithm<T> extends AbstractAlgorithm<Pair<Float, List<Integer>>>
{

    // Campos de la clase 
    private final WeightedGraph<T> g; // Graph
    private final int s;
    private final int t;
    private float maxFlow;
    private final Map<Integer, Integer> parent; // To store path
    private final WeightedGraph<T> rg; // Residual graph

    // Constructor de la clase 
    public FordFulkersonAlgorithm(WeightedGraph<T> graph, GraphIterator<T> source, GraphIterator<T> dest) throws
            CloneNotSupportedException
    {
        super(new Pair<Float, List<Integer>>());

        // This algorithm only works on weighted graphs
        if (!graph.isWeighted())
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Ford Fulkerson algorithm to an unweighted graph.");
        }

        // This algorithm only works on directed graphs
        if (!graph.isDirected())
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Ford Fulkerson algorithm to an undirected graph.");
        }

        // Inicializar campos de la clase
        this.g = graph;
        this.s = source.getLabel();
        this.t = dest.getLabel();
        this.maxFlow = 0;
        this.parent = new HashMap<>();

        // Create residual graph
        this.rg = (WeightedGraph<T>) g.duplicate();
    }
    
    @Override
    public Algorithm<Pair<Float, List<Integer>>> apply()
    {

        // While exist a path from source to dest
        while (bfs(rg, s, t, parent))
        {
            // To store path flow
            float pathFlow = Float.MAX_VALUE;

            // Find maximum flow of path
            for (int v = t; v != s; v = parent.get(v))
            {
                int u = parent.get(v);
                
                GraphIterator<T> it = rg.iterator(u);
                Edge edge = it.getAdjacentEdge(v);
                
                float weight = (float) edge.getWeight().getValue();
                pathFlow = Math.min(pathFlow, weight);
            }

            // Update residual graph capacities
            // Reverse edges along the path
            for (int v = t; v != s; v = parent.get(v))
            {
                int u = parent.get(v);
                
                GraphIterator<T> it = rg.iterator(u);
                Edge adjacentEdge = it.getAdjacentEdge(v);
                
                @SuppressWarnings ("unchecked")
                Weight<Float> ew = (Weight<Float>) adjacentEdge.getWeight();
                
                ew.setValue(ew.getValue() - pathFlow);

                // Reverse the edges
                
            }

            // Add path flow to max flow
            maxFlow += pathFlow;
        }

        // Create the final result
        List<Integer> list = new ArrayList<>(g.size());
        Stack<Integer> stack = new Stack<>();
        
        int v = t;
        while (v != s)
        {
            stack.push(v);
            v = parent.get(v);
        }
        
        while (!stack.isEmpty())
        {
            list.add(stack.pop());
        }
        
        Pair<Float, List<Integer>> result = getResult();
        
        result.setFirst(maxFlow);
        result.setLast(list);
        
        return this;
    }
    
    public boolean bfs(WeightedGraph<T> rg, int source, int dest, Map<Integer, Integer> parents)
    {
        // Array to store visited vertices
        Set<Integer> seen = new TreeSet<>();
        
        Queue<Integer> l = new LinkedList<>();

        // Visit source
        l.add(source);
        seen.add(source);
        parents.put(source, null);

        // Loop trough all vertices
        while (!l.isEmpty())
        {
            int i = l.poll();

            // Check neighbours of vertex i
            GraphIterator<T> it = rg.iterator(i);
            
            for (Integer j : it.getAllAdjacentVertices())
            {
                Edge e = it.getAdjacentEdge(j);
                if (e == null)
                {
                    throw new RuntimeException("There's no adjacent vertex from " + i + " to " + j);
                }
                
                float w = (float) e.getWeight().getValue();
                
                if ((seen.contains(j) == false) && w > 0)
                {
                    l.add(j);
                    seen.add(j);
                    parents.put(j, i);
                }
            }
            
        }
        
        return seen.contains(dest);
    }
    
}
