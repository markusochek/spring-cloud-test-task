package com.example.kafkaclient.controllers;

import com.example.kafkaclient.services.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/producer")
    public ResponseEntity<Boolean> producer() {
        return new ResponseEntity<>(kafkaService.producer(), HttpStatus.OK);
    }

    @GetMapping("/consumer")
    public ResponseEntity<ConsumerRecords<String, String>> consumer() {
        return new ResponseEntity<>(kafkaService.consumer(), HttpStatus.OK);
    }
}
