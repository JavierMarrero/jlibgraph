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
package cu.edu.cujae.graphy.tests.algorithms;

import cu.edu.cujae.graphy.algorithms.KruskalMinimumSpanningTree;
import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Weight;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;
import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class KruskalMinimumSpanningTreeTest
{
    public static void getKruskal (WeightedGraph<Integer> graph){
        System.out.println(new KruskalMinimumSpanningTree(graph).apply().get().toString());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
         WeightedGraph<Integer> graph = GraphBuilders.makeSimpleWeightedGraph(false);
         for (int i = 0; i <4; i++){
             graph.add(i, i);
         }
         graph.connect(0, 1, Weights.makeWeight(10));
         graph.connect(0, 2, Weights.makeWeight(6));
         graph.connect(0, 3, Weights.makeWeight(5));
         graph.connect(1, 3, Weights.makeWeight(15));
         graph.connect(2, 3, Weights.makeWeight(4));
         
         getKruskal(graph);
        
    }
    
}
