package com.ejercicio1.rest.service;

import java.util.List;
import java.util.Optional;

import com.ejercicio1.rest.entity.Categoria;
import com.ejercicio1.rest.entity.Producto;

public interface ProductoService {

	public abstract List<Categoria> listaCategoria();
	public abstract List<Producto> listaAll();
	public abstract Producto mantenerProducto(Producto bean);
	public abstract void eliminaProducto(int cod);
	public abstract Optional<Producto> buscaById(int cod);
	public abstract Optional<Producto> buscaByPartNumber(String number);
	
	
	
}
