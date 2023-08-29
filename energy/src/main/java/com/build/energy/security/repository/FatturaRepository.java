package com.build.energy.security.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.build.energy.security.entity.Fattura;
import com.build.energy.security.enumerated.StatoFattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
   
	@Query("SELECT f FROM Fattura f WHERE f.importo > :min AND f.importo < :max")
	public List<Fattura> findByRangeImporti(Double min, Double max);
	
	public List<Fattura> findByIdCliente(Long idCliente);
	public List<Fattura> findByStato(StatoFattura stato);
	public List<Fattura> findByData(LocalDate data);
	public List<Fattura> findByAnno(Integer anno);
	
}
