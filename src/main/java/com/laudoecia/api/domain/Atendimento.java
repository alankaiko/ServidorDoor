/*
 * Atendimento.java
 *
 * Created on 19/01/2012, 12:06:43
 */
package com.laudoecia.api.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Atendimento implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private Patient patient;
	private Convenio convenio;
	private ProfissionalSolicitante solicitante;
	private List<ProcedimentoAtendimento> procedimentos;
	private LocalDate dataatendimento;
	private String observacoes;
	
	public Atendimento() {
		this.procedimentos = new ArrayList<ProcedimentoAtendimento>();
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_patient_id", referencedColumnName = "idpatient")
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_convenio_id", referencedColumnName = "codigo")
	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_profsolicitante_id", referencedColumnName = "codigo")
	public ProfissionalSolicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(ProfissionalSolicitante solicitante) {
		this.solicitante = solicitante;
	}

	@JsonIgnoreProperties("pessoa")
	@OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ProcedimentoAtendimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<ProcedimentoAtendimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public LocalDate getDataatendimento() {
		return dataatendimento;
	}

	public void setDataatendimento(LocalDate dataatendimento) {
		this.dataatendimento = dataatendimento;
	}

	@Column(length = 500)
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
		Atendimento other = (Atendimento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
