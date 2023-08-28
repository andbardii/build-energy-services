package com.build.energy.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.build.energy.security.entity.Cliente;

@Configuration
public class ClienteConfig {

		@Bean("cliente")
	    @Scope("prototype")
	    public Cliente cliente() {
	        return new Cliente();
	    }
		
}
