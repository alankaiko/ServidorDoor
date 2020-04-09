package com.laudoecia.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laudoecia.api.service.BackupService;

@RestController
@RequestMapping("/backup")
@CrossOrigin("*")
public class BackupResource {
	@Autowired
	private BackupService service;
	

	@GetMapping
	public String Backup(){
		this.service.CriarBackup();
		return "BACKUP CRIADO";
	}	
	
}

