package com.arturlogan.mod36.segundobanco.controllers;

import com.arturlogan.mod36.segundobanco.dto.request.ProdutoRequest;
import com.arturlogan.mod36.segundobanco.dto.response.ProdutoResponse;
import com.arturlogan.mod36.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscar(@PathVariable Long id){
        ProdutoResponse produtoResponse = produtoService.buscar(id);

        return ResponseEntity.ok().body(produtoResponse);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> salvar(@RequestBody ProdutoRequest clienteRequest){
        ProdutoResponse produtoResponse = produtoService.salvar(clienteRequest);

        return ResponseEntity.ok().body(produtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        produtoService.deletar(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listar(){
        List list = produtoService.listarTodos();

        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @RequestBody ProdutoRequest clienteRequest){
        ProdutoResponse cliente = produtoService.atualizar(id, clienteRequest);

        return ResponseEntity.ok().body(cliente);
    }

}
