package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Abreviatura;
import com.laudoecia.api.repository.implementa.AbreviaturaRepositoryQuery;

@Repository
public interface AbreviaturaRepository extends JpaRepository<Abreviatura, Long>, AbreviaturaRepositoryQuery{

}
