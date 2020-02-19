package com.laudoecia.api.resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laudoecia.api.domain.Modality;
import com.laudoecia.api.service.PatientService;
import com.laudoecia.api.teste.HapiSendMessageSimpleExample;

@RestController
@RequestMapping("/enviar")
@CrossOrigin("*")
public class EnviarResource {
	
	@PostMapping
	public ResponseEntity<Modality> Envio(@Valid @RequestBody Modality modality, HttpServletResponse resposta){
		boolean aff = this.Testando(modality);
		return ResponseEntity.status(HttpStatus.CREATED).body(modality);
	}
	
	
	private boolean Testando(Modality modalidade) {
		HapiSendMessageSimpleExample exemplo = new HapiSendMessageSimpleExample();
		
		
		try {
			
			exemplo.setModalidade(modalidade);
			exemplo.ExecutarMensagem();
			System.out.println("rodou aqui");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}








