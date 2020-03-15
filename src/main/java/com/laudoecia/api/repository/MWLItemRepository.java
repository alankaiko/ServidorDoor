package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.MWLItem;
import com.laudoecia.api.repository.impl.MWLItemRepositoryQuery;

@Repository
public interface MWLItemRepository extends JpaRepository<MWLItem, Long>, MWLItemRepositoryQuery{

}
