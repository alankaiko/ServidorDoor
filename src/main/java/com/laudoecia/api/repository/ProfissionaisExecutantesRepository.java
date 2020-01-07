package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.ProfissionalExecutante;

@Repository
public interface ProfissionaisExecutantesRepository extends JpaRepository<ProfissionalExecutante, Long>{

}
