package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Convenio;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long>{

}
