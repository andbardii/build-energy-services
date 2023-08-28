package com.build.energy.security.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.build.energy.security.entity.Provincia;
import com.build.energy.security.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	private Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired ProvinciaRepository repo;
	
	@Autowired @Qualifier("provincia") private ObjectProvider<Provincia> provider;
	
	// SAVE METHODS
	public Provincia addProvincia(String nome,
								String sigla) {
		
		Provincia p = provider.getObject().builder()
						.nome(nome)
						.sigla(sigla)
						.build();
				repo.save(p);
				System.out.println();
				log.info("Provincia aggiunta al Database, Id: " + p.getId());
				return p;
	}
	
	// FIND METHODS
	public Provincia findById(long id) {
		Provincia p = repo.findById(id).get();
		log.info(p.toString());
		return p;
	}
	
	public List<Provincia> findAll(){
		List<Provincia> l = (List<Provincia>)repo.findAll();
		l.forEach(p -> log.info(p.toString()));
		return l;
	}

}
