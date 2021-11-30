package com.github.jenya705.nbt4j;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Jenya705
 */
public class StreamNBTWriter implements NBTWriter {

    protected final OutputStream outputStream;

    private final int floatDelta;
    private final int floatDeltaLength;
    private final int doubleDelta;
    private final int doubleDeltaLength;

    public StreamNBTWriter(OutputStream outputStream, int floatDelta, int doubleDelta) {
        this.outputStream = outputStream;
        this.floatDelta = (int) Math.pow(2, floatDelta);
        this.floatDeltaLength = floatDelta;
        this.doubleDelta = (int) Math.pow(2, doubleDelta);
        this.doubleDeltaLength = doubleDelta;
    }

    @Override
    public NBTWriter writeByte(byte num) {
        write(new byte[]{num});
        return this;
    }

    @Override
    public NBTWriter writeShort(short num) {
        write(num, 2);
        return this;
    }

    @Override
    public NBTWriter writeInteger(int num) {
        write(num, 4);
        return this;
    }

    @Override
    public NBTWriter writeLong(long num) {
        write(num, 8);
        return this;
    }

    @Override
    public NBTWriter writeFloat(float num) {
        long longNum = (long) num;
        writeLong(longNum);
        write(Math.round((num - longNum) * floatDelta), floatDeltaLength);
        return this;
    }

    @Override
    public NBTWriter writeDouble(double num) {
        long longNum = (long) num;
        writeLong(longNum);
        write(Math.round((num - longNum) * doubleDelta), doubleDeltaLength);
        return this;
    }

    @Override
    public NBTWriter writeString(String string) {
        writeInteger(string.length());
        write(string.getBytes());
        return this;
    }

    @Override
    public NBTWriter writeArray(Collection<NBTWriter> nodes) {
        writeInteger(nodes.size());
        for (NBTWriter writer: nodes) {
            if (!(writer instanceof ContainerNBTWriter)) {
                throw new IllegalArgumentException("NBT Writer is not container");
            }
            ContainerNBTWriter containerWriter = (ContainerNBTWriter) writer;
            byte[] bytes = containerWriter.getBytes();
            writeInteger(bytes.length);
            write(bytes);
        }
        return this;
    }

    @Override
    public NBTWriter writeArray(NBTWriter[] nodes) {
        return writeArray(Arrays.asList(nodes));
    }

    private void write(byte[] bytes) {
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void write(long num, int length) {
        long numCopy = num;
        byte[] result = new byte[length];
        for (int i = length - 1; i >= 0; --i) {
            result[i] = (byte) numCopy;
            numCopy >>= 8;
        }
        write(result);
    }

}
