package com.ejercicio1.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ejercicio1.rest.entity.Boleta;
import com.ejercicio1.rest.entity.DetalleBoleta;
import com.ejercicio1.rest.repository.BoletaRepository;
import com.ejercicio1.rest.repository.DetalleBoletaRepository;

public class BoletaServiceImpl implements BoletaService{
	
	@Autowired
	private BoletaRepository boletaRepository;
	
	@Autowired
	private DetalleBoletaRepository detalleRepository;

	@Override
	public List<Boleta> listaAll() {
		return boletaRepository.findAll();
	}

	@Override
	public Boleta registraBoleta(Boleta bean,List<DetalleBoleta> detalle) {
		Boleta obj=boletaRepository.save(bean);
		for(DetalleBoleta dt:detalle) {
			dt.setBoleta(obj);
			detalleRepository.save(dt);
		}
		return null;
	}

	@Override
	public void elimina(int cod) {
		boletaRepository.deleteById(cod);
	}

	@Override
	public Optional<Boleta> buscaById(int cod) {
		return boletaRepository.findById(cod);
	}

	@Override
	public List<DetalleBoleta> listaDetalleById(int bol) {
		return null;
	}

	@Override
	public DetalleBoleta registraDetalle(DetalleBoleta bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
