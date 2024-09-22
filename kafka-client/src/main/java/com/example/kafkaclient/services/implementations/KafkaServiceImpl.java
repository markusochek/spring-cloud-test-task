package com.example.kafkaclient.services.implementations;

import com.example.kafkaclient.configurations.KafkaConfiguration;
import com.example.kafkaclient.dto.KafkaResponseDto;
import com.example.kafkaclient.services.KafkaService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    public KafkaServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final static String TOPIC = "msg";

    @Getter
    private String lastReceivedMessage;

    @Override
    public boolean producer() {
        kafkaTemplate.send(TOPIC, "Hello World!");
        return true;
    }

    @Override
    public KafkaResponseDto consumer() {
        return new KafkaResponseDto(getLastReceivedMessage());
    }

    @KafkaListener(topics = TOPIC, groupId = "myGroup")
    public void listen(String message) {
        lastReceivedMessage = message;
    }
}
