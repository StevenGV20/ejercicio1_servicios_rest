package com.ejercicio1.rest.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalle_boleta")
public class DetalleBoleta {

	@EmbeddedId
	private DetalleBoletaPk detallePK;
	
	@ManyToOne
	@JoinColumn(name = "idboleta",nullable = false, insertable = false, updatable = false)
	private Boleta boleta;

	@ManyToOne
	@JoinColumn(name = "idproducto", nullable = false, insertable = false, updatable = false)
	private Producto producto;
	
	private int cantidad;
	private double precioTotal;
	private double importe;
	
	
	
}
