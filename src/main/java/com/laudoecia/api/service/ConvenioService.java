package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.Convenio;
import com.laudoecia.api.repository.ConvenioRepository;

@Service
public class ConvenioService {
	@Autowired
	private ConvenioRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	
	public List<Convenio> Listar() {
		return this.dao.findAll();
	}

	public Convenio Criar(Convenio convenio) {
		try {
			return this.dao.save(convenio);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}		
	}

	public Convenio BuscarPorId(Long id) {
		Optional<Convenio> convenio = this.dao.findById(id);

		if (convenio.get() == null)
			throw new EmptyResultDataAccessException(1);

		return convenio.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ConvenioService");
			e.printStackTrace();
		}
	}

	public void Deletar(Convenio convenio) {
		try {
			this.dao.delete(convenio);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ConvenioService");
			e.printStackTrace();
		}
	}

	public Convenio Atualizar(Long id, Convenio convenio) {
		try {
			Convenio salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(convenio, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}		
	}


}
