package com.example.kafkaclient.services;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface KafkaService {
    boolean producer();
    ConsumerRecords<String, String> consumer();
}
