package com.build.energy.security.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.build.energy.security.entity.Cliente;
import com.build.energy.security.entity.Fattura;
import com.build.energy.security.enumerated.StatoFattura;
import com.build.energy.security.repository.ClienteRepository;
import com.build.energy.security.repository.FatturaRepository;

import jakarta.transaction.Transactional;

@Service
public class FatturaService {
	
	private Logger log = LoggerFactory.getLogger(FatturaService.class);
	
	@Autowired FatturaRepository repo;
	@Autowired ClienteService clienteSvc;
	
	
	@Autowired @Qualifier("fattura") private ObjectProvider<Fattura> provider;

	public Fattura addFattura(Long idCliente,
							  Integer anno,
							  LocalDate data,
							  Double importo,
							  StatoFattura stato) {
		
		clienteSvc.nuovoContatto(LocalDate.now(), idCliente);
		
		Fattura f = provider.getObject().builder()
				        .idCliente(idCliente)
						.anno(anno)
						.data(data)
						.importo(importo)
	                    .numero(repo.findByIdCliente(idCliente).size()+1 )//Incrementa il numero delle fatture del Cliente
						.stato(stato)
						.build();
				repo.save(f);
				
				System.out.println();
				log.info("Fattura del cliente" + f.getIdCliente() + "aggiunta al Database, Id: " + f.getId());
				return f;
	}
	
	// FIND METHODS
	public Fattura findById(long id) {
		Fattura f = repo.findById(id).get();
		log.info(f.toString());
		return f;
	}
	
	public List<Fattura> findAll(){
		List<Fattura> l = (List<Fattura>)repo.findAll();
		l.forEach(f -> log.info(f.toString()));
		return l;
	}

	public void caricaFatture() {
		// TODO Auto-generated method stub
		addFattura( 2l,2023, LocalDate.of(2023, 8, 18),
				     34899.89, StatoFattura.PAGATA);
		
		addFattura( 1l,2023, LocalDate.of(2023, 3, 15),
			     3399.99, StatoFattura.POSTICIPATA);
		
		addFattura( 4l,2023, LocalDate.of(2023, 4, 11),
			     299.89, StatoFattura.RIFIUTATA);
		
		addFattura( 3l,2023, LocalDate.of(2023, 3, 23),
			     3349.89, StatoFattura.SOSPESA);
		
		
	}

}
