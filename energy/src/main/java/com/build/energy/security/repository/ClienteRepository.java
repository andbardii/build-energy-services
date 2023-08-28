package com.build.energy.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.build.energy.security.entity.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {

}
