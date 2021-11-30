package com.github.jenya705.nbt4j;

import java.io.ByteArrayOutputStream;
import java.util.Collection;

/**
 * @author Jenya705
 */
public class StreamContainerNBTWriter extends StreamNBTWriter implements ContainerNBTWriter {

    public StreamContainerNBTWriter(int floatDelta, int doubleDelta) {
        super(new ByteArrayOutputStream(), floatDelta, doubleDelta);
    }

    @Override
    public ContainerNBTWriter writeByte(byte num) {
        return (ContainerNBTWriter) super.writeByte(num);
    }

    @Override
    public ContainerNBTWriter writeShort(short num) {
        return (ContainerNBTWriter) super.writeShort(num);
    }

    @Override
    public ContainerNBTWriter writeInteger(int num) {
        return (ContainerNBTWriter) super.writeInteger(num);
    }

    @Override
    public ContainerNBTWriter writeLong(long num) {
        return (ContainerNBTWriter) super.writeLong(num);
    }

    @Override
    public ContainerNBTWriter writeFloat(float num) {
        return (ContainerNBTWriter) super.writeFloat(num);
    }

    @Override
    public ContainerNBTWriter writeDouble(double num) {
        return (ContainerNBTWriter) super.writeDouble(num);
    }

    @Override
    public ContainerNBTWriter writeString(String string) {
        return (ContainerNBTWriter) super.writeString(string);
    }

    @Override
    public ContainerNBTWriter writeArray(Collection<NBTWriter> nodes) {
        return (ContainerNBTWriter) super.writeArray(nodes);
    }

    @Override
    public ContainerNBTWriter writeArray(NBTWriter[] nodes) {
        return (ContainerNBTWriter) super.writeArray(nodes);
    }

    @Override
    public byte[] getBytes() {
        return ((ByteArrayOutputStream) outputStream).toByteArray();
    }
}
