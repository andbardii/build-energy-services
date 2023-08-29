package com.build.energy.security.pageable.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.build.energy.security.entity.Provincia;

public interface ProvinciaPageable extends PagingAndSortingRepository<Provincia, Long>{

}
