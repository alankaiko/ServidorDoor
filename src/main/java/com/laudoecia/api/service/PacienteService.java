package com.laudoecia.api.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.laudoecia.api.modelo.Estudo;
import com.laudoecia.api.modelo.Instancia;
import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.repository.PacienteRepository;
import com.laudoecia.api.repository.filtro.PacienteFilter;
import com.laudoecia.api.repository.resumo.ResumoPaciente;

@Service
@Qualifier
public class PacienteService {
	@Autowired
	private PacienteRepository dao;
	
	private final Logger LOG = LoggerFactory.getLogger(PacienteService.class);
	
	public PacienteService() {
		super();
	}
	
	@Value("${pacs.storage.dcm}")
	private String storageDir;
	
	@Autowired
	EstudoService servStudy;
	
	public List<Paciente> Listar() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datamodificacao"));
	}
	
	public Page<ResumoPaciente> ListaFiltros(PacienteFilter filtro, Pageable page){
		return this.dao.Resumir(filtro, page);		
	}
	
	public Page<Paciente> Listar(PacienteFilter filtro, Pageable pageable){
		try {
			return this.dao.Filtrar(filtro, pageable);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Listar Com Pageable------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public List<Paciente> ListarResultMaximo(int primeiro, int maximo) {
		try {
			return this.dao.ListarMaximoCom(primeiro, maximo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo ListarResultMaximo------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public Paciente Criar(Paciente paciente) {
		try {
			return this.dao.save(paciente);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public Paciente BuscarPorId(Long codigo) {
		Optional<Paciente> patient = this.dao.findById(codigo);

		if (patient.get() == null)
			throw new EmptyResultDataAccessException(1);

		return patient.get();
	}

	public void Deletar(Long codigo) {
		try {
			this.dao.deleteById(codigo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PatientService");
			e.printStackTrace();
		}
	}

	public void Deletar(Paciente paciente) {
		try {
			this.dao.delete(paciente);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PatientService");
			e.printStackTrace();
		}
	}

	public Paciente Atualizar(Long codigo, Paciente paciente) {
		try {
			Paciente salvo = this.BuscarPorId(codigo);
			BeanUtils.copyProperties(paciente, salvo, "codigo", "estudos");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}
	
	public byte[] BuscarImagem(String instanceuid){
		String caminho = this.storageDir + "/" + instanceuid + ".dcm";
	
    	byte[] bytes = null;
    	try {
    		InputStream imagem = new FileInputStream(caminho);
    		bytes = StreamUtils.copyToByteArray(imagem);
		} catch (Exception e) {
			e.printStackTrace();
		}                
        return bytes;
    }
	
	public List<Instancia> BuscarPorInstanciasDoPaciente(Long idpatient){
		Paciente paciente = this.BuscarPorId(idpatient);
		return paciente.getEstudos().get(0).getSeries().get(0).getInstance();
	}
	
	public List<Estudo> BuscaEstudo(String codigo) {
		return this.servStudy.BuscarPorIdPaciente(codigo);
	}
	
	public Paciente BuscarPorPacienteId(String patientid) {
		try {
			return this.dao.findBypacienteid(patientid);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorPacienteId------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}
	
//	public List<Paciente> ListaPorId(Long codigo) {
//		try {
//			return this.dao.findByCodigo(codigo);
//		} catch (Exception e) {
//			LOG.error("Erro ao executar o metodo ListaPorId------------------ de PatientService");
//			e.printStackTrace();
//			return null;
//		}
//	}

//	public List<TagImagemGamb> BuscarTags(Long idinstance){
//		Patient paciente = this.BuscarPorId(idpatient);
//		return paciente.getStudyes().get(0).getSeries().get(0).getInstance().get(0).getTagimagem();
//	}
}


