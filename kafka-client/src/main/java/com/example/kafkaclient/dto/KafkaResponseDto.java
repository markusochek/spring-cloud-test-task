package com.example.kafkaclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaResponseDto {
    private Long offset;
    private String key;
    private String value;
}
