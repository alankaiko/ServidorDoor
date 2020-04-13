package com.laudoecia.api.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profissionalexecutante")
public class ProfissionalExecutante implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String nome;
	private String numnoconselho;
	private Contato contato;
	private Endereco endereco;
	private TISS_Conselho conselho;
	private String frasepessoal = "";
	private byte[] assinatura;
	private double asswidth = 0;
	private double assheight = 0;
	private int espacoass = 10;
	private long version = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumnoconselho() {
		return numnoconselho;
	}

	public void setNumnoconselho(String numnoconselho) {
		this.numnoconselho = numnoconselho;
	}

	@Embedded
	public Contato getContato() {
		if (this.contato == null)
			this.contato = new Contato();
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	@Embedded
	public Endereco getEndereco() {
		if (this.endereco == null)
			this.endereco = new Endereco();
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "tabela_conselho_id", referencedColumnName = "codigo")
	public TISS_Conselho getConselho() {
		return conselho;
	}

	public void setConselho(TISS_Conselho conselho) {
		this.conselho = conselho;
	}

	public String getFrasepessoal() {
		return frasepessoal;
	}

	public void setFrasepessoal(String frasepessoal) {
		this.frasepessoal = frasepessoal;
	}

	public byte[] getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(byte[] assinatura) {
		this.assinatura = assinatura;
	}

	public double getAsswidth() {
		return asswidth;
	}

	public void setAsswidth(double asswidth) {
		this.asswidth = asswidth;
	}

	public double getAssheight() {
		return assheight;
	}

	public void setAssheight(double assheight) {
		this.assheight = assheight;
	}

	public int getEspacoass() {
		return espacoass;
	}

	public void setEspacoass(int espacoass) {
		this.espacoass = espacoass;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfissionalExecutante other = (ProfissionalExecutante) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}