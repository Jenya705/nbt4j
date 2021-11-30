package com.github.jenya705.nbt4j.test;

import com.github.jenya705.nbt4j.ContainerNBTWriter;
import com.github.jenya705.nbt4j.NBTNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

/**
 * @author Jenya705
 */
public class EasyValuesTest {

    @Test
    public void first() {
        ContainerNBTWriter nbtWriter =
                ContainerNBTWriter.container(8, 8)
                        .writeLong(128)
                        .writeInteger(-128)
                        .writeInteger(121)
                        .writeFloat(0.22f)
                        .writeDouble(0.5d)
                        .writeString("hello, world!");
        NBTNode nbtNode = NBTNode.stream(new ByteArrayInputStream(nbtWriter.getBytes()), 8, 8);
        Assertions.assertEquals(128, nbtNode.readLong());
        Assertions.assertEquals(-128, nbtNode.readInteger());
        Assertions.assertEquals(121, nbtNode.readInteger());
        Assertions.assertEquals(0.22f, nbtNode.readFloat(), 0.1f);
        Assertions.assertEquals(0.5d, nbtNode.readDouble());
        Assertions.assertEquals("hello, world!", nbtNode.readString());
    }
}
