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

import com.build.energy.security.entity.Fattura;
import com.build.energy.security.service.FatturaService;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {

	@Autowired FatturaService svc;
	
		// GET API METHODS
		@GetMapping
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Fattura>> findAll() {
			List<Fattura> list = svc.findAll();
			ResponseEntity<List<Fattura>> resp = new ResponseEntity<List<Fattura>>(list, HttpStatus.OK);
			return resp;
		}
			
		@GetMapping("/{id}")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<?> findById(@PathVariable Long id) {
			Fattura f = svc.findById(id);
			ResponseEntity<Fattura> resp = new ResponseEntity<Fattura>(f, HttpStatus.OK);
			return resp;
		}
}
