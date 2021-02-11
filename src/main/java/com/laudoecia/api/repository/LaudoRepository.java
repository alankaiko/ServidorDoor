package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Laudo;

@Repository
public interface LaudoRepository extends JpaRepository<Laudo, Long>{

}
