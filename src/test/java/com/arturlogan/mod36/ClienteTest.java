package com.arturlogan.mod36;

import com.arturlogan.mod36.entities.Cliente;
import com.arturlogan.mod36.entities.Produto;
import com.arturlogan.mod36.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    void cadastrar(){
        Cliente cliente = new Cliente();

        cliente.setEmail("teste@teste.com");
        cliente.setNome("LOL");

        clienteService.salvarProduto(cliente);

        assertNotNull(cliente);
        assertEquals("teste", cliente.getNome());
    }
}
