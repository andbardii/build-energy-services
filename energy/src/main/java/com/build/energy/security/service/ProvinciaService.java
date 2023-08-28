package com.build.energy.security.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.build.energy.security.entity.Comune;
import com.build.energy.security.entity.Provincia;
import com.build.energy.security.repository.ProvinciaRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class ProvinciaService {

	private Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired ProvinciaRepository repo;
	
	@Autowired @Qualifier("provincia") private ObjectProvider<Provincia> provider;
	
	// SAVE METHODS
	public Provincia addProvincia(String nome,
								String sigla,
								String regione) {
		
		Provincia p = provider.getObject().builder()
						.nome(nome)
						.sigla(sigla)
						.regione(regione)
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
	
	// OTHER METHODS
	
		public void caricaProvince() {
			try {
				CSVReader provinceReader = new CSVReader(new FileReader("src/main/resources/province-italiane.csv"));
				System.out.println(provinceReader);
				String[] provinceLine;
		        try {
					while ((provinceLine = provinceReader.readNext()) != null){
						String[] c = provinceLine[0].split(";");
					    addProvincia(c[1], c[0], c[2]); 
					    
					} 
				} catch (CsvValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        }
			 catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public Long findIdByNome(String nome) {
			Provincia p = repo.findByNome(nome);
			if (p == null) {
				return null;
			}else {
			return p.getId();
			}
			
		}

}
