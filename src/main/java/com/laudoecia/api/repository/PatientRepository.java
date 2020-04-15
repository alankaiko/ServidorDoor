package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.repository.impl.PatientRepositoryQuery;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, PatientRepositoryQuery{
	public Patient findByPatientid(String patientid);
	public List<Patient> findByIdpatient(Long idpatient); 
}
