package com.laudoecia.api.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.laudoecia.api.domain.Instance;
import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.repository.PatientRepository;
import com.laudoecia.api.repository.filtro.PatientFilter;
import com.laudoecia.api.repository.resumo.ResumoPatient;

@Service
public class PatientService {
	@Autowired
	private PatientRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(PatientService.class);
	
	@Autowired
	private InstanceService servInstance;

	@Value("${pacs.storage.dcm}")
	private String storageDir;
	
	public List<Patient> Listar() {
		return this.dao.findAll(Sort.by(Sort.Direction.ASC, "datemodify"));
	}
	
	public Page<ResumoPatient> ListaFiltros(PatientFilter filtro, Pageable page){
		return this.dao.Resumir(filtro, page);		
	}
	
	public Page<Patient> Listar(PatientFilter filtro, Pageable pageable){
		try {
			return this.dao.Filtrar(filtro, pageable);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Listar Com Pageable------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public List<Patient> ListarResultMaximo(int primeiro, int maximo) {
		try {
			return this.dao.ListarMaximoCom(primeiro, maximo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo ListarResultMaximo------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public Patient Criar(Patient paciente) {
		try {
			return this.dao.save(paciente);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de PatientService");
			e.printStackTrace();
			return null;
		}
	}

	public Patient BuscarPorId(Long id) {
		Optional<Patient> paciente = this.dao.findById(id);

		if (paciente.get() == null)
			throw new EmptyResultDataAccessException(1);

		return paciente.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PatientService");
			e.printStackTrace();
		}
	}

	public void Deletar(Patient paciente) {
		try {
			this.dao.delete(paciente);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de PatientService");
			e.printStackTrace();
		}
	}

	public Patient Atualizar(Long id, Patient paciente) {
		try {
			Patient salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(paciente, salvo, "id");
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

	public Patient BuscarPorPacienteId(String patientid) {
		try {
			return this.dao.findByPatientid(patientid);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorPacienteId------------------ de PatientService");
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
	
	public List<Instance> BuscarPorInstanciasDoPaciente(Long idpatient){
		Patient paciente = this.BuscarPorId(idpatient);
		return paciente.getStudyes().get(0).getSeries().get(0).getInstance();
	}

}
