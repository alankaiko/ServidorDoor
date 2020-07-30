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

import com.laudoecia.api.domain.PaginaImagens;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.service.PaginaImagensService;

@RestController
@RequestMapping("/paginasimagens")
@CrossOrigin("*")
public class PaginaImagensResources {
	@Autowired
	private PaginaImagensService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<PaginaImagens> ListarTodos(){
		return this.service.Listar();
	}	
	
	@PostMapping
	public ResponseEntity<PaginaImagens> Salvar(@Valid @RequestBody PaginaImagens pagina, HttpServletResponse resposta){
		PaginaImagens salvo = this.service.Criar(pagina);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<PaginaImagens> PorId(@PathVariable Long codigo){
		PaginaImagens salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<PaginaImagens> Atualizar(@PathVariable Long codigo, @Valid @RequestBody PaginaImagens pagina){
		PaginaImagens salvo = this.service.Atualizar(codigo, pagina);
		return ResponseEntity.ok(salvo);
	}
}