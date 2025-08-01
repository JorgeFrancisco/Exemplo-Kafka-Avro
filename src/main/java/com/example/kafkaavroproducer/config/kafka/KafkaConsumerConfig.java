package com.example.kafkaavroproducer.config.kafka;

import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.example.kafkaavro.model.Usuario;
import com.example.kafkaavroproducer.config.properties.KafkaProperties;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

@Configuration
public class KafkaConsumerConfig {

	private final KafkaProperties kafkaProperties;

	@Autowired
	public KafkaConsumerConfig(KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
	}

	@Bean
	public ConsumerFactory<String, Usuario> consumerFactory() {
		var configs = new HashMap<String, Object>();

		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.server());
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.groupId());
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);

		// Configuracao obrigatoria do Schema Registry
		configs.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, kafkaProperties.schemaRegistry());

		// Como usamos classes especificas Avro geradas
		configs.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Usuario> kafkaListenerContainerFactory() {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, Usuario>();

		factory.setConsumerFactory(consumerFactory());

		// Define concorrência de 5 threads para consumir mensagens em paralelo
		factory.setConcurrency(5);

		return factory;
	}
}