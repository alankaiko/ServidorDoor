package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Abreviatura;

@Repository
public interface AbreviaturasRepository extends JpaRepository<Abreviatura, Long>{

}