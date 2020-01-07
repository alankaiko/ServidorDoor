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

import com.laudoecia.api.domain.TextoPessoal;
import com.laudoecia.api.event.RecursoCriadoEvent;
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
	
	
	@PostMapping
	public ResponseEntity<TextoPessoal> Salvar(@Valid @RequestBody TextoPessoal texto, HttpServletResponse resposta){
		TextoPessoal salvo = this.service.Criar(texto);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id) {
		this.service.Deletar(id);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<TextoPessoal> PorId(@PathVariable Long id){
		TextoPessoal salvo = this.service.BuscarPorId(id);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<TextoPessoal> Atualizar(@PathVariable Long id, @Valid @RequestBody TextoPessoal texto){
		TextoPessoal salvo = this.service.Atualizar(id, texto);
		return ResponseEntity.ok(salvo);
	}
}