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

import cu.edu.cujae.graphy.algorithms.CompleteDigraphDetection;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

/**
 *
 * @author Amaya D. Fuentes
 */
public class CompleteDigraphDetectionTest {
    
    public static void isCompleteDigraph(Graph<Integer> graph) {
        System.out.println("     Complete digraph?: "+new CompleteDigraphDetection(graph).apply().get()+"\n");
    }
    
    public static void main(String[] args) {
        
        Graph<Integer> g1 = GraphBuilders.makeSimpleGraph(true);
        g1.add(0);
        System.out.println("G1 - Vertices: 0 \n     Edges: NONE");
        isCompleteDigraph(g1);
        
        Graph<Integer> g2 = GraphBuilders.makeSimpleGraph(true);
        g2.add(0);
        g2.add(1);
        g2.add(2);
        g2.connect(0, 1);
        g2.connect(1, 2);
        g2.connect(2, 0);
        System.out.println("G2 - Vertices: 0, 1, 2 \n     Edges: (0->1) (1->2) (2->0)");
        isCompleteDigraph(g2);
        
        Graph<Integer> g3 = GraphBuilders.makeSimpleGraph(true);
        g3.add(0);
        g3.add(1);
        g3.add(2);
        g3.connect(0, 1);
        g3.connect(1, 2);
        g3.connect(2, 0);
        g3.connect(1, 0);
        g3.connect(2, 1);
        g3.connect(0, 2);
        System.out.println("G3 - Vertices: 0, 1, 2 \n     Edges: (0->1) (1->2) (2->0) (2<-0) (1<-2) (0<-1)");
        isCompleteDigraph(g3);
        
        Graph<Integer> g4 = GraphBuilders.makeSimpleGraph(true);
        g4.add(0);
        g4.add(1);
        g4.add(2);
        g4.add(3);
        g4.connect(0, 1);
        g4.connect(1, 2);
        g4.connect(2, 0);
        g4.connect(1, 0);
        g4.connect(2, 1);
        g4.connect(0, 2);
        System.out.println("G4 - Vertices: 0, 1, 2, 3 \n     Edges: (0->1) (1->2) (2->0) (2<-0) (1<-2) (0<-1)");
        isCompleteDigraph(g4);
        
        Graph<Integer> g5 = GraphBuilders.makeSimpleGraph(true);
        g5.add(0);
        g5.add(1);
        g5.connect(0, 1);
        g5.connect(1, 0);
        g5.connect(1, 1);
        System.out.println("G5 - Vertices: 0, 1 \n     Edges: (0->1) (0<-1) (1->1)");
        isCompleteDigraph(g5);
        
        
        
        
        
        
        
    }
    
}
