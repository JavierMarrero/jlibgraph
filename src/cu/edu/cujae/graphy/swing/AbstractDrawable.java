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
package cu.edu.cujae.graphy.swing;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import javax.swing.JComponent;

/**
 *
 * @author Javier Marrero
 */
@SuppressWarnings("serial")
public class AbstractDrawable implements Drawable
{

    private Collection<Connector> connectors;
    private Point location;

    public AbstractDrawable(Point location)
    {
        this.location = location;
    }

    @Override
    public Collection<Connector> getConnectors()
    {
        return Collections.unmodifiableCollection(connectors);
    }

    @Override
    public Point getLocation()
    {
        return location;
    }

    @Override
    public void paintComponent(Graphics g)
    {

    }


}
