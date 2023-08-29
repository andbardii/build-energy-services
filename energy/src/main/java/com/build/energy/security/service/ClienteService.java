package com.build.energy.security.service;

import java.time.LocalDate;
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
	
	@Autowired IndirizzoService indirizzoSvc;
	
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
							  Long idSedeLegale,
							  Long idSedeOperativa) {
		
			Cliente c = provider.getObject().builder()
					    .dataInserimento(LocalDate.now())
					    .dataUltimoContatto(LocalDate.now())
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
						.sedeLegale(indirizzoSvc.findById(idSedeLegale))
						.sedeOperativa(indirizzoSvc.findById(idSedeOperativa))
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

	public void caricaClienti() {
		// TODO Auto-generated method stub
		addCliente( "Amazon", "GFSR537882", "v.schembri@gmail.com", 
	               3000000.00, "v.schembri@gmail.pec", 3452789, 
	               "a.bardi@gmail.pec","Andrea", "Bardi", 
	               3477828, TipoCliente.SPA, 1l, 1l);
		
		addCliente( "INPS", "DTS5978820", "v.falcone@gmail.com", 
	               200000.00, "v.falcone@gmail.pec", 2345789, 
	               "a.brancato@gmail.pec","Marco", "Brancato", 
	               6472328, TipoCliente.PA, 2l, 2l);
		
		addCliente( "CURELLA Servizi", "C0A3478820", "v.curella@gmail.com", 
	               200000.00, "v.curella@gmail.pec", 2334349, 
	               "a.licata@gmail.pec","Giuseppe", "Licata", 
	               8977828, TipoCliente.SAS, 3l,3l);
		
		addCliente( "Poste private", "C9P3478820", "v.poste@gmail.com", 
	               200000.00, "v.poste@gmail.pec", 4535789, 
	               "a.poste@gmail.pec","Servizi", "Milani", 
	               3477428, TipoCliente.SRL, 4l, 4l);
	}

	

}
