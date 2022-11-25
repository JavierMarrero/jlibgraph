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
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * This algorithm checks if a given undirected graph is biconnected.
 * 
 * @author Amaya D. Fuentes;
 */
public class BiconnectivityDetection<T> extends AbstractAlgorithm<Boolean> {
    
    private int V;
    private GraphIterator<T> iterator;

    public BiconnectivityDetection(Graph<T> graph) {
        super(Boolean.FALSE);
        
        if(graph.isDirected()) {
            throw new IllegalArgumentException("The graph is directed.");
        }
        
        this.V = graph.size();
        this.iterator = graph.randomIterator();
    }
    
    @Override
    public Algorithm<Boolean> apply() {
        
        Set<Integer> visited = new TreeSet<>();
        
        HashMap<Integer, Integer> parent = new HashMap<>();
        
        int discTime = 0;
        HashMap<Integer, Integer> disc = new HashMap<>();
        HashMap<Integer, Integer> low = new HashMap<>();
        
        boolean articulationPoint = hasArticulationPoint(iterator, visited, parent, disc, low, discTime);
        
        if(!articulationPoint && visited.size() == V) {
            setResult(true);
        }
        
        return this;
    }
    
    
    
    /**
     * This recursive function checks if a graph has an articulation point through DFS search
     * 
     * @param iterator - The graph's iterator
     * @param visited - Contains every visited vertex
     * @param parent - The key is the current node's label and the value is its parent's in the DFS tree
     * @param low - The key is the node's label and the value is the discovery time of it earliest visited adjacent node
     * @param disc - The key is the current node's label and the value is its discovery time 
     * @param discTime - The discovery time for the current node
     * @return true if there is an articulation point, otherwise false
     */
    private boolean hasArticulationPoint(GraphIterator<T> iterator, Set<Integer> visited, HashMap<Integer, Integer> parent, HashMap<Integer, Integer> low, HashMap<Integer, Integer> disc, int discTime) {
        
        int children = 0;
        int currentLabel = iterator.getLabel();
        visited.add(currentLabel);
        discTime++;
        disc.put(currentLabel, discTime);
        low.put(currentLabel, discTime);
        
        for(Integer adjLabel: iterator.getAllAdjacentVertices()) {
            iterator.next(adjLabel);
            
            if(!visited.contains(adjLabel)) {
                children++;
                parent.put(adjLabel, currentLabel);
                
                if(hasArticulationPoint(iterator, visited, parent, low, disc, discTime)){
                    return true;
                }
                
                low.put(currentLabel, Math.min(low.get(currentLabel), low.get(adjLabel)));
                
                if(!parent.containsKey(currentLabel) && children > 1) {
                    return true;
                }
                
                if(parent.containsKey(currentLabel) && low.get(adjLabel) >= disc.get(currentLabel)) {
                    return true;
                }
            }

            else if(parent.get(currentLabel) != adjLabel) {
                low.put(currentLabel, Math.min(low.get(currentLabel), disc.get(adjLabel)));
            }
        } 
        
        return false;
    }
    
    
}
