package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.ModeloDeLaudoDoProc;
import com.laudoecia.api.repository.implementa.ModeloDeLaudoDoProcRepositoryQuery;

@Repository
public interface ModeloDeLaudoDoProcRepository extends JpaRepository<ModeloDeLaudoDoProc, Long>, ModeloDeLaudoDoProcRepositoryQuery {
	public List<ModeloDeLaudoDoProc> findByProcedimentomedicoCodigoOrderByPrioridade(Long codigo);
}
