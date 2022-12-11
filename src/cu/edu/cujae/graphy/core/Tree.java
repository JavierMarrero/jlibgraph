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

/**
 * A tree is a data structure represented by a directed acyclic graph, imitating the shape of a tree. It is a set of
 * connected nodes. A node is the basic unit over which the tree is constructed and may have zero or several children.
 * A node A is parent of a node B if there is a direct connection from A to B. There can be only a single node without
 * a parent and that is called root.
 * <p>
 * This interface represents trees on its most wide definition.
 *
 * @see Graph
 *
 * @author Javier Marrero
 * @param <E>
 */
public interface Tree<E> extends Graph<E>
{

    /**
     * Adds a new children to the parent node.
     *
     * @param parent
     * @param data
     *
     * @return
     */
    public TreeNode<E> add(TreeNode<E> parent, E data);

    /**
     * Count how many levels this tree have. The number of levels is the depth of the tree.
     *
     * @return
     */
    public int countLevels();

    /**
     * Finds the first node containing data.
     *
     * @param data
     *
     * @return
     */
    public TreeNode<E> findFirstNode(E data);

    /**
     * Retrieves a node by its label.
     *
     * @param label
     *
     * @return
     */
    public TreeNode<E> getNodeByLabel(int label);

    /**
     * Returns the height of the tree. The height of a tree is a recursive relation defined by the following formula:
     * <ul>
     * <li>0 <i>if the tree contains the root only</i></li>
     * <li>max(H(c<sub>1</sub>), H(c<sub>2</sub>), ..., H(c<sub>n</sub>) <i>if the tree contains more nodes</i></li>
     * </ul>
     *
     * @return an integer representing the tree height
     */
    public int getHeight();

    /**
     * Returns a reference to the root of this tree.
     *
     * @return {@link TreeNode}
     */
    public TreeNode<E> getRoot();

    /**
     * Returns the result of testing wether the provided node is the root node of this tree.
     *
     * @param node
     *
     * @return
     */
    public boolean isRoot(TreeNode<E> node);
}
