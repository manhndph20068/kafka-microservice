package com.manhnd.profileservice.config.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manhnd.commonservice.kafka.dto.KafkaLogMessage;
import com.manhnd.commonservice.kafka.producer.IKafkaProducer;
import com.manhnd.profileservice.utils.anotation.KafkaEventLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class KafkaEventLogAspect {

    @Autowired
    private IKafkaProducer iKafkaProducer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("@annotation(kafkaEventLog)")
    public Object logKafkaEvent(ProceedingJoinPoint joinPoint, KafkaEventLog kafkaEventLog) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String eventType = kafkaEventLog.eventType();
        Object[] args = joinPoint.getArgs();

        try {
            log.info("Kafka Event Start - Class: {}, Method: {}, EventType: {}, Params: {}",
                    className,
                    methodName,
                    eventType,
                    objectMapper.writeValueAsString(joinPoint.getArgs())
            );

            Object result = joinPoint.proceed();

            log.info("Kafka Event Success - Class: {}, Method: {}, EventType: {}, Result: {}",
                    className,
                    methodName,
                    eventType,
                    objectMapper.writeValueAsString(result)
            );
            KafkaLogMessage successLog = KafkaLogMessage.builder()
                    .className(className)
                    .methodName(methodName)
                    .eventType(kafkaEventLog.eventType())
                    .request(args)
                    .response(result)
                    .timestamp(LocalDateTime.now())
                    .status("SUCCESS")
                    .build();
            iKafkaProducer.sendMessage("log", successLog);

            return result;

        } catch (Exception e) {
            log.error("Kafka Event Error - Class: {}, Method: {}, EventType: {}, Error: {}",
                    className,
                    methodName,
                    eventType,
                    e.getMessage()
            );
            KafkaLogMessage errorLog = KafkaLogMessage.builder()
                    .className(className)
                    .methodName(methodName)
                    .eventType(kafkaEventLog.eventType())
                    .request(args)
                    .errorMessage(e.getMessage())
                    .timestamp(LocalDateTime.now())
                    .status("FAILED")
                    .build();
            iKafkaProducer.sendMessage("log", errorLog);
            throw e;
        }
    }
}
