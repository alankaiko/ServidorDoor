package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.PaginaImagens;
import com.laudoecia.api.repository.PaginaImagensRepository;

@Service
public class PaginaImagensService {
	@Autowired
	private PaginaImagensRepository dao;

	private final Logger LOG = LoggerFactory.getLogger(AbreviaturaService.class);

	public List<PaginaImagens> Listar() {
		return this.dao.findAll();
	}

	public PaginaImagens Criar(PaginaImagens pagina) {
		try {
			return this.dao.save(pagina);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de PaginaImagensService");
			e.printStackTrace();
			return null;
		}
	}

	public PaginaImagens BuscarPorId(Long id) {
		Optional<PaginaImagens> pagina = this.dao.findById(id);

		if (pagina.get() == null)
			throw new EmptyResultDataAccessException(1);

		return pagina.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PaginaImagensService");
			e.printStackTrace();
		}
	}

	public void Deletar(PaginaImagens pagina) {
		try {
			this.dao.delete(pagina);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PaginaImagensService");
			e.printStackTrace();
		}
	}

	public PaginaImagens Atualizar(Long id, PaginaImagens pagina) {
		try {
			PaginaImagens salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(pagina, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de PaginaImagensService");
			e.printStackTrace();
			return null;
		}
	}

}
