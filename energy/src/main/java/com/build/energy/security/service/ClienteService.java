package com.build.energy.security.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.build.energy.security.entity.Cliente;
import com.build.energy.security.entity.Indirizzo;
import com.build.energy.security.enumerated.TipoCliente;
import com.build.energy.security.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired ClienteRepository repo;
	
	@Autowired @Qualifier("cliente") private ObjectProvider<Cliente> provider;
	
	// SAVE METHODS
	public Cliente addCliente(String ragioneSociale,
							  String partitaIva,
							  String email,
							  Double fatturatoAnnuale,
							  String pec,
							  Integer telefono,
							  String emailContatto,
							  String nomeContatto,
							  String cognomeContatto,
							  Integer telefonoContatto,
							  TipoCliente tipoCliente,
							  Indirizzo sedeLegale,
							  Indirizzo sedeOperativa) {
		
			Cliente c = provider.getObject().builder()
						.ragioneSociale(ragioneSociale)
						.partitaIva(partitaIva)
						.email(email)
						.fatturatoAnnuale(fatturatoAnnuale)
						.pec(pec)
						.telefono(telefono)
						.emailContatto(emailContatto)
						.nomeContatto(nomeContatto)
						.cognomeContatto(cognomeContatto)
						.telefonoContatto(telefonoContatto)
						.tipoCliente(tipoCliente)
						.sedeLegale(sedeLegale)
						.sedeOperativa(sedeOperativa)
						.build();
				repo.save(c);
				System.out.println();
				log.info("Cliente aggiunto al Database, Id: " + c.getId());
				return c;
	}
	
	// FIND METHODS
	public Cliente findById(long id) {
		Cliente c = repo.findById(id).get();
		log.info(c.toString());
		return c;
	}
	
	public List<Cliente> findAll(){
		List<Cliente> l = (List<Cliente>)repo.findAll();
		l.forEach(c -> log.info(c.toString()));
		return l;
	}

}
