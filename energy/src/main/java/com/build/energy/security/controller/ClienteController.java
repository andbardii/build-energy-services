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
		
		@PostMapping
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<?> addCliente(@RequestBody Cliente cliente) {
	        Cliente c = svc.addCliente(cliente.getRagioneSociale(), 
	        		                   cliente.getPartitaIva(),
	        		                   cliente.getEmail(),
	        		                   cliente.getFatturatoAnnuale(),
	        		                   cliente.getPec(),
	        		                   cliente.getTelefono(),
	        		                   cliente.getEmailContatto(),
	        		                   cliente.getNomeContatto(),
	        		                   cliente.getCognomeContatto(),
	        		                   cliente.getTelefonoContatto(),
	        		                   cliente.getTipoCliente(),
	        		                   cliente.getSedeLegale().getId(),
	        		                   cliente.getSedeOperativa().getId());
	        return new ResponseEntity<Cliente>(c, HttpStatus.CREATED);
	    }
		
		@GetMapping("/findbyname")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<Cliente> findByName(@RequestBody String nome) {
			Cliente c = svc.findByName(nome);
			ResponseEntity<Cliente> resp = new ResponseEntity<Cliente>(c, HttpStatus.OK);
			return resp;
		}
		
		@GetMapping("/findbyfatturato")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Cliente>> findClientiByFatturatoMaggiore(@RequestBody int fatturato) {
			List<Cliente> list = svc.findClientiByFatturatoMaggiore(fatturato);
			ResponseEntity<List<Cliente>> resp = new ResponseEntity<List<Cliente>>(list, HttpStatus.OK);
			return resp;
		}
		
		@GetMapping("/findbydatainserimento")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Cliente>> findByDataInserimento(@RequestBody LocalDate DataInserimento) {
			List<Cliente> list = svc.findByDataInserimento(DataInserimento);
			ResponseEntity<List<Cliente>> resp = new ResponseEntity<List<Cliente>>(list, HttpStatus.OK);
			return resp;
		}
		
		@GetMapping("/findbydataultimocontatto")
		@PreAuthorize("isAuthenticated()")
		public ResponseEntity<List<Cliente>> findByDataUltimoContatto(@RequestBody LocalDate DataUltimoContatto) {
			List<Cliente> list = svc.findByDataUltimoContatto(DataUltimoContatto);
			ResponseEntity<List<Cliente>> resp = new ResponseEntity<List<Cliente>>(list, HttpStatus.OK);
			return resp;
		}
		
		
}
