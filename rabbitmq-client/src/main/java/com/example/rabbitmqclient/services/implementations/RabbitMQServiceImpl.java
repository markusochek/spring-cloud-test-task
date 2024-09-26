package com.example.rabbitmqclient.services.implementations;

import com.example.rabbitmqclient.configurations.RabbitMQConfiguration;
import com.example.rabbitmqclient.dto.RabbitMQResponseDto;
import com.example.rabbitmqclient.services.RabbitMQService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeoutException;

import static com.example.rabbitmqclient.configurations.RabbitMQConfiguration.QUEUE_NAME;
import static com.example.rabbitmqclient.configurations.RabbitMQConfiguration.ROUTING_KEY;

@Service
@AllArgsConstructor
public class RabbitMQServiceImpl implements RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public boolean producer() {
        String message = "Hello World!";
        rabbitTemplate.convertAndSend(ROUTING_KEY, message);
        return true;
    }

    @Override
    public RabbitMQResponseDto consumer() {
        Message message = rabbitTemplate.receive(QUEUE_NAME);
        if (message == null) return null;
        return new RabbitMQResponseDto(new String(message.getBody()));
    }
}
