package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.GrupoCID10;
import com.laudoecia.api.repository.GrupoCid10Repository;

@Service
public class GrupoCid10Service {
	@Autowired
	private GrupoCid10Repository dao;
	private final Logger LOG = LoggerFactory.getLogger(GrupoCid10Service.class);

	
	public List<GrupoCID10> Listar() {
		return this.dao.findAll();
	}

	public GrupoCID10 Criar(GrupoCID10 grupo) {
		try {
			return this.dao.save(grupo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de GrupoCid10Service");
			e.printStackTrace();
			return null;
		}		
	}

	public GrupoCID10 BuscarPorId(Long id) {
		Optional<GrupoCID10> grupo = this.dao.findById(id);

		if (grupo.get() == null)
			throw new EmptyResultDataAccessException(1);

		return grupo.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de GrupoCid10Service");
			e.printStackTrace();
		}
	}

	public void Deletar(GrupoCID10 grupo) {
		try {
			this.dao.delete(grupo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de GrupoCid10Service");
			e.printStackTrace();
		}
	}

	public GrupoCID10 Atualizar(Long id, GrupoCID10 grupo) {
		try {
			GrupoCID10 salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(grupo, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de GrupoCid10Service");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de GrupoCid10Service");
			e.printStackTrace();
			return null;
		}		
	}

	public GrupoCID10 BuscarPorValor(String codigotexto) {
		try {
			return this.dao.findByCodigotexto(codigotexto);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Erro ao executar o metodo Atualizar------------------ de CategoriaCID10Service");
			return null;
		}
	}

}
