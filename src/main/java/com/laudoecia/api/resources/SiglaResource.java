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
import com.laudoecia.api.modelo.Sigla;
import com.laudoecia.api.repository.filtro.SiglaFilter;
import com.laudoecia.api.service.SiglaService;

@RestController
@RequestMapping("/siglas")
@CrossOrigin("*")
public class SiglaResource {
	@Autowired
	private SiglaService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Sigla> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<Sigla> Resumir(SiglaFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	
	@PostMapping
	public ResponseEntity<Sigla> Salvar(@Valid @RequestBody Sigla sigla, HttpServletResponse resposta){
		Sigla salvo = this.service.Criar(sigla, true);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Sigla> PorId(@PathVariable Long codigo){
		Sigla salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Sigla> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Sigla sigla){
		Sigla salvo = this.service.Atualizar(codigo, sigla);
		return ResponseEntity.ok(salvo);
	}
	
	@GetMapping("/verificar/{nome}")
	public Boolean SeNomeExiste(@PathVariable String nome){
		return this.service.VerificarSeNomeExiste(nome);
	}
}