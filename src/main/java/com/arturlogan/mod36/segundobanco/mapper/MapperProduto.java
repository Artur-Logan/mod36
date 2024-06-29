package com.arturlogan.mod36.segundobanco.mapper;

import com.arturlogan.mod36.segundobanco.dto.request.ProdutoRequest;
import com.arturlogan.mod36.segundobanco.dto.response.ProdutoResponse;
import com.arturlogan.mod36.segundobanco.entities.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MapperProduto {
    Produto toModel(ProdutoRequest produtoRequest);
    ProdutoResponse toResponse(Produto produto);
    Produto atualizar(ProdutoRequest produtoRequest, @MappingTarget Produto produto);
}
