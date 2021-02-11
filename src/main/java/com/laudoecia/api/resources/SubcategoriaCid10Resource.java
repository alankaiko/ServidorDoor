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
import com.laudoecia.api.modelo.SubcategoriaCid10;
import com.laudoecia.api.repository.filtro.SubcategoriaCid10Filter;
import com.laudoecia.api.service.SubcategoriaCid10Service;

@RestController
@RequestMapping("/subcategoriacid")
@CrossOrigin("*")
public class SubcategoriaCid10Resource {
	@Autowired
	private SubcategoriaCid10Service service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<SubcategoriaCid10> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<SubcategoriaCid10> Resumir(SubcategoriaCid10Filter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	@GetMapping("/porcategoria/{nome}")
	public List<SubcategoriaCid10> ListarTodos(@PathVariable String nome){
		return this.service.ListarPorCategoria(nome);
	}
	
	@GetMapping("/lista/{codigo}")
	public List<SubcategoriaCid10> ListarTodos(@PathVariable Long codigo){
		return this.service.BuscarListaPorId(codigo);
	}
	
	
	@PostMapping
	public ResponseEntity<SubcategoriaCid10> Salvar(@Valid @RequestBody SubcategoriaCid10 subcategoria, HttpServletResponse resposta){
		SubcategoriaCid10 salvo = this.service.Criar(subcategoria);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<SubcategoriaCid10> PorId(@PathVariable Long codigo){
		SubcategoriaCid10 salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<SubcategoriaCid10> Atualizar(@PathVariable Long codigo, @Valid @RequestBody SubcategoriaCid10 subcategoria){
		SubcategoriaCid10 salvo = this.service.Atualizar(codigo, subcategoria);
		return ResponseEntity.ok(salvo);
	}
}