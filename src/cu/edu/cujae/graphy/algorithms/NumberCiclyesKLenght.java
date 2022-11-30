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
package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Graph;

/**
 *Find number of ways from a node to make a loop of size K
 * in undirected complete connected graph of N nodes
 * @author Jose
 */
public class NumberCiclyesKLenght<T> extends AbstractAlgorithm<Integer>
{
    private int amountVertices;
    private int k;
    
    public NumberCiclyesKLenght (Graph<T> graph, int k){
        super (-1);
        this.k = k;
        this.amountVertices = graph.size();
    }
    
    @Override
    public Algorithm apply()
    {
        int p = 1;
        
        if(k%2 != 0)
        {
            p = -1;
        }
        
        setResult ((int)(Math.pow(amountVertices-1, k) + p*(amountVertices-1))/amountVertices);
        return this;
    }
    
}
