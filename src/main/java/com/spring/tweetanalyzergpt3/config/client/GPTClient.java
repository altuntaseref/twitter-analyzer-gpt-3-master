package com.spring.tweetanalyzergpt3.config.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GPTClient {
    private final String API_KEY = "**************";

    private final String API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String analyzeTweet(String tweet) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", "Sentiment analysis of tweet:\n\n" + tweet);
        requestBody.put("temperature", 0.5);
        requestBody.put("max_tokens", 1024);
       // requestBody.put("model","sentiment-beta-2");

        String responseText = null;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(API_URL);
            request.addHeader("Authorization", "Bearer " + API_KEY);
            request.addHeader("Content-Type", "application/json");
            StringEntity body = new StringEntity(objectMapper.writeValueAsString(requestBody),
                    ContentType.APPLICATION_JSON);
            request.setEntity(body);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                responseText = new String(inputStream.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseText;
    }
}
