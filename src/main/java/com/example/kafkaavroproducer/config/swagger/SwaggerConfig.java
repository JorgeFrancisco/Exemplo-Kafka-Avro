package com.example.kafkaavroproducer.config.swagger;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.kafkaavroproducer.config.properties.ServletProperties;
import com.example.kafkaavroproducer.controller.UsuarioController;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	private final BuildProperties buildProperties;

	private final ServletProperties servletProperties;

	@Autowired
	public SwaggerConfig(BuildProperties buildProperties, ServletProperties servletProperties) {
		this.buildProperties = buildProperties;
		this.servletProperties = servletProperties;
	}

	@Bean
	public GroupedOpenApi cloudConfigGroupedOpenApi() {
		return GroupedOpenApi.builder().group("Spring Cloud Config").pathsToMatch("/usuario/**")
				.addOpenApiCustomizer(cloudConfigOpenApiCustomiser())
				.packagesToScan(UsuarioController.class.getPackageName()).build();
	}

	public OpenApiCustomizer cloudConfigOpenApiCustomiser() {
		return openApi -> openApi.info(cloudConfigInfo())
				.servers(Arrays.asList(new Server().url(servletProperties.contextPath())));
	}

	private Info cloudConfigInfo() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss.SSS");
		String formattedString = buildProperties.getTime().atZone(ZoneId.of("America/Sao_Paulo")).format(formatter);

		return new Info().title("API - Exemplo Kafka").description("Documentação da API de exemplo de uso do Kafka Producer.")
				.version(buildProperties.getVersion() + "-" + formattedString)
				.license(new License().name("Apache License Version 2.0")
						.url("https://www.apache.org/licenses/LICENSE-2.0\""))
				.contact(new Contact().name("JFBM").url("http://www.jfbm.tech.br").email("contato@jfbm.tech.br"));
	}
}