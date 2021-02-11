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

import com.laudoecia.api.modelo.Licenciado;
import com.laudoecia.api.repository.LicenciadoRepository;
import com.laudoecia.api.repository.filtro.LicenciadoFilter;

@Service
public class LicenciadoService {
	@Autowired
	private LicenciadoRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(LicenciadoService.class);

	public List<Licenciado> Listar() {
		return this.dao.findAll();
	}

	public Licenciado Criar(Licenciado licenciado) {
		try {
			return this.dao.save(licenciado);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de LicenciadoService");
			e.printStackTrace();
			return null;
		}
	}

	public Licenciado BuscarPorId(Long id) {
		Optional<Licenciado> licenciado = this.dao.findById(id);

		if (licenciado.get() == null)
			throw new EmptyResultDataAccessException(1);

		return licenciado.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de LicenciadoService");
			e.printStackTrace();
		}
	}

	public void Deletar(Licenciado licenciado) {
		try {
			this.dao.delete(licenciado);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de LicenciadoService");
			e.printStackTrace();
		}
	}

	public Licenciado Atualizar(Long id, Licenciado licenciado) {
		try {
			Licenciado salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(licenciado, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de LicenciadoService");
			e.printStackTrace();
			return null;
		}
	}

	public Page<Licenciado> Filtrando(LicenciadoFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de LicenciadoService");
			e.printStackTrace();
			return null;
		}	
	}
}
