package com.example.ordermanagment.xport.client;

import com.example.ordermanagment.domain.valueobjects.Ticket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class TicketClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public TicketClient(@Value("${app.games.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var request = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(request);
    }
    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Ticket> findAll() {
        try {
            ResponseEntity<List<Ticket>> response = restTemplate.exchange(
                    uri().path("/api/ticket").build().toUri(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Ticket>>() {}
            );

            if (response.getBody() != null) {
                return response.getBody();
            } else {
                return Collections.emptyList();
            }
        } catch (RestClientException e) {
            // Optionally log the error
            // logger.error("Error fetching tickets", e);
            return Collections.emptyList();
        }
    }

}