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
import com.laudoecia.api.modelo.ProcedimentoMedico;
import com.laudoecia.api.repository.filtro.ProcedimentoMedicoFilter;
import com.laudoecia.api.repository.resumo.ResumoProcedimentoMedico;
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
	
	@GetMapping(params = "resumo")
	public Page<ResumoProcedimentoMedico> Resumir(ProcedimentoMedicoFilter filtro, Pageable page) {
		return this.service.Resumindo(filtro, page);
	}
	
	@GetMapping("/lista/{codigo}")
	public List<ProcedimentoMedico> ListarProcPorId(@PathVariable Long codigo){
		return this.service.BuscarListaPorId(codigo);
	}
	
	@GetMapping("/lista/grupo/{nomegrupo}")
	public List<ProcedimentoMedico> ListarProcPorGrupoNome(@PathVariable String nomegrupo){
		return this.service.BuscarListaPorGrupo(nomegrupo);
	}
	
	@PostMapping
	public ResponseEntity<ProcedimentoMedico> Salvar(@Valid @RequestBody ProcedimentoMedico procedimento, HttpServletResponse resposta){
		ProcedimentoMedico salvo = this.service.Criar(procedimento);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ProcedimentoMedico> PorId(@PathVariable Long codigo){
		ProcedimentoMedico salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<ProcedimentoMedico> Atualizar(@PathVariable Long codigo, @Valid @RequestBody ProcedimentoMedico procedimento){
		ProcedimentoMedico salvo = this.service.Atualizar(codigo, procedimento);
		return ResponseEntity.ok(salvo);
	}
}