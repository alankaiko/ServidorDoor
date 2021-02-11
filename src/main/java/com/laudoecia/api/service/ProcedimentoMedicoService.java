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

import com.laudoecia.api.modelo.ProcedimentoMedico;
import com.laudoecia.api.repository.ProcedimentoMedicoRepository;
import com.laudoecia.api.repository.filtro.ProcedimentoMedicoFilter;
import com.laudoecia.api.repository.resumo.ResumoProcedimentoMedico;

@Service
public class ProcedimentoMedicoService {
	@Autowired
	private ProcedimentoMedicoRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(ProcedimentoMedicoService.class);

	
	public List<ProcedimentoMedico> Listar() {
		return this.dao.findAll();
	}

	public ProcedimentoMedico Criar(ProcedimentoMedico procedimento) {
		try {
			return this.dao.save(procedimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ProcedimentoMedicoService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public List<ProcedimentoMedico> BuscarListaPorGrupo(String nomegrupo) {
		try {
			return this.dao.findByGrupoNomegrupoStartingWith(nomegrupo.toUpperCase());
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProcedimentoMedicoService");
			e.printStackTrace();
			return null;
		}
	}

	public List<ProcedimentoMedico> BuscarListaPorId(Long codigo) {
		try {
			return this.dao.findByCodigo(codigo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProcedimentoMedicoService");
			e.printStackTrace();
			return null;
		}
	}

	
	public ProcedimentoMedico BuscarPorId(Long id) {
		Optional<ProcedimentoMedico> procedimento = this.dao.findById(id);

		if (procedimento.get() == null)
			throw new EmptyResultDataAccessException(1);

		return procedimento.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProcedimentoMedicoService");
			e.printStackTrace();
		}
	}

	public void Deletar(ProcedimentoMedico procedimento) {
		try {
			this.dao.delete(procedimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProcedimentoMedicoService");
			e.printStackTrace();
		}
	}

	public ProcedimentoMedico Atualizar(Long id, ProcedimentoMedico procedimento) {
		try {
			ProcedimentoMedico salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(procedimento, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ProcedimentoMedicoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de ProcedimentoMedicoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Page<ResumoProcedimentoMedico> Resumindo(ProcedimentoMedicoFilter filtro, Pageable page){
		try {
			return this.dao.resumir(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Resumindo------------------ de ProcedimentoMedicoService");
			e.printStackTrace();
			return null;
		}		
	}

}
