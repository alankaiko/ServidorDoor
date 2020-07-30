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

import com.laudoecia.api.domain.ModeloLaudoClienteSalvo;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.service.ModeloLaudoClienteSalvoService;

@RestController
@RequestMapping("/modelosalvos")
@CrossOrigin("*")
public class ModeloLaudoClienteSalvoResources {
	@Autowired
	private  ModeloLaudoClienteSalvoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List< ModeloLaudoClienteSalvo> ListarTodos(){
		return this.service.Listar();
	}	
	
	
	@PostMapping
	public ResponseEntity< ModeloLaudoClienteSalvo> Salvar(@Valid @RequestBody  ModeloLaudoClienteSalvo modelo, HttpServletResponse resposta){
		 ModeloLaudoClienteSalvo salvo = this.service.Criar(modelo);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity< ModeloLaudoClienteSalvo> PorId(@PathVariable Long codigo){
		 ModeloLaudoClienteSalvo salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@GetMapping("/peloprocedimento/{codigo}")
	public ResponseEntity< ModeloLaudoClienteSalvo> PelaIdProcedimento(@PathVariable Long codigo){
		ModeloLaudoClienteSalvo salvo = this.service.BuscarPelaIdProcedimento(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity< ModeloLaudoClienteSalvo> Atualizar(@PathVariable Long codigo, @Valid @RequestBody  ModeloLaudoClienteSalvo modelo){
		 ModeloLaudoClienteSalvo salvo = this.service.Atualizar(codigo, modelo);
		return ResponseEntity.ok(salvo);
	}
}