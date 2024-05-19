package com.example.sharedkernel.domain.base.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;


import java.util.Date;
import java.time.Instant;

@Getter
public abstract class DomainEvent {
    private final String topic;
    private final Date date;

    public DomainEvent(String topic) {
        this.date = Date.from(Instant.ofEpochSecond(Instant.now().toEpochMilli()));
        this.topic = topic;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        try {
            result = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {

        }
        return result;
    }

    public String topic() {
        return topic;
    }
    public static <E extends DomainEvent> E fromJson(String json, Class<E> domainEventClass)throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, domainEventClass);
    }


}