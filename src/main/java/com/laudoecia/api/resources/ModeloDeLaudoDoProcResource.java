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
import com.laudoecia.api.modelo.ModeloDeLaudoDoProc;
import com.laudoecia.api.repository.filtro.ModeloDeLaudoDoProcFilter;
import com.laudoecia.api.service.ModeloDeLaudoDoProcService;

@RestController
@RequestMapping("/modelosprocedimento")
@CrossOrigin("*")
public class ModeloDeLaudoDoProcResource {
	@Autowired
	private ModeloDeLaudoDoProcService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<ModeloDeLaudoDoProc> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping("/proc/{codigo}")
	public List<ModeloDeLaudoDoProc> PorProcedimentoMedico(@PathVariable Long codigo) {
		return this.service.ListarPeloCodigoProcedimento(codigo);
	}
	
	@GetMapping(params = "resumo")
	public Page<ModeloDeLaudoDoProc> Resumir(ModeloDeLaudoDoProcFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	
	@PostMapping
	public ResponseEntity<ModeloDeLaudoDoProc> Salvar(@Valid @RequestBody ModeloDeLaudoDoProc modelo, HttpServletResponse resposta){
		ModeloDeLaudoDoProc salvo = this.service.Criar(modelo);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ModeloDeLaudoDoProc> PorId(@PathVariable Long codigo){
		ModeloDeLaudoDoProc salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<ModeloDeLaudoDoProc> Atualizar(@PathVariable Long codigo, @Valid @RequestBody ModeloDeLaudoDoProc modelo){
		ModeloDeLaudoDoProc salvo = this.service.Atualizar(codigo, modelo);
		return ResponseEntity.ok(salvo);
	}
}