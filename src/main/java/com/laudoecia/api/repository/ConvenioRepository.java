package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Convenio;
import com.laudoecia.api.repository.impl.ConvenioRepositoryQuery;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long>, ConvenioRepositoryQuery{

}
