package com.ejercicio1.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejercicio1.rest.entity.Categoria;

public interface CategoriaRepository extends  JpaRepository<Categoria, Integer>{

}
