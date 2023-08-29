package com.build.energy.security.runner;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.build.energy.security.entity.Cliente;
import com.build.energy.security.enumerated.StatoFattura;
import com.build.energy.security.enumerated.TipoCliente;
import com.build.energy.security.repository.ClienteRepository;
import com.build.energy.security.service.ClienteService;
import com.build.energy.security.service.ComuneService;
import com.build.energy.security.service.FatturaService;
import com.build.energy.security.service.IndirizzoService;
import com.build.energy.security.service.ProvinciaService;

@Component
public class EnergyRunner implements CommandLineRunner{
	
	@Autowired ClienteService clienteSvc;
	@Autowired FatturaService fatturaSvc;
	@Autowired IndirizzoService indirizzoSvc;
	@Autowired ComuneService comuneSvc;
	@Autowired ProvinciaService provinciaSvc;
	@Autowired ClienteRepository repoCliente;
	
	@Override
	public void run(String... args) throws Exception {
		
		// CARICA DATI FILE CSV
		//provinciaSvc.caricaProvince();
		//comuneSvc.caricaComuni();
		
		// CARICA ALTRI DATI STATICI
		//indirizzoSvc.caricaIndirizzi();
		//clienteSvc.caricaClienti();
		//fatturaSvc.caricaFatture();
		
		// METODI CLIENTE
		//clienteSvc.findByName("Amazon");
		//clienteSvc.findClientiByFatturatoMaggiore(200000);
		//clienteSvc.findByDataInserimento(LocalDate.now());
		//clienteSvc.findByDataUltimoContatto(LocalDate.now());
		
		// METODI FATTURE
		//fatturaSvc.findByCliente(1l);
		//fatturaSvc.findByAnno(2023);
		//fatturaSvc.findByData(LocalDate.of(2023, 8, 18));
		//fatturaSvc.findByStato(StatoFattura.PAGATA);
		//fatturaSvc.findByRangeImporti(2000.00, 5000.00);
		
	}
	

}
