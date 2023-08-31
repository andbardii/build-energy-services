package com.build.energy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.build.energy.security.entity.ERole;
import com.build.energy.security.entity.Fattura;
import com.build.energy.security.enumerated.StatoFattura;
import com.build.energy.security.payload.JWTAuthResponse;
import com.build.energy.security.payload.LoginDto;
import com.build.energy.security.payload.RegisterDto;
import com.build.energy.security.repository.FatturaRepository;


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class TestApi {
	
	@Autowired TestRestTemplate restTemplate;
	@Autowired FatturaRepository repo;
	@Autowired @Qualifier("fattura") ObjectProvider<Fattura> fatturaProvider;

	
	public String accessToken = "";
	
	
//	@Test
//	void testRegister() {
//		RegisterDto register = new RegisterDto();
//		String baseUrl = "http://localhost:8080/api/auth";
//		register.setName("utente");
//		register.setLastname("prova");
//		register.setEmail("utente@prova.com");
//		register.setPassword("prova1");
//		register.setUsername("utenteprova");
//		
//	    
//	   // HttpEntity<RegisterDto> requestEntity = new HttpEntity<>(register);
//	    
//	    
//	    // Effettua la chiamata POST
//        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/register", register , String.class);
//	    System.out.println(response);
//	    
//	    assertEquals(HttpStatus.CREATED, response.getStatusCode());
//	}
	
	

	@Test
	@Order(1)
	void testLogin() {
		LoginDto login = new LoginDto();
		String baseUrl = "http://localhost:8080/api/auth";
		login.setUsername("utenteprova");
		login.setPassword("prova1");
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<LoginDto> requestEntity = new HttpEntity<>(login, headers);
	        
	    // Effettua la chiamata POST
        
	    ResponseEntity<JWTAuthResponse> response = restTemplate.postForEntity(baseUrl + "/login", requestEntity, JWTAuthResponse.class);
	    System.out.println(response.getBody().getAccessToken());
	    
	    accessToken = response.getBody().getAccessToken().toString();
	    System.out.println("Primo" + accessToken);
	    
	    assertEquals(login.getUsername(), response.getBody().getUsername());
	}

	
	@Test
	@Order(2)
	void testGetById() {
		Fattura f = fatturaProvider.getObject();
		f.setIdCliente(1l);
		f.setAnno(2023);
		f.setData(LocalDate.of(2023, 8, 30));
		f.setImporto(5000.00);
		f.setStato(StatoFattura.PAGATA);
		repo.save(f);
		
		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
		
        HttpEntity<?> entity = new HttpEntity<>(headers);
		
		String url = "http://localhost:8080/api/fatture/" + f.getId();
		
		ResponseEntity<Fattura> resp = restTemplate.exchange(url, HttpMethod.GET, entity, Fattura.class);
		
	
		Fattura testFattura = resp.getBody();
		HttpStatusCode code = resp.getStatusCode();
		
		assertThat(code).isEqualTo(HttpStatus.OK);
		assertThat(testFattura.getId()).isEqualTo(f.getId());
		
	}
	
}
