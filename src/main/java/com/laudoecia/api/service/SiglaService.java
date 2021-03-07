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

import com.laudoecia.api.modelo.Sigla;
import com.laudoecia.api.repository.SiglaRepository;
import com.laudoecia.api.repository.filtro.SiglaFilter;

@Service
public class SiglaService {
	@Autowired
	private SiglaRepository dao;	
	private final Logger LOG = LoggerFactory.getLogger(SiglaService.class);

	
	public List<Sigla> Listar() {
		return this.dao.findAll();
	}

	public Sigla Criar(Sigla sigla) {
		try {
			Long codigo = this.dao.BuscarIdMax();
			if(codigo == null)
				codigo = 0L;
			
			sigla.setCodigo(codigo + 1);
			return this.dao.save(sigla);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de SiglaService");
			e.printStackTrace();
			return null;
		}		
	}

	public Sigla BuscarPorId(Long id) {
		Optional<Sigla> sigla = this.dao.findById(id);

		if (sigla.get() == null)
			throw new EmptyResultDataAccessException(1);

		return sigla.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de SiglaService");
			e.printStackTrace();
		}
	}

	public void Deletar(Sigla sigla) {
		try {
			this.dao.delete(sigla);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de SiglaService");
			e.printStackTrace();
		}
	}

	public Sigla Atualizar(Long id, Sigla sigla) {
		try {
			Sigla salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(sigla, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de SiglaService");
			e.printStackTrace();
			return null;
		}		
	}


	public Page<Sigla> Filtrando(SiglaFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de SiglaService");
			e.printStackTrace();
			return null;
		}	
	}


}
