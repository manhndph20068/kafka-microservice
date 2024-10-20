package com.manhnd.profileservice.event;

import com.manhnd.commonservice.kafka.producer.IKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventProducer {

    @Autowired
    private IKafkaProducer iKafkaProducer;

    public void sendEvent(String topic, String key, Object message) {
        iKafkaProducer.sendMessage(topic, key, message);
    }

}
