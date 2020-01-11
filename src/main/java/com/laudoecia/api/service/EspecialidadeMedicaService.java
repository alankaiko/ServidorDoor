package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.EspecialidadeMedica;
import com.laudoecia.api.repository.EspecialidadesMedicasRepository;

@Service
public class EspecialidadeMedicaService {
	@Autowired
	private EspecialidadesMedicasRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	
	public List<EspecialidadeMedica> Listar() {
		return this.dao.findAll();
	}

	public EspecialidadeMedica Criar(EspecialidadeMedica especialidade) {
		try {
			return this.dao.save(especialidade);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de EspecialidadeMedicaService");
			e.printStackTrace();
			return null;
		}		
	}

	public EspecialidadeMedica BuscarPorId(Long id) {
		Optional<EspecialidadeMedica> especialidade = this.dao.findById(id);

		if (especialidade.get() == null)
			throw new EmptyResultDataAccessException(1);

		return especialidade.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de EspecialidadeMedicaService");
			e.printStackTrace();
		}
	}

	public void Deletar(EspecialidadeMedica especialidade) {
		try {
			this.dao.delete(especialidade);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de EspecialidadeMedicaService");
			e.printStackTrace();
		}
	}

	public EspecialidadeMedica Atualizar(Long id, EspecialidadeMedica especialidade) {
		try {
			EspecialidadeMedica salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(especialidade, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de EspecialidadeMedicaService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de EspecialidadeMedicaService");
			e.printStackTrace();
			return null;
		}		
	}


}
