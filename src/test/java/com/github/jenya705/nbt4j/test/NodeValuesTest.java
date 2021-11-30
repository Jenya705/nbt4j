package com.github.jenya705.nbt4j.test;

import com.github.jenya705.nbt4j.ContainerNBTWriter;
import com.github.jenya705.nbt4j.NBTNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @author Jenya705
 */
public class NodeValuesTest {

    @Test
    public void first() {
        ContainerNBTWriter writer =
                ContainerNBTWriter.container(8, 8)
                        .writeArray(
                                ContainerNBTWriter
                                        .container(8, 8)
                                        .writeInteger(32)
                                        .writeString("hello"),
                                ContainerNBTWriter
                                        .container(8, 8)
                                        .writeInteger(0)
                                        .writeString("bye")
                        );
        NBTNode node = NBTNode.stream(new ByteArrayInputStream(writer.getBytes()), 8, 8);
        List<NBTNode> nodes = node.readArray();
        Assertions.assertEquals(32, nodes.get(0).readInteger());
        Assertions.assertEquals("hello", nodes.get(0).readString());
        Assertions.assertEquals(0, nodes.get(1).readInteger());
        Assertions.assertEquals("bye", nodes.get(1).readString());
    }
}
