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
 * This algorithm checks if a given graph, whether directed or undirected, is 
 * connected. A connected graph is a graph where there is a path from a  vertex 
 * to any other point in the vertex. That is, a connected graph can't have any
 * disconnected components or isolated vertices.
 * 
 * @author Amaya D. Fuentes
 * @param <T>
 */
public class ConnectivityDetection<T> extends AbstractAlgorithm<Boolean> {

    private GraphIterator<T> iterator;
    private int V;
    
    public ConnectivityDetection(Graph<T> graph) {
        
        super(Boolean.FALSE);
        
        if(graph.size() == 0) {
            throw new IllegalArgumentException("The graph has no vertices.");
        }
        
        this.iterator = (GraphIterator<T>) graph.depthFirstSearchIterator(false);
        this.V = graph.size();
        
    }
    
    @Override
    public Algorithm<Boolean> apply() {
       
        int count = 0;
        while(iterator.hasNext()) {
            iterator.next();
            count++;
        }
        
        if(count == V) {
            setResult(Boolean.TRUE);
        }
        
        return this;
    }
    
}
