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

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Kruskal’s algorithm to find the minimum cost spanning tree uses the greedy approach.
 * The Greedy Choice is to pick the smallest weight edge that does not cause a cycle in the
 * MST constructed so far.
 * <p>
 * Time Complexity: O(ElogE) or O(ElogV), Sorting of edges takes O(ELogE) time. After sorting,
 * we iterate through all edges and apply the find-union algorithm. The find and union operations
 * can take at most O(LogV) time. So overall complexity is O(ELogE + ELogV) time. The value of E
 * can be at most O(V2), so O(LogV) is O(LogE) the same. Therefore, the overall time complexity
 * is O(ElogE) or O(ElogV)
 *
 * @author Jose
 * @param <V>
 */
public class KruskalMinimumSpanningTree<V> extends AbstractAlgorithm<WeightedGraph<V>>
{

    private final WeightedGraph<V> graph;
    private final HashMap<Integer, Integer> subsetP;
    private final HashMap<Integer, Integer> subsetR;

    public KruskalMinimumSpanningTree(WeightedGraph<V> graph)
    {
        super(null);
        this.graph = graph;
        this.subsetP = new HashMap<>();
        this.subsetR = new HashMap<>();
    }

    @Override
    public Algorithm<WeightedGraph<V>> apply()
    {
        GraphIterator<V> iter = graph.iterator(0);
        //This will store all edges
        ArrayList<Edge> edges = new ArrayList<>();

        //This will store the edges of the resultant Minimum Spanning Tree
        ArrayList<Edge> result = new ArrayList<>(graph.size());

        while (iter.hasNext())
        {
            iter.next();
            Collection<Edge> coll = iter.getAllAdjacentEdges();
            for (Edge e : coll)
            {
                if (!edges.contains(e))
                {
                    edges.add(e);
                }
            }
        }

        //Sort all the edges in non-decreasing order of their weight
        Collections.sort(edges, (Edge e1, Edge e2) -> 
                 {
                     return (Integer) e1.getWeight().getValue() - (Integer) e2.getWeight().getValue();
                 });

        Collection<Integer> labels = graph.getLabels();
        for (Integer i : graph.getLabels())
        {
            subsetP.put(i, i);
            subsetR.put(i, 0);
        }

        //An index variable used for result
        int e = 0;
        //An index variable used for sorted edges
        int i = 0;

        //Number of edges to be taken is equal to the number of edges - 1
        while (e < graph.size() - 1)
        {
            Edge next_Edge = edges.get(i++);

            int x = find(next_Edge.getStartNode().getLabel());
            int y = find(next_Edge.getFinalNode().getLabel());
            if (x != y)
            {
                result.add(e++, next_Edge);
                union(x, y);
            }
        }

        setResult(makeNewGraph(result, labels));
        return this;
    }

    /*A utility function to find set of an element i 
    (Uses path compression technique)
     */
    public int find(int i)
    {
        //find root and make root as a parent of i
        //(path compression)
        if (subsetP.get(i) != i)
        {
            subsetP.put(i, find(subsetP.get(i)));
        }
        return subsetP.get(i);
    }

    /*
    A function that does union of two sets of x and y
    (uses union by rank)
     */
    public void union(int x, int y)
    {
        int xroot = find(x);
        int yroot = find(y);

        if (subsetR.get(xroot) < subsetR.get(yroot))
        {
            subsetP.put(xroot, yroot);
        }
        else if (subsetR.get(xroot) > subsetR.get(yroot))
        {
            subsetP.put(yroot, xroot);
        }
        else
        {
            subsetP.put(yroot, xroot);
            subsetR.put(xroot, subsetR.get(xroot) + 1);
        }
    }

    //Building the Minimum Spanning Tree
    public WeightedGraph<V> makeNewGraph(ArrayList<Edge> result, Collection<Integer> labels)
    {
        WeightedGraph<V> newGraph = GraphBuilders.makeSimpleWeightedGraph(false);
        GraphIterator<V> iter = graph.iterator(0);
        for (Integer i : labels)
        {
            newGraph.add(i, iter.next(i));
        }
        for (Edge e : result)
        {
            newGraph.connect(e.getStartNode().getLabel(), e.getFinalNode().getLabel(), e.getWeight());
        }
        return newGraph;
    }
}
