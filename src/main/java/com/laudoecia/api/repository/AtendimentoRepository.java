package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Atendimento;
import com.laudoecia.api.repository.impl.AtendimentoRepositoryQuery;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>, AtendimentoRepositoryQuery{
}
