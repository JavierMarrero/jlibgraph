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
import cu.edu.cujae.graphy.core.Weight;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Un árbol de expansión significa que todos los vértices deben estar conectados.
 * Por lo tanto, los dos subconjuntos disjuntos de vértices deben estar conectados
 * con el borde de peso mínimo para convertirlo en un árbol de expansión mínimo (MST).<p>
 * El <b>algoritmo de Boruvka</b> es considerado voraz y utilizado para encontrar
 * el <b>árbol recubridor mínimo</b> en un grafo ponderado, en el que todos sus 
 * arcos poseen distinto peso. Fue publicado por primera vez en 1926, por 
 * <i>Otakar Boruvka</i> como método eficiente para construir la red eléctrica 
 * de Moravia. También es conocido como <i>algoritmo de Sollin</i>.
 * <p>Su complejidad temporal es <code>O(E log(V))</code>, donde E es el número de 
 * arcos y V el número de vértices del grafo.
 * <p>Existen algoritmos similares para la obtención de árboles de expansión mínimo, 
 * como es el caso del <i>algoritmo de Kruskal</i> y el <i>algoritmo de Prim</i>.
 * 
 * @author Ananda
 * @param <T>
 */
public class BoruvkaMinimalTree<T> extends AbstractAlgorithm<WeightedGraph<T>> {
    private final WeightedGraph<T> graph;
    
    public BoruvkaMinimalTree(Graph<T> graph){
        super(GraphBuilders.makeSimpleWeightedGraph(false));

        if (!graph.isWeighted()){
            throw new IllegalArgumentException("Attempted to apply Boruvka algorithm to an unweighted graph.");
        }
        if (graph.isDirected()){
            throw new IllegalArgumentException("Attempted to apply Boruvka algorithm to a directed graph.");
        }
        this.graph = (WeightedGraph<T>) graph;
    }
    
    @Override
    public Algorithm<WeightedGraph<T>> apply(){
        //asumiendo que el grafo es conexo.
        GraphIterator<T> iter = graph.randomIterator();
        WeightedGraph<T> mst = null;
        //Paso 1: Inicializar vértices como componentes individuales.
        LinkedList<Component> listOfComponents = new LinkedList<>();
        while(iter.hasNext()){
            iter.next();
            listOfComponents.add(new Component(iter.getLabel(), graph));
        }
        //Paso 2: mientras existan componentes, encontrar la arista de menor peso y agregarla al MST.
        while(!listOfComponents.isEmpty()){
            Component current = listOfComponents.poll();
            int lesserWeight = current.getLesserWeightVertex();
            
        }
        
        //Paso 3: devolver MST.
        return this;
    }
    
    //clase creada con el objetivo de manejar más fácilmente los componentes.
    //sería muy parecido a un set de vértices.
    private final class Component extends TreeSet<Integer>{
        public Component(int u, WeightedGraph<T> graph){
            super();
            add(u);
        }
        //método para obtener el menor peso.
        private int getLesserWeightVertex(){
            int result = -1;
            int lesser = Integer.MAX_VALUE;
            Iterator<Integer> setIterator = this.iterator();
            GraphIterator<T> iter = graph.randomIterator();
            while(setIterator.hasNext()){
                int current = setIterator.next();
                iter.next(current);
                Weight<Integer> weight = null;
                
                for(Edge departing : iter.getEdgesDepartingSelf()){
                    weight = (Weight<Integer>) departing.getWeight();
                    if(weight.getValue() < lesser){
                        lesser = weight.getValue();
                        result = departing.getFinalNode().getLabel();
                    }
                }
                
                for(Edge arriving : iter.getEdgesArrivingSelf()){
                    weight = (Weight<Integer>) arriving.getWeight();
                    if(weight.getValue() < lesser){
                        lesser = weight.getValue();
                        result = arriving.getFinalNode().getLabel();
                    }
                }
            }
            
            return result;
        }
    }
}
