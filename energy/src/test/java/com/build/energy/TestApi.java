package com.build.energy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import com.build.energy.security.entity.Fattura;
import com.build.energy.security.enumerated.StatoFattura;
import com.build.energy.security.payload.LoginDto;
import com.build.energy.security.repository.FatturaRepository;


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class TestApi {
	
	@Autowired TestRestTemplate restTemplate;
	@Autowired FatturaRepository repo;
	@Autowired @Qualifier("fattura") ObjectProvider<Fattura> fatturaProvider;

	@Test
	void testGetById() {
		Fattura f = fatturaProvider.getObject();
		f.setIdCliente(1l);
		f.setAnno(2023);
		f.setData(LocalDate.of(2023, 8, 30));
		f.setImporto(5000.00);
		f.setStato(StatoFattura.PAGATA);
		repo.save(f);
		
		String url = "http://localhost:8080/api/fatture/" + f.getId();
		ResponseEntity<Fattura> resp = restTemplate.getForEntity(url, Fattura.class);
		
		Fattura testFattura = resp.getBody();
		HttpStatusCode code = resp.getStatusCode();
		
		assertThat(code).isEqualTo(HttpStatus.OK);
		assertThat(testFattura.getId()).isEqualTo(f.getId());
		
	}
	
	@Test
	void testLogin() {
		LoginDto login = new LoginDto();
		login.setUsername("andbardii");
		login.setPassword("qwerty");
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<LoginDto> requestEntity = new HttpEntity<>(login, headers);
	    
	    ResponseEntity<String> response = restTemplate.postForEntity("/login", requestEntity, String.class);
	    
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
