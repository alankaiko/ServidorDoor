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

import com.laudoecia.api.domain.GrupoProcedimento;
import com.laudoecia.api.repository.GrupoProcedimentoRepository;
import com.laudoecia.api.repository.filtro.GrupoProcedimentoFilter;
import com.laudoecia.api.repository.resumo.ResumoGrupoProcedimento;

@Service
public class GrupoProcedimentoService {
	@Autowired
	private GrupoProcedimentoRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	
	public List<GrupoProcedimento> Listar() {
		return this.dao.findAll();
	}

	public GrupoProcedimento Criar(GrupoProcedimento grupo) {
		try {
			return this.dao.save(grupo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de GrupoProcedimentoService");
			e.printStackTrace();
			return null;
		}		
	}

	public GrupoProcedimento BuscarPorId(Long id) {
		Optional<GrupoProcedimento> grupo = this.dao.findById(id);

		if (grupo.get() == null)
			throw new EmptyResultDataAccessException(1);

		return grupo.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de GrupoProcedimentoService");
			e.printStackTrace();
		}
	}

	public void Deletar(GrupoProcedimento grupo) {
		try {
			this.dao.delete(grupo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de GrupoProcedimentoService");
			e.printStackTrace();
		}
	}

	public GrupoProcedimento Atualizar(Long id, GrupoProcedimento grupo) {
		try {
			GrupoProcedimento salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(grupo, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de GrupoProcedimentoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de GrupoProcedimentoService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public Page<ResumoGrupoProcedimento> Resumindo(GrupoProcedimentoFilter filtro, Pageable page){
		try {
			return this.dao.resumir(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Resumindo------------------ de GrupoProcedimentoService");
			e.printStackTrace();
			return null;
		}	
	}


}
