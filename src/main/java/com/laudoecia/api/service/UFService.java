package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.UF;
import com.laudoecia.api.repository.UFRepository;

@Service
public class UFService {
	@Autowired
	private UFRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(UFService.class);

	
	public List<UF> Listar() {
		return this.dao.findAll();
	}

	public UF Criar(UF uf) {
		try {
			return this.dao.save(uf);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de UFService");
			e.printStackTrace();
			return null;
		}		
	}

	public UF BuscarPorId(Long id) {
		Optional<UF> uf = this.dao.findById(id);

		if (uf.get() == null)
			throw new EmptyResultDataAccessException(1);

		return uf.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de UFService");
			e.printStackTrace();
		}
	}

	public void Deletar(UF uf) {
		try {
			this.dao.delete(uf);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de UFService");
			e.printStackTrace();
		}
	}

	public UF Atualizar(Long id, UF uf) {
		try {
			UF salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(uf, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de UFService");
			e.printStackTrace();
			return null;
		}		
	}


}
