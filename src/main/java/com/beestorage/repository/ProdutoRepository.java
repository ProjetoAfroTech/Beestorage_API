package com.beestorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beestorage.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
