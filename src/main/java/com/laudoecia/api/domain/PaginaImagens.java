package com.laudoecia.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paginaimagens")
public class PaginaImagens {
	private Long codigo;
	private String descricao;
	private String dados;
	private ModeloLaudoClienteSalvo modelosalvo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(length = 1000)
	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	@ManyToOne
	@JoinColumn(name = "codigo_modelolaudoclientesalvo")
	public ModeloLaudoClienteSalvo getModelosalvo() {
		return modelosalvo;
	}

	public void setModelosalvo(ModeloLaudoClienteSalvo modelosalvo) {
		this.modelosalvo = modelosalvo;
	}

}
