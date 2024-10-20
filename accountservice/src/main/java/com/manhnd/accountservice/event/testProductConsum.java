package com.manhnd.accountservice.event;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manhnd.accountservice.model.ProfileDTO;
import com.manhnd.commonservice.kafka.consumer.AbstractKafkaConsumer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class testProductConsum extends AbstractKafkaConsumer<ProfileDTO> {
    protected testProductConsum(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected void processMessage(ProfileDTO message) {
        System.out.println("Consumed message: " + message);
    }

    @Override
    protected Class<ProfileDTO> getMessageType() {
//        return (Class<List<ProfileDTO>>) (Class<?>) List.class;
//        return new TypeReference<ProfileDTO>() {};
        return ProfileDTO.class;
    }


    @Override
    public String getTopic() {
        return "profile";
    }

    @Override
    public String getGroupId() {
        return "group-profile";
    }

}
