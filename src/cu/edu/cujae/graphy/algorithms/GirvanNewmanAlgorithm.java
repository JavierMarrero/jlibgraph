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
package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.utils.HashTuple;
import cu.edu.cujae.graphy.utils.Tuple;
import java.util.Set;

/**
 * The <b>Girvan-Newman</b> algorithm for the detection and analysis of community structure relies on the iterative
 * elimination of edges that have the highest number of shortest paths between nodes passing through them. By removing
 * edges from the graph one-by-one, the network breaks down into smaller pieces, so-called communities.
 * <p>
 * The algorithm was introduced by Michelle Girvan and Mark Newman.
 *
 * @author Javier Marrero
 */
public class GirvanNewmanAlgorithm extends AbstractAlgorithm<Tuple<Set<Integer>>>
{

    public GirvanNewmanAlgorithm(Graph<?> graph)
    {
        super(new HashTuple<>());
    }

    @Override
    public Algorithm<Tuple<Set<Integer>>> apply()
    {

        // Mandated by the interface
        return this;
    }

    @Override
    public Tuple<Set<Integer>> get()
    {
        getResult().freeze();   // Freeze the tuple
        return super.get();
    }

}
