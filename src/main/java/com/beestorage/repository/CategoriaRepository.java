package com.beestorage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beestorage.model.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
	public List<CategoriaModel>findAllByNomeCategoriaContainingIgnoreCase(String categoria);
}

