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

import com.laudoecia.api.modelo.Estudo;
import com.laudoecia.api.repository.EstudoRepository;

@Service
public class EstudoService {
	@Autowired
	private EstudoRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(EstudoService.class);

	public List<Estudo> Listar() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datemodify"));
	}

	public List<Estudo> ListarResultMaximo(int primeiro, int maximo) {
		try {
			return this.dao.ListarMaximoCom(primeiro, maximo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo ListarResultMaximo------------------ de StudyService");
			e.printStackTrace();
			return null;
		}	
	}

	public Estudo Criar(Estudo estudo) {
		try {
			return this.dao.save(estudo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de StudyService");
			e.printStackTrace();
			return null;
		}		
	}

	public Estudo BuscarPorId(Long id) {
		Optional<Estudo> estudo = this.dao.findById(id);

		if (estudo.get() == null)
			throw new EmptyResultDataAccessException(1);

		return estudo.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de StudyService");
			e.printStackTrace();
		}
	}

	public void Deletar(Estudo estudo) {
		try {
			this.dao.delete(estudo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de StudyService");
			e.printStackTrace();
		}
	}

	public Estudo Atualizar(Long id, Estudo estudo) {
		try {
			Estudo salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(estudo, salvo, "id");
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

	public List<Estudo> BuscarPorIdPaciente(String codigo) {
		try {
			return this.dao.findByPacienteCodigo(codigo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorIdPaciente------------------ de StudyService");
			e.printStackTrace();
			return null;
		}		
	}

	public Estudo BuscarPorStudyInstanceuid(String studyinstanceuid) {
		try {
			return this.dao.findByStudyinstanceuid(studyinstanceuid);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorStudyInstanceuid------------------ de StudyService");
			e.printStackTrace();
			return null;
		}
		
	}
	

}
