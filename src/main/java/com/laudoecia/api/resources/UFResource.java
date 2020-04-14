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

import com.laudoecia.api.domain.UF;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.service.UFService;

@RestController
@RequestMapping("/ufs")
@CrossOrigin("*")
public class UFResource {
	@Autowired
	private UFService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<UF> ListarTodos(){
		return this.service.Listar();
	}	
	
	@PostMapping
	public ResponseEntity<UF> Salvar(@Valid @RequestBody UF uf, HttpServletResponse resposta){
		UF salvo = this.service.Criar(uf);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<UF> PorId(@PathVariable Long codigo){
		UF salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<UF> Atualizar(@PathVariable Long codigo, @Valid @RequestBody UF uf){
		UF salvo = this.service.Atualizar(codigo, uf);
		return ResponseEntity.ok(salvo);
	}
}