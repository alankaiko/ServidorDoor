package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Estado;
import com.laudoecia.api.repository.implementa.EstadoRepositoryQuery;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>, EstadoRepositoryQuery{

}
