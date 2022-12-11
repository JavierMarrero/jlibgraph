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

import cu.edu.cujae.graphy.algorithms.FirstLevelCommunities;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.defaults.DefaultGraphBuilder;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Jose
 */
public class FirstLevelCommunitiesTest
{

    public static void getComunities (Graph<Integer> graph){
        ArrayList<Collection<Integer>>aux = (ArrayList<Collection<Integer>>) new FirstLevelCommunities(graph).apply().get();
        for(int i=0; i<aux.size();i++){
            Collection<Integer> c =aux.get(i);
            for (Integer in : c){
                System.out.print(in + " ");
            }
            System.out.println();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
      Graph<Integer> graph = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();
        
        for(int i = 0;i<10;i++)
        {
            graph.add(i, i);
        }
        
        graph.connect(0, 1);
        graph.connect(0, 2);
        graph.connect(0, 3);
        graph.connect(1, 2);
        graph.connect(1, 3);
        graph.connect(2, 3);
        graph.connect(2, 4);
        graph.connect(4, 5);
        graph.connect(5, 6);
        graph.connect(1, 9);
        graph.connect(9, 7);
        graph.connect(9, 8);
        graph.connect(7, 8);
        getComunities(graph);  
    }
    
}
