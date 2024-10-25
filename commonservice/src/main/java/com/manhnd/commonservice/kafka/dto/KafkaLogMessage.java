package com.manhnd.commonservice.kafka.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class KafkaLogMessage {
    private String className;
    private String methodName;
    private String eventType;
    private Object request;
    private Object response;
    private String errorMessage;
    private LocalDateTime timestamp;
    private String status; // SUCCESS, FAILED
}
