package com.example.kafkaavro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UsuarioDTO {

	@Schema(description = "Id do usuario")
	@JsonProperty("id")
	private Integer id;

	@Schema(description = "Nome do usuario")
	@JsonProperty("nome")
	private String nome;

	@Schema(description = "E-mail do usuario")
	@JsonProperty("email")
	private String email;
}