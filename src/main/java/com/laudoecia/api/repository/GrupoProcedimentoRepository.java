package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.GrupoProcedimento;

@Repository
public interface GrupoProcedimentoRepository extends JpaRepository<GrupoProcedimento, Long>{

}