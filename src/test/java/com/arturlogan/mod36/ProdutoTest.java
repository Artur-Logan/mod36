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

        ProdutoResponse produtoResponse = produtoService.atualizar(produto.getId(), produtoRequest);

        assertEquals(produtoRequest.getNome(), produtoResponse.getNome());
        assertEquals(produtoRequest.getMarca(), produtoResponse.getMarca());
        assertEquals(produtoRequest.getPreco(), produtoResponse.getPreco());

        assertNotNull(produtoResponse.getId());
    }

    @Test
    void listarTodosSucesso() {
        //cenario
        Produto produto = getCliente(19L);
        Produto produto2 = getCliente(2L);
        Produto produto3 = getCliente(3L);
        Produto produto4 = getCliente(4L);
        Produto produto5 = getCliente(5L);
        Produto produto6 = getCliente(6L);

        List<Produto> produtoList = List.of(
                produto, produto2, produto3, produto4, produto5, produto6
        );

        when(produtoRepository.findAll())
                .thenReturn(produtoList);
        //execução
        List<ProdutoResponse> ClienteResponses = produtoService.listarTodos();

        //verificação
        assertTrue(produtoList.size() > 0);
        assertEquals(6, produtoList.size());
    }

    @Test
    void buscar(){
        Long id = 5L;

        Produto produto = getCliente(id);

        when(produtoRepository.findById(any()))
                .thenReturn(Optional.of(produto));

        ProdutoResponse produtoResponse = produtoService.buscar(id);

        assertEquals(id, produtoResponse.getId());
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
