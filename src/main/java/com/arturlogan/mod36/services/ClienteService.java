package com.arturlogan.mod36.services;

import com.arturlogan.mod36.primeirobanco.dto.request.ClienteRequest;
import com.arturlogan.mod36.primeirobanco.dto.response.ClienteResponse;
import com.arturlogan.mod36.primeirobanco.entities.Cliente;
import com.arturlogan.mod36.primeirobanco.mapper.MapperCliente;
import com.arturlogan.mod36.primeirobanco.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MapperCliente mapperCliente;

    public ClienteResponse salvar(ClienteRequest clienteRequest){
        Cliente cliente = mapperCliente.toModel(clienteRequest);

        clienteRepository.save(cliente);

        return mapperCliente.toResponse(cliente);
    }

    public ClienteResponse buscar(Long id){
        Cliente cliente = clienteRepository.findById(id).get();

        return mapperCliente.toResponse(cliente);
    }

    public void deletar(Long id){
        Cliente cliente = clienteRepository.findById(id).get();

        clienteRepository.delete(cliente);
    }

    public List<ClienteResponse> listarTodos(){
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(mapperCliente::toResponse).collect(Collectors.toList());
    }

    public ClienteResponse atualizar(Long id, ClienteRequest clienteRequest){
        Cliente cliente = clienteRepository.findById(id).get();

        mapperCliente.atualizar(clienteRequest, cliente);

        clienteRepository.save(cliente);

        return mapperCliente.toResponse(cliente);
    }

}
