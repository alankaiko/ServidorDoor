package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.PaginaDeImagens;
import com.laudoecia.api.repository.PaginaDeImagensRepository;

@Service
public class PaginaImagensService {
	@Autowired
	private PaginaDeImagensRepository dao;

	private final Logger LOG = LoggerFactory.getLogger(AbreviaturaService.class);

	public List<PaginaDeImagens> Listar() {
		return this.dao.findAll();
	}

	public PaginaDeImagens Criar(PaginaDeImagens pagina) {
		try {
			return this.dao.save(pagina);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de PaginaImagensService");
			e.printStackTrace();
			return null;
		}
	}

	public PaginaDeImagens BuscarPorId(Long id) {
		Optional<PaginaDeImagens> pagina = this.dao.findById(id);

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

	public void Deletar(PaginaDeImagens pagina) {
		try {
			this.dao.delete(pagina);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PaginaImagensService");
			e.printStackTrace();
		}
	}

	public PaginaDeImagens Atualizar(Long id, PaginaDeImagens pagina) {
		try {
			PaginaDeImagens salvo = this.BuscarPorId(id);
			
			salvo.getImagemimpressa().clear();
			salvo.getImagemimpressa().addAll(pagina.getImagemimpressa());
			salvo.getImagemimpressa().forEach(pro -> pro.setPaginadeimagens(salvo));
			
			BeanUtils.copyProperties(pagina, salvo, "codigo", "imagemimpressa");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de PaginaImagensService");
			e.printStackTrace();
			return null;
		}
	}

}
