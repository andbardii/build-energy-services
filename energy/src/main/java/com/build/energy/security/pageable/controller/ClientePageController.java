package com.build.energy.security.pageable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.build.energy.security.entity.Cliente;
import com.build.energy.security.service.ClienteService;

@RestController
@RequestMapping("/api/clienti/pageable")
public class ClientePageController {

	@Autowired ClienteService svc;

	// GET API METHODS
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Cliente>> getAll(Pageable p) {
		Page<Cliente> page = svc.getAll(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/sort-nome")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Cliente>> sortByNome(Pageable p) {
		Page<Cliente> page = svc.sortByNome(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/sort-fatturato")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Cliente>> sortByFatturato(Pageable p) {
		Page<Cliente> page = svc.sortByFatturato(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/sort-datainserimento")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Cliente>> sortByDataInserimento(Pageable p) {
		Page<Cliente> page = svc.sortByDataInserimento(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/sort-ultimocontatto")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Cliente>> sortByDataUltimoContatto(Pageable p) {
		Page<Cliente> page = svc.sortByDataUltimoContatto(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/sort-provincia")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Cliente>> sortByProvincia(Pageable p) {
		Page<Cliente> page = svc.sortByProvincia(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
}
