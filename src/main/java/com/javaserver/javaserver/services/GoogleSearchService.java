package com.javaserver.javaserver.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

@Service
public class GoogleSearchService {

    @Value("${google.api.key}")
    private String API_KEY;

    @Value("${google.search.engine.id}")
    private String SEARCH_ENGINE_ID;

    public boolean isBusinessAvailableOnline(String businessName) {
        try {
            String query = URLEncoder.encode(businessName, StandardCharsets.UTF_8);
            String url = "https://www.googleapis.com/customsearch/v1?q=" + query +
                    "&key=" + API_KEY + "&cx=" + SEARCH_ENGINE_ID;

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            JSONObject body = new JSONObject(response.getBody());

            return body.has("items") && body.getJSONArray("items").length() > 0;

        } catch (Exception e) {
            System.out.println("Google Search API error: " + e.getMessage());
            return false;
        }
    }
}

