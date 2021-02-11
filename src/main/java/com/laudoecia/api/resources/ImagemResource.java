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

import com.laudoecia.api.gerenciarentrada.RecursoCriadoEvent;
import com.laudoecia.api.modelo.Imagem;
import com.laudoecia.api.service.ImagemService;

@RestController
@RequestMapping("/imagens")
@CrossOrigin("*")
public class ImagemResource {
	@Autowired
	private ImagemService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Imagem> ListarTodos(){
		return this.service.Listar();
	}		
	
	@PostMapping
	public ResponseEntity<Imagem> Salvar(@Valid @RequestBody Imagem imagem, HttpServletResponse resposta){
		Imagem salvo = this.service.Criar(imagem);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Imagem> PorId(@PathVariable Long codigo){
		Imagem salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Imagem> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Imagem imagem){
		Imagem salvo = this.service.Atualizar(codigo, imagem);
		return ResponseEntity.ok(salvo);
	}
	
	@GetMapping("/listadicom/{codigouid}")
	public ResponseEntity<Imagem> ListarPorCodigouid(@PathVariable String codigouid){
		Imagem imagem = this.service.BuscarPeloCodigouid(codigouid);
		return ResponseEntity.ok(imagem);
	}		
	
	@GetMapping(value = "/imagem/{codigo}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> BuscarImagem(@PathVariable Long codigo) throws IOException {
    	byte[] bytes = this.service.BuscarImagem(codigo);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
	
	@GetMapping("/imagemstring/{codigo}")
	public ResponseEntity<String> BuscarImagemStrings(@PathVariable Long codigo){
    	String valor = this.service.BuscarImagemString(codigo);
    	return ResponseEntity.ok(valor);
	}
}