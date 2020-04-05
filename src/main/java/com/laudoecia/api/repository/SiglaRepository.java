package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Sigla;
import com.laudoecia.api.repository.impl.SiglaRepositoryQuery;

@Repository
public interface SiglaRepository extends JpaRepository<Sigla, Long>, SiglaRepositoryQuery{

}
