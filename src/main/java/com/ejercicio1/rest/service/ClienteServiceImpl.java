package com.ejercicio1.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicio1.rest.entity.Cliente;
import com.ejercicio1.rest.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository repository;
	
	@Override
	public List<Cliente> listaAll() {
		return repository.findAll();
	}

	@Override
	public Cliente mantenerClient(Cliente bean) {
		return repository.save(bean);
	}

	@Override
	public void eliminaCliente(int cod) {
		repository.deleteById(cod);
	}

	@Override
	public Optional<Cliente> buscaById(int cod) {
		return repository.findById(cod);
	}

}
