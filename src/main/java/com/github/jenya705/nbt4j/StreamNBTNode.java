package com.github.jenya705.nbt4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jenya705
 */
public class StreamNBTNode implements NBTNode {

    private static final int nextByte = 256;

    private final InputStream inputStream;
    private final int floatDelta;
    private final int floatDeltaLength;
    private final int doubleDelta;
    private final int doubleDeltaLength;

    public StreamNBTNode(InputStream inputStream, int floatDelta, int doubleDelta) {
        this.inputStream = inputStream;
        this.floatDelta = (int) Math.pow(2, floatDelta);
        this.floatDeltaLength = floatDelta;
        this.doubleDelta = (int) Math.pow(2, doubleDelta);
        this.doubleDeltaLength = doubleDelta;
    }

    @Override
    public byte readByte() {
        return readNextBytes(1)[0];
    }

    @Override
    public short readShort() {
        byte[] bytes = readNextBytes(2);
        return (short) (bytes[1] * nextByte + bytes[0]);
    }

    @Override
    public int readInteger() {
        return (int) fromBytes(readNextBytes(4));
    }

    @Override
    public long readLong() {
        return fromBytes(readNextBytes(8));
    }

    @Override
    public float readFloat() {
        return readLong() + fromBytes(readNextBytes(floatDeltaLength)) / (float) floatDelta;
    }

    @Override
    public double readDouble() {
        return readLong() + fromBytes(readNextBytes(doubleDeltaLength)) / (double) doubleDelta;
    }

    @Override
    public String readString() {
        return new String(readNextBytes(readInteger()));
    }

    @Override
    public List<NBTNode> readArray() {
        List<NBTNode> result = new ArrayList<>();
        int length = readInteger();
        for (int i = 0; i < length; ++i) {
            int nodeLength = readInteger();
            result.add(new StreamNBTNode(
                    new ByteArrayInputStream(readNextBytes(nodeLength)),
                    floatDeltaLength, doubleDeltaLength
            ));
        }
        return result;
    }

    private byte[] readNextBytes(int bytes) {
        try {
            byte[] result = new byte[bytes];
            int length = inputStream.read(result);
            if (length < bytes) {
                throw new IllegalArgumentException("Get length smaller than requested");
            }
            return result;
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private long fromBytes(byte[] bytes) {
        long result = 0;
        for (byte currentByte: bytes) {
            result <<= 8;
            result += currentByte & 0xFF;
        }
        return result;
    }

}
