package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.domain.Study;
import com.laudoecia.api.repository.PatientRepository;
import com.laudoecia.api.repository.filtro.PatientFilter;

@Service
public class PatientService {
	@Autowired
	private PatientRepository dao;
	
	@Autowired
	private StudyService study;
	
	private final Logger LOG = LoggerFactory.getLogger(PatientService.class);

	public List<Patient> Listar() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datemodify"));
	}
	
	public Page<Patient> Listar(PatientFilter filtro, Pageable pageable){
		try {
			return this.dao.Filtrar(filtro, pageable);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Listar Com Pageable------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public List<Patient> ListarResultMaximo(int primeiro, int maximo) {
		try {
			return this.dao.ListarMaximoCom(primeiro, maximo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo ListarResultMaximo------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public Patient Criar(Patient paciente) {
		try {
			return this.dao.save(paciente);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public Patient BuscarPorId(Long id) {
		Optional<Patient> paciente = this.dao.findById(id);

		if (paciente.get() == null)
			throw new EmptyResultDataAccessException(1);

		return paciente.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PatientService");
			e.printStackTrace();
		}
	}

	public void Deletar(Patient paciente) {
		try {
			this.dao.delete(paciente);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PatientService");
			e.printStackTrace();
		}
	}

	public Patient Atualizar(Long id, Patient paciente) {
		try {
			Patient salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(paciente, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public Patient BuscarPorPacienteId(String patientid) {
		try {
			return this.dao.findByPatientid(patientid);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorPacienteId------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}
	
	public Study estudo(Long idstudy){
		return study.BuscarPorId(idstudy);
	}
}
