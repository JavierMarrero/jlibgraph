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
package cu.edu.cujae.graphy.tests;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test for cloning a graph
 *
 * @author Javier Marrero
 */
public class CloneTest
{
    
    private static void testSimpleGraphs()
    {
        // This is for normal, simple graphs
        Graph<Integer> graph = GraphBuilders.makeSimpleGraph(true);
        for (int i = 0; i < 3; ++i)
        {
            graph.add(i);
        }
        
        graph.connect(0, 1);
        graph.connect(1, 2);
        graph.connect(2, 0);
        
        System.out.println(graph);
        
        try
        {
            Graph<Integer> clone = graph.duplicate();
            System.out.println(clone);
        }
        catch (CloneNotSupportedException ex)
        {
            Logger.getLogger(CloneTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void testWeightedGraphs()
    {
        // This is for normal, simple graphs
        WeightedGraph<Integer> weightedGraph = GraphBuilders.makeSimpleWeightedGraph(true);
        for (int i = 0; i < 3; ++i)
        {
            weightedGraph.add(i);
        }
        
        weightedGraph.connect(0, 1, Weights.makeWeight(1));
        weightedGraph.connect(1, 2, Weights.makeWeight(6));
        weightedGraph.connect(2, 0, Weights.makeWeight(18));
        
        System.out.println(weightedGraph);
        
        try
        {
            WeightedGraph<Integer> clone = (WeightedGraph<Integer>) weightedGraph.duplicate();
            System.out.println(clone);
        }
        catch (CloneNotSupportedException ex)
        {
            Logger.getLogger(CloneTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Testing for simple graphs:\n");
        testSimpleGraphs();
        
        System.out.println("\nTesting for weighted graphs:\n");
        testWeightedGraphs();
    }
    
}
