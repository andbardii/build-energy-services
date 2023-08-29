package com.build.energy.security.pageable.controller;

import java.util.List;

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
	public ResponseEntity<Page<Cliente>> getAll(Pageable p) {
		Page<Cliente> page = svc.getAll(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/sort-nome")
	public ResponseEntity<Page<Cliente>> sortByNome(Pageable p) {
		Page<Cliente> page = svc.sortByNome(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/sort-fatturato")
	public ResponseEntity<Page<Cliente>> sortByFatturato(Pageable p) {
		Page<Cliente> page = svc.sortByFatturato(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/sort-datainserimento")
	public ResponseEntity<Page<Cliente>> sortByDataInserimento(Pageable p) {
		Page<Cliente> page = svc.sortByDataInserimento(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/sort-ultimocontatto")
	public ResponseEntity<Page<Cliente>> sortByDataUltimoContatto(Pageable p) {
		Page<Cliente> page = svc.sortByDataUltimoContatto(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/sort-provincia")
	public ResponseEntity<Page<Cliente>> sortByProvincia(Pageable p) {
		Page<Cliente> page = svc.sortByProvincia(p);
		ResponseEntity<Page<Cliente>> resp = new ResponseEntity<Page<Cliente>>(page, HttpStatus.OK);
		return resp;
	}
	
}
