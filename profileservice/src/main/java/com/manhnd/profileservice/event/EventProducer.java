package com.manhnd.profileservice.event;

import com.manhnd.commonservice.kafka.constant.KafkaConstant;
import com.manhnd.commonservice.kafka.events.profile.ProfileCreatedEvent;
import com.manhnd.commonservice.kafka.producer.IKafkaProducer;
import com.manhnd.profileservice.model.ProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventProducer {

    @Autowired
    private IKafkaProducer iKafkaProducer;

    public void sendEvent(String topic, Object message) {
        iKafkaProducer.sendMessage(topic, message);
    }

    public void sendProfileCreatedEvent(ProfileDTO profile, Long idProfile) {
        ProfileCreatedEvent event = new ProfileCreatedEvent();
        event.setId(idProfile);
        event.setEmail(profile.getEmail());
        event.setName(profile.getName());
        event.setRole(profile.getRole());
        event.setStatus(profile.getStatus());
        event.setInitialBalance(profile.getInitalBalance());
        iKafkaProducer.sendMessage(KafkaConstant.PROFILE_ONBOARDING_TOPIC, event);
    }

}
