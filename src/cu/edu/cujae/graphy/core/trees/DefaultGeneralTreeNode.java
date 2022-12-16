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
import cu.edu.cujae.graphy.core.exceptions.InvalidKeyException;
import java.util.*;

/**
 * Default implementation of the {@link TreeNode} interface.
 *
 * @author Javier Marrero
 * @param <E>
 */
public class DefaultGeneralTreeNode<E> extends AbstractTreeNode<E> implements TreeNode<E>
{

    private Edge parent;
    private final Set<Edge> children;

    public DefaultGeneralTreeNode(Object label, E data)
    {
        super(label, data);

        this.children = new HashSet<>();
    }

    @Override
    public boolean addEdge(Edge edge)
    {
        if (edge.getFinalNode() instanceof DefaultGeneralTreeNode)
        {
            ((DefaultGeneralTreeNode<?>) edge.getFinalNode()).parent = edge;
        }
        return children.add(edge);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    @Override
    public int degree()
    {
        return children.size();
    }

    @Override
    public void disconnect()
    {
        ///TODO: Fixme
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Integer> getAllAdjacentVertices()
    {
        Collection<Integer> vertices = new LinkedList<>();
        getChildren().
                forEach(child -> 
                {
                    vertices.add(child.getLabel());
                });
        return Collections.unmodifiableCollection(vertices);
    }

    @Override
    public Collection<Integer> getAllVerticesArrivingSelf()
    {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(parent.getStartNode().getLabel());

        return Collections.unmodifiableCollection(result);
    }

    @Override
    public Collection<Integer> getAllVerticesDepartingSelf()
    {
        Collection<Integer> result = new ArrayList<>(children.size());
        for (Edge e : children)
        {
            result.add(e.getFinalNode().getLabel());
        }

        return Collections.unmodifiableCollection(result);
    }

    @Override
    public Object getAttribute(Object key) throws InvalidKeyException
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    @SuppressWarnings ("unchecked")
    public TreeNode<E> getChildByIndex(int i)
    {
        if (i > children.size() || i < 0)
        {
            throw new IndexOutOfBoundsException("Attempted to access ith children with index " + i
                                                        + " and size of children list is " + children.size());
        }

        int j = 0;
        Iterator<Edge> it = children.iterator();
        Edge edge = null;

        while (j++ <= i && it.hasNext())
        {
            edge = it.next();
        }

        if (edge == null)
        {
            throw new IllegalStateException("Could not find child number " + i);
        }
        return (TreeNode<E>) edge.getFinalNode();
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
    public Set<Edge> getEdgesDepartingSelf()
    {
        return Collections.unmodifiableSet(children);
    }

    @Override
    public Set<Edge> getEdgesArrivingSelf()
    {
        Set<Edge> result = new HashSet<>(1);
        result.add(parent);

        return result;
    }

    @Override
    public Map<Object, Object> getNodeAttributes()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    @SuppressWarnings ("unchecked")
    public TreeNode<E> getParent()
    {
        return (TreeNode<E>) parent.getStartNode();
    }

    @Override
    public boolean hasChildren()
    {
        return !children.isEmpty();
    }

    @Override
    public boolean isAdjacent(Node<E> v)
    {
        if (v.equals(parent.getStartNode()))
        {
            return true;
        }

        for (Edge e : children)
        {
            if (e.getFinalNode().equals(v))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAdjacentAndArriving(Node<E> v)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isAdjacentAndDeparting(Node<E> v)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object removeAttribute(Object key)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeEdge(Edge edge)
    {
        return children.remove(edge);
    }

    @Override
    public Object setAttribute(Object key, Object value)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
