package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Tagimagem;

@Repository
public interface TagImagemRepository extends JpaRepository<Tagimagem, Long>{
}
