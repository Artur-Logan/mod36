package com.arturlogan.mod36.primeirobanco.mapper;

import com.arturlogan.mod36.primeirobanco.dto.request.ClienteRequest;
import com.arturlogan.mod36.primeirobanco.dto.response.ClienteResponse;
import com.arturlogan.mod36.primeirobanco.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface MapperCliente {
    Cliente toModel(ClienteRequest clienteRequest);
    ClienteResponse toResponse(Cliente cliente);
    Cliente atualizar(ClienteRequest clienteRequest, @MappingTarget Cliente cliente);
}
