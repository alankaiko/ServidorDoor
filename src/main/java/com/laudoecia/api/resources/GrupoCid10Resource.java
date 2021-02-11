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

import com.laudoecia.api.gerenciarentrada.RecursoCriadoEvent;
import com.laudoecia.api.modelo.GrupoCID10;
import com.laudoecia.api.service.GrupoCid10Service;

@RestController
@RequestMapping("/grupocid10s")
@CrossOrigin("*")
public class GrupoCid10Resource {
	@Autowired
	private GrupoCid10Service service;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<GrupoCID10> ListarTodos(){
		return this.service.Listar();
	}
	
	
	@PostMapping
	public ResponseEntity<GrupoCID10> Salvar(@Valid @RequestBody GrupoCID10 cid, HttpServletResponse resposta){
		GrupoCID10 salvo = this.service.Criar(cid);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<GrupoCID10> PorId(@PathVariable Long codigo){
		GrupoCID10 salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<GrupoCID10> Atualizar(@PathVariable Long codigo, @Valid @RequestBody GrupoCID10 cid){
		GrupoCID10 salvo = this.service.Atualizar(codigo, cid);
		return ResponseEntity.ok(salvo);
	}
}