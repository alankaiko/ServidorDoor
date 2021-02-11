package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Atendimento;
import com.laudoecia.api.repository.implementa.AtendimentoRepositoryQuery;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>, AtendimentoRepositoryQuery{
	public List<Atendimento> findByPacienteNomeStartingWith(String patientname);
	public List<Atendimento> findByCodigo(Long codigo); 
	
}
