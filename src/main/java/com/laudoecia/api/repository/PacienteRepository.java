package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
