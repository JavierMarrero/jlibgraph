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

import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * @author Javier Marrero
 */
public class BridgeFinderAlgorithm extends AbstractAlgorithm<List<Integer>>
{

    public BridgeFinderAlgorithm()
    {
        super(new LinkedList<>());
    }

    @Override
    public Algorithm<List<Integer>> apply()
    {

        // This is mandated by the interface
        return this;
    }

}
