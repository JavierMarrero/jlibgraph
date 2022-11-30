/*
 * Copyright (C) 2022 Asus.
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

import cu.edu.cujae.graphy.algorithms.MinimumVertexCoverAlgorithm;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.defaults.DefaultGraphBuilder;

/**
 *
 * @author Amanda Mendez
 */
public class MinimumVertexCoverAlgorithmTest 
{
    public static void main(String[] args)
    {
      Graph<Integer> g = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();

        for (int i = 0; i < 7; ++i)
        {
            g.add(i);
        }

        g.connect(0, 1);
        g.connect(0, 2);
        g.connect(1, 3);
        g.connect(3, 4);
        g.connect(4, 5);
        g.connect(5, 6);
        
        System.out.println("Minimum vertex Cover: ");
        System.out.println(new MinimumVertexCoverAlgorithm(g).apply().get());
    }
}
