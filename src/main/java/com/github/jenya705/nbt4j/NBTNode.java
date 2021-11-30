package com.github.jenya705.nbt4j;

import java.io.InputStream;
import java.util.List;

/**
 * @author Jenya705
 */
public interface NBTNode {

    static NBTNode stream(InputStream inputStream, int floatDelta, int doubleDelta) {
        return new StreamNBTNode(inputStream, floatDelta, doubleDelta);
    }

    byte readByte();

    short readShort();

    int readInteger();

    long readLong();

    float readFloat();

    double readDouble();

    String readString();

    List<NBTNode> readArray();

}
