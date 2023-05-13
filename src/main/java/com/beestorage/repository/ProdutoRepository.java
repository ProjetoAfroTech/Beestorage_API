package com.beestorage.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.beestorage.model.ProdutoModel;


public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
		public List <ProdutoModel> findAllByNomeProdutoContainingIgnoreCase(@Param("nomeProduto") String nomeProduto);
}
