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
import cu.edu.cujae.graphy.utils.HashTuple;
import cu.edu.cujae.graphy.utils.Tuple;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Dado un grafo no dirigido G(V,E) con N vértices y M aristas, se encuentra el 
 * número mínimo de aristas ente un par dado de vértices (u,v).
 * 
 * @author Amanda Mendez
 * @param<T>
 */
public class ShortestPathUnweightedGraph<T> extends AbstractAlgorithm<Tuple<Integer>>
{
    private final Tuple<Integer> path; 
    private final Graph<T> graph;
    private final GraphIterator<T> itU;
    private final GraphIterator<T> itV;
    private final Map<Integer, Integer> parents;
    private final int[] vertices;
    private final  Map<Integer,Integer> distances;
    private int u;
    private int v;
    
    public ShortestPathUnweightedGraph(Graph<T> graph,GraphIterator<T> itU,GraphIterator<T> itV)
    {
         super(new HashTuple());
         
         // Initialize fields 
         this.path = new HashTuple();
         this.graph = graph;
         this.itU = itU;
         this.itV = itV;
         this.vertices = new int[graph.size()];
         this.distances = new HashMap<>();
         this.parents = new TreeMap<>();
         this.u = 0;
         this.v = 0;
         
        // Get vertex
        int i = 0;

        GraphIterator<T> dfs = (GraphIterator<T>) graph.depthFirstSearchIterator(false);
        while (dfs.hasNext())
        {
            dfs.next();
            vertices[i++] = dfs.getLabel();
        }
        
         // Initialize distances as 0
         for (int e : vertices)
        {
            distances.put(v, 0);
            parents.put(v, null);
        }
    }

    @Override
    public Algorithm<Tuple<Integer>> apply() 
    {
        // Keep track of visited
        Set<Integer> visited = new HashSet<>();
        
        // Queue
        Queue<Integer> queue = new ArrayDeque<>();
        u = (int) itU.next();
        queue.add(u);
        visited.add(u);
        
        while(!queue.isEmpty())
        {
            int q = queue.poll();
            GraphIterator<T> it = graph.iterator(q);
            
            for(int e : it.getAllAdjacentVertices())
            {
                if(visited.contains(e))
                {
                   // Update distance for i
                    distances.put(e, distances.get(q) + 1);
                    parents.put(e, q);
                    queue.add(e);
                    visited.add(e);
                }
            }
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        int k = v; 
        while(parents.get(k) != null)
        {
            stack.push(parents.get(k));
        }
        
        while(!stack.isEmpty())
        {
            path.add(stack.pop());
        }
        path.freeze();
        
       return this;
    }
    
}
