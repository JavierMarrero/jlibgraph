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

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * En optimización matemática, el <b>algoritmo Push-Relabel</b> (alternativamente, 
 * <i>algoritmo preflow-push</i>) calcula <b>flujos máximos en una red
 * de flujo</b>.Su nombre proviene de las dos operaciones básicas utilizadas
 en el algoritmo. A lo largo de su ejecución, el algoritmo mantiene un "preflujo" 
 y lo convierte gradualmente en un flujo máximo moviendo el flujo localmente entre
 nodos vecinos utilizando operaciones <i>push</i>, bajo la guía de una red admisible mantenida
 * por operaciones de reetiquetado.<p>
 * Fue diseñado por <i>Andrew V. Goldberg</i> y <i>Robert Tarjan</i>, entre 1986  y 1988.
 * Se considera uno de los algoritmos de flujo máximo más eficientes. El algoritmo
 * genérico tiene una complejidad de tiempo <code>O(V2E)</code> 
 * fuertemente polinómica. Las variantes específicas de los algoritmos logran
 * complejidades de tiempo aún más bajas. Su implementación secuencial posee 
 * <code>O(V<sup>3</sup>)</code> de complejidad temporal.
 * <p>
 * El algoritmo Push-Relabel se ha ampliado para calcular flujos de costo mínimo. 
 * La idea de las etiquetas de distancia ha llevado a un algoritmo de trayectoria
 * aumentada más eficiente, que a su vez se puede incorporar de nuevo en el algoritmo
 * Push-Relabel para crear una variante con un rendimiento empírico aún mayor.
 * 
 * @author Ananda
 * @param <T>
 */
public class PushRelabelAlgorithm<T> extends AbstractAlgorithm<Pair<Float, List<Integer>>> {
    private final int source;
    private final int sink;
    private final WeightedGraph<T> graph;
    private final Graph<T> residualGraph;
    private final float maximumFlow;
    private final GraphIterator<T> iter; 
    
    public PushRelabelAlgorithm (Graph<T> graph, GraphIterator<T> s, GraphIterator<T> t) throws CloneNotSupportedException{
       super(new Pair<Float, List<Integer>>());
       if (!graph.isWeighted()){
            throw new IllegalArgumentException("Attempted to apply Push-Relabel algorithm to an unweighted graph.");
        }
       if (!graph.isDirected()){
            throw new IllegalArgumentException(
                    "Attempted to apply Push-Relabel algorithm to an undirected graph.");
        }
       
       this.graph = (WeightedGraph<T>) graph;
       this.source = s.getLabel();
       this.sink = t.getLabel();
       this.maximumFlow = 0;
       this.residualGraph = graph.duplicate();
       this.iter = residualGraph.iterator(source);
    }
    
    public Algorithm<Pair<Float, List<Integer>>> apply(){
        
        //Paso 1: Inicializar pre-flujo.
        int height = residualGraph.size();
        int sinkHeight = 0;
        int excessFlow = 0;
        Map<Integer, Integer> edgeFlow = new TreeMap<>();
        //Inicializar el flujo de cada borde a 0.
        for(int i : iter.getAdjacentVerticesDepartingSelf()){
            iter.next();
            edgeFlow.put(0, iter.getLabel());
        }
        
        //Paso 2: Actualizar pre-flujo mientras exista alguna operación de inserción o reetiquetado aplicable.
        
        
        return this;
    }
}

