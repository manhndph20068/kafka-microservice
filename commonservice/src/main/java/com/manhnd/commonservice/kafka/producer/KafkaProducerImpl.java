package com.manhnd.commonservice.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl implements IKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProducerImpl(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> void sendMessage(String topic, T message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, null, jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Lỗi khi chuyển đổi message thành JSON", e);
        }
    }

    @Override
    public <T> void sendMessage(String topic, String key, T message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, key, jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Lỗi khi chuyển đổi message thành JSON", e);
        }
    }
}
