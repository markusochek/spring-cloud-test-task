package com.example.kafkaclient.services;

import com.example.kafkaclient.dto.KafkaResponseDto;

import java.util.ArrayList;

public interface KafkaService {
    boolean producer();
    ArrayList<KafkaResponseDto> consumer();
}
