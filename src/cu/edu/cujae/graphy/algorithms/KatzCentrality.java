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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Jose
 * @param <V>
 */
public class KatzCentrality<V> extends AbstractAlgorithm<Integer>
{

    private final Graph<V> graph;
    private final double alpha;

    public KatzCentrality(Graph<V> graph, double alpha)
    {
        super(-1);
        this.graph = graph;
        this.alpha = alpha;
    }

    @Override
    public Algorithm<Integer> apply()
    {
        int cantVertices = graph.size();

        ArrayList<Integer> vertices = new ArrayList<>(graph.size());
        GraphIterator<V> iter = (GraphIterator<V>) graph.breadthFirstSearchIterator(false);
        ArrayList<Nodo> centralityValue = new ArrayList<>();

        // Guardando en una lista las etiquetas de cada nodo del grafo para acceder a ellas de forma secuencial
        while (iter.hasNext())
        {
            iter.next();
            vertices.add(iter.getLabel());
        }

        for (int i = 0; i < cantVertices; i++)
        {
            int current = vertices.get(i);
            HashMap<Integer, Integer> nivel = new HashMap<>();
            Queue<Integer> cola = new LinkedList<>();
            nivel.put(current, 0);

            double centrality = 0;

            while (nivel.size() != cantVertices)
            {
                GraphIterator<V> iterat1 = graph.iterator(current);
                for (Integer node : iterat1.getAllAdjacentVertices())
                {
                    if (!nivel.containsKey(node))
                    {
                        int newLevel = nivel.get(current) + 1;
                        centrality += Math.pow(alpha, newLevel);
                        nivel.put(node, newLevel);
                        cola.add(node);
                    }
                }
                if (!cola.isEmpty())
                {
                    current = cola.poll();
                }
            }
            centralityValue.add(new Nodo(current, centrality));
        }

        Collections.sort(centralityValue, Collections.reverseOrder());
        setResult(centralityValue.get(0).id);
        return this;
    }

    private class Nodo implements Comparable<Nodo>
    {

        private Integer id;
        private final double value;

        public Nodo(Integer id, double value)
        {
            this.id = id;
            this.value = value;
        }

        @Override
        public int compareTo(Nodo o)
        {
            return Double.compare(value, o.value);
        }

    }

}
