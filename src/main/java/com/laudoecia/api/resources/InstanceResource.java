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

import com.laudoecia.api.domain.Instance;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.repository.resumo.ResumoInstance;
import com.laudoecia.api.service.InstanceService;

@RestController
@RequestMapping("/instances")
@CrossOrigin("*")
public class InstanceResource {
	@Autowired
	private InstanceService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Instance> ListarTodos(){
		return this.service.Listar();
	}
	
	@GetMapping(value = "/{codigo}" , params = "resumo")
	public ResponseEntity<ResumoInstance> ResumirProDicom(@PathVariable Long codigo) {
		ResumoInstance resum = this.service.ResumirProDicom(codigo);
		return ResponseEntity.ok(resum);
	}
	
	
	@PostMapping
	public ResponseEntity<Instance> Salvar(@Valid @RequestBody Instance instance, HttpServletResponse resposta){
		Instance salvo = this.service.Criar(instance);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{idinstance}")
	public ResponseEntity<Instance> PorId(@PathVariable Long idinstance){
		Instance salvo = this.service.BuscarPorId(idinstance);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Instance> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Instance instance){
		Instance salvo = this.service.Atualizar(codigo, instance);
		return ResponseEntity.ok(salvo);
	}
}
