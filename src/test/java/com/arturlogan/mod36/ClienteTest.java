package com.arturlogan.mod36;

import com.arturlogan.mod36.primeirobanco.dto.request.ClienteRequest;
import com.arturlogan.mod36.primeirobanco.dto.response.ClienteResponse;
import com.arturlogan.mod36.primeirobanco.entities.Cliente;
import com.arturlogan.mod36.primeirobanco.repositories.ClienteRepository;
import com.arturlogan.mod36.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

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

    @Test
    void deletar(){
        Long id = 3L;

        when(clienteRepository.findById(any()))
                .thenReturn(Optional.of(getCliente(id)));

        clienteService.deletar(3L);
    }

    @Test
    void atualizar(){
        ClienteRequest clienteRequest = getClienteRequest();

        Cliente cliente = getCliente(2L);

        cliente.setNome(clienteRequest.getNome());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setIdade(clienteRequest.getIdade());
        cliente.setSobrenome(clienteRequest.getSobrenome());

        when(clienteRepository.findById(any()))
                .thenReturn(Optional.of(cliente));

        when(clienteRepository.save(any()))
                .thenReturn(cliente);

        ClienteResponse clienteResponse = clienteService.atualizar(cliente.getId(), clienteRequest);

        assertEquals(clienteRequest.getNome(), clienteResponse.getNome());
        assertEquals(clienteRequest.getSobrenome(), clienteResponse.getSobrenome());
        assertEquals(clienteRequest.getIdade(), clienteResponse.getIdade());
        assertEquals(clienteRequest.getEmail(), clienteResponse.getEmail());
        assertNotNull(clienteResponse.getId());
    }

    @Test
    void listarTodosSucesso() {
        //cenario
        Cliente cliente = getCliente(19L);
        Cliente cliente2 = getCliente(2L);
        Cliente cliente3 = getCliente(3L);
        Cliente cliente4 = getCliente(4L);
        Cliente cliente5 = getCliente(5L);
        Cliente cliente6 = getCliente(6L);

        List<Cliente> clienteList = List.of(
                cliente, cliente2, cliente3, cliente4, cliente5, cliente6
        );

        when(clienteRepository.findAll())
                .thenReturn(clienteList);
        //execução
        List<ClienteResponse> ClienteResponses = clienteService.listarTodos();

        //verificação
        assertTrue(clienteList.size() > 0);
        assertEquals(6, clienteList.size());
    }

    @Test
    void buscar(){
        Long id = 5L;

        Cliente cliente = getCliente(id);

        when(clienteRepository.findById(any()))
                .thenReturn(Optional.of(cliente));

        ClienteResponse clienteResponse = clienteService.buscar(id);

        assertEquals(id, clienteResponse.getId());
    }

    public Cliente getCliente(Long id){
        Cliente cliente = new Cliente();

        cliente.setId(id);
        cliente.setNome("Artur");
        cliente.setSobrenome("Logan");
        cliente.setIdade(22);
        cliente.setEmail("artur@gmail.com");

        return cliente;
    }

    public ClienteRequest getClienteRequest(){
        ClienteRequest clienteRequest = new ClienteRequest();

        clienteRequest.setEmail("teste@teste.com");
        clienteRequest.setIdade(99);
        clienteRequest.setNome("Teste");
        clienteRequest.setSobrenome("Testes");

        return clienteRequest;
    }

}
