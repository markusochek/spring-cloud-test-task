package com.example.kafkaclient.configurations;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "kafka")
@Getter
@Setter
public class KafkaConfiguration {
    private String host;
    private String port;
}
