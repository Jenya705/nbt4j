package com.github.jenya705.nbt4j;

import java.util.Collection;

/**
 * @author Jenya705
 */
public interface ContainerNBTWriter extends NBTWriter {

    static ContainerNBTWriter container(int floatDelta, int doubleDelta) {
        return new StreamContainerNBTWriter(floatDelta, doubleDelta);
    }

    @Override
    ContainerNBTWriter writeByte(byte num);

    @Override
    ContainerNBTWriter writeShort(short num);

    @Override
    ContainerNBTWriter writeInteger(int num);

    @Override
    ContainerNBTWriter writeLong(long num);

    @Override
    ContainerNBTWriter writeFloat(float num);

    @Override
    ContainerNBTWriter writeDouble(double num);

    @Override
    ContainerNBTWriter writeString(String string);

    @Override
    ContainerNBTWriter writeArray(Collection<NBTWriter> nodes);

    @Override
    ContainerNBTWriter writeArray(NBTWriter... nodes);

    byte[] getBytes();

}
