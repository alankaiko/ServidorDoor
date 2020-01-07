package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.EspecialidadeMedica;

@Repository
public interface EspecialidadesMedicasRepository extends JpaRepository<EspecialidadeMedica, Long>{

}
