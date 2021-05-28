package com.ejercicio1.rest.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejercicio1.rest.controller.ProductoController;
import com.ejercicio1.rest.entity.Producto;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>>{

	@Override
	public EntityModel<Producto> toModel(Producto producto) {
		return EntityModel.of(producto,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).buscaById(producto.getIdproducto())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).listaAll()).withRel("clientes"));
	}
	
}
