package com.laudoecia.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

import com.laudoecia.api.domain.ProcedimentoMedico;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.service.ProcedimentoMedicoService;

@RestController
@RequestMapping("/procedimentomedicos")
@CrossOrigin("*")
public class ProcedimentoMedicoResource {
	@Autowired
	private ProcedimentoMedicoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<ProcedimentoMedico> ListarTodos(){
		return this.service.Listar();
	}
	
	
	@PostMapping
	public ResponseEntity<ProcedimentoMedico> Salvar(@Valid @RequestBody ProcedimentoMedico procedimento, HttpServletResponse resposta){
		ProcedimentoMedico salvo = this.service.Criar(procedimento);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id) {
		this.service.Deletar(id);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ProcedimentoMedico> PorId(@PathVariable Long id){
		ProcedimentoMedico salvo = this.service.BuscarPorId(id);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<ProcedimentoMedico> Atualizar(@PathVariable Long id, @Valid @RequestBody ProcedimentoMedico procedimento){
		ProcedimentoMedico salvo = this.service.Atualizar(id, procedimento);
		return ResponseEntity.ok(salvo);
	}
}