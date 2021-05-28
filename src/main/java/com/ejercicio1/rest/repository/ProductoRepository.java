package com.ejercicio1.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ejercicio1.rest.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	@Query("Select p from Producto p where p.partNumber = :partNumber")
	public Optional<Producto> buscaPorPartNumber(@Param("partNumber") String partNumber);
}
