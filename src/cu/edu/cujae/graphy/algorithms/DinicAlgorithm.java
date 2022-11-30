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
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Dado un grafo que representa una red de flujo donde cada borde tiene una capacidad
 * y dos vértices <i>fuente 's'</i> y <i>sumidero 't'</i>, encontrar el 
 * <b>flujo máximo</b> posible de s a t con las siguientes restricciones:<ul>
 * <li>El flujo en un borde no excede la capacidad dada del borde.</li>
 * <li>Un flujo entrante es igual a un flujo saliente para cada vértice excepto s y t.</li>
 * </ul>
 * El <b>algoritmo de Dinic</b> posee un tiempo polinómico para la computación de
 * un flujo maximal en una red de flujo, concebida en 1970 por <i>Yefim Dinitz</i>.Es ejecutado
 * en un tiempo de <code>O(V<sup>2</sup>E)</code> y está basado en el
 <i>algoritmo de Edmonds-Karp</i>.<p>La introducción de los conceptos <i>nivel de grafo</i> y
 * <i>bloqueo de flujo</i>,
 * es lo que define el rendimiento de este algoritmo.
 * <ul><li>Entrada: una red <code>G=((V,E),c,s,t)</code>.</li>
 * <li>Salida: un flujo <code>s-t</code> de valor <code>f</code> maximizado.</li></ul>
 * 
 * 
 * @author Ananda
 * @param <T>
 */
public class DinicAlgorithm<T> extends AbstractAlgorithm<Pair<Float, List<Integer>>> {
    private final WeightedGraph<T> graph; 
    private final WeightedGraph<T> residualGraph;
    private final int source; //fuente
    private final int sink; //sumidero
    private float maximumFlow;
    private final Map<Integer, Integer> path; 
    
    public DinicAlgorithm(Graph<T> graph, GraphIterator<T> source, GraphIterator<T> destiny) throws CloneNotSupportedException{
       super(new Pair<Float, List<Integer>>());
       if (!graph.isWeighted()){
            throw new IllegalArgumentException("Attempted to apply Dinic algorithm to an unweighted graph.");
        }
       if (!graph.isDirected()){
            throw new IllegalArgumentException(
                    "Attempted to apply Dinic algorithm to an undirected graph.");
        }
       
       this.graph = (WeightedGraph<T>) graph;
       this.source = source.getLabel();
       this.sink = destiny.getLabel();
       this.maximumFlow = 0;
       this.path = new HashMap<>();
       this.residualGraph = (WeightedGraph<T>) graph.duplicate();
        
    }
    
    @Override
    public Algorithm<Pair<Float, List<Integer>>> apply(){
        GraphIterator<T> iter = (GraphIterator<T>) residualGraph.breadthFirstSearchIterator(false);
        Set<Edge> unused = new TreeSet<>();
        int level = 0;
        Set<Integer> levels = new TreeSet<>();
        for(Edge e : iter.getAllAdjacentEdges()){
            iter.next();
            level++;
            levels.add(level);
            if(!e.getLabel().equals(source)){
               unused.add(e);
            }
        }
        
        
        /*verifique si es posible más flujo.
        Si no es posible tener más flujo, regrese
        Envíe múltiples flujos en residualGraph usando el grafo de nivel hasta que se alcance el flujo de bloqueo.
        Aquí, usar el gráfico de nivel significa que, en cada flujo, los niveles de los nodos de ruta 
        deben ser 0, 1, 2 ... (en orden) de S a T.
        Flujo de bloqueo si no se puede enviar más flujo usando el gráfico de nivel, 
        es decir, no existe más camino s-t tal que los vértices de camino tengan niveles actuales 0, 1, 2 ... en orden. 
        */
        
        return this;
        
    }
}
