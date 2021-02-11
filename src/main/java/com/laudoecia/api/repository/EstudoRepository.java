package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Estudo;
import com.laudoecia.api.repository.implementa.EstudoRepositoryQuery;

@Repository
public interface EstudoRepository extends JpaRepository<Estudo, Long>, EstudoRepositoryQuery{
	public List<Estudo> findByPacienteCodigo(String codigo);
	public Estudo findByStudyinstanceuid(String studyinstanceuid);
}
