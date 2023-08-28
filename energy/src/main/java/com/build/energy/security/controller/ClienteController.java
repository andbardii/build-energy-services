package com.build.energy.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.build.energy.security.entity.Cliente;
import com.build.energy.security.service.ClienteService;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
	
	@Autowired ClienteService svc;

		// GET API METHODS
		@GetMapping
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Cliente>> findAll() {
			List<Cliente> list = svc.findAll();
			ResponseEntity<List<Cliente>> resp = new ResponseEntity<List<Cliente>>(list, HttpStatus.OK);
			return resp;
		}
		
		@GetMapping("/{id}")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<?> findById(@PathVariable Long id) {
			Cliente c = svc.findById(id);
			ResponseEntity<Cliente> resp = new ResponseEntity<Cliente>(c, HttpStatus.OK);
			return resp;
		}
}
