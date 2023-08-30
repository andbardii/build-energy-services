package com.build.energy.security.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.build.energy.security.entity.Fattura;
import com.build.energy.security.enumerated.StatoFattura;
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
		//@PreAuthorize("isAuthenticated()")
		public ResponseEntity<?> findById(@PathVariable Long id) {
			Fattura f = svc.findById(id);
			ResponseEntity<Fattura> resp = new ResponseEntity<Fattura>(f, HttpStatus.OK);
			return resp;
		}
		
		@PostMapping
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<?> addFattura(@RequestBody Fattura fattura) {
	        Fattura f = svc.addFattura(fattura.getIdCliente(), 
	        		                   fattura.getAnno(),
	        		                   fattura.getData(),
	        		                   fattura.getImporto(),
	        		                   fattura.getStato());
	        return new ResponseEntity<Fattura>(f, HttpStatus.CREATED);
	    }
		
		@GetMapping("/findbycliente/{id}")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Fattura>> findByCliente(@PathVariable Long id) {
			List<Fattura> list = svc.findByCliente(id);
			ResponseEntity<List<Fattura>> resp = new ResponseEntity<List<Fattura>>(list, HttpStatus.OK);
			return resp;
		}
		
		@GetMapping("/findbyanno")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Fattura>> findByAnno(@RequestBody int anno) {
			List<Fattura> list = svc.findByAnno(anno);
			ResponseEntity<List<Fattura>> resp = new ResponseEntity<List<Fattura>>(list, HttpStatus.OK);
			return resp;
		}
		
		@GetMapping("/findbydata")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Fattura>> findByData(@RequestBody LocalDate data) {
			List<Fattura> list = svc.findByData(data);
			ResponseEntity<List<Fattura>> resp = new ResponseEntity<List<Fattura>>(list, HttpStatus.OK);
			return resp;
		}
		
		@GetMapping("/findbystato")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Fattura>> findByStato(@RequestBody StatoFattura stato) {
			List<Fattura> list = svc.findByStato(stato);
			ResponseEntity<List<Fattura>> resp = new ResponseEntity<List<Fattura>>(list, HttpStatus.OK);
			return resp;
		}
		
		@GetMapping("/findbyrangeimporti")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Fattura>> findByRangeImporti(@RequestBody double min, @RequestBody double max) {
			List<Fattura> list = svc.findByRangeImporti(min, max);
			ResponseEntity<List<Fattura>> resp = new ResponseEntity<List<Fattura>>(list, HttpStatus.OK);
			return resp;
		}
}
