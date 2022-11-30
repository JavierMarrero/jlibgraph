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

import cu.edu.cujae.graphy.algorithms.BipartiteGraphDetection;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

/**
 *
 * @author Amaya D. Fuentes
 */
public class BipartiteGraphDetectionTest {
    
    public static void isBipartite(Graph<Integer> graph) {
        System.out.println("     Bipartite?: "+new BipartiteGraphDetection(graph).apply().get()+"\n");
    }
    
    public static void main(String[] args) {
        
        Graph<Integer> g1 = GraphBuilders.makeSimpleGraph(false);
        g1.add(1, 1);
        g1.add(2, 2);
        g1.add(3, 3);
        g1.add(4, 4);
        g1.add(5, 5);
        g1.add(6, 6);
        g1.add(7, 7);
        g1.add(8, 8);
        g1.add(9, 9);
        g1.connect(1, 2);
        g1.connect(1, 8);
        g1.connect(2, 5);
        g1.connect(3, 6);
        g1.connect(4, 5);
        g1.connect(6, 7);
        g1.connect(7, 8);
        g1.connect(8, 9);
        System.out.println("G1 - Vertices: 1, 2, 3, 4, 5, 6, 7, 8, 9 \n     "
                + "Edges: (1<->2)(1<->8)(2<->5)(3<->6)(4<->5)(6<->7)(7<->8)(8<->9)");
        isBipartite(g1);
        
        Graph<Integer> g2 = GraphBuilders.makeSimpleGraph(true);
        g2.add(0);
        g2.add(1);
        g2.add(2);
        g2.connect(0, 1);
        g2.connect(0, 2);
        System.out.println("G2 - Vertices: 0, 1, 2 \n     Edges: (0->1)(0->2)");
        isBipartite(g2);
        
        Graph<Integer> g3 = GraphBuilders.makeSimpleGraph(true);
        g3.add(0);
        g3.add(1);
        g3.add(2);
        g3.connect(0, 0);
        g3.connect(0, 1);
        g3.connect(0, 2);
        System.out.println("G3 - Vertices: 0, 1, 2 \n     Edges: (0->1)(0->2)(0->0)");
        isBipartite(g3);
        
        Graph<Integer> g4 = GraphBuilders.makeSimpleGraph(false);
        g4.add(0);
        g4.add(1);
        g4.add(2);
        g4.add(3);
        g4.connect(0, 1);
        g4.connect(0, 3);
        g4.connect(2, 1);
        g4.connect(2, 3);
        g4.connect(1, 3);
        g4.connect(2, 0);
        System.out.println("G4 - Vertices: 0, 1, 2, 3 \n     Edges: (0<->1)(0<->3)(2<->1)(2<->3)(1<->3)(2<->0)");
        isBipartite(g4);
        
        
        
         
        
        
        
    }
    
}
