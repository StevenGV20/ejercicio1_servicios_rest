package com.ejercicio1.rest.exception;

public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(int id) {
		super("No existe el cliente "+id);
	}
}
