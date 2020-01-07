package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.Paciente;
import com.laudoecia.api.repository.PacienteRepository;

@Service
public class PacienteService {
	@Autowired
	private PacienteRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	
	public List<Paciente> Listar() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datemodify"));
	}

	public Paciente Criar(Paciente paciente) {
		try {
			return this.dao.save(paciente);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de PacienteService");
			e.printStackTrace();
			return null;
		}		
	}

	public Paciente BuscarPorId(Long id) {
		Optional<Paciente> paciente = this.dao.findById(id);

		if (paciente.get() == null)
			throw new EmptyResultDataAccessException(1);

		return paciente.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PacienteService");
			e.printStackTrace();
		}
	}

	public void Deletar(Paciente paciente) {
		try {
			this.dao.delete(paciente);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PacienteService");
			e.printStackTrace();
		}
	}

	public Paciente Atualizar(Long id, Paciente paciente) {
		try {
			Paciente salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(paciente, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de PacienteService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de PacienteService");
			e.printStackTrace();
			return null;
		}		
	}


}
