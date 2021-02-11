package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.ProcedimentoAtendimento;

@Repository
public interface ProcedimentoAtendimentoRepository extends JpaRepository<ProcedimentoAtendimento, Long>{
}
