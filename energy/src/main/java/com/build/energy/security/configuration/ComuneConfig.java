package com.build.energy.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.build.energy.security.entity.Comune;

@Configuration
public class ComuneConfig {
	
	@Bean("comune")
    @Scope("prototype")
    public Comune comune() {
        return new Comune();
    }

}
