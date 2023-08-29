package com.build.energy.security.pageable.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.build.energy.security.entity.Indirizzo;

public interface IndirizzoPageable extends PagingAndSortingRepository<Indirizzo, Long> {

}
