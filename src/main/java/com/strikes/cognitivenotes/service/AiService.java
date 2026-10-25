package com.strikes.cognitivenotes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Value("${groq.api.key}")
    private String apiKey;

    private static final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";

    // Groq ke current active models (GPT OSS 20B / 120B)
    private static final String[] ACTIVE_MODELS = {
        "openai/gpt-oss-20b",
        "openai/gpt-oss-120b"
    };

    public String simplifyNote(String content) {
        RestTemplate restTemplate = new RestTemplate();
        String systemPrompt = "You are an expert AI assistant. Simplify and structure the given note into clear bullet points, key takeaways, and a short summary using professional Markdown formatting.";
        String userPrompt = "Note Content:\n\n" + content;

        Map<String, Object> messageSystem = Map.of("role", "system", "content", systemPrompt);
        Map<String, Object> messageUser = Map.of("role", "user", "content", userPrompt);

        for (String modelName : ACTIVE_MODELS) {
            try {
                Map<String, Object> requestBody = Map.of(
                    "model", modelName,
                    "messages", List.of(messageSystem, messageUser),
                    "temperature", 0.5
                );

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(apiKey);

                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

                ResponseEntity<Map> response = restTemplate.postForEntity(GROQ_URL, entity, Map.class);
                Map responseBody = response.getBody();

                if (responseBody != null && responseBody.containsKey("choices")) {
                    List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> choice = choices.get(0);
                        Map<String, Object> message = (Map<String, Object>) choice.get("message");
                        if (message != null && message.containsKey("content")) {
                            return (String) message.get("content");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Model " + modelName + " failed, trying backup model... Error: " + e.getMessage());
            }
        }
        return "Could not simplify note with AI at the moment. Please try again later.";
    }
}