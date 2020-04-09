package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.Crm;
import com.laudoecia.api.repository.CrmRepository;

@Service
public class CrmService {
	@Autowired
	private CrmRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(CrmService.class);

	
	public List<Crm> Listar() {
		return this.dao.findAll();
	}

	public Crm Criar(Crm crm) {
		try {
			return this.dao.save(crm);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de CrmRepository");
			e.printStackTrace();
			return null;
		}		
	}

	public Crm BuscarPorId(Long id) {
		Optional<Crm> crm = this.dao.findById(id);

		if (crm.get() == null)
			throw new EmptyResultDataAccessException(1);

		return crm.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de CrmRepository");
			e.printStackTrace();
		}
	}

	public void Deletar(Crm crm) {
		try {
			this.dao.delete(crm);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de CrmRepository");
			e.printStackTrace();
		}
	}

	public Crm Atualizar(Long id, Crm crm) {
		try {
			Crm salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(crm, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de CrmRepository");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de CrmRepository");
			e.printStackTrace();
			return null;
		}		
	}


}
