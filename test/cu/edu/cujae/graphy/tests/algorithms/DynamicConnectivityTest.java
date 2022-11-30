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

import cu.edu.cujae.graphy.algorithms.DynamicConnectivity;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

/**
 *
 * @author Jose
 */
public class DynamicConnectivityTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Graph<Integer> graph = GraphBuilders.makeSimpleGraph(false);
        for (int i = 0; i < 7; i++)
        {
            graph.add(i);
        }

        DynamicConnectivity<Integer> dc = new DynamicConnectivity<Integer>(graph);
        
        //Inicialmente no hay bordes, por lo que los nodos 0 y 1 estarán desconectados por lo que la respuesta será FALSE 
        System.out.println(dc.apply(1, 0, 1));
        
        //Los nodos 0 y 2 se conectarán a partir del nodo 1 por lo que la respuesta será TRUE
        dc.apply(2, 0, 1);
        dc.apply(2, 1, 2);
        System.out.println(dc.apply(1, 0, 2));
        
        dc.apply(2, 0, 2);
        dc.apply(2, 2, 3);
        //Los nodos 0 y 5 se encuentran desconectados por lo que la respuesta será FALSE
        System.out.println(dc.apply(1, 0, 5));
        
        //Los nodos 2 y 6 serán conectados a partir de varios nodos por lo que la respuesta será TRUE
        dc.apply(2, 3, 4);
        dc.apply(2,4,5);
        dc.apply(2, 5, 6);
        System.out.println(dc.apply(1, 2, 6));
        
    }
    
}
