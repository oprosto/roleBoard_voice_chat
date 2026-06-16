package com.roleplace.AIAssistant.model;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileWriterService {

    private final String outputPath = "dnd_notes.txt";

    public synchronized void append(String text) {
        try (FileWriter fw = new FileWriter(outputPath, true)) {
            fw.write(text);
            fw.write("\n---------------------\n");
        } catch (IOException e) {
            throw new RuntimeException("File write failed", e);
        }
    }
}