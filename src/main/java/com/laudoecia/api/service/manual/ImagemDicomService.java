package com.laudoecia.api.service.manual;

import com.laudoecia.api.modelo.Imagem;

public class ImagemDicomService {
	private ImagemDicomDAO dao;
	
	public ImagemDicomService() {
		this.dao = new ImagemDicomDAO();
	}
	
	public void Salvando(Imagem imagem){
		this.dao.Salvar(imagem);	
	}
	
	public void Atualizando(Imagem imagem) {
		this.dao.Atualizar(imagem);
	}
	
	public Imagem BuscandoPorNome(String nomeimagem) {
		return this.dao.BuscarPeloNome(nomeimagem);
	}
	
	public Imagem BuscandoPeloCodigo(Long codigo) {
		return this.dao.BuscarPelaId(codigo);
	}
}
