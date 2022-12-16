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
package cu.edu.cujae.graphy.core.serialization;

import java.io.IOException;

/**
 * A custom serialization mechanism must be provided in order to ensure correct serialization of graphs.
 *
 * @author Javier Marrero
 * @param <T>
 */
public interface CustomSerializer<T>
{

    /**
     * Serializes an object.
     *
     * @param object
     *
     * @return
     *
     * @throws java.io.IOException
     */
    public byte[] serialize(final T object) throws IOException;

    /**
     * De-serializes an object.
     *
     * @param stream
     *
     * @return
     *
     * @throws java.io.IOException
     */
    public T deserialize(byte[] stream) throws IOException;
}
