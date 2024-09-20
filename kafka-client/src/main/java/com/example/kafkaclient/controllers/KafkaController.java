package com.example.kafkaclient.controllers;

import com.example.kafkaclient.configurations.KafkaConfiguration;
import com.example.kafkaclient.dto.KafkaResponseDto;
import com.example.kafkaclient.services.KafkaService;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/kafka")
@AllArgsConstructor
public class KafkaController {

    private KafkaService kafkaService;

    @GetMapping("/producer")
    public ResponseEntity<Boolean> producer() {
        return new ResponseEntity<>(kafkaService.producer(), HttpStatus.OK);
    }

    @GetMapping("/consumer")
    public ResponseEntity<ArrayList<KafkaResponseDto>> consumer() {
        return new ResponseEntity<>(kafkaService.consumer(), HttpStatus.OK);
    }
}
