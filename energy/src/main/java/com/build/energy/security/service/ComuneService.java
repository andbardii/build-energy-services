package com.build.energy.security.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.build.energy.security.entity.Comune;
import com.build.energy.security.entity.Provincia;
import com.build.energy.security.repository.ComuneRepository;

@Service
public class ComuneService {

	private Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired ComuneRepository repo;
	
	@Autowired @Qualifier("comune") private ObjectProvider<Comune> provider;
	
	// SAVE METHODS
	public Comune addComune(String nome,
							 Provincia provincia) {
		
		Comune c = provider.getObject().builder()
						.nome(nome)
						.provincia(provincia)
						.build();
				repo.save(c);
				System.out.println();
				log.info("Comune aggiunto al Database, Id: " + c.getId());
				return c;
	}
	
	// FIND METHODS
	public Comune findById(long id) {
		Comune c = repo.findById(id).get();
		log.info(c.toString());
		return c;
	}
	
	public List<Comune> findAll(){
		List<Comune> l = (List<Comune>)repo.findAll();
		l.forEach(c -> log.info(c.toString()));
		return l;
	}


}
