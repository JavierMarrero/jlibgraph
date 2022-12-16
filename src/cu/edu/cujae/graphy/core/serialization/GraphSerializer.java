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

import cu.edu.cujae.graphy.core.Graph;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Presents serialization and de-serialization operations for graphs regardless of their type.
 *
 * @author Javier Marrero
 */
public interface GraphSerializer
{

    public static byte[] MAGIC_NUMBER =
    {
        0x41,
        0x4D,
        0x42,
        0x4D
    };

    public static boolean matchesMagicNumber(byte[] argument)
    {
        return Arrays.equals(argument, MAGIC_NUMBER);
    }

    /**
     * Serializes a graph and writes the result via the provided output stream.
     *
     * @param destination
     * @param graph
     * @param serializer
     *
     * @throws IOException
     */
    public void serialize(OutputStream destination, Graph<?> graph, CustomSerializer<Object> serializer) throws
            IOException;

    /**
     * De-serializes a graph.
     *
     * @param origin
     * @param customSerializer
     *
     * @return
     *
     * @throws IOException
     */
    public Graph<?> deserialize(InputStream origin, CustomSerializer<Object> customSerializer) throws IOException,
                                                                                                      ClassNotFoundException;
}
