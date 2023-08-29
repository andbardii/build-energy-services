package com.build.energy.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.build.energy.security.entity.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
   public List<Fattura> findByIdCliente( Long idCliente);
}
