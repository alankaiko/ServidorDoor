package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.ParametrosDoSistema;

@Repository
public interface ParametrosDoSistemaRepository extends JpaRepository<ParametrosDoSistema, Long>{

}
