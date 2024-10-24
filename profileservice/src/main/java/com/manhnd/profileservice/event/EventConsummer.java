package com.manhnd.profileservice.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manhnd.commonservice.kafka.constant.KafkaConstant;
import com.manhnd.commonservice.kafka.consumer.AbstractKafkaConsumer;
import com.manhnd.commonservice.kafka.events.account.AccountCreatedEvent;
import com.manhnd.profileservice.model.ProfileDTO;
import com.manhnd.profileservice.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsummer extends AbstractKafkaConsumer<AccountCreatedEvent> {

    @Autowired
    private IProfileService iProfileService;

    protected EventConsummer(ObjectMapper objectMapper) {
        super(objectMapper);
    }


    @Override
    protected void processMessage(AccountCreatedEvent message) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(message.getIdProfile());
        profileDTO.setStatus(message.getStatus());
        iProfileService.updateProfile(profileDTO);
    }

    @Override
    protected Class<AccountCreatedEvent> getMessageType() {
        return AccountCreatedEvent.class;
    }

    @Override
    public String getTopic() {
        return KafkaConstant.PROFILE_ONBOARDED_TOPIC;
    }

    @Override
    public String getGroupId() {
        return KafkaConstant.GROUP_ONBOARDED_TOPIC;
    }
}
