package com.laudoecia.api.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.laudoecia.api.modelo.enuns.EnumTitulo;

@Entity
@Table(name = "profissionalexecutante")
public class ProfissionalExecutante {
	private Long codigo;
	private String nome;
	private EnumTitulo titulo;
	private Contato contato;
	private Endereco endereco;
	private TISSConselho conselho;
	private String frasepessoal;


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
	public TISSConselho getConselho() {
		return conselho;
	}

	public void setConselho(TISSConselho conselho) {
		this.conselho = conselho;
	}

	public String getFrasepessoal() {
		return frasepessoal;
	}

	public void setFrasepessoal(String frasepessoal) {
		this.frasepessoal = frasepessoal;
	}
	
	@Enumerated(EnumType.STRING)
	public EnumTitulo getTitulo() {
		return titulo;
	}

	public void setTitulo(EnumTitulo titulo) {
		this.titulo = titulo;
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