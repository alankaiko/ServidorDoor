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

import com.laudoecia.api.domain.TextoPessoal;
import com.laudoecia.api.repository.TextoPessoalRepository;
import com.laudoecia.api.repository.filtro.TextoPessoalFilter;

@Service
public class TextoPessoalService {
	@Autowired
	private TextoPessoalRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(TextoPessoalService.class);

	
	public List<TextoPessoal> Listar() {
		return this.dao.findAll();
	}

	public TextoPessoal Criar(TextoPessoal texto) {
		try {
			return this.dao.save(texto);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de TextoPessoalService");
			e.printStackTrace();
			return null;
		}		
	}

	public TextoPessoal BuscarPorId(Long id) {
		Optional<TextoPessoal> texto = this.dao.findById(id);

		if (texto.get() == null)
			throw new EmptyResultDataAccessException(1);

		return texto.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de TextoPessoalService");
			e.printStackTrace();
		}
	}

	public void Deletar(TextoPessoal texto) {
		try {
			this.dao.delete(texto);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de TextoPessoalService");
			e.printStackTrace();
		}
	}

	public TextoPessoal Atualizar(Long id, TextoPessoal texto) {
		try {
			TextoPessoal salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(texto, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de TextoPessoalService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de TextoPessoalService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public Page<TextoPessoal> Filtrando(TextoPessoalFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de TextoPessoalService");
			e.printStackTrace();
			return null;
		}	
	}


}
