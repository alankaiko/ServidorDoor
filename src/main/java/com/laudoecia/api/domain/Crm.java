package com.laudoecia.api.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "crm")
public class Crm implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String crm;
	private String nome;
	private UF uf;
	private List<EspecialidadeMedica> especialidades;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(length = 10)
	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	@Column(length = 70)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "tabela_uf", referencedColumnName = "codigo", nullable = false)
	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "crm_especialidade", joinColumns = @JoinColumn(name = "tabela_crm", referencedColumnName = "codigo"), inverseJoinColumns = @JoinColumn(name = "tabela_esp", referencedColumnName = "codigo"))
	public List<EspecialidadeMedica> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadeMedica> especialidades) {
		this.especialidades = especialidades;
	}

}
