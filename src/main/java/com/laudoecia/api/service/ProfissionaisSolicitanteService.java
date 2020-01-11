package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.ProfissionalSolicitante;
import com.laudoecia.api.repository.ProfissionaisSolicitantesRepository;

@Service
public class ProfissionaisSolicitanteService {
	@Autowired
	private ProfissionaisSolicitantesRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	
	public List<ProfissionalSolicitante> Listar() {
		return this.dao.findAll();
	}

	public ProfissionalSolicitante Criar(ProfissionalSolicitante prof) {
		try {
			return this.dao.save(prof);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ProfissionaisSolicitanteService");
			e.printStackTrace();
			return null;
		}		
	}

	public ProfissionalSolicitante BuscarPorId(Long id) {
		Optional<ProfissionalSolicitante> prof = this.dao.findById(id);

		if (prof.get() == null)
			throw new EmptyResultDataAccessException(1);

		return prof.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProfissionaisSolicitanteService");
			e.printStackTrace();
		}
	}

	public void Deletar(ProfissionalSolicitante prof) {
		try {
			this.dao.delete(prof);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProfissionaisSolicitanteService");
			e.printStackTrace();
		}
	}

	public ProfissionalSolicitante Atualizar(Long id, ProfissionalSolicitante prof) {
		try {
			ProfissionalSolicitante salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(prof, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ProfissionaisSolicitanteService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de ProfissionaisSolicitanteService");
			e.printStackTrace();
			return null;
		}		
	}


}
