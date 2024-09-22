package com.example.rabbitmqclient.services;

import com.example.rabbitmqclient.dto.RabbitMQResponseDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface RabbitMQService {
    boolean producer() throws IOException, TimeoutException;
    RabbitMQResponseDto consumer() throws IOException, TimeoutException;
}
