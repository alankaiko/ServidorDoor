package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Estado;
import com.laudoecia.api.repository.impl.EstadoRepositoryQuery;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>, EstadoRepositoryQuery{

}
