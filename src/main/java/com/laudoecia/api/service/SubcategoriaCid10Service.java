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

import com.laudoecia.api.modelo.SubcategoriaCid10;
import com.laudoecia.api.repository.SubcategoriaCid10Repository;
import com.laudoecia.api.repository.filtro.SubcategoriaCid10Filter;

@Service
public class SubcategoriaCid10Service {
	@Autowired
	private SubcategoriaCid10Repository dao;
	private final Logger LOG = LoggerFactory.getLogger(SubcategoriaCid10Service.class);


	
	public List<SubcategoriaCid10> Listar() {
		return this.dao.findAll();
	}
	
	public List<SubcategoriaCid10> ListarPorCategoria(String nome) {
		try {
			return this.dao.findByCategoriacid10NomeStartingWith(nome.toUpperCase());
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo ListarPorCategoria------------------ de SubcategoriaCid10Service");
			e.printStackTrace();
			return null;
		}	
	}

	public SubcategoriaCid10 Criar(SubcategoriaCid10 subcategoria) {
		try {
			Long codigo = this.dao.BuscarIdMax();
			if(codigo == null)
				codigo = 0L;
			
			subcategoria.setCodigo(codigo + 1);
			return this.dao.save(subcategoria);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de SubcategoriaCid10Service");
			e.printStackTrace();
			return null;
		}		
	}

	public SubcategoriaCid10 BuscarPorId(Long id) {
		Optional<SubcategoriaCid10> subcategoria = this.dao.findById(id);

		if (subcategoria.get() == null)
			throw new EmptyResultDataAccessException(1);

		return subcategoria.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de SubcategoriaCid10Service");
			e.printStackTrace();
		}
	}

	public void Deletar(SubcategoriaCid10 subcategoria) {
		try {
			this.dao.delete(subcategoria);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de SubcategoriaCid10Service");
			e.printStackTrace();
		}
	}

	public SubcategoriaCid10 Atualizar(Long id, SubcategoriaCid10 subcategoria) {
		try {
			SubcategoriaCid10 salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(subcategoria, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de SubcategoriaCid10Service");
			e.printStackTrace();
			return null;
		}		
	}

	public Page<SubcategoriaCid10> Filtrando(SubcategoriaCid10Filter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de SubcategoriaCid10Service");
			e.printStackTrace();
			return null;
		}	
	}

	public List<SubcategoriaCid10> BuscarListaPorId(Long codigo) {
		try {
			return this.dao.findByCodigo(codigo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}
	}


}






