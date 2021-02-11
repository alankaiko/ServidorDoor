package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.CBHPM;

@Repository
public interface CbhpmRepository extends JpaRepository<CBHPM, Long>{

}
