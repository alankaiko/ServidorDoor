package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.ModeloLaudoClienteSalvo;
import com.laudoecia.api.repository.ModeloLaudoClienteSalvoRepository;

@Service
public class ModeloLaudoClienteSalvoService {
	@Autowired
	private ModeloLaudoClienteSalvoRepository dao;
	
	
	private final Logger LOG = LoggerFactory.getLogger(AbreviaturaService.class);

	
	public List<ModeloLaudoClienteSalvo> Listar() {
		return this.dao.findAll();
	}

	public ModeloLaudoClienteSalvo Criar(ModeloLaudoClienteSalvo modelo) {
		try {
			return this.dao.save(modelo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ModeloLaudoClienteSalvoService");
			e.printStackTrace();
			return null;
		}		
	}

	public ModeloLaudoClienteSalvo BuscarPorId(Long id) {
		Optional<ModeloLaudoClienteSalvo> modelo = this.dao.findById(id);

		if (modelo.get() == null)
			throw new EmptyResultDataAccessException(1);

		return modelo.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ModeloLaudoClienteSalvoService");
			e.printStackTrace();
		}
	}

	public void Deletar(ModeloLaudoClienteSalvo modelo) {
		try {
			this.dao.delete(modelo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ModeloLaudoClienteSalvoService");
			e.printStackTrace();
		}
	}

	public ModeloLaudoClienteSalvo Atualizar(Long id, ModeloLaudoClienteSalvo modelo) {
		try {
			ModeloLaudoClienteSalvo salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(modelo, salvo, "codigo", "procedimentomedico", "paginas");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ModeloLaudoClienteSalvoService");
			e.printStackTrace();
			return null;
		}		
	}

	
	public ModeloLaudoClienteSalvo BuscarPelaIdProcedimento(Long codigo) {
		try {
			return this.dao.findByProcedimentomedicoCodigo(codigo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPelaIdProcedimento------------------ de ModeloLaudoClienteSalvoService");
			e.printStackTrace();
			return null;
		}		
	}
	

}
