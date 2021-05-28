package com.ejercicio1.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class DetalleBoletaPk implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "idboleta", unique=true, nullable = false, length = 10, insertable =true, updatable = false)
	private int idboleta;
	
	@Column(name = "idproducto", unique=true, nullable = false, length = 10, insertable =true, updatable = false)
	private int idproducto;
	
	@Override
	public int hashCode() {
		final int prime=31;
		int result=-1;
		result = prime * result + idproducto;
		result = prime * result + idboleta;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleBoletaPk other = (DetalleBoletaPk) obj;
		if (idproducto != other.idproducto)
			return false;
		if (idboleta != other.idboleta)
			return false;
		return true;
	}
}
