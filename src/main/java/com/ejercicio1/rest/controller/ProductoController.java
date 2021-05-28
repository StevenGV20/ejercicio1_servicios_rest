package com.ejercicio1.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio1.rest.assembler.ProductoModelAssembler;
import com.ejercicio1.rest.entity.Producto;
import com.ejercicio1.rest.exception.NotFoundException;
import com.ejercicio1.rest.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoService service;
	
	@Autowired
	private ProductoModelAssembler assembler;
	
	@GetMapping("/")
	public CollectionModel<EntityModel<Producto>> listaAll(){
		List<EntityModel<Producto>> products = service.listaAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(products,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class).listaAll()).withSelfRel());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaById(@PathVariable int id){
		Optional<Producto> producto=service.buscaById(id);
		if(producto.isPresent()) {
			return ResponseEntity
					.created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).buscaById(producto.get().getIdproducto())).toUri())
					.body(assembler.toModel(producto.get()));			
		}else {
			return ResponseEntity
					.status(HttpStatus.METHOD_NOT_ALLOWED)
					.header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
					.body(Problem.create()
							.withTitle("Error")
							.withDetail("El producto con codigo " + id +" no existe"));
		}
		
	}
	
	@PostMapping("/")
	public ResponseEntity<?> registraProducto(@RequestBody Producto producto){
		try {
			Optional<Producto> option=service.buscaByPartNumber(producto.getPartNumber());
			if(option.isPresent()) {
				return ResponseEntity
						.status(HttpStatus.NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
						.body(Problem.create()
								.withStatus(HttpStatus.NOT_ACCEPTABLE)
								.withTitle("Error. Producto no aceptado")
								.withDetail("El producto con partNumber " + producto.getPartNumber()+ " ya existe"));
			}
			
			Producto obj=service.mantenerProducto(producto);
			
			return ResponseEntity
					.created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).buscaById(obj.getIdproducto())).toUri())
					.body(assembler.toModel(obj));			
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
					.body(Problem.create()
							.withStatus(HttpStatus.BAD_REQUEST)
							.withTitle("Error. Revise sus datos")
							.withDetail("Existen error en los datos enviados"));
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<?> modifcicaProducto(@RequestBody Producto bean){
		try {
			Optional<Producto> option=service.buscaById(bean.getIdproducto());
			if(option.isPresent()) {
				Producto obj = service.buscaById(bean.getIdproducto()).
						map(producto ->{
							return service.mantenerProducto(bean);
						})
						.orElseGet(()->{
							bean.setIdproducto(bean.getIdproducto());
							return service.mantenerProducto(bean);
						});
				
				EntityModel<Producto> entityModel =assembler.toModel(obj);
				
				return ResponseEntity
						.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
						.body(entityModel);
			}else {
				return ResponseEntity
						.status(HttpStatus.METHOD_NOT_ALLOWED)
						.header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
						.body(Problem.create()
								.withTitle("Error")
								.withDetail("El producto con codigo " + bean.getIdproducto() +" no existe"));
			}			
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
					.body(Problem.create()
							.withStatus(HttpStatus.BAD_REQUEST)
							.withTitle("Error. Revise sus datos")
							.withDetail("Existen error en los datos enviados"));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable int id){
		service.eliminaProducto(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
