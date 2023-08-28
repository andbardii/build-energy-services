package com.build.energy.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.build.energy.security.entity.Indirizzo;

@Configuration
public class IndirizzoConfig {

	@Bean("indirizzo")
    @Scope("prototype")
    public Indirizzo indirizzo() {
        return new Indirizzo();
    }

}
