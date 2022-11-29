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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Algoritmo para resolve el problema de la cobertura del vértice.
 * La cobertura del vértice de un grafo no dirigido es un subconjunto de sus 
 * vértices para los que para cada arista (u,v) de un grafo, o 'u' o 'v' está
 * en la cobertura. Aunque el nombre es cobertura de vértice, el set cubre 
 * todas las aristas de un grafo determminado. 
 * 
 * @author Amanda Mendez 
 * @param<T>
 */
public class MinimumVertexCoverAlgorithm<T> extends AbstractAlgorithm<Tuple<Integer>>
{
    private final Graph<T> graph;
    private final Set<Integer> visited;

    public MinimumVertexCoverAlgorithm(Graph<T> graph)
    {
        super(new HashTuple());
        
        // Initialize fields
        this.graph = graph;
        this.visited = new HashSet<Integer>();
        
    }
    
    @Override
    public Algorithm<Tuple<Integer>> apply() 
    {
        GraphIterator<?> it;
        
        // Consider all edges one by one 
        for(int u: graph.getLabels())
        {
            if(!visited.contains(u))
            {
                it = graph.iterator(u);
                for(int i : it.getAllAdjacentVertices())
                {
                    int v = it.getLabel();
                    if(!visited.contains(v))
                    {
                        visited.add(v);
                        visited.add(u);
                        break;
                    }
                } 
            }
        }
        
        // Construction of result 
        Tuple<Integer> result = new HashTuple();
        
        for(int j: graph.getLabels())
        {
            if(visited.contains(j))
            {
                result.add(j);
            }
        }
        
        result.freeze();
        
        return this;
    }
    
}
