package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.CNES;
import com.laudoecia.api.repository.CnesRepository;

@Service
public class CnesService {
	@Autowired
	private CnesRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	
	public List<CNES> Listar() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datemodify"));
	}

	public CNES Criar(CNES cnes) {
		try {
			return this.dao.save(cnes);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de CnesService");
			e.printStackTrace();
			return null;
		}		
	}

	public CNES BuscarPorId(Long id) {
		Optional<CNES> cnes = this.dao.findById(id);

		if (cnes.get() == null)
			throw new EmptyResultDataAccessException(1);

		return cnes.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de CnesService");
			e.printStackTrace();
		}
	}

	public void Deletar(CNES cnes) {
		try {
			this.dao.delete(cnes);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de CnesService");
			e.printStackTrace();
		}
	}

	public CNES Atualizar(Long id, CNES cnes) {
		try {
			CNES salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(cnes, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de CnesService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de CnesService");
			e.printStackTrace();
			return null;
		}		
	}


}
