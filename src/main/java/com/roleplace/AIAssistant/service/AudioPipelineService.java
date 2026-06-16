package com.roleplace.AIAssistant.service;

import com.roleplace.AIAssistant.model.FileWriterService;
import com.roleplace.AIAssistant.model.VadClient;
import com.roleplace.AIAssistant.model.WhisperClient1;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class AudioPipelineService {

    private final VadClient vadClient;
    private final WhisperClient1 whisperClient;
    private final FileWriterService fileWriterService;

    public AudioPipelineService(VadClient vadClient,
                                WhisperClient1 whisperClient,
                                FileWriterService fileWriterService) {
        this.vadClient = vadClient;
        this.whisperClient = whisperClient;
        this.fileWriterService = fileWriterService;
    }

    public void processAudio(MultipartFile file) {
        try {
            File tempFile = File.createTempFile("audio_", ".wav");
            file.transferTo(tempFile);

            // 1. VAD segmentation
            File[] speechChunks = vadClient.splitSpeech(tempFile);

            // 2. Whisper transcription
            for (File chunk : speechChunks) {
                String text = whisperClient.transcribe(chunk);

                // 3. write notes
                fileWriterService.append(text);
            }

        } catch (Exception e) {
            throw new RuntimeException("Audio pipeline failed", e);
        }
    }
}