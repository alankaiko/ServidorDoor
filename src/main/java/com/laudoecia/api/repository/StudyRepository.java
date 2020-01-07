package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Study;
import com.laudoecia.api.repository.impl.StudyRepositoryQuery;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long>, StudyRepositoryQuery{
	public List<Study> findByPatientIdpatient(Long idpatient);
	public Study findByStudyinstanceuid(String studyinstanceuid);
}
