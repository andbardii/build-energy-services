package com.build.energy.security.pageable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.build.energy.security.entity.Comune;
import com.build.energy.security.service.ComuneService;

@RestController
@RequestMapping("/api/comuni/pageable")
public class ComunePageController {

	@Autowired ComuneService svc;

	// GET API METHODS
	@GetMapping
	public ResponseEntity<Page<Comune>> getAll(Pageable p) {
		Page<Comune> page = svc.getAll(p);
		ResponseEntity<Page<Comune>> resp = new ResponseEntity<Page<Comune>>(page, HttpStatus.OK);
		return resp;
	}
}
