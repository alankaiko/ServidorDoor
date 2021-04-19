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

import com.laudoecia.api.modelo.Instancia;
import com.laudoecia.api.repository.InstanciaRepository;
import com.laudoecia.api.repository.resumo.ResumoInstancia;

@Service
public class InstanciaService {
	@Autowired
	private InstanciaRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(InstanciaService.class);

	public List<Instancia> Listar() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datemodify"));
	}

	public Instancia Criar(Instancia instancia) {
		try {
			return this.dao.save(instancia);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}

	public Instancia BuscarPorId(Long codigo) {
		Optional<Instancia> instancia = this.dao.findById(codigo);

		if (instancia.get() == null)
			throw new EmptyResultDataAccessException(1);

		return instancia.get();
	}

	public void Deletar(long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de InstanceRepository");
			e.printStackTrace();
		}
	}

	public void Deletar(Instancia instancia) {
		try {
			this.dao.delete(instancia);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de InstanceRepository");
			e.printStackTrace();
		}
	}

	public Instancia Atualizar(Long id, Instancia instancia) {
		try {
			Instancia salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(instancia, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo ------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}

	public List<Instancia> BuscarPorIdSerieDaInstancia(Long idseries) {
		try {
			return this.dao.findBySeriesCodigo(idseries);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorIdSerieDaInstancia------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}

	public Instancia BuscarPorInstanciaUid(String sopinstanceuid) {
		try {
			return this.dao.findBySopinstanceuid(sopinstanceuid);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorInstanciaUid------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}

	public List<Instancia> BuscarPacientesPeloEstudoSerie(String codigo) {
		try {
			return this.dao.findAllByseriesEstudoPacienteCodigo(codigo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPacientesPeloEstudoSerie------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}
	
	public ResumoInstancia ResumirProDicom(Long codigo) {
		try {
			Instancia insta = this.BuscarPorId(codigo);
			ResumoInstancia resumo = new ResumoInstancia();
			resumo.setIdinstance(insta.getCodigo());
			resumo.setMediastoragesopinstanceuid(insta.getMediastoragesopinstanceuid());
			resumo.setTagimagem(insta.getTagimagem().getCodigo());
			return resumo;
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPacientesPeloEstudoSerie------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}

}
