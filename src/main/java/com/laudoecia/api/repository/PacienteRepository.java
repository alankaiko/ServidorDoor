package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.repository.implementa.PacienteRepositoryQuery;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>, PacienteRepositoryQuery{
	public Paciente findBypacienteid(String pacienteid);
	//public List<Paciente> findByCodigo(Long codigo);
}
