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
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Set;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * El algoritmo de Kosaraju está basado en DFS utilizado para encontrar componentes
 * fuertemente conexos (SCC) en un grafo.Si uno es capaz de alcanzar un vértice v
 * a partir del vértice u, entonces uno debería ser capaz de alcanzar el vértice u
 * a partir de v y si se cumple, puede decirse que v y u están en un subgrafo
 * fuertemente conectados.
 *
 * @author Ananda
 * @param <T>
 */
public class KosarajuAlgorithm<T> extends AbstractAlgorithm<List<Set<Integer>>>
{
    private final Graph<T> graph;
    private final GraphIterator<T> iter;
    private int source;

    public KosarajuAlgorithm(GraphIterator<T> iter, Graph<T> graph, int v)
    {
        super(new LinkedList<>());
        if (!graph.isDirected())
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Kosaraju algorithm to an undirected graph.");
        }
        this.graph = graph;
        this.iter = (GraphIterator<T>) graph.depthFirstSearchIterator(iter.getLabel(), false);
        this.source = v;
    }
    
    @Override
    public Algorithm<List<Set<Integer>>> apply()
    {
        //Paso 1: crear pila y realizar DFS al grafo, guardando sus labels.
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while(iter.hasNext()){
            source = iter.getLabel();
            stack.push(source);
            iter.next();
        }
        try {
            //Paso 2: obtener grafo traspuesto.
            Graph<T> transposedGraph = graph.duplicate();
            for(int v : transposedGraph.getLabels()){
                GraphIterator<T> it = (GraphIterator<T>) graph.iterator(v);
                for(Edge e : it.getEdgesDepartingSelf()){
                    e.reverseApparentDirection();
                }
            }
            //Paso 3: obtener SCC del vértice tomado como origen.
            Set<Integer> visited = new TreeSet<>();
            while(!stack.isEmpty()){
                int v = (int)stack.pop();
                //incompleto.
                GraphIterator<T> iterT = transposedGraph.iterator(v);
                while(iterT.hasNext()){
                    if(!visited.contains(iterT.getLabel())){
                        visited.add(iter.getLabel());
                    }   
                }
                if(!visited.add(v)){
                    transposedGraph.depthFirstSearchIterator(v, true);
                }   
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(KosarajuAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    
 }
