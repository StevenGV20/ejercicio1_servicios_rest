package com.ejercicio1.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cli")
	private int idcliente;
	@Column(name = "nom_cli")
	private String nombre;
	@Column(name = "ape_cli")
	private String apellido;
	@Column(name = "dni_cli")
	private String dni;
	private String correo;
	private String direccion;
}
