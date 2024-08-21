package com.example.Biblioteka.service;

import com.example.Biblioteka.model.BookFromApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@Service
public class WolneLekturyClient {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public WolneLekturyClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<BookFromApi> getBooksByAuthor(String authorSlug) {
        String url = "https://wolnelektury.pl/api/authors/" + authorSlug + "/books/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), new TypeReference<List<BookFromApi>>() {});
            } else {
                return Collections.emptyList();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
