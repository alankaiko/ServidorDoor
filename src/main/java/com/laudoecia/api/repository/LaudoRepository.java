package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Laudo;

@Repository
public interface LaudoRepository extends JpaRepository<Laudo, Long>{

}
