package com.build.energy.security.pageable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.build.energy.security.entity.Indirizzo;
import com.build.energy.security.service.IndirizzoService;

@RestController
@RequestMapping("/api/indirizzi/pageable")
public class IndirizzoPageController {

	@Autowired IndirizzoService svc;

	// GET API METHODS
	@GetMapping
	public ResponseEntity<Page<Indirizzo>> getAll(Pageable p) {
		Page<Indirizzo> page = svc.getAll(p);
		ResponseEntity<Page<Indirizzo>> resp = new ResponseEntity<Page<Indirizzo>>(page, HttpStatus.OK);
		return resp;
	}
}
