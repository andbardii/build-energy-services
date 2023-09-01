package com.build.energy.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.build.energy.security.entity.Indirizzo;
import com.build.energy.security.service.IndirizzoService;

@RestController
@CrossOrigin(origins = "*", maxAge= 3600)
@RequestMapping("/api/indirizzi")
public class IndirizzoController {

	@Autowired IndirizzoService svc;
	
		// GET API METHODS
		@GetMapping
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Indirizzo>> findAll() {
			List<Indirizzo> list = svc.findAll();
			ResponseEntity<List<Indirizzo>> resp = new ResponseEntity<List<Indirizzo>>(list, HttpStatus.OK);
			return resp;
		}
				
		@GetMapping("/{id}")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<?> findById(@PathVariable Long id) {
			Indirizzo i = svc.findById(id);
			ResponseEntity<Indirizzo> resp = new ResponseEntity<Indirizzo>(i, HttpStatus.OK);
			return resp;
		}
		
		@PostMapping
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<?> addIndirizzo(@RequestBody Indirizzo indirizzo) {
	        Indirizzo i = svc.addIndirizzo(indirizzo.getVia(),
	        		                       indirizzo.getCivico(),
	        		                       indirizzo.getLocalita(),
	        		                       indirizzo.getCap(),
	        		                       indirizzo.getComune().getId());
	        return new ResponseEntity<Indirizzo>(i, HttpStatus.CREATED);
	    }
}
