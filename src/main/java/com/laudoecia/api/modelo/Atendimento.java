package com.laudoecia.api.modelo;

import java.time.LocalDate;
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
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Atendimento {
	private Long codigo;
	private Paciente paciente;
	private Convenio convenio;
	private ProfissionalSolicitante solicitante;
	private List<ProcedimentoAtendimento> procedimentos;
	private LocalDate dataatendimento;
	private LocalDate datacadastro;
	private String observacoes;
	private boolean gravarexame = false;
	private long version = 0;
	private Long codigoprofexecutante;
	private Long codigodecid;
	private Date dataatestado;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_paciente_id", referencedColumnName = "codigo")
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_convenio_id", referencedColumnName = "codigo")
	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_profsolicitante_id", nullable = true)
	public ProfissionalSolicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(ProfissionalSolicitante solicitante) {
		this.solicitante = solicitante;
	}

	@JsonIgnoreProperties("atendimento")
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

	public boolean isGravarexame() {
		return gravarexame;
	}

	public void setGravarexame(boolean gravarexame) {
		this.gravarexame = gravarexame;
	}
	
	public LocalDate getDatacadastro() {
		return datacadastro;
	}
	
	public void setDatacadastro(LocalDate datacadastro) {
		this.datacadastro = datacadastro;
	}

	@Version
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	public Long getCodigodecid() {
		return codigodecid;
	}
	
	public void setCodigodecid(Long codigodecid) {
		this.codigodecid = codigodecid;
	}
	
	public Long getCodigoprofexecutante() {
		return codigoprofexecutante;
	}
	
	public void setCodigoprofexecutante(Long codigoprofexecutante) {
		this.codigoprofexecutante = codigoprofexecutante;
	}
	
	public Date getDataatestado() {
		return dataatestado;
	}
	
	public void setDataatestado(Date dataatestado) {
		this.dataatestado = dataatestado;
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

	@Override
	public String toString() {
		return "Atendimento [codigo=" + codigo + ", paciente=" + paciente + ", convenio=" + convenio + ", solicitante="
				+ solicitante + ", procedimentos=" + procedimentos + ", dataatendimento=" + dataatendimento
				+ ", observacoes=" + observacoes + "]";
	}

}