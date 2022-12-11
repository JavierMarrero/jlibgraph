/*
 * Copyright (C) 2022 CUJAE.
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

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class represents an associative tridimensional array.However, this is implemented using maps to allow for non
 * sequential keys to be used.
 * <p>
 * This implementation actually uses red black trees to implement the subjacent data structure.
 *
 * @author Javier Marrero
 * @param <K> First key type
 * @param <I> Second key type
 * @param <J> Third key type
 * @param <V> Value type
 */
public class MapTriArray<K, I, J, V>
{

    /// An associative array of maps.
    private final Map<K, Map<I, Map<J, V>>> dictionary;

    /**
     * Creates a new tridimensional array.
     */
    public MapTriArray()
    {
        this.dictionary = new TreeMap<>();
    }

    /**
     * Similar to operator [] on tridimensional arrays. Can be imagined as Map[i][j][k].
     *
     * @param k
     * @param i
     * @param j
     *
     * @return
     */
    public V get(K k, I i, J j)
    {
        return dictionary.get(k).get(i).get(j);
    }

    /**
     * Similar to operator [] on tridimensional arrays. It returns a {@link Map} instance holding all the elements on
     * the row j. The returned map is unmodifiable.
     *
     * @param k
     * @param i
     *
     * @return
     */
    public Map<J, V> get(K k, I i)
    {
        return Collections.unmodifiableMap(dictionary.get(k).get(i));
    }

    /**
     * Similar to operator [] on tridimensional arrays. It returns a {@link Map} instance holding all the elements on
     * the k<sup>th</sup> row. The returned map is inmutable.
     *
     * @param k
     *
     * @return
     */
    public Map<I, Map<J, V>> get(K k)
    {
        return Collections.unmodifiableMap(dictionary.get(k));
    }

    /**
     * Similar to the operation Map[k][i][j] = newValue
     *
     * @param k
     * @param i
     * @param j
     * @param newValue
     *
     * @return
     */
    public V put(K k, I i, J j, V newValue)
    {
        Map<I, Map<J, V>> secondLevel = dictionary.get(k);
        if (secondLevel == null)
        {
            secondLevel = new TreeMap<>();
            dictionary.put(k, secondLevel);
        }

        Map<J, V> thirdLevel = secondLevel.get(i);
        if (thirdLevel == null)
        {
            thirdLevel = new TreeMap<>();
            secondLevel.put(i, thirdLevel);
        }

        return thirdLevel.put(j, newValue);
    }

    public Set<K> keySet()
    {
        return dictionary.keySet();
    }
}
