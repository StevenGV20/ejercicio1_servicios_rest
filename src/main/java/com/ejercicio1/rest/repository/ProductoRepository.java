package com.ejercicio1.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejercicio1.rest.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
