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
package cu.edu.cujae.graphy.core.trees;

import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.TreeNode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link Tree} interface.
 *
 * @author Javier Marrero
 * @param <E>
 */
public class DefaultGeneralTree<E> extends AbstractTree<E>
{

    private final Map<Integer, Node<E>> nodes;
    private TreeNode<E> root;

    public DefaultGeneralTree()
    {
        this.nodes = new HashMap<>();
        this.root = null;
    }

    @Override
    public boolean add(int label, E data)
    {
        TreeNode<E> newNode = new DefaultGeneralTreeNode<>(label, data);
        if (root == null)
        {
            root = newNode;
        }
        return nodes.put(label, newNode) == null;
    }

    @Override
    public boolean add(TreeNode<E> parent, E data)
    {
        throw new UnsupportedOperationException("not supported yet!");
    }

    @Override
    public Node<E> findNodeByLabel(int label)
    {
        return nodes.get(label);
    }

    @Override
    public Collection<Integer> getLabels()
    {
        return nodes.keySet();
    }

    @Override
    public TreeNode<E> getRoot()
    {
        return root;
    }

    @Override
    public boolean isRoot(TreeNode<E> node)
    {
        return root.equals(node);
    }

    @Override
    public boolean isVertexAdjacent(int u, int v)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isWeighted()
    {
        ///TODO: Fixme
        return false;
    }

    @Override
    public int size()
    {
        return nodes.size();
    }

    @Override
    protected Collection<Node<E>> getNodes()
    {
        return nodes.values();
    }

}
