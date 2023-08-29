package com.build.energy.security.pageable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.build.energy.security.entity.Provincia;
import com.build.energy.security.service.ProvinciaService;

@RestController
@RequestMapping("/api/province/pageable")
public class ProvinciaPageController {

	@Autowired ProvinciaService svc;

	// GET API METHODS
	@GetMapping
	public ResponseEntity<Page<Provincia>> getAll(Pageable p) {
		Page<Provincia> page = svc.getAll(p);
		ResponseEntity<Page<Provincia>> resp = new ResponseEntity<Page<Provincia>>(page, HttpStatus.OK);
		return resp;
	}
}
