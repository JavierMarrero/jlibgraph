/*
 * Copyright (C) 2022 Jose.
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
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Jose
 */

/*IsolatedVertices<V> se realiza para poder obtener todos los vértices aislados en un grafo*/

public class IsolatedVertices<V> extends AbstractAlgorithm<LinkedList<Integer>>
{
    private final Graph<V> graph;
    public IsolatedVertices (Graph<V> graph)
    {
        //En el caso de que no existan vértices aislados se retorna una lista vacía
        super( new LinkedList<Integer>());
        this.graph = graph;   
    }
    
    @Override
    public Algorithm<LinkedList<Integer>> apply()
    {
       //Lista para ir guardando los vertices aislados
       LinkedList<Integer> aislados = new LinkedList<>();
       //Lista para guardar los nodos visitados y no analizarlos de nuevo 
       Set<Integer> visited = new TreeSet<>();
       
       GraphIterator<V> dfs = (GraphIterator<V>) graph.depthFirstSearchIterator(true);
       while(dfs.hasNext())
       {
           dfs.next();
           //Si no ha sido visitado y no tiene aristas adyacentes
           if(!visited.contains(dfs.getLabel()) && dfs.getAllAdjacentEdges().isEmpty())
           {
               aislados.add(dfs.getLabel());
               visited.add(dfs.getLabel());
           }
       }
       setResult(aislados);
       return this;
    }
    
}
