package com.example.kafkaavroproducer.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;

@Validated
@ConfigurationProperties(prefix = "kafka")
public record KafkaProperties(

		@NotEmpty(message = "O host do servidor do Kafka não pode estar vazio") String server,

		@NotEmpty(message = "O tópico do Kafka não pode estar vazio") String topic,

		@NotEmpty(message = "O groupId do Kafka não pode estar vazio") String groupId,

		@NotEmpty(message = "O host do Schema Registry do Kafka não pode estar vazio") String schemaRegistry) {
}