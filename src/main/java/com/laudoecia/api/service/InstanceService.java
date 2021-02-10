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

import com.laudoecia.api.domain.Instance;
import com.laudoecia.api.repository.InstanceRepository;
import com.laudoecia.api.repository.resumo.ResumoInstance;

@Service
public class InstanceService {
	@Autowired
	private InstanceRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(InstanceService.class);

	public List<Instance> Listar() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datemodify"));
	}

	public Instance Criar(Instance instancia) {
		try {
			return this.dao.save(instancia);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}

	public Instance BuscarPorId(Long codigo) {
		Optional<Instance> instancia = this.dao.findById(codigo);

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

	public void Deletar(Instance instancia) {
		try {
			this.dao.delete(instancia);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de InstanceRepository");
			e.printStackTrace();
		}
	}

	public Instance Atualizar(Long id, Instance instancia) {
		try {
			Instance salvo = this.BuscarPorId(id);
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

	public List<Instance> BuscarPorIdSerieDaInstancia(Long idseries) {
		try {
			return this.dao.findBySeriesCodigo(idseries);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorIdSerieDaInstancia------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}

	public Instance BuscarPorInstanciaUid(String sopinstanceuid) {
		try {
			return this.dao.findBySopinstanceuid(sopinstanceuid);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorInstanciaUid------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}

	public List<Instance> BuscarPacientesPeloEstudoSerie(Long idpatient) {
		try {
			return this.dao.findAllByseriesStudyPacienteIdpatient(idpatient);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPacientesPeloEstudoSerie------------------ de InstanceRepository");
			e.printStackTrace();
			return null;
		}
	}
	
	public ResumoInstance ResumirProDicom(Long codigo) {
		try {
			Instance insta = this.BuscarPorId(codigo);
			ResumoInstance resumo = new ResumoInstance();
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
