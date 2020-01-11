package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.ProfissionalExecutante;
import com.laudoecia.api.repository.ProfissionaisExecutantesRepository;

@Service
public class ProfissionaisExecutantesService {
	@Autowired
	private ProfissionaisExecutantesRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	
	public List<ProfissionalExecutante> Listar() {
		return this.dao.findAll();
	}

	public ProfissionalExecutante Criar(ProfissionalExecutante prof) {
		try {
			return this.dao.save(prof);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
			return null;
		}		
	}

	public ProfissionalExecutante BuscarPorId(Long id) {
		Optional<ProfissionalExecutante> prof = this.dao.findById(id);

		if (prof.get() == null)
			throw new EmptyResultDataAccessException(1);

		return prof.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
		}
	}

	public void Deletar(ProfissionalExecutante prof) {
		try {
			this.dao.delete(prof);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
		}
	}

	public ProfissionalExecutante Atualizar(Long id, ProfissionalExecutante prof) {
		try {
			ProfissionalExecutante salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(prof, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
			return null;
		}		
	}


}
