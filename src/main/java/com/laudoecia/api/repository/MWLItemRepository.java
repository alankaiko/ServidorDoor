package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.MWLItem;
import com.laudoecia.api.repository.implementa.MWLItemRepositoryQuery;

@Repository
public interface MWLItemRepository extends JpaRepository<MWLItem, Long>, MWLItemRepositoryQuery{

}
