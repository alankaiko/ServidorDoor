package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.ModeloDeLaudoDoProc;
import com.laudoecia.api.repository.impl.ModeloDeLaudoDoProcRepositoryQuery;

@Repository
public interface ModeloDeLaudoDoProcRepository extends JpaRepository<ModeloDeLaudoDoProc, Long>, ModeloDeLaudoDoProcRepositoryQuery{
	public List<ModeloDeLaudoDoProc> findByProcedimentomedicoCodigo(Long codigo);

}
