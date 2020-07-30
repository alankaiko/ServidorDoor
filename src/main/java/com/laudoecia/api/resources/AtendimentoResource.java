package com.laudoecia.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.laudoecia.api.domain.Atendimento;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.repository.filtro.AtendimentoFilter;
import com.laudoecia.api.service.AtendimentoService;

@RestController
@RequestMapping("/atendimentos")
@CrossOrigin("*")
public class AtendimentoResource {
	@Autowired
	private AtendimentoService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Atendimento> ListarTodos() {
		return this.service.Listar();
	}

	@GetMapping(params = "resumo")
	public Page<Atendimento> Resumir(AtendimentoFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}

	@GetMapping("/lista/{codigo}")
	public List<Atendimento> ListarTodos(@PathVariable Long codigo){
		return this.service.BuscarListaPorId(codigo);
	}
	
	@GetMapping("/listapac/{patientname}")
	public List<Atendimento> ListarPorNomePacientes(@PathVariable String patientname){
		return this.service.BuscarPorNomePaciente(patientname);
	}
	
	@PostMapping
	public ResponseEntity<Atendimento> Salvar(@Valid @RequestBody Atendimento atendimento, HttpServletResponse resposta) {
		Atendimento salvo = this.service.Criar(atendimento);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Atendimento> PorId(@PathVariable Long codigo) {
		Atendimento salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Atendimento> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Atendimento atendimento) {
		try {
			Atendimento salvo = this.service.Atualizar(codigo, atendimento);
			return ResponseEntity.ok(salvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GetMapping("/relatorios/atestado/{codigo}")
	public ResponseEntity<byte[]> PegarAtestado(@PathVariable Long codigo) throws Exception {
	
		try {
			byte[] relatorio = this.service.AtestadoMontar(codigo);
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}	
}