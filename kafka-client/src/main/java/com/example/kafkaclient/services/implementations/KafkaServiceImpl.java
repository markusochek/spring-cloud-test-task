package com.example.kafkaclient.services.implementations;

import com.example.kafkaclient.configurations.KafkaConfiguration;
import com.example.kafkaclient.dto.KafkaResponseDto;
import com.example.kafkaclient.services.KafkaService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final static String TOPIC = "msg";

    @Override
    public boolean producer() {
        kafkaTemplate.send(TOPIC, "Hello World!");
        return true;
    }

    @Override
    public KafkaResponseDto consumer() {
        ConsumerRecord<String, String> record = kafkaTemplate.receive(TOPIC, 0, 0L);
        assert record != null;
        return new KafkaResponseDto(record.value());
    }
}
