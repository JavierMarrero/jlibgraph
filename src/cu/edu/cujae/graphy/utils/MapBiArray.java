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
package cu.edu.cujae.graphy.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Jose
 * @param <K> First key type
 * @param <I> Second key type
 * @param <V> Value type
 *
 */
public class MapBiArray<K, I, V>
{
    /// An associative array of maps.

    private final Map<K, Map<I, V>> dictionary;

    /**
     * Creates a new bidimensional array.
     */
    public MapBiArray()
    {
        this.dictionary = new TreeMap<>();
    }

    /**
     * Similar to operator [] on bidimensional arrays. Can be imagined as Map[i][j].
     *
     * @param k
     * @param i
     *
     * @return
     */
    public V get(K k, I i)
    {
        return dictionary.get(k).get(i);
    }

    /**
     * Similar to operator [] on bidimensional arrays. It returns a {@link Map} instance holding all the elements on
     * the k<sup>th</sup> row. The returned map is inmutable.
     *
     * @param k
     *
     * @return
     */
    public Map<I, V> get(K k)
    {
        return Collections.unmodifiableMap(dictionary.get(k));
    }

    /**
     * Similar to the operation Map[i][j] = newValue
     *
     * @param k
     * @param i
     * @param newValue
     *
     * @return
     */
    public V put(K k, I i, V newValue)
    {
        Map<I, V> secondLevel = dictionary.get(k);
        if (secondLevel == null)
        {
            secondLevel = new TreeMap<>();
            dictionary.put(k, secondLevel);
        }
        return secondLevel.put(i, newValue);
    }
    
    public int size()
    {
        return dictionary.size();
    }
    
    public int subSize()
    {
        return get(dictionary.keySet().iterator().next()).size();
    }
    
    public Set<K> keySet()
    {
        return dictionary.keySet();
    }
    
    
}
