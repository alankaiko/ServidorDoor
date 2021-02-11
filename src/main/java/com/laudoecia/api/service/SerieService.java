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

import com.laudoecia.api.modelo.Serie;
import com.laudoecia.api.repository.SerieRepository;

@Service
public class SerieService {
	@Autowired
	private SerieRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(SerieService.class);

	public List<Serie> Listar() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datemodify"));
	}

	public List<Serie> ListarResultMaximo(int primeiro, int maximo) {
		try {
			return this.dao.ListarMaximoCom(primeiro, maximo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo ListarResultMaximo------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
	}

	public Serie Criar(Serie serie) {
		try {
			return this.dao.save(serie);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
	}

	public Serie BuscarPorId(Long id) {
		Optional<Serie> serie = this.dao.findById(id);

		if (serie.get() == null)
			throw new EmptyResultDataAccessException(1);

		return serie.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de StudyService");
			e.printStackTrace();
		}
	}

	public void Deletar(Serie serie) {
		try {
			this.dao.delete(serie);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de StudyService");
			e.printStackTrace();
		}
	}

	public Serie Atualizar(Long id, Serie serie) {
		try {
			Serie salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(serie, salvo, "id");
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

	public List<Serie> BuscarPorIdEstudo(Long idstudy) {
		try {
			return this.dao.findByStudyCodigo(idstudy);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo ------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
	}

	public Serie BuscarPorSeriesinstanceuid(String seriesinstanceuid) {
		try {
			return this.dao.findBySeriesinstanceuid(seriesinstanceuid);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorSeriesinstanceuid------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
	}

	public Serie BuscaPorInstanceENumber(String seriesinstanceuid, Integer numero) {
		try {
			return this.dao.BuscarPorSeriesinstanceuid(seriesinstanceuid, numero);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorSeriesInstanceENumber------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
	}

	public List<Serie> BuscarTodosEstudosPorPacietne(String codigo) {
		try {
			return this.dao.findAllByStudyPacienteCodigo(codigo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarTodosEstudosPorPacietne------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
	}

}
