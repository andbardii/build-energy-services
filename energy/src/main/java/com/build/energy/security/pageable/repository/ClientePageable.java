package com.build.energy.security.pageable.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.build.energy.security.entity.Cliente;

public interface ClientePageable extends PagingAndSortingRepository<Cliente, Long>{
	
}
