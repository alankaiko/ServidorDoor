package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.ProcedimentoMedico;

@Repository
public interface ProcedimentoMedicoRepository extends JpaRepository<ProcedimentoMedico, Long>{

}
