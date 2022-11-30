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
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import java.util.LinkedList;

/**
 * El <b>algoritmo <i>MST</i> de tiempo lineal esperado</b> es aleatorio y se emplea para
 * calcular el <b>bosque de expansión mínimo de un grafo ponderado sin vértices aislados</b>.
 * Fue desarrollado por <i>David Karger, Philip Klein</i> y <i>Robert Tarjan</i>. <p>
 * El algoritmo se basa en técnicas del algoritmo de Borůvka junto con un algoritmo 
 * para verificar un árbol de expansión mínimo en tiempo lineal.Combina 
 * los paradigmas de diseño de algoritmos de divide y vencerás, algoritmos codiciosos
 * y algoritmos aleatorios para lograr el rendimiento lineal esperado.<p>
 * Los algoritmos deterministas que encuentran el árbol de expansión mínimo incluyen 
 * <i>el algoritmo de Prim, el algoritmo de Kruskal, el algoritmo de eliminación 
 * inversa y el algoritmo de Borůvka</i>.
 * <p>El peor tiempo de ejecución es equivalente al tiempo de ejecución del <i>algoritmo
 * de Borůvka</i>. Esto ocurre si se agregan todas las aristas al subproblema izquierdo 
 * o derecho en cada invocación. En este caso, el algoritmo es idéntico al algoritmo 
 * de Borůvka que se ejecuta en <code>O(min{n<sup>2</sup>, m log n})</code> en un grafo con
 * <b>n</b> vértices y <b>m</b> aristas.
 * <ul><li>Entrada: Grafo <code>G</code> sin vértices aislados.</li>
 * <li>Salida: MST de <code>G´</code> y los bordes contraídos de los escalones de Boruvka.</li></ul>
 * 
 * @author Ananda
 * @param <T>
 */
public class ExpectedLinearTimeMST<T> extends AbstractAlgorithm<WeightedGraph<T>> {
    private final WeightedGraph<T> graph;
    
    public ExpectedLinearTimeMST(Graph<T> graph){
        super(GraphBuilders.makeSimpleWeightedGraph(false));
        if(!graph.isWeighted()){
            throw new IllegalArgumentException("Attempted to apply Expected Linear Time MST algorithm to an unweighted graph.");
        }
        if(graph.isDirected()){
            throw new IllegalArgumentException("Attempted to apply Expected Linear Time MST algorithm to a directed graph.");
        }
        LinkedList<Integer> aux = (LinkedList<Integer>) (new IsolatedVertices(graph)).apply().get();
        if(!aux.isEmpty()){
            throw new IllegalArgumentException("Attempted to apply Expected Linear Time MST algorithm to an isolated vertices in graph.");
        }
        this.graph = (WeightedGraph<T>) graph;
    }
    
    @Override
    public Algorithm<WeightedGraph<T>> apply(){
        if(graph.size()== 0){
            //devolver bosque vacío.
        }
        //paso 1: crear grafo contraído G´ ejecutando dos pasos de boruvka en G
        //paso 2: crear subgrafo H seleccionando cada arista en G´ con probabilidad 1/2. aplique recursivamente 
        //el algoritmo a H para obtener su bosque ms F.
        //paso 3: elimine todas las aristas pesadas de F a G´utilizando un algoritmo de verificación mst
        //paso 4: aplique recursivamente el algoritmo a G´ para obtener su mst
        
        return this;
    }
}
