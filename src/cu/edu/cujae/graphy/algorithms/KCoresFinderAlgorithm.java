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
import cu.edu.cujae.graphy.utils.HashTuple;
import cu.edu.cujae.graphy.utils.Tuple;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Dado un grafo G y un entero K se encuentran los k-núcleos de dicho grafo, los 
 * cuales no son más que los componentes conexos que quedan luego de que todos 
 * los vértices con grado menor que K han sido removidos.
 * 
 * El algoritmo tiene una solución de complejidad O(V+E).
 *
 * @author Amanda Mendez 
 * @param<T>
 */
public class KCoresFinderAlgorithm<T> extends AbstractAlgorithm<Tuple<Tuple<Integer>>>
{
    private final Graph<T> graph;
    private final int k;
    private final GraphIterator<T> it;
    private Set<Integer> visited;
    private Set<Integer> processed;
    private Map<Integer,Integer> vDegree;
    private final int[] vertices;
    
    public KCoresFinderAlgorithm(Graph<T> graph, int k)
    {
        super(new HashTuple());
        
        // Initialize
        this.graph = graph;
        this.k = k;
        this.it = graph.randomIterator();
        this.visited = new HashSet<>();
        this.processed = new HashSet<>();
        this.vDegree = new HashMap<>();
        this.vertices = new int[graph.size()];
        
        // Get vertices
        int i = 0;

        GraphIterator<T> dfs = (GraphIterator<T>) graph.depthFirstSearchIterator(false);
        while (dfs.hasNext())
        {
            dfs.next();
            vertices[i++] = dfs.getLabel();
        } 
    }
    
    @Override
    public Algorithm<Tuple<Tuple<Integer>>> apply() 
    {
        int minDeg = Integer.MAX_VALUE;
        int startVertex = 0;
        
        // Store degrees of all vertices
        for(int i: vertices)
        {
            it.next(i);
            vDegree.put(i, it.getAllAdjacentVertices().size());
            
            if(vDegree.get(i) < minDeg)
            {
                minDeg = vDegree.get(i);
                startVertex = i;
            }  
        }
        
        it.next(startVertex);
        walk(it,visited,vDegree,k);
        
        // Traversal to update degrees of all vertices 
        for(int i:vertices)
        {
           if(!visited.contains(i)) 
           {
               it.next(i);
               walk(it,visited,vDegree,k);
           }
        }
        
        // Construction of the result
        Tuple<Tuple<Integer>> result = new HashTuple();
        
        for(int j: vertices)
        {
            // Only considering those vertices wich have degree >= k after walk
            if(vDegree.get(j) >= k)
            {
                Tuple<Integer> t = new HashTuple();
                t.add(j);
                
                it.next(j);
                for(int i: it.getAdjacentVerticesArrivingSelf())
                {
                    if(vDegree.get(i) >= k)
                    {
                        t.add(i);
                    }
                }
                result.add(t);
            }
        }
        result.freeze();
        
        return this;
    }
    
    public boolean walk(GraphIterator<T> it, Set<Integer> visited, Map<Integer,Integer> vDegree,int k)
    {
        // Mark the current node as visited
        int v = it.getLabel();
        visited.add(v);
        
        // Recur for all the vertices adjacent to this vertex
        for(int i: it.getAdjacentVerticesArrivingSelf())
        {
            // degree of v is less than k, then degree of adjacent must be 
            // reduced
            if(vDegree.get(v) < k)
            {
                int value = vDegree.get(i);
                vDegree.replace(i, value);
            }
            
            //if adjacent is not processed, process it
            if(!visited.contains(i))
            {
                // if degree of adjacent after processing becomes less than k,
                // the reduce degree of v also
                it.next(i);
                walk(it,visited,vDegree,k);
            }
        }
        
        // Return true if degree of v is less than k
        return (vDegree.get(v) < k);
    }
}
