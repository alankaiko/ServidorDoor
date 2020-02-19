package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laudoecia.api.domain.Modality;
import com.laudoecia.api.repository.impl.ModalityRepositoryQuery;

public interface ModalityRepository extends JpaRepository<Modality, Long>, ModalityRepositoryQuery{

}
