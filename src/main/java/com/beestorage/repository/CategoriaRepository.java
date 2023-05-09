package com.beestorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beestorage.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>  {

}
