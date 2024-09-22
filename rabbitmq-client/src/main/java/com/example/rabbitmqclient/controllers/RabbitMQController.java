package com.example.rabbitmqclient.controllers;

import com.example.rabbitmqclient.dto.RabbitMQResponseDto;
import com.example.rabbitmqclient.services.RabbitMQService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/rabbitmq")
@AllArgsConstructor
public class RabbitMQController {

    private RabbitMQService rabbitMQService;

    @GetMapping("/producer")
    public ResponseEntity<Boolean> producer() throws IOException, TimeoutException {
        return new ResponseEntity<>(rabbitMQService.producer(), HttpStatus.OK);
    }

    @GetMapping("/consumer")
    public ResponseEntity<RabbitMQResponseDto> consumer() throws IOException, TimeoutException {
        return new ResponseEntity<>(rabbitMQService.consumer(), HttpStatus.OK);
    }
}
