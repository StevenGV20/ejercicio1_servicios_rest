package com.ejercicio1.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ejercicio1.rest.entity.Categoria;
import com.ejercicio1.rest.entity.Producto;
import com.ejercicio1.rest.repository.CategoriaRepository;
import com.ejercicio1.rest.repository.ProductoRepository;

public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> listaCategoria() {
		return categoriaRepository.findAll();
	}

	@Override
	public List<Producto> listaAll() {
		return productoRepository.findAll();
	}

	@Override
	public Producto mantenerProducto(Producto bean) {
		return productoRepository.save(bean);
	}

	@Override
	public void eliminaProducto(int cod) {
		productoRepository.deleteById(cod);
	}

	@Override
	public Optional<Producto> buscaById(int cod) {
		return productoRepository.findById(cod);
	}

}
