package com.example.cart.kafka.dto;

public record KafkaStatus<T>(
        T data, String status

) {
}
