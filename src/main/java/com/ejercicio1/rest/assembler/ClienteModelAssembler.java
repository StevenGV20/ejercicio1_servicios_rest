package com.ejercicio1.rest.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejercicio1.rest.controller.ClienteController;
import com.ejercicio1.rest.entity.Cliente;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>>{

	@Override
	public EntityModel<Cliente> toModel(Cliente cliente) {
		return EntityModel.of(cliente,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class).buscaById(cliente.getIdcliente())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class).listaAll()).withRel("clientes"));
	}

}
