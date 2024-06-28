package com.arturlogan.mod36;

import com.arturlogan.mod36.primeirobanco.dto.request.ClienteRequest;
import com.arturlogan.mod36.primeirobanco.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    void cadastrar(){
        ClienteRequest cliente = new ClienteRequest();

        cliente.setEmail("teste@teste.com");
        cliente.setNome("LOL");
        cliente.setIdade(19);
        cliente.setSobrenome("Teste");

        clienteService.salvar(cliente);

        assertNotNull(cliente);
        assertEquals("LOL", cliente.getNome());
    }
}
