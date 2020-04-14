package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.UF;

@Repository
public interface UFRepository extends JpaRepository<UF, Long>{

}
