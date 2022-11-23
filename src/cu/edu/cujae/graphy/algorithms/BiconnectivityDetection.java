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

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This algorithm checks if a given undirected graph is biconnected
 * 
 * @author Amaya D. Fuentes
 */
public class BiconnectivityDetection<T> extends AbstractAlgorithm<Boolean> {

    private int V;
    private GraphIterator<T> iterator;
    private Graph<T> duplicateGraph;
    
    public BiconnectivityDetection(Graph<T> graph) {
        super(Boolean.FALSE);
        
         if(graph.isDirected()) {
            throw new IllegalArgumentException("The graph is directed.");
        }
        
        this.iterator = (GraphIterator<T>) graph.depthFirstSearchIterator(false);
        this.V = graph.size();
        try {
            this.duplicateGraph = graph.duplicate();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(BiconnectivityDetection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Algorithm<Boolean> apply() {
        
        boolean articulationPoint = false;
        int count = 0;
        
        while(iterator.hasNext() && !articulationPoint) {
            iterator.next();
            count++;
            
            //get current vertex information and remove it
            int label = iterator.getLabel();
            /*Collection<Integer> outAdjacents = iterator.getAdjacentVerticesDepartingSelf();
            Collection<Integer> inAdjacents = iterator.getAdjacentVerticesArrivingSelf();
            T vertexInfo = duplicateGraph.remove(label);*/
            
            Collection<Edge> outEdges = iterator.getEdgesDepartingSelf();
            Collection<Edge> inEdges = iterator.getEdgesArrivingSelf();
            for(Edge outEdge: outEdges) {
                duplicateGraph.disconnect(label, (int) outEdge.getLabel());
            }
            for(Edge inEdge: inEdges) {
                duplicateGraph.disconnect(label, (int) inEdge.getLabel());
            }
            
            //check if the current node is an articulation point
            if(!(boolean)new ConnectivityDetection(duplicateGraph).apply().get()) {
                articulationPoint = true;
            }
            
            //add the previously removed vertex to the graph
            /*duplicateGraph.add(label, vertexInfo);
            for(Integer adj: outAdjacents) {
                duplicateGraph.connect(label, adj);
            }
            for(Integer adj: inAdjacents) {
                duplicateGraph.connect(adj, label);
            }*/
            
            for(Edge outEdge: outEdges) {
                duplicateGraph.connect(label, (int) outEdge.getLabel());
            }
            for(Edge inEdge: inEdges) {
                duplicateGraph.connect((int) inEdge.getLabel(), label);
            }
        }
        
        if(!articulationPoint && count == V) {
            setResult(true);
        }
        
        return this;
    }
    
   
}
