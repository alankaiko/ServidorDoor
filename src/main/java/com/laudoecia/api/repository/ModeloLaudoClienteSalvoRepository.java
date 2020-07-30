package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.ModeloLaudoClienteSalvo;

@Repository
public interface ModeloLaudoClienteSalvoRepository extends JpaRepository<ModeloLaudoClienteSalvo, Long>{
	public ModeloLaudoClienteSalvo findByProcedimentomedicoCodigo(Long codigo);
}
