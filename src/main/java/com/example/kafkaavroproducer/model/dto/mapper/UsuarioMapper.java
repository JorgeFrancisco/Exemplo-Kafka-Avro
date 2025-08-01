package com.example.kafkaavroproducer.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.kafkaavro.model.Usuario;
import com.example.kafkaavroproducer.model.dto.UsuarioDTO;

import lombok.Generated;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Generated
public interface UsuarioMapper {

	Usuario toAvro(UsuarioDTO dto);
}