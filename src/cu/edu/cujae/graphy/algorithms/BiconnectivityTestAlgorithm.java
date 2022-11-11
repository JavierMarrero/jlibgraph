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
package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Graph;

/**
 *
 * @author Amaya
 */
public class BiconnectivityTestAlgorithm<T> extends AbstractAlgorithm<Boolean>{

    
    
    
    public BiconnectivityTestAlgorithm(Graph<T> graph) throws IllegalArgumentException {
        super(Boolean.FALSE);
        
        if(graph.isDirected()) {
            throw new IllegalArgumentException("The graph is directed.");
        }
        
    }
    
    
    

    @Override
    public Algorithm<Boolean> apply() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
