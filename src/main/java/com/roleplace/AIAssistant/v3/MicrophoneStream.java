package com.roleplace.AIAssistant.v3;

import javax.sound.sampled.*;

public class MicrophoneStream {

    public void start(AudioRingBuffer ringBuffer) throws Exception {

        AudioFormat format = new AudioFormat(
                16000,
                16,
                1,
                true,
                false
        );

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);

        line.open(format);
        line.start();

        byte[] buf = new byte[4096];

        Thread t = new Thread(() -> {
            while (true) {
                int read = line.read(buf, 0, buf.length);

                if (read > 0) {
                    byte[] copy = new byte[read];
                    System.arraycopy(buf, 0, copy, 0, read);
                    ringBuffer.append(copy, read);
                }
            }
        });

        t.setDaemon(true);
        t.start();
    }
}