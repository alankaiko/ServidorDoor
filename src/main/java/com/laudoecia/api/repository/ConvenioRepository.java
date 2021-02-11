package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Convenio;
import com.laudoecia.api.repository.implementa.ConvenioRepositoryQuery;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long>, ConvenioRepositoryQuery{
	public List<Convenio> findByCodigo(Long codigo); 
}
