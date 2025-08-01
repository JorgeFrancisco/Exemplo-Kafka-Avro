package com.example.kafkaavro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import com.example.kafkaavro.config.properties.ServletProperties;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = ServletProperties.class)
public class KafkaAvroApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroApplication.class, args);
	}
}