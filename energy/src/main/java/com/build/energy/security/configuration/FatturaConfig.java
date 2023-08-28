package com.build.energy.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.build.energy.security.entity.Fattura;

@Configuration
public class FatturaConfig {

	@Bean("fattura")
    @Scope("prototype")
    public Fattura fattura() {
        return new Fattura();
    }
	
}
