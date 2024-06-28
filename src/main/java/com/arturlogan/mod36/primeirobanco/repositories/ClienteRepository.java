package com.arturlogan.mod36.primeirobanco.repositories;

import com.arturlogan.mod36.primeirobanco.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
