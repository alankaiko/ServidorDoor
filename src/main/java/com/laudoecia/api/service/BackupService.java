package com.laudoecia.api.service;

import org.springframework.stereotype.Service;

import com.laudoecia.api.backup.Backup;

@Service
public class BackupService {
	Backup bac = new Backup();
	
	public void CriarBackup() {
		try {
			bac.fazBackup();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
