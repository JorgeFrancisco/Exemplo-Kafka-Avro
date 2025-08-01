package com.example.kafkaavroproducer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kafkaavroproducer.model.dto.UsuarioDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioListenerService {

	@KafkaListener(topics = "#{@kafkaProperties.topic}", groupId = "#{@kafkaProperties.groupId}", containerFactory = "kafkaListenerContainerFactory")
	public void consumir(ConsumerRecord<String, UsuarioDTO> record) {
		log.info("Usuário: {} | Chave: {} | Partição: {} | Offset: {}", record.value(), record.key(),
				record.partition(), record.offset());
	}
}