package com.arturlogan.mod36.segundobanco.repositories;

import com.arturlogan.mod36.segundobanco.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
