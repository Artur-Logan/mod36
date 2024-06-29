package com.arturlogan.mod36;

import com.arturlogan.mod36.segundobanco.dto.request.ProdutoRequest;
import com.arturlogan.mod36.segundobanco.dto.response.ProdutoResponse;
import com.arturlogan.mod36.segundobanco.entities.Produto;
import com.arturlogan.mod36.segundobanco.repositories.ProdutoRepository;
import com.arturlogan.mod36.services.ProdutoService;
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
public class ProdutoTest {

    @Autowired
    private ProdutoService produtoService;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Test
    void cadastrar(){
        ProdutoRequest produto = new ProdutoRequest();

        produto.setMarca("teste@teste.com");
        produto.setNome("LOL");
        produto.setPreco(12);

        produtoService.salvar(produto);

        assertNotNull(produto);
        assertEquals("LOL", produto.getNome());
    }

    @Test
    void deletar(){
        Long id = 3L;

        when(produtoRepository.findById(any()))
                .thenReturn(Optional.of(getCliente(id)));

        produtoService.deletar(3L);
    }

    @Test
    void atualizar(){
        ProdutoRequest produtoRequest = getClienteRequest();

        Produto produto = getCliente(2L);

        produto.setNome(produtoRequest.getNome());
        produto.setMarca(produtoRequest.getMarca());
        produto.setPreco(produtoRequest.getPreco());

        when(produtoRepository.findById(any()))
                .thenReturn(Optional.of(produto));

        when(produtoRepository.save(any()))
                .thenReturn(produto);

        ProdutoResponse clienteResponse = produtoService.atualizar(produto.getId(), produtoRequest);

        assertEquals(produtoRequest.getNome(), clienteResponse.getNome());
        assertEquals(produtoRequest.getMarca(), clienteResponse.getMarca());
        assertEquals(produtoRequest.getPreco(), clienteResponse.getPreco());

        assertNotNull(clienteResponse.getId());
    }

    @Test
    void listarTodosSucesso() {
        //cenario
        Produto produto = getCliente(19L);
        Produto cliente2 = getCliente(2L);
        Produto cliente3 = getCliente(3L);
        Produto cliente4 = getCliente(4L);
        Produto cliente5 = getCliente(5L);
        Produto cliente6 = getCliente(6L);

        List<Produto> clienteList = List.of(
                produto, cliente2, cliente3, cliente4, cliente5, cliente6
        );

        when(produtoRepository.findAll())
                .thenReturn(clienteList);
        //execução
        List<ProdutoResponse> ClienteResponses = produtoService.listarTodos();

        //verificação
        assertTrue(clienteList.size() > 0);
        assertEquals(6, clienteList.size());
    }

    @Test
    void buscar(){
        Long id = 5L;

        Produto produto = getCliente(id);

        when(produtoRepository.findById(any()))
                .thenReturn(Optional.of(produto));

        ProdutoResponse clienteResponse = produtoService.buscar(id);

        assertEquals(id, clienteResponse.getId());
    }

    public Produto getCliente(Long id){
        Produto produto = new Produto();

        produto.setId(id);
        produto.setNome("Artur");
        produto.setPreco(10);

        return produto;
    }

    public ProdutoRequest getClienteRequest(){
        ProdutoRequest produtoRequest = new ProdutoRequest();

        produtoRequest.setMarca("teste@teste.com");
        produtoRequest.setPreco(12);
        produtoRequest.setNome("Teste");

        return produtoRequest;
    }

}
