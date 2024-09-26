package com.example.rabbitmqclient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.cloud.config.enabled=false")
class RabbitmqClientApplicationTests {

    @Test
    void contextLoads() {
    }

}
