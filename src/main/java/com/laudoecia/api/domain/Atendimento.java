/*
 * Atendimento.java
 *
 * Created on 19/01/2012, 12:06:43
 */
package com.laudoecia.api.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Atendimento implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private Patient paciente;
	private Convenio convenio;
	private ProfissionalSolicitante solicitante;
	private List<ProcedimentoAtendimento> procedimentos;
	private Date dataatendimento;
	private String observacoes;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_paciente_id", referencedColumnName = "idpatient")
	public Patient getPaciente() {
		return paciente;
	}

	public void setPaciente(Patient paciente) {
		this.paciente = paciente;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_convenio_id", referencedColumnName = "codigo")
	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_profsolicitante_id", referencedColumnName = "codigo")
	public ProfissionalSolicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(ProfissionalSolicitante solicitante) {
		this.solicitante = solicitante;
	}

	@OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<ProcedimentoAtendimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<ProcedimentoAtendimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataatendimento() {
		return dataatendimento;
	}

	public void setDataatendimento(Date dataatendimento) {
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
