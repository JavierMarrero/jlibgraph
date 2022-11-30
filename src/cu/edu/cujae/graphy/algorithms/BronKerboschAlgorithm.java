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
import cu.edu.cujae.graphy.utils.HashTuple;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * The <b>Bron-Kerbosch algorithm</b> is an enumeration algorithm for finding all 
 * maximal cliques in an undirected graph. That is, it lists all the subsets of 
 * vertices with the properties that each pair of vertices in one of the listed
 * subsets is connected by an edge, and no listed subset can have any vertices
 * added to it while preserving its complete connectivity. An isolated vertex is 
 * not considered a maximal clique.
 * 
 * The variant of the algorithm implemented is the one that involves a "pivot element" 
 * for efficiency purposes.
 * 
 * @author Amaya D. Fuentes
 * @param <T>
 */
public class BronKerboschAlgorithm<T> extends AbstractAlgorithm<Collection<Collection<Integer>>>
{
    private Set<Integer> vertices;
    private HashMap<Integer, Collection<Integer>> neighbors;

    public BronKerboschAlgorithm(Graph<T> graph) {
        
        super(new HashTuple<Collection<Integer>>());
        
        if(graph.isDirected()) {
            throw new IllegalArgumentException("The graph is directed");
        }
        
        if(graph.size() == 0) {
            throw new IllegalArgumentException("The graph is empty.");
        }
            
        //initialize class fields
        this.vertices = new TreeSet<>();
        this.neighbors = new HashMap<>();

        //get the vertices and their neighbors, excluding isolated vertices
        GraphIterator<T> dfsIterator = (GraphIterator<T>) graph.depthFirstSearchIterator(true);
        while(dfsIterator.hasNext()) {
            dfsIterator.next();
            if(!dfsIterator.getAllAdjacentVertices().isEmpty()) {
                vertices.add(dfsIterator.getLabel());
                neighbors.put(dfsIterator.getLabel(),dfsIterator.getAllAdjacentVertices());
            }
        }
    }
    
    @Override
    public Algorithm<Collection<Collection<Integer>>> apply() {
    
        Set<Integer> P = vertices;
        Set<Integer> R = new TreeSet<>();
        Set<Integer> X = new TreeSet<>();

        maximalCliques(R, P, X);

        return this;
    }
    
    /**
     * Recursive method for finding all maximal cliques (Bron-Kerbosch algorithm
     * with pivot).
     * 
     * @param R - The set to fill with the vertices that form a maximal clique
     * @param P - The set of candidate vertices to be added to R
     * @param X - The set of processed vertices or belonging to a maximal clique
     */
    private void maximalCliques(Set<Integer> R, Set<Integer> P, Set<Integer> X) {
        
        if(P.isEmpty() && X.isEmpty()) {
            Collection<Collection<Integer>> result = getResult();
            result.add(R);
        }
        else {
            Set<Integer> P1 = new TreeSet<>(P);
            Integer u = findPivot(unite(P1, X));
            for(Integer v: remove(P, u)) {    
                maximalCliques(unite(R, v), intersect(P1, v), intersect(X, v));
                P1.remove(v);
                X.add(v);
            }
        }
    }
    
    
    /**
     * Method for finding the element of a set with the higher degree in the 
     * DFS search tree (the most neighbors)
     * 
     * @param set - The set where the pivot will be found
     * @return The element of the set with the highest degree
     */
    private Integer findPivot(Set<Integer> set) {
        Integer pivot = null;
        int maxDegree = 0;
        for(Integer vertex: set) {
            int degree = neighbors.get(vertex).size();
            if(degree >= maxDegree) {
                maxDegree = degree;
                pivot = vertex;
            }
        }
        return pivot;
        
    }
    
    
    /**
     * Method to get the intersection of a set with the neighbors of a vertex
     * without altering the original set.
     * 
     * @param set 
     * @param vertex 
     * @return The set corresponding to the intersection
     */
    private Set<Integer> intersect(Set<Integer> set, Integer vertex) {
        Set<Integer> intersection = new TreeSet<>(set);
        intersection.retainAll(neighbors.get(vertex));
        return intersection;
    }
    
    /**
     * Method to get the union of two sets without altering them
     * 
     * @param set1
     * @param set2
     * @return The set corresponding to the union of set1 and set2
     */
    private Set<Integer> unite(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> union = new TreeSet<>(set1);
        union.addAll(set2);
        return union;
    }
    
    /**
     * Method to get the union of a set and the neighbors of a vertex without
     * altering the original set
     * 
     * @param set
     * @param vertex
     * @return The set corresponding to the union
     */
    private Set<Integer> unite(Set<Integer> set, Integer vertex) {
        Set<Integer> union = new TreeSet<>(set);
        union.add(vertex);
        return union;
    }
     
    /**
     * Method to get the difference of a set with the neighbors of a vertex 
     * without altering the original set
     * 
     * @param set
     * @param vertex
     * @return The set corresponding to the difference
     */
    private Set<Integer> remove(Set<Integer> set, Integer vertex) {
        Set<Integer> removal = new TreeSet<>(set);
        removal.removeAll(neighbors.get(vertex));
        return removal;
    }
}
