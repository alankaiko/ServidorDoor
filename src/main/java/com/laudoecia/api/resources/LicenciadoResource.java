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

import com.laudoecia.api.domain.Licenciado;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.repository.filtro.LicenciadoFilter;
import com.laudoecia.api.service.LicenciadoService;

@RestController
@RequestMapping("/licenciados")
@CrossOrigin("*")
public class LicenciadoResource {
	@Autowired
	private LicenciadoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Licenciado> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<Licenciado> Resumir(LicenciadoFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	@PostMapping
	public ResponseEntity<Licenciado> Salvar(@Valid @RequestBody Licenciado licenciado, HttpServletResponse resposta){
		Licenciado salvo = this.service.Criar(licenciado);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Licenciado> PorId(@PathVariable Long codigo){
		Licenciado salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Licenciado> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Licenciado licenciado){
		Licenciado salvo = this.service.Atualizar(codigo, licenciado);
		return ResponseEntity.ok(salvo);
	}
}