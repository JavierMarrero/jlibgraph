/*
 * Copyright (C) 2022 Amaya.
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

import cu.edu.cujae.graphy.algorithms.FringeSearchShortestPath;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import cu.edu.cujae.graphy.core.utility.Weights;

/**
 *
 * @author Amaya D. Fuentes
 */
public class FringeSearchTest {
    
    public static void executeFringeSearch(WeightedGraph<Integer> graph, int start, int goal) {
        System.out.println("     Shortest path from "+start+" to "+goal+": "+new FringeSearchShortestPath(graph, start, goal).apply().get()+"\n");
    }
    
    public static void main(String[] args) {
        
        WeightedGraph<Integer> g1 = GraphBuilders.makeSimpleWeightedGraph(true);
        for (int i = 0; i < 4; ++i) {
            g1.add(i);
        }
        g1.connect(0, 1, Weights.makeWeight(1));
        g1.connect(0, 2, Weights.makeWeight(2));
        g1.connect(1, 2, Weights.makeWeight(5));
        g1.connect(1, 3, Weights.makeWeight(2));
     
        System.out.println("G1 - Vertices: 0, 1, 2, 3 \n     Edges: (0->1)(w:1) (0->2)(w:2) (2->1)(w:5) (1->3)(w:2)");
        executeFringeSearch(g1, 0, 3);
        
        
        WeightedGraph<Integer> g2 = GraphBuilders.makeSimpleWeightedGraph(true);
        for(int i = 0; i < 4; i++) {
            g2.add(i);
        }
        g2.connect(0, 1, Weights.makeWeight(15));
        g2.connect(0, 2, Weights.makeWeight(2));
        g2.connect(2, 1, Weights.makeWeight(5));
        g2.connect(1, 3, Weights.makeWeight(2));
        
        System.out.println("G2 - Vertices: 0, 1, 2, 3 \n     Edges: (0->1)(w:15) (0->2)(w:2) (2->1)(w:5) (1->3)(w:2)");
        executeFringeSearch(g2, 0, 3);
        
        
        WeightedGraph<Integer> g3 = GraphBuilders.makeSimpleWeightedGraph(true);
        for(int i = 0; i < 4; i++) {
            g3.add(i);
        }
        g3.connect(0, 1, Weights.makeWeight(15));
        
        System.out.println("G3 - Vertices: 0, 1, 2 \n     Edges: (0->1)(w:15)");
        executeFringeSearch(g3, 0, 2);
        
        
    }
    
}
