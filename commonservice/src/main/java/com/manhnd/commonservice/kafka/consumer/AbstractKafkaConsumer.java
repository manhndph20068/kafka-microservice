package com.manhnd.commonservice.kafka.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public abstract class AbstractKafkaConsumer<T> {
    protected final ObjectMapper objectMapper;

    protected AbstractKafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    protected abstract void processMessage(T message);

    @KafkaListener(topics = "#{__listener.topic}", groupId = "#{__listener.groupId}")
    public void consume(String message) {
        try {
            T typedMessage = objectMapper.readValue(message, getMessageType());
            processMessage(typedMessage);
        } catch (JsonProcessingException e) {
            log.error("[ERROR] consume Lỗi khi chuyển đổi JSON thành object: " + message, e);
        } catch (Exception e) {
            log.error("[ERROR] consume Lỗi khi xử lý tin nhắn Kafka: " + message, e);
        }
    }

    protected abstract Class<T> getMessageType();

    public abstract String getTopic();

    public abstract String getGroupId();
}
