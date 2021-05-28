package com.ejercicio1.rest.service;

import java.util.List;
import java.util.Optional;

import com.ejercicio1.rest.entity.Cliente;

public interface ClienteService {

	public abstract List<Cliente> listaAll();
	public abstract Cliente mantenerClient(Cliente bean);
	public abstract void eliminaCliente(int cod);
	public abstract Optional<Cliente> buscaById(int cod);
}
