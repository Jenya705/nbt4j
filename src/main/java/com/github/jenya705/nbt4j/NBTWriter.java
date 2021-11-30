package com.github.jenya705.nbt4j;

import java.io.OutputStream;
import java.util.Collection;

/**
 * @author Jenya705
 */
public interface NBTWriter {

    static NBTWriter stream(OutputStream outputStream, int floatDelta, int doubleDelta) {
        return new StreamNBTWriter(outputStream, floatDelta, doubleDelta);
    }

    static ContainerNBTWriter container(int floatDelta, int doubleDelta) {
        return new StreamContainerNBTWriter(floatDelta, doubleDelta);
    }

    NBTWriter writeByte(byte num);

    NBTWriter writeShort(short num);

    NBTWriter writeInteger(int num);

    NBTWriter writeLong(long num);

    NBTWriter writeFloat(float num);

    NBTWriter writeDouble(double num);

    NBTWriter writeString(String string);

    NBTWriter writeArray(Collection<NBTWriter> nodes);

    NBTWriter writeArray(NBTWriter... nodes);

}
