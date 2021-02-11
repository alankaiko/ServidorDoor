package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.CNES;

@Repository
public interface CnesRepository extends JpaRepository<CNES, Long>{

}
