package com.laudoecia.api.resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

import com.laudoecia.api.domain.ParametrosDoSistema;
import com.laudoecia.api.event.RecursoCriadoEvent;
import com.laudoecia.api.service.ParametrosDoSistemaService;

@RestController
@RequestMapping("/parametrosdosistema")
@CrossOrigin("*")
public class ParametrosDoSistemaResource {
	@Autowired
	private ParametrosDoSistemaService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<ParametrosDoSistema> ListarTodos() {
		return this.service.Listar();
	}

	@PostMapping
	public ResponseEntity<ParametrosDoSistema> Salvar(@Valid @RequestBody ParametrosDoSistema parametro,
			HttpServletResponse resposta) {
		ParametrosDoSistema salvo = this.service.Criar(parametro);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<ParametrosDoSistema> PorId(@PathVariable Long codigo) {
		ParametrosDoSistema salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<ParametrosDoSistema> Atualizar(@PathVariable Long codigo,
			@Valid @RequestBody ParametrosDoSistema parametro) {
		ParametrosDoSistema salvo = this.service.Atualizar(codigo, parametro);
		return ResponseEntity.ok(salvo);
	}
	
	@GetMapping(value = "/imagem/{codigo}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> BuscarImagem(@PathVariable Long codigo) throws IOException {
    	byte[] bytes = this.service.BuscarImagem(codigo);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
    }
}