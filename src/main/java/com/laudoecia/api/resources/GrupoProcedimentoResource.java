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

import com.laudoecia.api.domain.GrupoProcedimento;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.repository.filtro.GrupoProcedimentoFilter;
import com.laudoecia.api.service.GrupoProcedimentoService;

@RestController
@RequestMapping("/grupoprocedimentos")
@CrossOrigin("*")
public class GrupoProcedimentoResource {
	@Autowired
	private GrupoProcedimentoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<GrupoProcedimento> ListarTodos(){
		return this.service.Listar();
	}
	
	@GetMapping(params = "resumo")
	public Page<GrupoProcedimento> Filtrando(GrupoProcedimentoFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	@GetMapping("/lista/{codigo}")
	public List<GrupoProcedimento> ListarTodos(@PathVariable Long codigo){
		return this.service.BuscarListaPorId(codigo);
	}	
	
	@PostMapping
	public ResponseEntity<GrupoProcedimento> Salvar(@Valid @RequestBody GrupoProcedimento procedimento, HttpServletResponse resposta){
		GrupoProcedimento salvo = this.service.Criar(procedimento);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<GrupoProcedimento> PorId(@PathVariable Long codigo){
		GrupoProcedimento salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<GrupoProcedimento> Atualizar(@PathVariable Long codigo, @Valid @RequestBody GrupoProcedimento procedimento){
		GrupoProcedimento salvo = this.service.Atualizar(codigo, procedimento);
		return ResponseEntity.ok(salvo);
	}
}