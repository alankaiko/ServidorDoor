package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.ProcedimentoAtendimento;
import com.laudoecia.api.repository.ProcedimentoAtendimentoRepository;

@Service
public class ProcedimentoAtendimentoService {
	
	@Autowired
	ProcedimentoAtendimentoRepository dao;
	
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);
	
	public List<ProcedimentoAtendimento> Listar() {
		return this.dao.findAll();
	}

	public ProcedimentoAtendimento Criar(ProcedimentoAtendimento procedimento) {
		try {
			return this.dao.save(procedimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
			return null;
		}		
	}

	public ProcedimentoAtendimento BuscarPorId(Long id) {
		Optional<ProcedimentoAtendimento> procedimento = this.dao.findById(id);

		if (procedimento.get() == null)
			throw new EmptyResultDataAccessException(1);

		return procedimento.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
		}
	}

	public void Deletar(ProcedimentoAtendimento procedimento) {
		try {
			this.dao.delete(procedimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
		}
	}

	public ProcedimentoAtendimento Atualizar(Long id, ProcedimentoAtendimento procedimento) {
		try {
			ProcedimentoAtendimento salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(procedimento, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
			return null;
		}		
	}

	
	
}
