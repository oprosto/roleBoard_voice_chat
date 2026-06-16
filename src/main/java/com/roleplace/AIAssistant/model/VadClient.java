package com.roleplace.AIAssistant.model;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class VadClient {

    private final WebClient webClient = WebClient.create("http://localhost:8000");
    private final ObjectMapper mapper = new ObjectMapper();

    public File[] splitSpeech(File audioFile) {

        try {
            MultipartBodyBuilder builder = new MultipartBodyBuilder();
            builder.part("file", new FileSystemResource(audioFile));

            String response = webClient.post()
                    .uri("/vad")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(builder.build()))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JsonNode root = mapper.readTree(response);
            JsonNode chunks = root.get("chunks");

            List<File> result = new ArrayList<>();

            for (JsonNode node : chunks) {
                result.add(new File(node.asText()));
            }

            return result.toArray(new File[0]);

        } catch (Exception e) {
            throw new RuntimeException("VAD failed", e);
        }
    }
}