package com.example.kafkaclient.services;

import com.example.kafkaclient.dto.KafkaResponseDto;

public interface KafkaService {
    boolean producer();
    KafkaResponseDto consumer();
}
