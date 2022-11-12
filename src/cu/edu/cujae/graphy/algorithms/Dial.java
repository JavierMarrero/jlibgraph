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

import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Dial<T> extends AbstractAlgorithm<Map<Integer, Pair<Integer, List<Integer>>>>
{
    private final WeightedGraph<T> graph;
    private final GraphIterator<T> iter;
    private final int vertex;
    private final int label;

    public Dial(WeightedGraph<T> graph, GraphIterator<T> iter){
        super(new HashMap<>(graph.size()));
        if (!graph.isWeighted())
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Dial algorithm to an unweighted graph.");
        }
        this.graph = graph;
        this.iter = iter;
        this.vertex = graph.size();
        this.label = iter.getLabel();
    }
    
    @Override
    public Algorithm<Map<Integer, Pair<Integer, List<Integer>>>> apply(){
        
        return this;
    }
}
