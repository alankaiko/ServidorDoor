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

import com.laudoecia.api.modelo.GrupoProcedimento;
import com.laudoecia.api.repository.GrupoProcedimentoRepository;
import com.laudoecia.api.repository.filtro.GrupoProcedimentoFilter;

@Service
public class GrupoProcedimentoService {
	@Autowired
	private GrupoProcedimentoRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(GrupoProcedimentoService.class);

	
	public List<GrupoProcedimento> Listar() {
		return this.dao.findAll();
	}

	public GrupoProcedimento Criar(GrupoProcedimento grupo, boolean conf) {
		try {
			Long codigo = this.dao.BuscarIdMax();
			if(codigo == null) 
				codigo = 0L;
			
			if(conf)
				grupo.setCodigo(codigo + 1);
			
			return this.dao.save(grupo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de GrupoProcedimentoService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public List<GrupoProcedimento> BuscarListaPorId(Long codigo) {
		try {
			return this.dao.findByCodigo(codigo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}
	}

	public GrupoProcedimento BuscarPorId(Long id) {
		Optional<GrupoProcedimento> grupo = this.dao.findById(id);

		if (grupo.get() == null)
			throw new EmptyResultDataAccessException(1);

		return grupo.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de GrupoProcedimentoService");
			e.printStackTrace();
		}
	}

	public void Deletar(GrupoProcedimento grupo) {
		try {
			this.dao.delete(grupo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de GrupoProcedimentoService");
			e.printStackTrace();
		}
	}

	public GrupoProcedimento Atualizar(Long id, GrupoProcedimento grupo) {
		try {
			grupo.setNomegrupo(grupo.getNomegrupo().toUpperCase());
			GrupoProcedimento salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(grupo, salvo, "codigo");
			return this.Criar(salvo, false);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de GrupoProcedimentoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de GrupoProcedimentoService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public Page<GrupoProcedimento> Filtrando(GrupoProcedimentoFilter filtro, Pageable page){
		try {
			return this.dao.resumir(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Resumindo------------------ de GrupoProcedimentoService");
			e.printStackTrace();
			return null;
		}	
	}
	
	public Boolean VerificarSeNomeExiste(String nome) {
		try {
			return this.dao.VerificarGrupoProcNome(nome);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo VerificarSeNomeExiste------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}	
	}


}
