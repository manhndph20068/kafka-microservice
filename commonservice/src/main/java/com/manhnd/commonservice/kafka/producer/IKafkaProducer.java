package com.manhnd.commonservice.kafka.producer;

public interface IKafkaProducer {
    <T> void sendMessage(String topic, T message);
    <T> void sendMessage(String topic, String key, T message);
}
