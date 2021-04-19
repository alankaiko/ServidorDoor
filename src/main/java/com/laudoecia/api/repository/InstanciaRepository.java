package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Instancia;
import com.laudoecia.api.repository.implementa.InstanciaRepositoryQuery;

@Repository
public interface InstanciaRepository extends JpaRepository<Instancia, Long>, InstanciaRepositoryQuery{
	public List<Instancia> findBySeriesCodigo(Long codigo);
	public Instancia findBySopinstanceuid(String sopinstanceuid);
	public List<Instancia> findAllByseriesEstudoPacienteCodigo(String codigo);
	public Instancia findByCodigo(Long codigo);
}
