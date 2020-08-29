package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.Laudo;
import com.laudoecia.api.repository.LaudoRepository;

@Service
public class LaudoService {
	@Autowired
	private LaudoRepository dao;
	
	private final Logger LOG = LoggerFactory.getLogger(AbreviaturaService.class);

	
	public List<Laudo> Listar() {
		return this.dao.findAll();
	}

	public Laudo Criar(Laudo laudo) {
		try {
			return this.dao.save(laudo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de LaudoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Laudo BuscarPorId(Long id) {
		Optional<Laudo> laudo = this.dao.findById(id);

		if (laudo.get() == null)
			throw new EmptyResultDataAccessException(1);

		return laudo.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de LaudoService");
			e.printStackTrace();
		}
	}

	public void Deletar(Laudo laudo) {
		try {
			this.dao.delete(laudo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de AbreviaturaService");
			e.printStackTrace();
		}
	}

	public Laudo Atualizar(Long id, Laudo laudo) {
		try {
			Laudo salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(laudo, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de LaudoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de LaudoService");
			e.printStackTrace();
			return null;
		}		
	}
}
