package com.build.energy.security.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.build.energy.security.entity.Comune;
import com.build.energy.security.entity.Indirizzo;
import com.build.energy.security.repository.IndirizzoRepository;

@Service
public class IndirizzoService {
	
	private Logger log = LoggerFactory.getLogger(ClienteService.class);

	@Autowired IndirizzoRepository repo;
	
	@Autowired ComuneService comuneSvc;
	
	@Autowired @Qualifier("indirizzo") private ObjectProvider<Indirizzo> provider;
	
	// SAVE METHODS
	public Indirizzo addIndirizzo(String via,
								Integer civico,
								String localita,
								Integer cap,
								Long idComune) {
		
		Indirizzo i = provider.getObject().builder()
						.via(via)
						.civico(civico)
						.localita(localita)
						.cap(cap)
						.comune(comuneSvc.findById(idComune))
						.build();
				repo.save(i);
				System.out.println();
				log.info("Indirizzo aggiunto al Database, Id: " + i.getId());
				return i;
	}
	
	// FIND METHODS
	public Indirizzo findById(long id) {
		Indirizzo i = repo.findById(id).get();
		log.info(i.toString());
		return i;
	}
	
	public List<Indirizzo> findAll(){
		List<Indirizzo> l = (List<Indirizzo>)repo.findAll();
		l.forEach(i -> log.info(i.toString()));
		return l;
	}


}
