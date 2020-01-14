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

import com.laudoecia.api.domain.Abreviatura;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.repository.filtro.AbreviaturaFilter;
import com.laudoecia.api.repository.resumo.ResumoAbreviatura;
import com.laudoecia.api.service.AbreviaturaService;

@RestController
@RequestMapping("/abreviaturas")
@CrossOrigin("*")
public class AbreviaturaResource {
	@Autowired
	private AbreviaturaService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Abreviatura> ListarTodos(){
		return this.service.Listar();
	}	
//	@GetMapping(params = "resumo")
//	public Page<ResumoAbreviatura> Resumir(AbreviaturaFilter filtro, Pageable page) {
//		return this.service.Resumindo(filtro, page);
//	}
//	
	
	@PostMapping
	public ResponseEntity<Abreviatura> Salvar(@Valid @RequestBody Abreviatura abreviatura, HttpServletResponse resposta){
		Abreviatura salvo = this.service.Criar(abreviatura);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Abreviatura> PorId(@PathVariable Long codigo){
		Abreviatura salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Abreviatura> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Abreviatura abreviatura){
		Abreviatura salvo = this.service.Atualizar(codigo, abreviatura);
		return ResponseEntity.ok(salvo);
	}
}