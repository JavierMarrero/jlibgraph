/*
 * Copyright (C) 2022 Amaya.
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
import java.util.Collection;

/**
 * This algorithm checks if a given graph is a complete digraph. A complete digraph
 * is a directed graph in which every pair of distinct vertices is connected by 
 * a pair of unique edges (one in each direction). A lone vertex is also a complete
 * digraph.
 * 
 * @author Amaya D. Fuentes
 * @param <T>
 */
public class CompleteDigraphDetection<T> extends AbstractAlgorithm<Boolean> {
    
    private int V;
    private GraphIterator<T> iter;
    
    public CompleteDigraphDetection(Graph<T> graph) {
        
        super(Boolean.TRUE);
        
        if(graph.size() == 0) {
            throw new IllegalArgumentException("The graph has no vertices.");
        }
        if(!graph.isDirected()) {
            throw new IllegalArgumentException("The graph is undirected. For undirected graphs, use CompleteGraphDetection.");
        }
        
        //initialize class variables
        this.V = graph.size();
        this.iter = (GraphIterator<T>) graph.depthFirstSearchIterator(true);
        
    }

    @Override
    public Algorithm<Boolean> apply() {
        
        boolean stop = false;
        
        while(iter.hasNext() && !stop) {
            iter.next();
            
            Integer current = iter.getLabel();
            Collection<Integer> arriving = iter.getAdjacentVerticesArrivingSelf();
            Collection<Integer> departing = iter.getAdjacentVerticesDepartingSelf();
            
            if(arriving.size() != V-1 && departing.size() != V-1 || arriving.contains(current) || departing.contains(current)) {
                stop = true;
                setResult(Boolean.FALSE);
            }   
        }
        
        return this;
    }
    
}
