package com.example.rabbitmqclient.services.implementations;

import com.example.rabbitmqclient.configurations.RabbitMQConfiguration;
import com.example.rabbitmqclient.dto.RabbitMQResponseDto;
import com.example.rabbitmqclient.services.RabbitMQService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
public class RabbitMQServiceImpl implements RabbitMQService {

    private RabbitMQConfiguration rabbitMQConfiguration;
    private final RabbitTemplate rabbitTemplate;
    private final static String QUEUE_NAME = "msg";

    @Override
    public boolean producer() {
        rabbitTemplate.convertAndSend("", QUEUE_NAME, "Hello World!");
        return true;
    }

    @Override
    public RabbitMQResponseDto consumer() {
        Object message = rabbitTemplate.receiveAndConvert(QUEUE_NAME);
        return (message != null) ? new RabbitMQResponseDto(message.toString()) : null;
    }
}
