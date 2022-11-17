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
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * An implementation of the {@link Tuple} interface that uses hash mapping to provide O(1) access.
 *
 * @author Javier Marrero
 * @param <E>
 */
public final class HashTuple<E> implements Tuple<E>
{

    private E[] fastArray;
    private boolean frozen;
    private final HashSet<E> set;

    public HashTuple()
    {
        this(5);
    }

    public HashTuple(int initialCapacity)
    {
        fastArray = null;
        frozen = false;
        set = new HashSet<>(initialCapacity);
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
        return set.contains(o);
    }

    @Override
    public boolean containsAll(
            Collection<?> c)
    {
        return set.containsAll(c);
    }

    @Override
    @SuppressWarnings ("unchecked")
    public void freeze()
    {
        this.frozen = true;
        this.fastArray = (E[]) set.toArray();
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
        return set.isEmpty();
    }

    @Override
    public Iterator<E> iterator()
    {
        return set.iterator();
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
        return set.size();
    }

    @Override
    public Object[] toArray()
    {
        return set.toArray();
    }

    @Override
    @SuppressWarnings ("unchecked")
    public <T> T[] toArray(T[] a)
    {
        return (T[]) fastArray;
    }

}
