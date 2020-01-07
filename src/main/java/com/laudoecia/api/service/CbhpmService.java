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

import com.laudoecia.api.domain.CBHPM;
import com.laudoecia.api.repository.CbhpmRepository;

@Service
public class CbhpmService {
	@Autowired
	private CbhpmRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	
	public List<CBHPM> Listar() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datemodify"));
	}

	public CBHPM Criar(CBHPM cbhpm) {
		try {
			return this.dao.save(cbhpm);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de CbhpmService");
			e.printStackTrace();
			return null;
		}		
	}

	public CBHPM BuscarPorId(Long id) {
		Optional<CBHPM> cbhpm = this.dao.findById(id);

		if (cbhpm.get() == null)
			throw new EmptyResultDataAccessException(1);

		return cbhpm.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de CbhpmService");
			e.printStackTrace();
		}
	}

	public void Deletar(CBHPM cbhpm) {
		try {
			this.dao.delete(cbhpm);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de CbhpmService");
			e.printStackTrace();
		}
	}

	public CBHPM Atualizar(Long id, CBHPM cbhpm) {
		try {
			CBHPM salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(cbhpm, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de CbhpmService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de CbhpmService");
			e.printStackTrace();
			return null;
		}		
	}


}
