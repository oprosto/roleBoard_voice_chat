package com.roleplace.AIAssistant.v3;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.*;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class WhisperClient {

    private final CloseableHttpClient client = HttpClients.createDefault();

    public String transcribe(byte[] wav) {

        try {
            HttpPost post = new HttpPost("http://localhost:8001/inference");

            var entity = MultipartEntityBuilder.create()
                    .addBinaryBody(
                            "file",
                            new ByteArrayInputStream(wav),
                            ContentType.create("audio/wav"),
                            "audio.wav"
                    )
                    .addTextBody("language", "ru")
                    .build();

            post.setEntity(entity);

            try (CloseableHttpResponse response = client.execute(post)) {

                String body = response.getEntity() != null
                        ? EntityUtils.toString(response.getEntity())
                        : "";

                if (response.getCode() != 200) {
                    System.out.println("WHISPER ERROR: " + response.getCode());
                    System.out.println(body);
                }

                return body;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}