package com.manhnd.commonservice.kafka.events;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class BaseEvent implements Serializable {
    private String eventId = UUID.randomUUID().toString();
    private long timestamp = System.currentTimeMillis();
    private String eventType;

    @Override
    public String toString() {
        return "BaseEvent{" +
                "eventId='" + eventId + '\'' +
                ", timestamp=" + timestamp +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
