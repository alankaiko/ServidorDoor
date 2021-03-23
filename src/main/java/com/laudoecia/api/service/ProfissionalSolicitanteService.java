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
import org.springframework.stereotype.Service;

import com.laudoecia.api.modelo.ProfissionalSolicitante;
import com.laudoecia.api.repository.ProfissionalSolicitanteRepository;
import com.laudoecia.api.repository.filtro.ProfissionalSolicitanteFilter;

@Service
public class ProfissionalSolicitanteService {
	@Autowired
	private ProfissionalSolicitanteRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(ProfissionalSolicitanteService.class);

	
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
	
	public List<ProfissionalSolicitante> BuscarListaPorId(String descricao) {
		try {
			return this.dao.findByConselhoDescricao(descricao);
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


	public Page<ProfissionalSolicitante> Filtrando(ProfissionalSolicitanteFilter filtro, Pageable page) {
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de ProfissionaisSolicitanteService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public Boolean VerificarSeNomeExiste(String nome) {
		try {
			return this.dao.VerificarProcSolNome(nome);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo VerificarSeNomeExiste------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}	
	}
}
