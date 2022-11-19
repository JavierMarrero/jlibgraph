/*
 * Copyright (C) 2022 Ananda.
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

import cu.edu.cujae.graphy.algorithms.ColorableAlgorithm;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

/**
 *
 * @author Ananda
 */
public class ColorableAlgorithmTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Primer grafo, con 2 colores, 5 vértices y 5 aristas. No se cumple (false)!
        Graph<Integer> graph1 = GraphBuilders.makeSimpleWeightedGraph(false);
        int mColors1 = 2;
        
        for (int i = 0; i < 5; i++) {
            graph1.add(i);
        }
        graph1.connect(0, 3);
        graph1.connect(0, 2);
        graph1.connect(1, 0);
        graph1.connect(2, 1);
        graph1.connect(3, 4);

        System.out.println(graph1);
        System.out.println("Colorable´s Algorithm applicable to the first graph:");
        System.out.println(new ColorableAlgorithm<>(graph1, graph1.iterator(0), mColors1).apply().get());
        
        //Segundo grafo, con 3 colores, 5 vértices y 5 aristas. Se cumple (true)!
        Graph<Integer> graph2 = GraphBuilders.makeSimpleWeightedGraph(false);
        int mColors2 = 3;
        
        for (int i = 0; i < 5; i++) {
            graph2.add(i);
        }
        graph2.connect(0, 3);
        graph2.connect(0, 2);
        graph2.connect(1, 0);
        graph2.connect(2, 1);
        graph2.connect(3, 4);

        System.out.println(graph2);
        System.out.println("Colorable´s Algorithm applicable to the second graph:");
        System.out.println(new ColorableAlgorithm<>(graph2, graph2.iterator(0), mColors2).apply().get());
    }
    
}
