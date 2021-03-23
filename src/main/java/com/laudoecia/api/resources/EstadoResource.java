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
import com.laudoecia.api.modelo.Estado;
import com.laudoecia.api.repository.filtro.EstadoFilter;
import com.laudoecia.api.service.EstadoService;

@RestController
@RequestMapping("/estados")
@CrossOrigin("*")
public class EstadoResource {
	@Autowired
	private EstadoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Estado> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<Estado> Resumir(EstadoFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	
	@PostMapping
	public ResponseEntity<Estado> Salvar(@Valid @RequestBody Estado estado, HttpServletResponse resposta){
		Estado salvo = this.service.Criar(estado, true);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Estado> PorId(@PathVariable Long codigo){
		Estado salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Estado> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Estado estado){
		Estado salvo = this.service.Atualizar(codigo, estado);
		return ResponseEntity.ok(salvo);
	}
	
	@GetMapping("/verificar/{nome}")
	public Boolean SeNomeExiste(@PathVariable String nome){
		return this.service.VerificarSeNomeExiste(nome);
	}
}