package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.CategoriaCID10;
import com.laudoecia.api.repository.CategoriaCID10Repository;

@Service
public class CategoriaCID10Service {
	@Autowired
	private CategoriaCID10Repository dao;
	

	private final Logger LOG = LoggerFactory.getLogger(CategoriaCID10Service.class);

	public List<CategoriaCID10> Listar() {
		return this.dao.findAll();
	}

	public CategoriaCID10 Criar(CategoriaCID10 categoria) {
		try {
			return this.dao.save(categoria);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de CategoriaCID10Service");
			e.printStackTrace();
			return null;
		}
	}

	public CategoriaCID10 BuscarPorId(Long id) {
		Optional<CategoriaCID10> categoria = this.dao.findById(id);

		if (categoria.get() == null)
			throw new EmptyResultDataAccessException(1);

		return categoria.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de CategoriaCID10Service");
			e.printStackTrace();
		}
	}

	public void Deletar(CategoriaCID10 categoria) {
		try {
			this.dao.delete(categoria);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de CategoriaCID10Service");
			e.printStackTrace();
		}
	}

	public CategoriaCID10 Atualizar(Long id, CategoriaCID10 categoria) {
		try {
			CategoriaCID10 salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(categoria, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de CategoriaCID10Service");
			e.printStackTrace();
			return null;
		}
	}
	
	public CategoriaCID10 BuscarCodigoTexto(String codigotexto) {
		try {
			return this.dao.findByCodigotexto(codigotexto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}






