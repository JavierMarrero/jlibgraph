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
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * El algoritmo de Ford-Fulkerson resuelve el problema de flujo máximo. Dado un 
 * grafo que representa una red de flujo donde toda arista tiene una capacidad, 
 * y dados dos vértices: fuente (s)  y vertedero (t) en el grafo, se encuentra 
 * el flujo máximo posible desde s hasta t, teniendo en cuenta las siguientes 
 * restriciones:
 * - El flujo en un vértice no puede superar la capacidad máxima de dicho 
 * vértice.
 * - El flujo de entrada debe ser igual al flujo de salida para cada vértice 
 * excepto s y t.
 * 
 * Su nombre viene dado por sus creadores , L.R. Ford, Jr. y D.R. Fulkerson. 
 * 
 * @author Amanda Mendez
 * @param <T>
 */
public class FordFulkersonAlgorithm<T> extends AbstractAlgorithm<Pair<Float,List<Integer>>> {
    
    // Campos de la clase 
    private final WeightedGraph<T> g; // Graph
    private final int s;
    private final int t;
    private float maxFlow;
    private final List<Integer> parent; // To store path
    private final WeightedGraph<T> rg; // Residual graph
    
    // Constructor de la clase 
    public FordFulkersonAlgorithm(WeightedGraph<T> graph,int source, int dest) throws CloneNotSupportedException
    {
        super(new Pair<Float,List<Integer>>());
        
        if (!graph.isWeighted()) 
        {

            throw new IllegalArgumentException(
                "Attempted to apply Ford Fulkerson algorithm to an unweighted graph.");

        }
        
        if (!graph.isDirected()) 
        {

            throw new IllegalArgumentException(
                "Attempted to apply Ford Fulkerson algorithm to an undirected graph.");

        }
        
        // Inicializar campos de la clase
        this.g = graph;
        this.s = source;
        this.t = dest;
        this.maxFlow = 0;
        this.parent = new ArrayList<Integer>(graph.size());
        
        // Create residual graph
       this.rg = (WeightedGraph<T>) g.duplicate();       
    }

    @Override
    public Algorithm<Pair<Float,List<Integer>>> apply() {
        boolean existPath = (boolean) new ExistPath(rg,s,t).apply().get();

        
        // While exist a path from source to dest
        while(existPath)
        {
            // To store path flow
            float pathFlow = Float.MAX_VALUE;
            
            // Find maximum flow of path
            for(int i = t; i != s; i = parent.get(i))
            {
                int j = parent.get(i);
                GraphIterator<T> it = rg.iterator(j);
                Edge edge = it.getAdjacentEdge(i);
                float weight = (float) edge.getWeight().getValue();
               
                pathFlow = Math.min(pathFlow, weight);
            }
            
            // Update residual graph capacities
            // Reverse edges along the path
            for(int i = t; i != s; i = parent.get(i))
            {
                int j = parent.get(i);
                GraphIterator<T> it = rg.iterator(i);
                Edge e = it.getAdjacentEdge(j);
                float wj = (float) e.getWeight().getValue();
                GraphIterator<T> ite = rg.iterator(j);
                Edge ed = it.getAdjacentEdge(i);
                float wi = (float) ed.getWeight().getValue();
                
                wj -= pathFlow;
                wi += pathFlow;
                
            }
            
            // Add path flow to max flow
            maxFlow += pathFlow; 
        }
        
        // Create the final result
        Pair<Float,List<Integer>> result = getResult();
       
        result.setFirst(maxFlow);
        result.setLast(parent);
        
        return this;
    }
    
}
