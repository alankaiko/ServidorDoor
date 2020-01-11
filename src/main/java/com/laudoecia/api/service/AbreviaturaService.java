package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.Abreviatura;
import com.laudoecia.api.repository.AbreviaturasRepository;

@Service
public class AbreviaturaService {
	@Autowired
	private AbreviaturasRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	
	public List<Abreviatura> Listar() {
		return this.dao.findAll();
	}

	public Abreviatura Criar(Abreviatura abreviatura) {
		try {
			return this.dao.save(abreviatura);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de AbreviaturaService");
			e.printStackTrace();
			return null;
		}		
	}

	public Abreviatura BuscarPorId(Long id) {
		Optional<Abreviatura> abreviatura = this.dao.findById(id);

		if (abreviatura.get() == null)
			throw new EmptyResultDataAccessException(1);

		return abreviatura.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de AbreviaturaService");
			e.printStackTrace();
		}
	}

	public void Deletar(Abreviatura abreviatura) {
		try {
			this.dao.delete(abreviatura);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de AbreviaturaService");
			e.printStackTrace();
		}
	}

	public Abreviatura Atualizar(Long id, Abreviatura abreviatura) {
		try {
			Abreviatura salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(abreviatura, salvo, "id");
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
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de AbreviaturaService");
			e.printStackTrace();
			return null;
		}		
	}

//	public Page<Abreviatura> Filtrar(AbreviaturaFilter filtro, Pageable page) {
//		try {
//			return this.dao.Filtrando(filtro, page);
//		} catch (Exception e) {
//			LOG.error("Erro ao executar o metodo Filtrar------------------ de AbreviaturaService");
//			e.printStackTrace();
//			return null;
//		}		
//	}


}
