package com.manhnd.accountservice.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manhnd.commonservice.kafka.constant.KafkaConstant;
import com.manhnd.commonservice.kafka.consumer.AbstractKafkaConsumer;
import com.manhnd.commonservice.kafka.events.profile.ProfileCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsummer extends AbstractKafkaConsumer<ProfileCreatedEvent> {


    protected EventConsummer(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected void processMessage(ProfileCreatedEvent message) {
        log.info("Consumed message: " + message.toString() + " at "+ message.getTimestamp()+ " with id: " + message.getEventId() + " with type: " + message.getEventType() + " with topic: " + getTopic() + " with group id: " + getGroupId());



    }

    @Override
    protected Class<ProfileCreatedEvent> getMessageType() {
        return ProfileCreatedEvent.class;
    }

    @Override
    public String getTopic() {
        return KafkaConstant.PROFILE_ONBOARDING_TOPIC;
    }

    @Override
    public String getGroupId() {
        return KafkaConstant.GROUP_ONBOARDING_TOPIC;
    }
}
