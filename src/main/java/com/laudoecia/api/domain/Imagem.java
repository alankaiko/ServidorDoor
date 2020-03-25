package com.laudoecia.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table
@Entity
public class Imagem {
	private Long codigo;
	private String caminho;
	private String nomeimagem;
	private String extensao;
	private ProcedimentoAtendimento procedimentoatendimento;
	byte[] imagem;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getNomeimagem() {
		return nomeimagem;
	}

	public void setNomeimagem(String nomeimagem) {
		this.nomeimagem = nomeimagem;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	@ManyToOne
	@JoinColumn(name = "procedimento_codigo", nullable = true)
	public ProcedimentoAtendimento getProcedimentoatendimento() {
		return procedimentoatendimento;
	}
	
	public void setProcedimentoatendimento(ProcedimentoAtendimento procedimentoatendimento) {
		this.procedimentoatendimento = procedimentoatendimento;
	}

	@Transient
	public byte[] getImagem() {
		return imagem;
	}
	
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
}
