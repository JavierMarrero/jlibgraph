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

/**
 *El objetivo de este algoritmo es determinar si un grafo no dirigido puede ser
 * coloreado con m colores, de forma tal que dos vértices adyacentes no posean
 * igual coloratura.
 * 
 * @author Ananda
 * @param <T>
 */
public class ColorableAlgorithm<T> extends AbstractAlgorithm<Boolean> {
    private final Graph<T> graph;
    private final int m;
    private final String color;
    private final GraphIterator<T> iter;
    private int vertices;
    
    public ColorableAlgorithm(Graph<T> graph, int m, String color, GraphIterator<T> iter){
        super(Boolean.TRUE);
        if(graph.isDirected()){
            throw new IllegalArgumentException(
                    "Attempted to apply Colorable algorithm to an directed graph.");
        }
        this.graph = graph;
        this.m = m; 
        this.color = color;
        this.iter = (GraphIterator<T>) graph.depthFirstSearchIterator(false);
    }
    
    @Override
    public Algorithm<Boolean> apply(){
        while(iter.hasNext()){
    /*        Collections.sort(vertices, new VertexComparator());
            Map<String, String> vertex_color_index = new HashMap<String, String>();
            for (int i = 0; i < vertices.size(); i++){
                if ((vertex_color_index.containsKey(vertices.get(i).node))){	
                    continue;
                }
                else{
                    vertex_color_index.put(vertices.get(i).node, "Color" + i);
                    for (int j = i+1; j < vertices.size(); j++){
                        if (!( vertices.get(i).neighbors.contains(vertices.get(j).node)) && ! (vertex_color_index.containsKey(vertices.get(j).node))) {
                            vertex_color_index.put(vertices.get(j).node, "Color" + i);
                        }
			else{
                            continue;
                        }
                    }
		}	
            }
            System.out.println(vertex_color_index);	
	}*/
        }
        
        return this;
    }
    
}
