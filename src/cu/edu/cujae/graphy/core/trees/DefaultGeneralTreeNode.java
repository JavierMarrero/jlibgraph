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
package cu.edu.cujae.graphy.core.trees;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.TreeNode;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Default implementation of the {@link TreeNode} interface.
 *
 * @author Javier Marrero
 * @param <E>
 */
public class DefaultGeneralTreeNode<E> extends AbstractTreeNode<E> implements TreeNode<E>
{

    private final Set<Edge> children;

    public DefaultGeneralTreeNode(Object label, E data)
    {
        super(label, data);

        this.children = new HashSet<>();
    }

    @Override
    public boolean addEdge(Edge edge)
    {
        return children.add(edge);
    }

    @Override
    @SuppressWarnings ("unchecked")
    public Collection<TreeNode<E>> getChildren()
    {
        Collection<TreeNode<E>> childList = new LinkedList<>();
        for (Edge e : children)
        {
            childList.add((TreeNode<E>) e.getFinalNode());
        }
        return Collections.unmodifiableCollection(childList);
    }

    @Override
    public Edge getAdjacentEdge(Node<E> v)
    {
        for (Edge e : children)
        {
            if (e.getFinalNode().equals(v))
            {
                return e;
            }
        }
        return null;
    }

    @Override
    public Set<Edge> getConnectedEdges()
    {
        return Collections.unmodifiableSet(children);
    }

    @Override
    public Set<Edge> getEdgesConnectingSelf()
    {
        return Collections.emptySet();
    }

    @Override
    public boolean hasChildren()
    {
        return !children.isEmpty();
    }

    @Override
    public boolean isAdjacent(Node<E> v)
    {
        for (Edge e : children)
        {
            if (e.getFinalNode().equals(v))
            {
                return true;
            }
        }
        return false;
    }

}
