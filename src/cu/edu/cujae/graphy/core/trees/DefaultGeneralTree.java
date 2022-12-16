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

import cu.edu.cujae.graphy.core.Graph;
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
        return nodes.put(label, newNode) == null;
    }

    @Override
    public TreeNode<E> add(TreeNode<E> parent, E data)
    {
        int l = generateLabel();
        if (add(l, data))
        {
            TreeNode<E> newNode = (TreeNode<E>) findNodeByLabel(l);
            if (parent == null)
            {
                if (root == null)
                {
                    root = newNode;
                }
                else
                {
                    // Substitute the root and attach
                    TreeNode<E> oldRoot = root;
                    root = newNode;

                    connect(root, oldRoot);
                }
            }
            else
            {
                connect(parent, newNode);
            }
            return newNode;
        }
        else
        {
            throw new IllegalStateException("The tree was unable to add a new node with data " + data);
        }
    }

    @Override
    public int countLevels()
    {
        TreeNode<E> rootNode = this.root;
        if (rootNode != null)
        {
            int firstLevelChildren = rootNode.getChildren().size();
            int[] levels = new int[firstLevelChildren];

            for (int i = 0; i < firstLevelChildren; ++i)
            {
                levels[i] = walkLevels(rootNode.getChildByIndex(i));
            }

            return max(levels) + 1;
        }
        return 0;
    }

    private int walkLevels(TreeNode<E> node)
    {
        if (node.hasChildren())
        {
            int childCount = node.getChildren().size();
            int[] levels = new int[childCount];

            for (int i = 0; i < childCount; ++i)
            {
                levels[i] = walkLevels(node.getChildByIndex(i));
            }
            return max(levels) + 1;
        }
        return 1;
    }

    @Override
    public Graph<E> duplicate() throws CloneNotSupportedException
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public E remove(Node<E> node)
    {
        if (nodes.containsKey(node.getLabel()) == false)
        {
            throw new IllegalArgumentException("Attempted to remove a node not belonging to this graph.");
        }

        // Find the parent and removeAt the child node NODE
        return node.get();
    }

    @Override
    public E removeAt(int u)
    {
        return remove(findNodeByLabel(u));
    }

    @Override
    public int size()
    {
        return nodes.size();
    }

    @Override
    protected boolean addNode(Node<E> node)
    {
        ///TODO: Fix this
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected Collection<Node<E>> getNodes()
    {
        return nodes.values();
    }

}
