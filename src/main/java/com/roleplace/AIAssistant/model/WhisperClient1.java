package com.roleplace.AIAssistant.model;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.http.HttpClient;

@Service
public class WhisperClient1 {

    private final HttpClient client = HttpClient.newHttpClient();

    public String transcribe(File wavFile) {

        try {

            HttpPost post = new HttpPost("http://localhost:8001/inference");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.addBinaryBody(
                    "file",
                    wavFile,
                    ContentType.create("audio/wav"),
                    wavFile.getName()
            );

            builder.addTextBody("language", "ru");
            builder.addTextBody("response_format", "text");

            post.setEntity(builder.build());

            try (CloseableHttpClient client = HttpClients.createDefault()) {
                CloseableHttpResponse response = client.execute(post);

                return EntityUtils.toString(response.getEntity());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}