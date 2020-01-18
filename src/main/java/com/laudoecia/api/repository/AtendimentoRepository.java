package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>{
}
