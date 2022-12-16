/*
 * Copyright (C) 2022 CUJAE.
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
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Given an undirected and unweighted graph, find the smallest cut (smallest number of edges that disconnects the graph
 * into two components). The input graph may have parallel edges.
 *
 * @author Javier Marrero
 */
public class KargerMinimumCut extends AbstractAlgorithm<Integer>
{

    private static final class Subset
    {

        // A utility function to find set of an element i
        // (uses path compression technique)
        public static int find(Map<Integer, Subset> subsets, int i)
        {
            if (subsets.get(i).parent != i)
            {
                subsets.get(i).parent = find(subsets, subsets.get(i).parent);
            }
            return subsets.get(i).parent;
        }

        // A function that does union of two sets of x and y
        // (uses union by rank)
        public static void union(Map<Integer, Subset> subsets, int x, int y)
        {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);

            // Attach smaller rank tree under root of high
            // rank tree (Union by Rank)
            if (subsets.get(xroot).rank < subsets.get(yroot).rank)
            {
                subsets.get(xroot).parent = yroot;
            }
            else
            {
                if (subsets.get(xroot).rank > subsets.get(yroot).rank)
                {
                    subsets.get(yroot).parent = xroot;
                }
                // If ranks are same, then make one as root and
                // increment its rank by one
                else
                {
                    subsets.get(yroot).parent = xroot;
                    subsets.get(xroot).rank++;
                }
            }
        }

        protected int rank;
        protected int parent;

        public Subset(int parent, int rank)
        {
            this.parent = parent;
            this.rank = rank;
        }
    }

    private final int E;
    private final Graph<?> G;
    private final ArrayList<Edge> edge;
    private final Random random;
    private final int V;

    public KargerMinimumCut(Graph<?> graph)
    {
        super(0);
        if (graph.isDirected())
        {
            throw new IllegalArgumentException("The graph is directed.");
        }
        if (graph.isWeighted())
        {
            throw new IllegalArgumentException("The graph is weighted.");
        }

        // Initialize the class fields
        this.G = graph;
        this.random = new Random();
        this.V = graph.size();

        this.edge = new ArrayList<>(V * 2);
        GraphIterator<?> dfsIterator = (GraphIterator<?>) graph.depthFirstSearchIterator(true);
        while (dfsIterator.hasNext())
        {
            dfsIterator.next();
            edge.addAll(dfsIterator.getEdgesDepartingSelf());
        }

        this.E = edge.size();
    }

    @Override
    public Algorithm<Integer> apply()
    {
        // Allocate memory for the new subset
        Map<Integer, Subset> subsets = new TreeMap<>();

        // Create V subsets with single elements
        for (int v : G.getLabels())
        {
            subsets.put(v, new Subset(v, 0));
        }

        // Initially, there are V vertices
        int vertexCount = V;

        // Keep contracting vertices until there are
        // 2 vertices.
        while (vertexCount > 2)
        {
            // Pick a random edge
            int i = random.nextInt(E);

            // Find vertices (or sets) of two corners
            // of current edge
            int subset1 = Subset.find(subsets, edge.get(i).getStartNode().getLabel());
            int subset2 = Subset.find(subsets, edge.get(i).getFinalNode().getLabel());

            if (subset1 != subset2)
            {
                // System.out.println("Contracting edge " + edge.get(i).getStartNode().getLabel() + "-" + edge.get(i).
                //        getFinalNode().getLabel());
                vertexCount--;
                Subset.union(subsets, subset1, subset2);
            }
        }

        // Now we have two vertices (or subsets) left in
        // the contracted graph, so count the edges between
        // two components and return the count.
        int cutedges = 0;
        for (int i = 0; i < E; i++)
        {
            int subset1 = Subset.find(subsets, edge.get(i).getStartNode().getLabel());
            int subset2 = Subset.find(subsets, edge.get(i).getFinalNode().getLabel());

            if (subset1 != subset2)
            {
                cutedges++;
            }
        }

        // This is mandated by the interface
        setResult(cutedges);
        return this;
    }

}
