package com.laudoecia.api.modelo;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laudoecia.api.modelo.enuns.EnumSexo;

@Entity
@Table
public class Paciente {
	private Long codigo; 
	private String pacienteid;
	private String nome;
	private LocalDate datanasc;
	private String idade;
	private EnumSexo sexo;
	private LocalDate datacriacao;
	private LocalDate datamodificacao;
	private List<Estudo> estudos;
	private Endereco endereco;
	private Contato contato;
	private String tamanho;
    private String peso;
    private LocalDate datamenstruacao;
    private String atributoextra1;
    private String atributoextra2;
    private String atributoextra3;
    private boolean dicom;
    private String observacoes;

	public Paciente() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getPacienteid() {
		return pacienteid;
	}
	
	public void setPacienteid(String pacienteid) {
		this.pacienteid = pacienteid;
	}
	
	@Column(length = 150, nullable = false)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	public EnumSexo getSexo() {
		return sexo;
	}
	
	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}
	
	@Column(nullable = true)
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

	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
	public List<Estudo> getEstudos() {
		return estudos;
	}
	
	public void setEstudos(List<Estudo> estudos) {
		this.estudos = estudos;
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

	@Column(nullable = true)
	public boolean isDicom() {
		return dicom;
	}
	
	public void setDicom(boolean dicom) {
		this.dicom = dicom;
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
	
	@PrePersist
	private void CriarObjeto() {
		if(this.endereco.getCep() == null)
			this.endereco.setCep("");
		
		if(this.contato.getTelefone() == null)
			this.contato.setTelefone("");
		
		if(this.contato.getTelefone2() == null)
			this.contato.setTelefone2("");
	}

	@Override
	public String toString() {
		return "Paciente [codigo=" + codigo + ", pacienteid=" + pacienteid + ", nome=" + nome + ", datanasc=" + datanasc
				+ ", idade=" + idade + ", sexo=" + sexo + ", datacriacao=" + datacriacao + ", datamodificacao="
				+ datamodificacao + ", estudos=" + estudos + ", endereco=" + endereco + ", contato=" + contato
				+ ", tamanho=" + tamanho + ", peso=" + peso + ", datamenstruacao=" + datamenstruacao
				+ ", atributoextra1=" + atributoextra1 + ", atributoextra2=" + atributoextra2 + ", atributoextra3="
				+ atributoextra3 + ", dicom=" + dicom + ", observacoes=" + observacoes + "]";
	}
	
}