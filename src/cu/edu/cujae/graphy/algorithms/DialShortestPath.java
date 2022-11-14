/*
 * Copyright (C) 2022 Ananda.
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
import cu.edu.cujae.graphy.utils.Pair;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * El algoritmo de Dial, es decir, Dijkstra optimizado para pesos de rango pequeño,
 * emplea una nueva estructura denominada cubo y posee una complejidad de tiempo
 * O(E+WV), donde W es el peso máximo en cualquier borde del gráfico.
 * La distancia máxima entre dos nodos puede tener un máximo de w(V-1).
 *
 *
 * @author Ananda
 * @param <T>
 */
public class DialShortestPath<T> extends AbstractAlgorithm<Map<Integer, Pair<Integer, List<Integer>>>>{
    private final WeightedGraph<T> graph;
    private final GraphIterator<T> iter;
    private final int numberOfNodes;
    private final int sourceNode;
    private final float maxWeight;
    private final Map<Integer, Integer> distances;
    private final Map<Integer, Integer> previous;
    private final ArrayDeque<Integer> queue;
    private final int[] vertex;
    private final Set<Edge> edges;

    public DialShortestPath(WeightedGraph<T> graph, GraphIterator<T> iter, float maxW){
        super(new HashMap<>(graph.size()));
        //debo verificar que ninguna arista posea un peso mayor que maxWeight!!!
        if (!graph.isWeighted()) 
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Dial algorithm to an unweighted graph.");
        }
        this.graph = graph;
        this.iter = (GraphIterator<T>) graph.depthFirstSearchIterator(false);
        this.numberOfNodes = graph.size();
        this.sourceNode = iter.getLabel();
        this.maxWeight = maxW;
        this.distances = new HashMap<>(graph.size(), 0.25f);
        this.previous = new TreeMap<>();
        this.queue = new ArrayDeque<>();
        this.vertex = new int[numberOfNodes];
        this.edges = new HashSet<>();
        
        int i = 0;
        while(iter.hasNext()){
            iter.next();
            vertex[i++] = iter.getLabel();
            edges.addAll(iter.getAllAdjacentEdges());
        }
        
        for(int v : vertex){
            if (graph.isVertexAdjacent(sourceNode, v)){
                distances.put(v, (Integer) iter.getAdjacentEdge(v).getWeight().getValue());
                previous.put(v, iter.getLabel());
            }
            else{
                distances.put(v, Integer.MAX_VALUE);
                previous.put(v, null);
            }
            queue.add(v);
        }
    }
    
    @Override
    public Algorithm<Map<Integer, Pair<Integer, List<Integer>>>> apply(){
        //aquí empieza la potajera
        while(!queue.isEmpty()){
            int current = queue.poll();
            iter.next(current);
            for(Edge e : iter.getAllAdjacentEdges()){
                int j;
                if(iter.getEdgesDepartingSelf().contains(e))
                    j = e.getFinalNode().getLabel();
                else
                    j = e.getStartNode().getLabel();
                
                if(queue.contains(e)){ //arreglar, la cola almacena int, no edge
                    int k = distances.get(current) + (int) e.getWeight().getValue();
                    if (k <= distances.get(j))
                    {
                        distances.put(j, k);
                        previous.put(j, current);
                    }
                }
            }
        }
        
        //esto es común
        Map<Integer, Pair<Integer, List<Integer>>> result = getResult();
        for (int i : distances.keySet())
        {
            result.put(i, new Pair<>(distances.get(i), makeShortestPathSequence(sourceNode, i)));
        }

        return this;
    }
    
    private List<Integer> makeShortestPathSequence(int source, int target)
    {
        LinkedList<Integer> S = new LinkedList<>();
        int u = target;

        if (previous.get(u) != null)
        {
            while (previous.get(u) != null)
            {
                S.push(u);
                u = previous.get(u);
            }
        }
        S.push(source);
        return S;
    }
}
