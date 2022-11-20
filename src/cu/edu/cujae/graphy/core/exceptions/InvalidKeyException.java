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
package cu.edu.cujae.graphy.core.exceptions;

/**
 * An exception that is thrown when attempting to access an association with an invalid key.
 *
 * @author Javier Marrero
 */
public class InvalidKeyException extends RuntimeException
{

    private static final long serialVersionUID = 3435465734546764211L;

    private final Object map;
    private final Object key;

    public InvalidKeyException(Object container, Object key)
    {
        super("Attempted invalid key mapping M(" + key + ") with M = " + container);

        this.map = container;
        this.key = key;
    }

    public Object getMap()
    {
        return map;
    }

    public Object getKey()
    {
        return key;
    }
}
