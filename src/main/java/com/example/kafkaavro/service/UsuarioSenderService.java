package com.example.kafkaavro.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kafkaavro.config.properties.KafkaProperties;
import com.example.kafkaavro.model.Usuario;
import com.example.kafkaavro.model.dto.UsuarioDTO;
import com.example.kafkaavro.model.dto.mapper.UsuarioMapper;

@Service
public class UsuarioSenderService {

	private final KafkaTemplate<String, Usuario> kafkaTemplate;

	private final KafkaProperties kafkaProperties;

	@Autowired
	public UsuarioSenderService(KafkaTemplate<String, Usuario> kafkaTemplate, KafkaProperties kafkaProperties) {
		this.kafkaTemplate = kafkaTemplate;
		this.kafkaProperties = kafkaProperties;
	}

	public void send(String key, UsuarioDTO usuarioDTO) {
		var usuarioMapper = Mappers.getMapper(UsuarioMapper.class);

		Usuario usuarioAvro = usuarioMapper.toAvro(usuarioDTO);

		kafkaTemplate.send(kafkaProperties.topic(), key, usuarioAvro);
	}
}