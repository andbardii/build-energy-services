package com.build.energy.security.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.build.energy.security.enumerated.TipoCliente;
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
	
	@Override
	public void run(String... args) throws Exception {
		
		provinciaSvc.caricaProvince();
		comuneSvc.caricaComuni();
		
		indirizzoSvc.caricaIndirizzi();
		clienteSvc.caricaClienti();
		
		fatturaSvc.caricaFatture();
		 
		  
		  
		  
		 
		
		
		
		
	}
	

}
