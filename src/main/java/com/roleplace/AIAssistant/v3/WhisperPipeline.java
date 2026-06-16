package com.roleplace.AIAssistant.v3;

import org.springframework.stereotype.Service;

@Service
public class WhisperPipeline {

    private final WhisperClient whisperClient1;

    public WhisperPipeline(WhisperClient whisperClient1) {
        this.whisperClient1 = whisperClient1;
    }

    public String process(byte[] pcmSnapshot) {

        // ❗ защита от пустых буферов
        if (pcmSnapshot == null || pcmSnapshot.length < 2000) {
            return "";
        }

        byte[] wav = WavUtil.wrapPcm16ToWav(
                pcmSnapshot,
                16000,
                1
        );

        return whisperClient1.transcribe(wav);
    }
}