package com.ejercicio1.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejercicio1.rest.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
