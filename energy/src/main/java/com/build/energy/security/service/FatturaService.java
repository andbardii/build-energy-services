package com.build.energy.security.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.build.energy.security.entity.Fattura;
import com.build.energy.security.enumerated.StatoFattura;
import com.build.energy.security.repository.FatturaRepository;

@Service
public class FatturaService {
	
	private Logger log = LoggerFactory.getLogger(FatturaService.class);
	
	@Autowired FatturaRepository repo;
	@Autowired ClienteService clienteSvc;
	
	
	@Autowired @Qualifier("fattura") private ObjectProvider<Fattura> provider;
	
	// SAVE METHODS
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
	
	public List<Fattura> findByCliente(long id) {
		List<Fattura> l = repo.findByIdCliente(id);
		System.out.println();
		log.info("Fatture del Cliente id " + id + ":");
		l.forEach(c -> log.info(c.toString()));
		return l;
	}

	public List<Fattura> findByAnno(int anno) {
		List<Fattura> l = repo.findByAnno(anno);
		System.out.println();
		log.info("Fatture nell'anno " + anno + ":");
		l.forEach(c -> log.info(c.toString()));
		return l;
	}

	public List<Fattura> findByData(LocalDate data) {
		List<Fattura> l = repo.findByData(data);
		System.out.println();
		log.info("Fatture nella data " + data + ":");
		l.forEach(c -> log.info(c.toString()));
		return l;
	}

	public List<Fattura> findByStato(StatoFattura stato) {
		List<Fattura> l = repo.findByStato(stato);
		System.out.println();
		log.info("Fatture con stato " + stato + ":");
		l.forEach(c -> log.info(c.toString()));
		return l;
	}

	public List<Fattura> findByRangeImporti(double min, double max) {
		List<Fattura> l = repo.findByRangeImporti(min, max);
		System.out.println();
		log.info("Fatture con importo compreso tra " + min + " e " + max + ":");
		l.forEach(c -> log.info(c.toString()));
		return l;
	}
	
	// OTHER METHODS
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
