package com.arturlogan.mod36.primeirobanco.controllers;

import com.arturlogan.mod36.primeirobanco.dto.request.ClienteRequest;
import com.arturlogan.mod36.primeirobanco.dto.response.ClienteResponse;
import com.arturlogan.mod36.primeirobanco.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscar(@PathVariable Long id){
        ClienteResponse clienteResponse = clienteService.buscar(id);

        return ResponseEntity.ok().body(clienteResponse);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> salvar(@RequestBody ClienteRequest clienteRequest){
        ClienteResponse clienteResponse = clienteService.salvar(clienteRequest);

        return ResponseEntity.ok().body(clienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        clienteService.deletar(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar(){
        List list = clienteService.listarTodos();

        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest){
        ClienteResponse cliente = clienteService.atualizar(id, clienteRequest);

        return ResponseEntity.ok().body(cliente);
    }

}
