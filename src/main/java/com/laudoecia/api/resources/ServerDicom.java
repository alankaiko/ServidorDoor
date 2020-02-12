package com.laudoecia.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.domain.Series;
import com.laudoecia.api.domain.Study;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.repository.filtro.PatientFilter;
import com.laudoecia.api.repository.resumo.ResumoPatient;
import com.laudoecia.api.service.PatientService;

@RestController
@RequestMapping("/servidor")
@CrossOrigin("*")
public class ServerDicom {

	@Autowired
	private PatientService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Patient> ListarPacientes() {
		return this.service.Listar();
	}

	@GetMapping(params = "resumo")
	public Page<ResumoPatient> Listar(PatientFilter filtro, Pageable page) {
		return this.service.ListaFiltros(filtro, page);
	}

	@GetMapping("/filtro")
	public Page<Patient> ListarPacientes(PatientFilter filtro, Pageable pageable) {
		return this.service.Listar(filtro, pageable);
	}

	@PostMapping
	public ResponseEntity<Patient> Salvar(@Valid @RequestBody Patient patient, HttpServletResponse resposta) {
		Patient salvo = this.service.Criar(patient);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getIdpatient()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@GetMapping("/{idpatient}")
	public ResponseEntity<Patient> PorId(@PathVariable Long idpatient) {
		Patient patient = this.service.BuscarPorId(idpatient);
		return ResponseEntity.ok(patient);
	}

	@DeleteMapping("/{idpatient}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long idpatient) {
		this.service.Deletar(idpatient);
	}

	@PutMapping("/{idpatient}")
	public ResponseEntity<Patient> Atualizar(@PathVariable Long idpatient, @Valid @RequestBody Patient patient) {
		Patient salvo = this.service.Atualizar(idpatient, patient);
		return ResponseEntity.ok(salvo);
	}

	@GetMapping("/dicom/{instanceuid}")
	public ResponseEntity<byte[]> BuscarArquivoDicom(@PathVariable String instanceuid) {
		byte[] dados = this.service.BuscarImagem(instanceuid);
		return ResponseEntity.ok().body(dados);
	}
	

	@GetMapping("/study/{idpatient}")
	public List<Study> BuscarInstancias(@PathVariable Long idpatient) {
		return  this.service.BuscaEstudo(idpatient);
	}

	
}
