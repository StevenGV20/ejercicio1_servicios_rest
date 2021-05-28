package com.ejercicio1.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio1.rest.assembler.ClienteModelAssembler;
import com.ejercicio1.rest.entity.Cliente;
import com.ejercicio1.rest.exception.ClienteNotFoundException;
import com.ejercicio1.rest.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired(required = true)
	private ClienteService service;
	
	@Autowired
	private ClienteModelAssembler assembler;
	/*
	public ClienteController() {
		assembler=new ClienteModelAssembler();
	}*/
	
	@GetMapping("/")
	public CollectionModel<EntityModel<Cliente>> listaAll(){
		List<EntityModel<Cliente>> clients = service.listaAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(clients,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class).listaAll()).withSelfRel());
	}
	
	@GetMapping("/{id}")
	public EntityModel<Cliente> buscaById(@PathVariable int id){
		Cliente cliente=service.buscaById(id)
				.orElseThrow(()->new ClienteNotFoundException(id));
		return assembler.toModel(cliente);
	}
	
	@PostMapping("/")
	public ResponseEntity<EntityModel<Cliente>> registraCliente(@RequestBody Cliente cliente){
		Cliente obj=service.mantenerClient(cliente);
		
		return ResponseEntity
				.created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class).buscaById(obj.getIdcliente())).toUri())
				.body(assembler.toModel(obj));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modifcicaCliente(@RequestBody Cliente bean, @PathVariable int id){
		Cliente obj = service.buscaById(id).
				map(cliente ->{
					cliente.setNombre(bean.getNombre());
					cliente.setApellido(bean.getApellido());
					cliente.setDni(bean.getDni());
					cliente.setCorreo(bean.getCorreo());
					cliente.setDireccion(bean.getDireccion());
					return service.mantenerClient(cliente);
				})
				.orElseGet(()->{
					bean.setIdcliente(id);
					return service.mantenerClient(bean);
				});
		
		EntityModel<Cliente> entityModel =assembler.toModel(obj);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	@DeleteMapping("/{id}/")
	public ResponseEntity<?> deleteCliente(@PathVariable int id){
		service.eliminaCliente(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
