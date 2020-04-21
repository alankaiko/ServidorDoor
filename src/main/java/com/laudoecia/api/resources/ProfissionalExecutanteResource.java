package com.laudoecia.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.laudoecia.api.domain.ProfissionalExecutante;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.repository.filtro.ProfissionalExecutanteFilter;
import com.laudoecia.api.service.ProfissionalExecutanteService;

@RestController
@RequestMapping("/profissionaisexecutantes")
@CrossOrigin("*")
public class ProfissionalExecutanteResource {
	@Autowired
	private ProfissionalExecutanteService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<ProfissionalExecutante> ListarTodos(){
		return this.service.Listar();
	}
	
	@GetMapping(params = "resumo")
	public Page<ProfissionalExecutante> Resumir(ProfissionalExecutanteFilter filtro, Pageable page) {
		return this.service.Resumindo(filtro, page);
	}
	
	@GetMapping("/lista/{descricao}")
	public List<ProfissionalExecutante> ListarTodos(@PathVariable String descricao){
		return this.service.BuscarListaPorId(descricao);
	}
	
	@PostMapping
	public ResponseEntity<ProfissionalExecutante> Salvar(@Valid @RequestBody ProfissionalExecutante profissional, HttpServletResponse resposta){
		ProfissionalExecutante salvo = this.service.Criar(profissional);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ProfissionalExecutante> PorId(@PathVariable Long codigo){
		ProfissionalExecutante salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<ProfissionalExecutante> Atualizar(@PathVariable Long codigo, @Valid @RequestBody ProfissionalExecutante profissional){
		ProfissionalExecutante salvo = this.service.Atualizar(codigo, profissional);
		return ResponseEntity.ok(salvo);
	}
	
	@GetMapping("/relatorios/por-executante/{descricao}/{uf}")
	public ResponseEntity<byte[]> RelatorioDeExecutantes(@PathVariable String descricao,@PathVariable String uf) throws Exception {
		System.out.println(descricao);
		System.out.println(uf);
		try {
			byte[] relatorio = this.service.RelatorioPorExecutante(descricao, uf);
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
}