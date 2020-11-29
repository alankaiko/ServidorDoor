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

import com.laudoecia.api.domain.Estado;
import com.laudoecia.api.repository.EstadoRepository;
import com.laudoecia.api.repository.filtro.EstadoFilter;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository dao;	
	private final Logger LOG = LoggerFactory.getLogger(EstadoService.class);


	public List<Estado> Listar() {
		return this.dao.findAll();
	}

	public Estado Criar(Estado estado) {
		try {
			return this.dao.save(estado);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de EstadoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Estado BuscarPorId(Long id) {
		Optional<Estado> estado = this.dao.findById(id);

		if (estado.get() == null)
			throw new EmptyResultDataAccessException(1);

		return estado.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de EstadoService");
			e.printStackTrace();
		}
	}

	public void Deletar(Estado estado) {
		try {
			this.dao.delete(estado);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de EstadoService");
			e.printStackTrace();
		}
	}

	public Estado Atualizar(Long id, Estado estado) {
		try {
			Estado salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(estado, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de EstadoService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public Page<Estado> Filtrando(EstadoFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de EstadoService");
			e.printStackTrace();
			return null;
		}	
	}

}
