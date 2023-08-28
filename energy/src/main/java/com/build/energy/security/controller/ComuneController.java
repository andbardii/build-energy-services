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

import com.build.energy.security.entity.Comune;
import com.build.energy.security.service.ComuneService;

@RestController
@RequestMapping("/api/comuni")
public class ComuneController {

	@Autowired ComuneService svc;
	
		// GET API METHODS
		@GetMapping
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Comune>> findAll() {
			List<Comune> list = svc.findAll();
			ResponseEntity<List<Comune>> resp = new ResponseEntity<List<Comune>>(list, HttpStatus.OK);
			return resp;
		}
		
		@GetMapping("/{id}")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<?> findById(@PathVariable Long id) {
			Comune c = svc.findById(id);
			ResponseEntity<Comune> resp = new ResponseEntity<Comune>(c, HttpStatus.OK);
			return resp;
		}
			
}
