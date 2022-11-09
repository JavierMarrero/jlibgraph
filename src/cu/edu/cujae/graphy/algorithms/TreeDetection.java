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
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Jose
 * @param <V>
 */
public class TreeDetection<V> extends AbstractAlgorithm<Boolean>
{

    private final GraphIterator<V> iterator;
    private final Graph<V> graph;

    public TreeDetection(Graph<V> graph, GraphIterator<V> iterator)
    {
        super(Boolean.TRUE);

        this.graph = graph;
        this.iterator = iterator;
    }

    @Override
    public Algorithm<Boolean> apply()
    {
        Set<Integer> visited = new TreeSet<>();
        GraphIterator<V> dfs = (GraphIterator<V>) graph.depthFirstSearchIterator(iterator.getLabel(), false);
        boolean stop = false;
        while (dfs.hasNext() && !stop)
        {
            dfs.next();
            int node = dfs.getLabel();

            /*Verificar si ya se ha visitado ese nodo antes o si a ese nodo entra más de una arista*/
            if (visited.contains(node) || dfs.getEdgesArrivingSelf().size() > 1)
            {
                setResult(Boolean.FALSE);
                stop = true;
            }
            else
            {
                visited.add(node);
            }
        }
        /*Verificar si la cantidad de nodos del grafo coincide con todos los nodos visitados y por tanto no hay nodos desconectados*/
        if (!stop && graph.size() != visited.size())
        {
            setResult(Boolean.FALSE);
        }
        return this;
    }

}
