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
package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.utils.MapBiArray;
import static java.sql.JDBCType.INTEGER;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Mínimo match bipartito: Un match bipartito es el conjunto de aristas elegidas
 * de tal forma que dos aristas no compartan vértices. 
 * 
 * @author Amanda Mendez
 * @param<T>
 */
public class MaximumBipartiteMatch<T> extends AbstractAlgorithm<Integer>
{
     private final MapBiArray<Integer, Integer, Boolean> bg;
     private final Map<Integer,Integer> matchR;
     private final Set<Integer> seen;
     private final Set<Integer> setM;
     private final Set<Integer> setN;

    public MaximumBipartiteMatch(MapBiArray<Integer, Integer, Boolean> bg)
    {
        super(null);
        
        // Initialize fields
        this.matchR =  new HashMap<>();
        this.seen = new HashSet<>();
        this.bg = bg;
        this.setM = bg.keySet();
        this.setN = new HashSet<>();
        
        
        
        // Initialize all jobs as available
        // The value of map is the applicant number assigned to a job 
        // (represented by a key) 
        // (-1) indicates nobody is assigned
         for (int m: bg.keySet())
        {
            for(int n : bg.get(m).keySet())
            {
                matchR.put(n, -1);
                setN.add(n);
            }
        }
    }
        
    @Override
    public Algorithm<Integer> apply() 
    {
        int result = 0;
        
        for(int u : setM)
        {
            //Find if the applicant 'u' can get a job
            if(walk(u,seen,matchR))
            {
                result++;
            }
        }
       
        return this;
    }
    
    public boolean walk(int u, Set<Integer> seen, Map<Integer,Integer> matchR)
    {
        for (int v : setN)
        {
            if(bg.get(u, v) == true && !seen.contains(v))
            {
                // Mark v as visited
                seen.add(v);
                
                if(matchR.get(v) < 0 || walk(matchR.get(v),seen,matchR))
                {
                    matchR.replace(v, u);
                    return true;
                }
            }
        }    
        
        return false;
    }
}
