package com.laudoecia.api.gerenciarentrada;

import java.io.File;
import java.io.Serializable;

public class Arquivo implements Serializable{
	private static final long serialVersionUID = 1L;
	private final File arquivo;
	
	public Arquivo(File arquivo){
		this.arquivo = arquivo;
	}	

	public File getArquivo() {
		return arquivo;
	}
}
