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

import com.laudoecia.api.domain.Atendimento;
import com.laudoecia.api.repository.AtendimentoRepository;

@Service
public class AtendimentoService {
	@Autowired
	private AtendimentoRepository dao;

	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	public List<Atendimento> Listar() {
		return this.dao.findAll();
	}

	public Atendimento Criar(Atendimento atendimento) {
		try {
			atendimento.getProcedimentos().forEach(pro -> pro.setAtendimento(atendimento));
			return this.dao.save(atendimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de AtendimentoService");
			e.printStackTrace();
			return null;
		}
	}

	public Atendimento BuscarPorId(Long id) {
		Optional<Atendimento> atendimento = this.dao.findById(id);

		if (atendimento.get() == null)
			throw new EmptyResultDataAccessException(1);

		return atendimento.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de AtendimentoService");
			e.printStackTrace();
		}
	}

	public void Deletar(Atendimento atendimento) {
		try {
			this.dao.delete(atendimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de AtendimentoService");
			e.printStackTrace();
		}
	}

	public Atendimento Atualizar(Long id, Atendimento atendimento) {
		try {
			Atendimento salvo = this.BuscarPorId(id);
			
			salvo.getProcedimentos().clear();
			salvo.getProcedimentos().addAll(atendimento.getProcedimentos());
			salvo.getProcedimentos().forEach(pro -> pro.setAtendimento(salvo));
			
			BeanUtils.copyProperties(atendimento, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de AbreviaturaService");
			e.printStackTrace();
			return null;
		}
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de AtendimentoService");
			e.printStackTrace();
			return null;
		}
	}

	public Page<Atendimento> Filtrando(Atendimento filtro, Pageable page) {
		try {
			return this.dao.FiltroPaginado(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de AbreviaturaService");
			e.printStackTrace();
			return null;
		}
	}

}
