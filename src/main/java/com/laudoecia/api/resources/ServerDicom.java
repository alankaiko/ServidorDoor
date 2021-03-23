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

import com.laudoecia.api.gerenciarentrada.RecursoCriadoEvent;
import com.laudoecia.api.modelo.Estudo;
import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.repository.filtro.PacienteFilter;
import com.laudoecia.api.repository.resumo.ResumoPaciente;
import com.laudoecia.api.service.PacienteService;

@RestController
@RequestMapping("/servidor")
@CrossOrigin("*")
public class ServerDicom {

	@Autowired
	private PacienteService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Paciente> ListarPacientes() {
		return this.service.Listar();
	}

	@GetMapping(params = "resumo")
	public Page<ResumoPaciente> Listar(PacienteFilter filtro, Pageable page) {
		return this.service.ListaFiltros(filtro, page);
	}

	@GetMapping("/filtro")
	public Page<Paciente> ListarPacientes(PacienteFilter filtro, Pageable pageable) {
		return this.service.Listar(filtro, pageable);
	}

	@PostMapping
	public ResponseEntity<Paciente> Salvar(@Valid @RequestBody Paciente paciente, HttpServletResponse resposta) {
		Paciente salvo = this.service.Criar(paciente);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Paciente> PorId(@PathVariable Long codigo) {
		Paciente paciente = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(paciente);
	}
	

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Paciente> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Paciente paciente) {
		Paciente salvo = this.service.Atualizar(codigo, paciente);
		return ResponseEntity.ok(salvo);
	}

	@GetMapping("/dicom/{instanceuid}")
	public ResponseEntity<byte[]> BuscarArquivoDicom(@PathVariable String instanceuid) {
		byte[] dados = this.service.BuscarImagem(instanceuid);
		return ResponseEntity.ok().body(dados);
	}

	@GetMapping("/study/{codigo}")
	public List<Estudo> Teste(@PathVariable String codigo){
		return this.service.BuscaEstudo(codigo);
	}

	@GetMapping("/verificar/{nome}")
	public Boolean SeNomeExiste(@PathVariable String nome){
		return this.service.VerificarSeNomeExiste(nome);
	}
}