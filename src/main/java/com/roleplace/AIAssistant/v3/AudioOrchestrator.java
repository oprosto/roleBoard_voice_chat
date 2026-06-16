package com.roleplace.AIAssistant.v3;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class AudioOrchestrator {

    private final AudioRingBuffer ringBuffer = new AudioRingBuffer();
    private final WhisperClient whisperClient = new WhisperClient();
    private final MicrophoneStream mic = new MicrophoneStream();

    @PostConstruct
    public void start() throws Exception {

        mic.start(ringBuffer);

        Thread t = new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(2500); // 5 sec window

                    byte[] pcm = ringBuffer.snapshotAndClear();

                    System.out.println("PCM snapshot = " + pcm.length);

                    if (pcm.length < 2000) continue;

                    byte[] wav = WavUtil.wrapPcm16ToWav(pcm, 16000, 1);

                    String text = whisperClient.transcribe(wav);

                    System.out.println("TRANSCRIPT = " + text);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t.setDaemon(true);
        t.start();
    }
}