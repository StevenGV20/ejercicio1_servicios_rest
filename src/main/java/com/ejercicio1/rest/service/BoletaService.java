package com.ejercicio1.rest.service;

import java.util.List;
import java.util.Optional;

import com.ejercicio1.rest.entity.Boleta;
import com.ejercicio1.rest.entity.DetalleBoleta;

public interface BoletaService {
	
	public abstract List<Boleta> listaAll();
	public abstract Boleta registraBoleta(Boleta bean,List<DetalleBoleta> detalle);
	public abstract void elimina(int cod);
	public abstract Optional<Boleta> buscaById(int cod);
	public abstract List<DetalleBoleta> listaDetalleById(int bol);
	public abstract DetalleBoleta registraDetalle(DetalleBoleta bean);
	
}
