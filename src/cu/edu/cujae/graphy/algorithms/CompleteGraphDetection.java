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

/**
 * This algorithm checks if a given graph is a complete graph. A complete graph 
 * is a simple undirected graph where all pairs of vertices are connected by an 
 * edge. A complete graph <i>n</i> vertices has <i>n(n-1)/2</i> edges, since each 
 * of its vertices has as degree of <i>n-1</i>. A lone vertex is also a complete 
 * graph.
 * 
 * @author Amaya D. Fuentes
 * @param <T>
 */
public class CompleteGraphDetection<T> extends AbstractAlgorithm<Boolean> {

    private GraphIterator<T> iter;
    private int V;
    
    public CompleteGraphDetection(Graph<T> graph) {   
        
        super(Boolean.TRUE);
        
        if(graph.size() == 0) {
            throw new IllegalArgumentException("The graph has no vertices.");
        }
        if(graph.isDirected()) {
            throw new IllegalArgumentException("The graph is directed. For directed graphs, use CompleteDigraphDetection.");
        }
        
        //initialize class variables
        this.iter = (GraphIterator<T>) graph.depthFirstSearchIterator(true);
        this.V = graph.size();
    }
    
    @Override
    public Algorithm<Boolean> apply() {
       
        boolean stop = false;
        
        while(iter.hasNext() && !stop) {
            iter.next(); 
            if(iter.getAllAdjacentEdges().size() != V - 1 || iter.isAdjacent(iter.getLabel())) {
                setResult(Boolean.FALSE);
                stop = true;
            }
        }
        
        return this;
    }
    
}
