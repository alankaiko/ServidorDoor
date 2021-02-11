package com.laudoecia.api.modelo;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String nome;
	private LocalDate datanasc;
	private String idade;
	private String sexo;
	private LocalDate datacriacao;
	private LocalDate datamodificacao;
	private List<Estudo> studyes;
	private Endereco endereco;
	private Contato contato;
	private String tamanho;
    private String peso;
    private LocalDate datamenstruacao;
    private String atributoextra1;
    private String atributoextra2;
    private String atributoextra3;
    private int numerodeestudos;
    private PersonName personname;
    private String observacoes;

	public Paciente() {
		super();
	}

	@Id
	@Column(length = 8)
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(length = 200)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(length = 20)
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDatanasc() {
		return datanasc;
	}
	
	public void setDatanasc(LocalDate datanasc) {
		this.datanasc = datanasc;
	}

	@Column(length = 10)
	public String getIdade() {
		return idade;
	}
	
	public void setIdade(String idade) {
		this.idade = idade;
	}

	public LocalDate getDatacriacao() {
		return datacriacao;
	}
	
	public void setDatacriacao(LocalDate datacriacao) {
		this.datacriacao = datacriacao;
	}

	public LocalDate getDatamodificacao() {
		return datamodificacao;
	}
	
	public void setDatamodificacao(LocalDate datamodificacao) {
		this.datamodificacao = datamodificacao;
	}

	@OneToMany(mappedBy = "paciente")
	public List<Estudo> getStudyes() {
		return studyes;
	}

	public void setStudyes(List<Estudo> studyes) {
		this.studyes = studyes;
	}

	@Embedded
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Embedded
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getAtributoextra1() {
		return atributoextra1;
	}
	
	public void setAtributoextra1(String atributoextra1) {
		this.atributoextra1 = atributoextra1;
	}

	public String getAtributoextra2() {
		return atributoextra2;
	}
	
	public void setAtributoextra2(String atributoextra2) {
		this.atributoextra2 = atributoextra2;
	}

	public String getAtributoextra3() {
		return atributoextra3;
	}
	
	public void setAtributoextra3(String atributoextra3) {
		this.atributoextra3 = atributoextra3;
	}

	public int getNumerodeestudos() {
		return numerodeestudos;
	}
	
	public void setNumerodeestudos(int numerodeestudos) {
		this.numerodeestudos = numerodeestudos;
	}

	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personname_fk")
	public PersonName getPersonname() {
		return personname;
	}

	public void setPersonname(PersonName personname) {
		this.personname = personname;
	}

	public String getObservacoes() {
		return observacoes;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public String getTamanho() {
		return tamanho;
	}
	
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	public String getPeso() {
		return peso;
	}
	
	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	public LocalDate getDatamenstruacao() {
		return datamenstruacao;
	}
	
	public void setDatamenstruacao(LocalDate datamenstruacao) {
		this.datamenstruacao = datamenstruacao;
	}
}