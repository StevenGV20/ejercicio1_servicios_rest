package com.ejercicio1.rest.exception;

public class ClienteNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(int id) {
		super("No existe el cliente "+id);
	}
}
