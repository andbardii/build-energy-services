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

import com.build.energy.security.entity.Provincia;
import com.build.energy.security.service.ProvinciaService;

@RestController
@CrossOrigin(origins = "*", maxAge= 3600)
@RequestMapping("/api/province")
public class ProvinciaController {
	
	@Autowired ProvinciaService svc;
	
		// GET API METHODS
		@GetMapping("/set")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Provincia>> findAll() {
			List<Provincia> list = svc.findAll();
			ResponseEntity<List<Provincia>> resp = new ResponseEntity<List<Provincia>>(list, HttpStatus.OK);
			return resp;
		}
					
		@GetMapping("/{id}")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<?> findById(@PathVariable Long id) {
			Provincia p = svc.findById(id);
			ResponseEntity<Provincia> resp = new ResponseEntity<Provincia>(p, HttpStatus.OK);
			return resp;
		}
		
		@GetMapping("/search/{nome}")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<?> findByNomeAPI(@PathVariable String nome) {
			List<Provincia> p = svc.findByNomeAPI(nome);
			ResponseEntity<List<Provincia>> resp = new ResponseEntity<List<Provincia>>(p, HttpStatus.OK);
			return resp;
		}
		
		@PostMapping
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<?> addProvincia(@RequestBody Provincia provincia) {
		      Provincia p = svc.addProvincia(provincia.getNome(),
		    		                         provincia.getSigla(),
		    		                         provincia.getRegione());
		      return new ResponseEntity<Provincia>(p, HttpStatus.CREATED);
		    }

}
