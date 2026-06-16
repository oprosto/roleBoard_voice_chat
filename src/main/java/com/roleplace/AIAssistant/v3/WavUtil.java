package com.roleplace.AIAssistant.v3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WavUtil {

    public static byte[] wrapPcm16ToWav(byte[] pcm, int sampleRate, int channels) {

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int byteRate = sampleRate * channels * 2;
            int blockAlign = channels * 2;

            out.write("RIFF".getBytes());
            out.write(intToLE(36 + pcm.length));
            out.write("WAVE".getBytes());

            out.write("fmt ".getBytes());
            out.write(intToLE(16));
            out.write(shortToLE((short) 1));
            out.write(shortToLE((short) channels));
            out.write(intToLE(sampleRate));
            out.write(intToLE(byteRate));
            out.write(shortToLE((short) blockAlign));
            out.write(shortToLE((short) 16));

            out.write("data".getBytes());
            out.write(intToLE(pcm.length));
            out.write(pcm);

            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] intToLE(int v) {
        return new byte[]{
                (byte) v,
                (byte) (v >> 8),
                (byte) (v >> 16),
                (byte) (v >> 24)
        };
    }

    private static byte[] shortToLE(short v) {
        return new byte[]{
                (byte) v,
                (byte) (v >> 8)
        };
    }
}