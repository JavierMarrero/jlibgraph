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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation for various serialization types.
 *
 * @author Javier Marrero
 */
public class DefaultCustomSerializers
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCustomSerializers.class);

    private static class StringSerializer implements CustomSerializer<String>
    {

        @Override
        public String deserialize(byte[] stream)
        {
            return String.valueOf(stream);
        }

        @Override
        public byte[] serialize(String object)
        {
            return object.getBytes(Charset.forName("UTF-8"));
        }

    }

    private static class IntegerSerializer implements CustomSerializer<Integer>
    {

        @Override
        public Integer deserialize(byte[] stream) throws IOException
        {
            try
            {
                ByteArrayInputStream bais = new ByteArrayInputStream(stream);
                DataInputStream dis = new DataInputStream(bais);

                int result = dis.readInt();

                dis.close();
                return result;
            }
            catch (IOException ex)
            {
                LOGGER.error("{}", ex);
                throw ex;
            }
        }

        @Override
        public byte[] serialize(Integer object) throws IOException
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(4);
            byte[] byteArray;
            try ( DataOutputStream stream = new DataOutputStream(baos))
            {
                stream.writeInt(object);
                byteArray = baos.toByteArray();
            }

            return byteArray;
        }

    }

    /**
     * Builds a new serializer for String types.
     *
     * @return
     */
    public static CustomSerializer<?> getStringSerializer()
    {
        return new StringSerializer();
    }

    /**
     * Return a new serializer for Integer types.
     *
     * @return
     */
    public static CustomSerializer<?> getIntegerSerializer()
    {
        return new IntegerSerializer();
    }
}
