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
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * El <b>algoritmo de Dial</b>, es decir, <i>Dijkstra optimizado para pesos de rango
 * pequeño</i>, emplea una nueva estructura denominada cubo y posee una complejidad
 * de tiempo <code>O(E+WV)</code>, donde W es el peso máximo en cualquier borde del
 * gráfico.La distancia máxima entre dos nodos puede tener un máximo de w(V-1).
 *
 *
 * @author Ananda
 */
public class DialShortestPath extends AbstractAlgorithm<Map<Integer, Pair<Integer, List<Integer>>>>
{

    private final static int INFINITY = Integer.MAX_VALUE;
    private final WeightedGraph<?> graph;
    private GraphIterator<?> source;
    private final Map<Integer, Deque<Integer>> buckets;
    private final Map<Integer, Integer> distances;
    private final Map<Integer, Integer> previous;
    private final int numberOfVertices;
    private final int W;

    public DialShortestPath(WeightedGraph<?> graph, GraphIterator<?> s, int maxW)
    {
        super(new HashMap<>(graph.size()));
        //debo verificar que ninguna arista posea un peso mayor que maxWeight!!!
        if (!graph.isWeighted())
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Dial algorithm to an unweighted graph.");
        }

        this.buckets = new HashMap<>(maxW * graph.size() + 1);
        this.distances = new TreeMap<>();
        this.graph = graph;
        this.previous = new TreeMap<>();
        this.numberOfVertices = graph.size();
        this.W = maxW;
        this.source = s;

        //Inicializar las distancias a infinito.
        for (int v : graph.getLabels())
        {
            distances.put(v, INFINITY);
            previous.put(v, null);
        }

        //Inicializar las cubetas.
        for (int i = 0; i < (maxW * numberOfVertices + 1); ++i)
        {
            buckets.put(i, new LinkedList<>());
        }

        // Set the distance to the source to 0
        // and put it in the 0 bucket
        distances.put(s.getLabel(), 0);
        buckets.get(0).push(s.getLabel());

        //System.out.println(distances);
        //System.out.println(buckets);
    }

    @Override
    public Algorithm<Map<Integer, Pair<Integer, List<Integer>>>> apply()
    {

        //Paso 1: Verificar el ìndice de las cubetas y si estàn vacías.
        int index = 0;
        while (true)
        {
            while (buckets.get(index).isEmpty() && index < W * numberOfVertices)
            {
                index++;
            }
            if (index == W * numberOfVertices)
            {
                break;
            }
            GraphIterator<?> u = graph.iterator(buckets.get(index).pop());

            // Paso 2: Procesar todos los adyacentes del vértice u y actualizar sus distancias si es requerido.
            for (Edge e : u.getAllAdjacentEdges())
            {
                int v = e.getFinalNode().getLabel();
                int weight = (int) e.getWeight().getValue();

                int du = distances.get(u.getLabel());
                int dv = distances.get(v);

                //Verificar si hay un camino corto de v a u. 
                if (dv > du + weight)
                {
                    distances.put(v, du + weight);
                    dv = distances.get(v);
                    previous.put(v, u.getLabel());
                    //Añadir vértice v a las distancias actualizadas de la cubeta.
                    buckets.get(dv).push(v);
                }
            }
        }
        for (int v : distances.keySet())
        {
            getResult().put(v, new Pair<>(distances.get(v), makeShortestPathSequence(source.getLabel(), v)));
        }
        return this;
    }

    //Esta función es común con Dijkstra´s Algorithm.
    private List<Integer> makeShortestPathSequence(int source, int target)
    {
        LinkedList<Integer> S = new LinkedList<>();
        int u = target;

        if (previous.get(u) != null)
        {
            while (previous.get(u) != null)
            {
                S.push(u);
                u = previous.get(u);
            }
        }
        S.push(source);
        return S;
    }
}
