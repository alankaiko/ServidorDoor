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

import com.laudoecia.api.domain.ModeloDeLaudoDoProc;
import com.laudoecia.api.repository.ModeloDeLaudoDoProcRepository;
import com.laudoecia.api.repository.filtro.ModeloDeLaudoDoProcFilter;

@Service
public class ModeloDeLaudoDoProcService {
	@Autowired
	private ModeloDeLaudoDoProcRepository dao;
	
	
	private final Logger LOG = LoggerFactory.getLogger(ModeloDeLaudoDoProcService.class);

	
	public List<ModeloDeLaudoDoProc> Listar() {
		return this.dao.findAll();
	}

	public ModeloDeLaudoDoProc Criar(ModeloDeLaudoDoProc modelo) {
		try {
			return this.dao.save(modelo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ModeloDeLaudoDoProcService");
			e.printStackTrace();
			return null;
		}		
	}

	public ModeloDeLaudoDoProc BuscarPorId(Long id) {
		Optional<ModeloDeLaudoDoProc> abreviatura = this.dao.findById(id);

		if (abreviatura.get() == null)
			throw new EmptyResultDataAccessException(1);

		return abreviatura.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ModeloDeLaudoDoProcService");
			e.printStackTrace();
		}
	}

	public void Deletar(ModeloDeLaudoDoProc modelo) {
		try {
			this.dao.delete(modelo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ModeloDeLaudoDoProcService");
			e.printStackTrace();
		}
	}

	public ModeloDeLaudoDoProc Atualizar(Long id, ModeloDeLaudoDoProc modelo) {
		try {
			ModeloDeLaudoDoProc salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(modelo, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ModeloDeLaudoDoProcService");
			e.printStackTrace();
			return null;
		}		
	}


	public Page<ModeloDeLaudoDoProc> Filtrando(ModeloDeLaudoDoProcFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de ModeloDeLaudoDoProcService");
			e.printStackTrace();
			return null;
		}	
	}
	
	public List<ModeloDeLaudoDoProc> ListarPeloCodigoProcedimento(Long codigo){
		try {
			List<ModeloDeLaudoDoProc> modelos = this.dao.findByProcedimentomedicoCodigo(codigo);
			return modelos;
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de ModeloDeLaudoDoProcService");
			e.printStackTrace();
			return null;
		}
	}

}
