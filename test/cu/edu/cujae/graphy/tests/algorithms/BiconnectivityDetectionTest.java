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

import cu.edu.cujae.graphy.algorithms.BiconnectivityDetection;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

/**
 *
 * @author Amaya D. Fuentes
 */
public class BiconnectivityDetectionTest {
    
    public static void main (String[] args) {
   
        Graph<Integer> g1 = GraphBuilders.makeSimpleGraph(false);
        
        g1.add(0);
        g1.add(1);
        g1.connect(0, 1);
        
        System.out.println("G1 - Vertices: 0, 1 - Edges: (0<->1) - Biconnected: "+new BiconnectivityDetection(g1).apply().get()+"\n");
        
        Graph<Integer> g2 = GraphBuilders.makeSimpleGraph(false);
        
        g2.add(0);
        g2.add(1);
        g2.add(2);
        g2.connect(0, 1);
        g2.connect(1, 2);
        
        System.out.println("G2 - Vertices: 0, 1, 2 - Edges: (0<->1) (1<->2) - Biconnected: "+new BiconnectivityDetection(g2).apply().get()+"\n");
        
        Graph<Integer> g3 = GraphBuilders.makeSimpleGraph(false);
        
        g3.add(0);
        g3.add(1);
        g3.add(2);
        g3.connect(0, 1);
        g3.connect(1, 2);
        g3.connect(2, 0);
        
        System.out.println("G3 - Vertices: 0, 1, 2 - Edges: (0<->1) (1<->2) (2<->0) - Biconnected: "+new BiconnectivityDetection(g3).apply().get()+"\n");
        
        Graph<Integer> g4 = GraphBuilders.makeSimpleGraph(false);
        
        g4.add(0);
        g4.add(1);
        g4.add(2);
        g4.add(3);
        g4.connect(0, 1);
        g4.connect(1, 2);
        g4.connect(2, 0);
        g4.connect(0, 3);
        
        System.out.println("G4 - Vertices: 0, 1, 2, 3 - Edges: (0<->1) (1<->2) (2<->0) (0<->3) - Biconnected: "+new BiconnectivityDetection(g4).apply().get()+"\n");
        
        Graph<Integer> g5 = GraphBuilders.makeSimpleGraph(false);
        
        g5.add(0);
        
        System.out.println("G5 - Vertices: 0 - Edges: NONE - Biconnected: "+new BiconnectivityDetection(g5).apply().get());
       
    }
}
