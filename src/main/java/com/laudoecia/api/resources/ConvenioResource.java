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

import com.laudoecia.api.domain.Convenio;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.repository.filtro.ConvenioFilter;
import com.laudoecia.api.repository.resumo.ResumoConvenio;
import com.laudoecia.api.service.ConvenioService;

@RestController
@RequestMapping("/convenios")
@CrossOrigin("*")
public class ConvenioResource {
	@Autowired
	private ConvenioService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Convenio> ListarTodos(){
		return this.service.Listar();
	}
	
	@GetMapping(params = "resumo")
	public Page<ResumoConvenio> Resumir(ConvenioFilter filtro, Pageable page) {
		return this.service.Resumindo(filtro, page);
	}
	
	
	@PostMapping
	public ResponseEntity<Convenio> Salvar(@Valid @RequestBody Convenio convenio, HttpServletResponse resposta){
		Convenio salvo = this.service.Criar(convenio);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Convenio> PorId(@PathVariable Long codigo){
		Convenio salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Convenio> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Convenio convenio){
		Convenio salvo = this.service.Atualizar(codigo, convenio);
		return ResponseEntity.ok(salvo);
	}
}