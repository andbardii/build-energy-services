package com.build.energy.security.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class ComuneService {

	private Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired ComuneRepository repo;
	
	@Autowired ProvinciaService provinciaSvc;
	
	@Autowired @Qualifier("comune") private ObjectProvider<Comune> provider;
	
	// SAVE METHODS
	public Comune addComune(String nome,
							Long idProvincia) {
		
		Comune c = provider.getObject().builder()
						.nome(nome)
						.provincia(provinciaSvc.findById(idProvincia))
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
	
	// OTHER METHODS
	
	public void caricaComuni() {
		try {
			CSVReader comuniReader = new CSVReader(new FileReader("src/main/resources/comuni-italiani.csv"));
			System.out.println(comuniReader);
			String[] comuniLine;
	        try {
				while ((comuniLine = comuniReader.readNext()) != null){
					String[] c = comuniLine[0].split(";");
				    addComune(c[2], provinciaSvc.findIdByNome(c[3]));
				} findAll();
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


}
