/*
 * Copyright (C) 2022 Javier Marrero.
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

import cu.edu.cujae.graphy.core.exceptions.InvalidOperationException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

/**
 * An implementation of the {@link Tuple} interface that uses hash mapping to provide O(1) access.
 *
 * @author Javier Marrero
 * @param <E>
 */
public final class HashTuple<E> extends AbstractTuple<E> implements Tuple<E>
{

    private boolean empty;
    private E[] fastArray;
    private boolean frozen;
    private int frozenSize;
    private HashSet<E> set;
    private final Comparator<E> comparator;

    public HashTuple()
    {
        this(250);
    }

    public HashTuple(int initialCapacity)
    {
        this(initialCapacity, new Comparator<E>()
     {
         @Override
         @SuppressWarnings ("unchecked")
         public int compare(E o1, E o2)
         {
             if ((o1 instanceof Comparable) == false)
             {
                 throw new ClassCastException(
                         "The elements in the tuple are not comparable therefore they can't be sorted. Please, provide a comparator.");
             }
             return ((Comparable<E>) o1).compareTo(o2);
         }
     });
    }

    public HashTuple(int initialCapacity, Comparator<E> comparator)
    {
        this.empty = false;
        this.fastArray = null;
        this.frozen = false;
        this.frozenSize = -1;
        this.set = new HashSet<>(initialCapacity);
        this.comparator = comparator;
    }

    @Override
    public boolean add(E e)
    {
        if (!frozen)
        {
            return set.add(e);
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean addAll(
            Collection<? extends E> c)
    {
        if (!frozen)
        {
            return set.addAll(c);
        }
        else
        {
            return false;
        }
    }

    @Override
    public void clear()
    {
        if (!frozen)
        {
            set.clear();
        }
        else
        {
            throw new InvalidOperationException("Attempted to remove elements from a frozen tuple");
        }
    }

    @Override
    public boolean contains(Object o)
    {
        if (!frozen)
        {
            return set.contains(o);
        }

        return Arrays.binarySearch(fastArray, o) >= 0;
    }

    @Override
    public boolean containsAll(
            Collection<?> c)
    {
        if (!frozen)
        {
            return set.containsAll(c);
        }

        boolean result = true;
        for (E element : fastArray)
        {
            result &= (Arrays.binarySearch(fastArray, element, comparator) >= 0);
        }
        return result;
    }

    @Override
    @SuppressWarnings ("unchecked")
    public void freeze()
    {
        this.empty = set.isEmpty();
        this.frozen = true;
        this.frozenSize = set.size();
        this.fastArray = (E[]) set.toArray();

        // Finalizes the set
        this.set = null;

        // Orders the array
        Arrays.sort(fastArray, comparator);
    }

    @Override
    public E get(int n)
    {
        if (!frozen)
        {
            throw new IllegalStateException("Freeze this tuple first.");
        }
        if (n < 0 || n > fastArray.length)
        {
            throw new IndexOutOfBoundsException(n);
        }
        return fastArray[n];
    }

    @Override
    public boolean isEmpty()
    {
        if (!frozen)
        {
            return set.isEmpty();
        }
        else
        {
            return empty;
        }
    }

    @Override
    public Iterator<E> iterator()
    {
        if (!frozen)
        {
            return set.iterator();
        }
        return new Iterator<E>()
        {
            private int currentIndex = 0;

            @Override
            public boolean hasNext()
            {
                return currentIndex != frozenSize;
            }

            @Override
            public E next()
            {
                return fastArray[currentIndex++];
            }
        };
    }

    @Override
    public boolean remove(Object o)
    {
        if (!frozen)
        {
            return set.remove(o);
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean removeAll(
            Collection<?> c)
    {
        if (!frozen)
        {
            return set.removeAll(c);
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean retainAll(
            Collection<?> c)
    {
        if (!frozen)
        {
            return set.retainAll(c);
        }
        else
        {
            return false;
        }
    }

    @Override
    public int size()
    {
        if (!frozen)
        {
            return set.size();
        }
        return frozenSize;
    }

    @Override
    public Object[] toArray()
    {
        if (!frozen)
        {
            return set.toArray();
        }
        else
        {
            return Arrays.copyOf(fastArray, frozenSize);
        }
    }

    @Override
    @SuppressWarnings ("unchecked")
    public <T> T[] toArray(T[] a)
    {
        return (T[]) fastArray;
    }

}
