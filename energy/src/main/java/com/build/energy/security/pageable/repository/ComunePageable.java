package com.build.energy.security.pageable.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.build.energy.security.entity.Comune;

public interface ComunePageable extends PagingAndSortingRepository<Comune, Long> {

}
