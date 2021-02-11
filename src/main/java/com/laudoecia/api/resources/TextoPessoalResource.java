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
import com.laudoecia.api.modelo.TextoPessoal;
import com.laudoecia.api.repository.filtro.TextoPessoalFilter;
import com.laudoecia.api.service.TextoPessoalService;

@RestController
@RequestMapping("/textospessoais")
@CrossOrigin("*")
public class TextoPessoalResource {
	@Autowired
	private TextoPessoalService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<TextoPessoal> ListarTodos(){
		return this.service.Listar();
	}
	
	@GetMapping(params = "resumo")
	public Page<TextoPessoal> Resumir(TextoPessoalFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	@GetMapping("/lista/{codigo}")
	public List<TextoPessoal> ListarTodos(@PathVariable Long codigo){
		return this.service.BuscarListaPorId(codigo);
	}
	
	
	@PostMapping
	public ResponseEntity<TextoPessoal> Salvar(@Valid @RequestBody TextoPessoal texto, HttpServletResponse resposta){
		TextoPessoal salvo = this.service.Criar(texto);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<TextoPessoal> PorId(@PathVariable Long codigo){
		TextoPessoal salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<TextoPessoal> Atualizar(@PathVariable Long codigo, @Valid @RequestBody TextoPessoal texto){
		TextoPessoal salvo = this.service.Atualizar(codigo, texto);
		return ResponseEntity.ok(salvo);
	}
}