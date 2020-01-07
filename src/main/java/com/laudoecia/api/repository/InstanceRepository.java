package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Instance;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long>{
	public List<Instance> findBySeriesIdseries(Long idseries);
	public Instance findBySopinstanceuid(String sopinstanceuid);
	public List<Instance> findAllByseriesStudyPatientIdpatient(Long idpatient);
}
