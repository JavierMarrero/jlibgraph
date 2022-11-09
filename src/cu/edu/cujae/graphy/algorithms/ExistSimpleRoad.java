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
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Jose
 * @param <V>
 */
public class ExistSimpleRoad<V> extends AbstractAlgorithm<Boolean> {
    private Graph<V> graph;
    private int k;
    private final GraphIterator<V> iterator;
    
    public ExistSimpleRoad (Graph<V> graph, GraphIterator<V> iterator ,int k){
        super(Boolean.FALSE);
        this.graph=graph;
        this.k = k;
        this.iterator = (iterator);
    }
    
    @Override
    public Algorithm apply() {
        Set<Integer> visited = new TreeSet<>();
        boolean stop = false;
        while(iterator.hasNext() && k>0 && !stop){
            iterator.next();
            /*Verificar si ya se ha visitado ese nodo, es decir si existe un ciclo*/
            if(visited.contains(iterator.getLabel())){
                setResult(Boolean.FALSE);
                stop = false;
            }else{
                visited.add(iterator.getLabel());
                k--;
            }
        }
        if(!stop && k>0){
            setResult(Boolean.FALSE);
        }
        
        
        return this;
    }
    
}
