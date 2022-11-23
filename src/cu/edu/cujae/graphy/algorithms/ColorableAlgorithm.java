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
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * El objetivo de este algoritmo es determinar si un grafo no dirigido puede ser
 * coloreado con m colores, de forma tal que dos vértices adyacentes no posean
 * igual coloratura.
 *
 * @author Ananda
 * @param <T>
 */
public class ColorableAlgorithm<T> extends AbstractAlgorithm<Boolean>
{

    private final Graph<T> graph;
    private final int mColors;
    private final GraphIterator<T> iter;
    private final int vertices;
    

    public ColorableAlgorithm(Graph<T> graph, int m)
    {
        super(Boolean.TRUE);
        if (graph.isDirected())
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Colorable algorithm to an directed graph.");
        }
        this.graph = graph;
        this.mColors = m;
        this.iter = graph.randomIterator();
        this.vertices = graph.size();
    }

    @Override
    public Algorithm<Boolean> apply()
    {
        boolean result = true;
        //Paso 1: Almacenar vértices en la lista.
        LinkedList<Integer> listOfVertices = new LinkedList<>();
        for (int v : graph.getLabels())
        {
            listOfVertices.add(iter.getLabel());
        }
        //Paso 2: Ordenar la lista descendentemente a partir de los grados.
        Collections.sort(listOfVertices, (Integer a, Integer b) -> b.compareTo(a));
        //Paso 3: Colorear vértice de mayor grado y verificar que su adyacente no posea igual color.
        Map<Integer, Integer> colors = new TreeMap<>();;
        Set<Integer> unavailableColors = new TreeSet<>();
        while(!listOfVertices.isEmpty()){
            int v = listOfVertices.poll();
            iter.next(v);
            for(int u : iter.getAllAdjacentVertices()){
                if(colors.containsKey(u)){
                    unavailableColors.add(u);
                }
            }
            int c = 1;
            while(unavailableColors.contains(c)){
                c++;
            }
            if(c > mColors){
                result = false;
                break;
            }
            else{
                colors.put(v, c);
                unavailableColors.clear();
            }
        }
        setResult(result);
        return this;
    }

}
