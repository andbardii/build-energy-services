package com.build.energy.security.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.build.energy.security.entity.Cliente;




public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
   
   @Query(" SELECT c FROM Cliente c WHERE c.ragioneSociale LIKE :nome ")
   public Cliente findByName(String nome);
   
   @Query(" SELECT c FROM Cliente c WHERE c.fatturatoAnnuale > :fatturato")
   public List<Cliente> findByClienteFatturato(double fatturato);
   
   public List<Cliente> findByDataInserimento(LocalDate DataInserimento );
   public List<Cliente> findByDataUltimoContatto(LocalDate DataUltimoContatto );
   
}
