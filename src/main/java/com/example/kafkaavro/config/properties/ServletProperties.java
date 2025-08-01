package com.example.kafkaavro.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;

@Validated
@ConfigurationProperties(prefix = "server.servlet")
public record ServletProperties(

		@NotEmpty(message = "O context path não pode estar vazio") String contextPath) {
}