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
import java.util.Map;
import java.util.TreeMap;

/**
 * This algorithm checks if a given graph is bipartite. A bipartite graph is a 
 * graph whose vertices can be divided into two disjoint and independent sets, 
 * such that no two vertices within the same set are adjacent. Equivalently, a 
 * graph that has an odd-lenght cycle is not bipartite. This kind of graph can
 * contain isolated vertices.
 * 
 * @author Amaya D. Fuentes
 * @param <T>
 */
public class BipartiteGraphDetection<T> extends AbstractAlgorithm<Boolean> {
    
    private Map<Integer, Integer> colors;
    private int V;
    private GraphIterator<T> bfsIterator;
    
    public BipartiteGraphDetection(Graph<T> graph) {
        
        super(Boolean.TRUE);
        
        if(graph.size() == 0) {
            throw new IllegalArgumentException("The graph has no vertices.");
        }
        
        //initialize class variables
        this.V = graph.size();
        this.bfsIterator = (GraphIterator<T>) graph.depthFirstSearchIterator(true);
        this.colors = new TreeMap<>();
        
        //mark all vertices as not colored
        for(Integer lbl: graph.getLabels()) {
            colors.put(lbl, -1);
        }
    }

    @Override
    public Algorithm<Boolean> apply() {
        
        //advance the iterator and color the root of the BFS search tree
        bfsIterator.next();
        colors.put(bfsIterator.getLabel(), 1);
    
        //run while there are more vertex in the BFS search tree 
        while(bfsIterator.hasNext()) {
            
            Integer current = bfsIterator.getLabel();
            
            //if there's a self loop return false
            if(bfsIterator.getAllAdjacentVertices().contains(current)) {
                setResult(Boolean.FALSE);
                break;
            }
            else {
                //for each vertex in the graph
                for(Integer lbl: bfsIterator.getAllAdjacentVertices()) {

                    //the adjacent vertex is not colored assign it the alternate color
                    if(colors.get(lbl) == -1) {
                        colors.put(lbl, 1-colors.get(current));
                    }
                    //the adjacent is colored the same as current mark conflict as true and break the for cycle
                    else if(colors.get(lbl) == colors.get(current)) {
                        setResult(Boolean.FALSE);
                        break;
                    }
                }
            }
            bfsIterator.next();
            
        }
        return this;
    }
}
