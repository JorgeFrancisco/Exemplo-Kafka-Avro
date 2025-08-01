package com.example.kafkaavroproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkaavroproducer.model.dto.UsuarioDTO;
import com.example.kafkaavroproducer.service.UsuarioSenderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuario")
@Tag(description = "Conjunto de endpoints para gerenciamento do usuario.", name = "Usuario")
public class UsuarioController {

	private final UsuarioSenderService usuarioSenderService;

	@Autowired
	public UsuarioController(UsuarioSenderService usuarioSenderService) {
		this.usuarioSenderService = usuarioSenderService;
	}

	@Operation(summary = "Envia os dados do usuário.", tags = "Usuario", responses = {
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida.", content = @Content),
			@ApiResponse(responseCode = "403", description = "Proibido acessar este recurso.", content = @Content),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content),
			@ApiResponse(responseCode = "405", description = "Método HTTP não permitido.", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro no servidor.", content = @Content) })
	@PostMapping
	public ResponseEntity<String> enviarMensagemKafka(@RequestParam String key, @RequestBody UsuarioDTO usuario) {
		usuarioSenderService.send(key, usuario);

		return ResponseEntity.ok("Mensagem enviada com sucesso para o Kafka!");
	}
}