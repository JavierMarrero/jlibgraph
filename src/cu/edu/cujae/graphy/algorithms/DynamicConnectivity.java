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
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.HashMap;


/** 
 *Dynamic connectivity is a data structure that dynamically maintains the information about the connected components 
 * of graph. In simple words suppose there is a graph G(V, E) in which no. of vertices V is constant but no. of edges E 
 * is variable. There are three ways in which we can change the number of edges:
 *  -Incremental Connectivity : Edges are only added to the graph.
 *  -Decremental Connectivity : Edges are only deleted from the graph.
 *  -Fully Dynamic Connectivity : Edges can both be deleted and added to the graph.
 *In this algorithm only Incremental connectivity is used. There are mainly two operations that need to be handled: 
 *  -An edge is added to the graph.
 *  -Information about two nodes x and y whether they are in the same connected components or not.
 *To solve the problems of incremental connectivity disjoint data structure is used. Here each connected component represents 
 *a set and if the two nodes belong to the same set it means that they are connected. 
 *The amortized time complexity is O(alpha(n)) per operation where alpha is inverse ackermann function which is nearly constant.
 * @author Jose
 * @param <T>
 */
public class DynamicConnectivity<T>
{

    private final Graph<T> graph;
    private final int amountVertices;
    
    // The following two maps are used to
    // implement disjoint set data structure.
    // arr holds the parent nodes while rank
    // map holds the rank of subset
    
    private HashMap<Integer, Integer> arr; 
    private HashMap<Integer, Integer> rank;
    
    private int type;
    private int x;
    private int y;

    public DynamicConnectivity(Graph<T> graph)
    {
        this.graph = graph;
        this.amountVertices = graph.size();
        this.arr = new HashMap<>();
        this.rank = new HashMap<>();
        for (int label : graph.getLabels())
        {
            arr.put(label, label);
            rank.put(label, 1);
        }
        GraphIterator<Integer> dfs = (GraphIterator<Integer>) graph.breadthFirstSearchIterator(true);
        while (dfs.hasNext())
        {
            dfs.next();
            for (Edge e : dfs.getAllAdjacentEdges())
            {
                apply(2, e.getStartNode().getLabel(), e.getFinalNode().getLabel());
            }
        }
    }

    //Finding the root of node label
    public static int root(HashMap<Integer, Integer> arr, int label)
    {
        while (arr.get(label) != label)
        {
            arr.put(label, arr.get(arr.get(label)));
            label = arr.get(label);
        }
        return label;
    }

    public static void weighted_union(HashMap<Integer, Integer> arr, HashMap<Integer, Integer> rank, int a, int b)
    {
        int root_a = root(arr, a);
        int root_b = root(arr, b);

        //union based on rank
        if (rank.get(root_a) < rank.get(root_b))
        {
            arr.put(root_a, arr.get(root_b));
            rank.put(root_b, rank.get(root_b) + rank.get(root_a));
        }
        else
        {
            arr.put(root_b, arr.get(root_a));
            rank.put(root_a, rank.get(root_a) + rank.get(root_b));
        }
    }

    public static boolean areSame(HashMap<Integer, Integer> arr, int a, int b)
    {
        return (root(arr, a) == root(arr, b));
    }

    /**Performing an operation according to query type
     * 
     * @param type 1 query means checking if node x and y are connected or not || 2 query refers union of x and y
     * @param x label
     * @param y label
     * @return true if x and y are connected || false if x and y aren't connected
     */
    
    public final boolean apply(int type, int x, int y)
    {
        boolean result = false;
        //type 1 query means checking if node x and y are connected or not
        if (type == 1)
        {
            //If roots of x and y is same then yes is the answer 
            if (areSame(arr, x, y) == true)
            {
                result = true;
            }
            else
            {
                result = false;
            }

        }
        else if (type == 2)
        { // type 2 query refers union of x and y
            //If x and y have different roots then union them
            if (areSame(arr, x, y) == false)
            {
                weighted_union(arr, rank, x, y);
            }
        }
        return result;
    }

}
