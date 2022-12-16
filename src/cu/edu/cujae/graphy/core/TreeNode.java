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
package cu.edu.cujae.graphy.core;

import java.util.Collection;

/**
 * An interface representing nodes of a tree.
 *
 * @author Javier Marrero
 * @param <E>
 */
public interface TreeNode<E> extends Node<E>
{

    /**
     * Returns the parent of this node. If this node is not in a tree or is the root, returns null.
     *
     * @return
     */
    public TreeNode<E> getParent();

    /**
     * Returns the i<sup>th</sup> child.
     *
     * @param i
     *
     * @return
     */
    public TreeNode<E> getChildByIndex(int i);

    /**
     * Returns the first child of this node.
     *
     * @return
     */
    public TreeNode<E> getFirstChild();

    /**
     * Returns a {@link Collection} containing all the children nodes of this.
     *
     * @return
     */
    public Collection<TreeNode<E>> getChildren();

    /**
     * Returns if the node has any child nodes.
     *
     * @return
     */
    public boolean hasChildren();

    /**
     * Prints a node with correct indentation.
     *
     * @param level
     *
     * @return
     */
    public String toString(int level);
}
