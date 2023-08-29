package com.build.energy.security.entity;

import java.time.LocalDate;

import com.build.energy.security.enumerated.TipoCliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Table(name="clienti")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable= false, unique=true)
	private String ragioneSociale;
	
	@Column(nullable= false, unique=true)
	private String partitaIva;
	
	@Column(nullable= false, unique=true)
	private String email;
	
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private Double fatturatoAnnuale;
	
	@Column(nullable= false, unique=true)
	private String pec;
	
	@Column(nullable= false, unique=true)
	private Integer telefono;
	
	@Column(nullable= false, unique=true)
	private String emailContatto;
	
	private String nomeContatto;
	private String cognomeContatto;
	
	@Column(nullable= false, unique=true)
	private Integer telefonoContatto;
	
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	
	@OneToOne
	private Indirizzo sedeLegale;
	
	@OneToOne
	private Indirizzo sedeOperativa;

}
