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

import com.laudoecia.api.modelo.Equipamento;
import com.laudoecia.api.repository.EquipamentoRepository;

@Service
public class EquipamentoService {

	@Autowired
	private EquipamentoRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(EquipamentoService.class);

	public List<Equipamento> ListarTodos() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datemodify"));
	}

	public Equipamento Criar(Equipamento dispositivo) {
		try {
			return this.dao.save(dispositivo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
	}

	public Equipamento BuscarPorId(Long id) {
		Optional<Equipamento> dispositivo = this.dao.findById(id);

		if (dispositivo.get() == null)
			throw new EmptyResultDataAccessException(1);

		return dispositivo.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de StudyService");
			e.printStackTrace();
		}
	}

	public void Deletar(Equipamento dispositivo) {
		try {
			this.dao.delete(dispositivo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de StudyService");
			e.printStackTrace();
		}
	}

	public Equipamento Atualizar(Long id, Equipamento dispositivo) {
		try {
			Equipamento salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(dispositivo, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
	}

	public Equipamento BuscarPorSerieEquipamento(Long idseries) {
		try {
			return this.dao.findBySeriesCodigo(idseries);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorSerieEquipamento------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
	}

}
