package com.example.kafkaclient.services.implementations;

import com.example.kafkaclient.configurations.KafkaConfiguration;
import com.example.kafkaclient.dto.KafkaResponseDto;
import com.example.kafkaclient.services.KafkaService;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

@Service
@AllArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private KafkaConfiguration kafkaConfiguration;

    @Override
    public boolean producer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getHost() + ":" + kafkaConfiguration.getPort());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        Producer<String, String> producer = null;

        try {
            producer = new KafkaProducer<>(props);
            ProducerRecord<String, String> record = new ProducerRecord<>("Messages", "key", "Hello, Kafka!");
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    System.err.println("Error sending record: " + exception.getMessage());
                } else {
                    System.out.println("Record sent successfully to topic " + metadata.topic() +
                            " partition " + metadata.partition() + " at offset " + metadata.offset());
                }
            });

            return true;
        } catch (Exception e) {
            System.err.println("Error creating or sending producer: " + e.getMessage());
        } finally {
            if (producer != null) {
                try {
                    producer.close();
                } catch (Exception e) {
                    System.err.println("Error closing producer: " + e.getMessage());
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<KafkaResponseDto> consumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getHost() + ":" + kafkaConfiguration.getPort());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        Consumer<String, String> consumer = null;
        try {
            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Collections.singletonList("Messages"));
            while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                if (!records.isEmpty()) {
                    ArrayList<KafkaResponseDto> kafkaResponseDtoArrayList = new ArrayList<>();
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                        kafkaResponseDtoArrayList.add(new KafkaResponseDto(record.offset(), record.key(), record.value()));
                    }

                    return kafkaResponseDtoArrayList;
                }
            } catch (Exception e) {
                System.err.println("Error during poll: " + e.getMessage());
            }
        }
        } catch (Exception e) {
            System.err.println("Error creating or using consumer: " + e.getMessage());
        } finally {
            if (consumer != null) {
                try {
                    consumer.close();
                } catch (Exception e) {
                    System.err.println("Error closing consumer: " + e.getMessage());
                }
            }
        }
        return null;
    }
}
