package com.manhnd.accountservice.event;

import com.manhnd.accountservice.model.AccountDTO;
import com.manhnd.commonservice.kafka.constant.KafkaConstant;
import com.manhnd.commonservice.kafka.events.account.AccountCreatedEvent;
import com.manhnd.commonservice.kafka.events.profile.ProfileCreatedEvent;
import com.manhnd.commonservice.kafka.producer.IKafkaProducer;
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

    public void sendAccountCreatedEvent(AccountDTO accountDTO, Long idProfile) {
        AccountCreatedEvent event = new AccountCreatedEvent();
        event.setIdProfile(idProfile);
        event.setEmail(accountDTO.getEmail());
        iKafkaProducer.sendMessage(KafkaConstant.PROFILE_ONBOARDED_TOPIC, event);
    }
}
