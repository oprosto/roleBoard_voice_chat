package com.roleplace.AIAssistant.v3;

import java.util.Arrays;

public class PcmBuffer {

    private byte[] buffer = new byte[0];

    public synchronized void append(byte[] data, int length) {
        int oldLen = buffer.length;
        buffer = Arrays.copyOf(buffer, oldLen + length);
        System.arraycopy(data, 0, buffer, oldLen, length);
    }

    public synchronized byte[] snapshotAndClear() {
        byte[] copy = Arrays.copyOf(buffer, buffer.length);
        buffer = new byte[0];
        return copy;
    }

    public synchronized int size() {
        return buffer.length;
    }
}