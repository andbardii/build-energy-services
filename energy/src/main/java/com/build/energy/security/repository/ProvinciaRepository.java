package com.build.energy.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.build.energy.security.entity.Provincia;

public interface ProvinciaRepository  extends JpaRepository<Provincia, Long> {

	public List<Provincia> findByNome(String nome); 
}
