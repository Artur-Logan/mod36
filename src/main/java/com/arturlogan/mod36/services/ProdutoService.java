package com.arturlogan.mod36.services;

import com.arturlogan.mod36.segundobanco.dto.request.ProdutoRequest;
import com.arturlogan.mod36.segundobanco.dto.response.ProdutoResponse;
import com.arturlogan.mod36.segundobanco.entities.Produto;
import com.arturlogan.mod36.segundobanco.mapper.MapperProduto;
import com.arturlogan.mod36.segundobanco.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MapperProduto mapperProduto;

    public ProdutoResponse salvar(ProdutoRequest ProdutoRequest){
        Produto produto = mapperProduto.toModel(ProdutoRequest);

        produtoRepository.save(produto);

        return mapperProduto.toResponse(produto);
    }

    public ProdutoResponse buscar(Long id){
        Produto produto = produtoRepository.findById(id).get();

        return mapperProduto.toResponse(produto);
    }

    public void deletar(Long id){
        Produto produto = produtoRepository.findById(id).get();

        produtoRepository.delete(produto);
    }

    public List<ProdutoResponse> listarTodos(){
        List<Produto> Produtos = produtoRepository.findAll();

        return Produtos.stream().map(mapperProduto::toResponse).collect(Collectors.toList());
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest produtoRequest){
        Produto produto = produtoRepository.findById(id).get();

        mapperProduto.atualizar(produtoRequest, produto);

        produtoRepository.save(produto);

        return mapperProduto.toResponse(produto);
    }

}
