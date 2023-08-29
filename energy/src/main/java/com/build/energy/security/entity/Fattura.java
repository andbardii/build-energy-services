package com.build.energy.security.entity;

import java.time.LocalDate;

import com.build.energy.security.enumerated.StatoFattura;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="fatture")
public class Fattura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	private Long idCliente;
	private Integer anno;
	private LocalDate data;
	private Double importo;
	private Integer numero;
	
	@Enumerated(EnumType.STRING)
	private StatoFattura stato;
	
	
}
