package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Instance;
import com.laudoecia.api.repository.impl.InstanceRepositoryQuery;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long>, InstanceRepositoryQuery{
	public List<Instance> findBySeriesCodigo(Long codigo);
	public Instance findBySopinstanceuid(String sopinstanceuid);
	public List<Instance> findAllByseriesStudyPacienteIdpatient(Long idpatient);
	public Instance findByCodigo(Long codigo);
}
