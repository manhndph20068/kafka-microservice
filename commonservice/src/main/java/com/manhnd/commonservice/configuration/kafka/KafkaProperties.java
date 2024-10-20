package com.manhnd.commonservice.configuration.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
@Getter
@Setter
public class KafkaProperties {
    private String bootstrapServers;
    private String groupId;
    private String autoOffsetReset;
    private Boolean enableAutoCommit;
    private String keyDeserializer;
    private String valueDeserializer;
    private String acks;
    private Integer retries;
    private Integer batchSize;
    private Integer lingerMs;
    private Integer maxInFlightRequestsPerConnection;
    private Integer maxPollRecords;
    private Integer fetchMinBytes;
    private Integer fetchMaxWaitMs;
}
