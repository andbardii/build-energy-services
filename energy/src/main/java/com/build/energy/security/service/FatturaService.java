package com.build.energy.security.service;

import java.time.LocalDate;
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

@Service
public class FatturaService {
	
	private Logger log = LoggerFactory.getLogger(FatturaService.class);
	
	@Autowired FatturaRepository repo;
	@Autowired ClienteRepository repoCliente;
	
	@Autowired @Qualifier("fattura") private ObjectProvider<Fattura> provider;
	
	// SAVE METHODS
	public Fattura addFattura(Long id,
							  Integer anno,
							  LocalDate data,
							  Double importo,
							  Integer numero,
							  StatoFattura stato) {
			Fattura f = provider.getObject().builder()
						.anno(anno)
						.data(data)
						.importo(importo)
						.numero(numero)
						.stato(stato)
						.build();
				repo.save(f);
				
				Cliente c = repoCliente.findById(id).get();
				Set<Fattura> s = c.getFatture();
				s.add(f);
				
				c.builder()
				.fatture(s)
				.build();
				
				repoCliente.save(c);
				
				System.out.println();
				log.info("Fattura del cliente" + c.getId() + "aggiunta al Database, Id: " + f.getId());
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

}
