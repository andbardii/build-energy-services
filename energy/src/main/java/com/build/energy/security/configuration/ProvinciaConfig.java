package com.build.energy.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.build.energy.security.entity.Provincia;

@Configuration
public class ProvinciaConfig {

	@Bean("provincia")
    @Scope("prototype")
    public Provincia provincia() {
        return new Provincia();
    }

}
