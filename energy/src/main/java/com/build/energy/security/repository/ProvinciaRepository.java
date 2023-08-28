package com.build.energy.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.build.energy.security.entity.Provincia;

public interface ProvinciaRepository  extends JpaRepository<Provincia, Long> {

	public Provincia findByNome(String nome); 
}
