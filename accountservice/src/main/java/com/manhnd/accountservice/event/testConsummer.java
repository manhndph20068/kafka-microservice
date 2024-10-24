package com.manhnd.accountservice.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manhnd.commonservice.kafka.consumer.AbstractKafkaConsumer;
import com.manhnd.commonservice.kafka.events.profile.ProfileCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class testConsummer extends AbstractKafkaConsumer<String> {

    protected testConsummer(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected void processMessage(String message) {
        System.out.println("Consumed message: " + message + " --- with topic: " + getTopic() + " -- with group id: " + getGroupId());
    }

    @Override
    protected Class<String> getMessageType() {
        return String.class;
    }

    @Override
    public String getTopic() {
        return "test";
    }

    @Override
    public String getGroupId() {
        return "grouptest";
    }
}
