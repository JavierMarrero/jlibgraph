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
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * The <i>fringe search</i> agorithm is is a graph search algorithm that finds 
 * the least-cost path from a given initial node to one goal node.
 * ONLY IMPLEMENTED FOR DIRECTED GRAPHS SO FAR!
 *
 * @author Amaya D. Fuentes
 * @param <T>
 */
public class FringeSearchShortestPath<T> extends AbstractAlgorithm<Collection<Integer>> {
    
     private WeightedGraph<T> graph;
     
     private Map<Integer, Integer> g;
     private Map<Integer, Integer> h;
     private Map<Integer, Integer> parents;
     
     private int start;
     private int goal;
     
     private LinkedList<Integer> fringe;

    public FringeSearchShortestPath(WeightedGraph<T> graph, int start, int goal) {
        
        super(new LinkedList<>());
        
        if(!graph.isWeighted()) {
            throw new IllegalArgumentException("Attempted to apply a shortest path algorithm to an unweighted graph.");
        }
        if(graph.isEmpty()) {
            throw new IllegalArgumentException("The graph has no vertices.");
        }
        if(!graph.getLabels().contains(start) || !graph.getLabels().contains(goal)) {
            throw new IllegalArgumentException("Nodes with the labels "+start+" and/or "+goal+" are not a part of the graph.");
        }
        if(!graph.isDirected()) {
            throw new IllegalArgumentException("This algorithm is still to be modified to support undirected graphs.");
        }
        
        //Initialize the class variables
        this.graph = graph;
        this.start = start;
        this.goal = goal;
        this.g = new HashMap<>();
        this.h = new HashMap<>();
        this.parents = new HashMap<>();
        this.fringe = new LinkedList<>();
        
        GraphIterator<T> dfs = (GraphIterator<T>) graph.depthFirstSearchIterator(start, Boolean.FALSE);
        while (dfs.hasNext())
        {
            dfs.next();
            int v = dfs.getLabel();
            g.put(v, Integer.MAX_VALUE);
            h.put(v, Integer.MAX_VALUE);
            parents.put(v, null);
            if(dfs.getAdjacentVerticesDepartingSelf().isEmpty()) {
                fringe.add(v);
            }
        }
        
        g.put(start, 0);
        h.put(goal, 0);
        initializeCostsFromStart(this.start);
        initializeCostsToGoal(this.goal);        
    }
    
    @Override
    public Algorithm<Collection<Integer>> apply() {
        
        int flimit = h.get(start);
        boolean found = false;
        
        while(!found && !fringe.isEmpty()) {
            
            int fmin = Integer.MAX_VALUE;
            
            for(Integer node: fringe) {
                int f = g.get(node) + h.get(node);
                if(f > flimit) {
                    fmin = Math.min(f, fmin);
                    continue;
                }
                if(node == goal) {
                    found = true;
                    break;
                }
                
                GraphIterator<T> it = (GraphIterator<T>) graph.depthFirstSearchIterator(node, Boolean.FALSE);
                
                for(Integer adj: it.getAdjacentVerticesDepartingSelf()) {
                        g.put(adj,g.get(adj) + getCostForAdjacents(node, adj));
                        if(fringe.contains(adj)) { 
                            fringe.remove(adj);
                        }
                        fringe.add(adj);
                }
                fringe.remove(node);
            }
            flimit = fmin;
        }
        
        if(found) {
            Collection<Integer> result = getResult();
            reversePath(goal, result);
        }
        
        return this;
    }
 
    /**
     * Recursive function that Builds the shortest path from start to goal in 
     * reverse, adding the corresponding nodes to the result.
     * 
     * @param node
     * @param result 
     */
    private void reversePath(Integer node,  Collection<Integer> result) {
        if(parents.get(node) != null) {
            reversePath(parents.get(node), result);   
        }
        result.add(node);
    }

    
    /**
     * Recursive method that initializes the HashMap <i>g</i> with  the cost of 
     * the search path from the first node to the current node.
     * 
     * @param start - the label of the start node
     */
    private void initializeCostsFromStart(int start) {
        
        GraphIterator<T> it = graph.iterator(start);
        
        for(Edge e: it.getEdgesDepartingSelf()) {
            
            int v = e.getFinalNode().getLabel();
            int weight = (int) e.getWeight().getValue();
            
            int gStart = g.get(it.getLabel());
            int gv = g.get(v);

            if (gv > gStart + weight)
            {
                g.put(v, gStart + weight);
                parents.put(v, it.getLabel());
            }
            
            initializeCostsFromStart(v);
        }
    }
    
    /**
     * Recursive method that initializes the HashMap <i>h</i> with  the cost of 
     * the search path from the current node to the goal.
     * 
     * @param goal - the label of the goal node
     */
    private void initializeCostsToGoal(int goal) {
        
        GraphIterator<T> it = graph.iterator(goal);
        
        for(Edge e: it.getEdgesArrivingSelf()) {
            
            int v = e.getStartNode().getLabel();
            int weight = (int) e.getWeight().getValue();
            
            int hEnd = h.get(it.getLabel());
            int hv = h.get(v);
            
            if(hv > hEnd + weight) {
                h.put(v, hEnd + weight);
            }
            
            initializeCostsToGoal(v);
        }
    }
    
    /**
     * Gets the cost for two adjacent nodes of the graph 
     * 
     * @param u - the start node
     * @param v - the final node
     * @return The cost (weight) of the edge connecting these nodes
     */
    private int getCostForAdjacents(int u, int v) {
        
        int weight = 0;
        
        GraphIterator<T> it = graph.iterator(u);
        for(Edge e: it.getAllAdjacentEdges()) {
            if(e.getFinalNode().getLabel() == v) {
                weight = (int) e.getWeight().getValue();
                break;
            }
        }
        
        return weight;
    }
}
