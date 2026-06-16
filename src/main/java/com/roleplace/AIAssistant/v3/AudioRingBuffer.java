package com.roleplace.AIAssistant.v3;

import java.util.Arrays;

public class AudioRingBuffer {

    private byte[] buffer = new byte[0];

    public synchronized void append(byte[] data, int len) {
        byte[] newBuf = Arrays.copyOf(buffer, buffer.length + len);
        System.arraycopy(data, 0, newBuf, buffer.length, len);
        buffer = newBuf;

        // limit ~15 sec PCM16 16kHz mono (~320 KB/s)
        int maxSize = 320_000 * 15;

        if (buffer.length > maxSize) {
            buffer = Arrays.copyOfRange(buffer, buffer.length - maxSize, buffer.length);
        }
    }

    // 🔥 CRITICAL FIX: IMMUTABLE SNAPSHOT
    public synchronized byte[] snapshotAndClear() {
        byte[] copy = Arrays.copyOf(buffer, buffer.length);
        buffer = new byte[0];
        return copy;
    }

    public synchronized int size() {
        return buffer.length;
    }
}