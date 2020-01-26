package com.laudoecia.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laudoecia.api.repository.resumo.TagImagemGamb;
import com.laudoecia.api.service.TagImagemService;

@RestController
@RequestMapping("/tagimagens")
@CrossOrigin("*")
public class TagImagemResource {
	@Autowired
	private TagImagemService service;
	
	@GetMapping("/tab/{codigo}")
	public List<TagImagemGamb> BuscarTabela(@PathVariable Long codigo) {
		return this.service.CriarTabela(codigo);
	}
}
