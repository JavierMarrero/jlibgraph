/*
 * Copyright (C) 2022 Asus.
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

import cu.edu.cujae.graphy.core.Weight;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.utility.Weights;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.*;

/**
 * El algoritmo de Ford-Fulkerson resuelve el problema de flujo máximo. Dado un
 * grafo que representa una red de flujo donde toda arista tiene una capacidad,
 * y dados dos vértices: fuente (s) y vertedero (t) en el grafo, se encuentra
 * el flujo máximo posible desde s hasta t, teniendo en cuenta las siguientes
 * restriciones:
 * <ul>
 * <li>El flujo en un vértice no puede superar la capacidad máxima de dicho
 * vértice.</li>
 * <li>El flujo de entrada debe ser igual al flujo de salida para cada vértice
 * excepto s y t.</li>
 * </ul>
 * <p>
 * Su nombre viene dado por sus creadores , L.R. Ford, Jr. y D.R. Fulkerson.
 *
 * @author Amanda Mendez
 */
public class FordFulkersonAlgorithm extends AbstractAlgorithm<Pair<Float, List<Integer>>>
{

    // Fields of class 
    private final WeightedGraph<?> G; // Graph
    private final int s;
    private final int t;
    private float maxFlow;
    private final Map<Integer, Integer> parent; // To store path
    private final WeightedGraph<?> rG; // Residual graph

    // Constructor
    public FordFulkersonAlgorithm(WeightedGraph<?> graph, GraphIterator<?> source, GraphIterator<?> dest)
    {
        super(new Pair<Float, List<Integer>>());

        // This algorithm only works on weighted graphs
        if (!graph.isWeighted())
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Ford Fulkerson algorithm to an unweighted graph.");
        }

        // This algorithm only works on directed graphs
        if (!graph.isDirected())
        {
            throw new IllegalArgumentException(
                    "Attempted to apply Ford Fulkerson algorithm to an undirected graph.");
        }

        // Inicializar campos de la clase
        this.G = graph;
        this.s = source.getLabel();
        this.t = dest.getLabel();
        this.maxFlow = 0;
        this.parent = new HashMap<>();

        // Create residual graph
        try
        {
            this.rG = (WeightedGraph<?>) G.duplicate();

            // For each arc in the graph, make a duplicate of the arc, of reversed direction, with zero weight
            GraphIterator<?> it = this.rG.randomIterator();

            for (int u : rG.getLabels())
            {
                it.next(u);
                for (int v : rG.getLabels())
                {
                    // If there's no edge going from u to v add a new connection with weight equals to zero
                    if (it.isAdjacentAndArriving(v) == false)
                    {
                        rG.connect(u, v, Weights.makeWeight(0.0f));
                    }
                }
            }

            // System.out.println(rG);
        }
        catch (CloneNotSupportedException ex)
        {
            throw new IllegalArgumentException("The provided graph is not cloneable nor duplicable.");
        }
    }

    @Override
    public Algorithm<Pair<Float, List<Integer>>> apply()
    {
        int u, v;

        // While exist a path from source to dest
        while (bfs(parent))
        {
            // Store the max flow
            float pathFlow = Float.MAX_VALUE;

            // Find minimum residual capacity of the edges
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            for (v = t; v != s; v = parent.get(v))
            {
                u = parent.get(v);
                GraphIterator<?> it = rG.iterator(u);

                pathFlow = Math.min(pathFlow, (float) it.getAdjacentEdge(v).getWeight().getValue());
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent.get(v))
            {
                u = parent.get(v);

                GraphIterator<?> uIterator = rG.iterator(u);
                GraphIterator<?> vIterator = rG.iterator(v);

                @SuppressWarnings ("unchecked")
                Weight<Float> directWeight = (Weight<Float>) uIterator.getAdjacentEdge(v).getWeight();
                @SuppressWarnings ("unchecked")
                Weight<Float> inverseWeight = (Weight<Float>) vIterator.getAdjacentEdge(u).getWeight();

                directWeight.setValue(directWeight.getValue() - pathFlow);
                inverseWeight.setValue(inverseWeight.getValue() + pathFlow);
            }

            // Add path to the overall flow
            maxFlow += pathFlow;
        }

        // Create the final result
        List<Integer> list = new ArrayList<>(G.size());
        Stack<Integer> stack = new Stack<>();

        Integer i = t;
        while (i != null)
        {
            stack.push(i);
            i = parent.get(i);
        }

        while (!stack.isEmpty())
        {
            list.add(stack.pop());
        }

        Pair<Float, List<Integer>> result = getResult();

        result.setFirst(maxFlow);
        result.setLast(list);

        return this;
    }

    public boolean bfs(Map<Integer, Integer> parents)
    {

        // Array to store visited vertices
        Set<Integer> seen = new TreeSet<>();
        Queue<Integer> queue = new LinkedList<>();

        // Visit source
        queue.add(s);
        seen.add(s);
        parents.put(s, null);

        // Loop trough all vertices
        while (!queue.isEmpty())
        {
            int u = queue.poll();
            GraphIterator<?> aux = this.rG.iterator(u);

            for (Integer v : rG.getLabels())
            {
                if (seen.contains(v) == false && ((float) aux.getAdjacentEdge(v).getWeight().getValue() > 0.0f))
                {
                    if (v == t)
                    {
                        parents.put(v, u);
                        return true;
                    }

                    queue.add(v);
                    seen.add(v);
                    parents.put(v, u);
                }
            }
        }

        return false;
    }
}
