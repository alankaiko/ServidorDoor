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

import com.laudoecia.api.domain.ProfissionalSolicitante;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.service.ProfissionaisSolicitanteService;

@RestController
@RequestMapping("/profissionaissolicitantes")
@CrossOrigin("*")
public class ProfissionaisSolicitanteResource {
	@Autowired
	private ProfissionaisSolicitanteService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<ProfissionalSolicitante> ListarTodos(){
		return this.service.Listar();
	}
	
	
	@PostMapping
	public ResponseEntity<ProfissionalSolicitante> Salvar(@Valid @RequestBody ProfissionalSolicitante profissional, HttpServletResponse resposta){
		ProfissionalSolicitante salvo = this.service.Criar(profissional);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ProfissionalSolicitante> PorId(@PathVariable Long codigo){
		ProfissionalSolicitante salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<ProfissionalSolicitante> Atualizar(@PathVariable Long codigo, @Valid @RequestBody ProfissionalSolicitante profissional){
		ProfissionalSolicitante salvo = this.service.Atualizar(codigo, profissional);
		return ResponseEntity.ok(salvo);
	}
}