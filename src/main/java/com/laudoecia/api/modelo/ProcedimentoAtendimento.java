package com.laudoecia.api.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.laudoecia.api.modelo.enuns.EnumStatusProcedimento;

@Entity
@Table(name ="procedimentoatendimento")
public class ProcedimentoAtendimento {
	private Long codigo;
	private ProfissionalExecutante profexecutante;
	private ProcedimentoMedico procedimentomedico;
	private BigDecimal valorpaciente;
	private BigDecimal valorconvenio;
	private LocalDate preventregalaudo;
	private LocalDate dataexecucao;
	private LocalTime horaexecucao;
	private Atendimento atendimento;
	private List<PaginaDeImagens> paginadeimagens;
	private List<Imagem> listaimagem;
	private Laudo laudo;
	private Long codigoatdteste;
	private EnumStatusProcedimento status;

	public ProcedimentoAtendimento() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne
	@JoinColumn(name = "tbl_profexecutante_id", referencedColumnName = "codigo", nullable = true)
	public ProfissionalExecutante getProfexecutante() {
		return profexecutante;
	}

	public void setProfexecutante(ProfissionalExecutante profexecutante) {
		this.profexecutante = profexecutante;
	}

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_procedimentomedico_id", referencedColumnName = "codigo")
	public ProcedimentoMedico getProcedimentomedico() {
		return procedimentomedico;
	}

	public void setProcedimentomedico(ProcedimentoMedico procedimentomedico) {
		this.procedimentomedico = procedimentomedico;
	}

	public BigDecimal getValorpaciente() {
		return valorpaciente;
	}

	public void setValorpaciente(BigDecimal valorpaciente) {
		this.valorpaciente = valorpaciente;
	}

	public BigDecimal getValorconvenio() {
		return valorconvenio;
	}

	public void setValorconvenio(BigDecimal valorconvenio) {
		this.valorconvenio = valorconvenio;
	}

	public LocalDate getDataexecucao() {
		return dataexecucao;
	}

	public void setDataexecucao(LocalDate dataexecucao) {
		this.dataexecucao = dataexecucao;
	}

	public LocalTime getHoraexecucao() {
		return horaexecucao;
	}
	
	public void setHoraexecucao(LocalTime horaexecucao) {
		this.horaexecucao = horaexecucao;
	}
	
	public LocalDate getPreventregalaudo() {
		return preventregalaudo;
	}

	public void setPreventregalaudo(LocalDate preventregalaudo) {
		this.preventregalaudo = preventregalaudo;
	}
	
	@JsonIgnoreProperties("procedimentoatendimento")
	@OneToMany(mappedBy = "procedimentoatendimento", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<PaginaDeImagens> getPaginadeimagens() {
		return paginadeimagens;
	}
	
	public void setPaginadeimagens(List<PaginaDeImagens> paginadeimagens) {
		this.paginadeimagens = paginadeimagens;
	}

	@ManyToOne
	@JoinColumn(name = "codigo_atendimento")
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	
	@Fetch(FetchMode.SUBSELECT)
	@JsonIgnoreProperties("procedimentoatendimento")
	@OneToMany(mappedBy = "procedimentoatendimento", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Imagem> getListaimagem() {
		return this.listaimagem;
	}
	
	public void setListaimagem(List<Imagem> listaimagem) {
		this.listaimagem = listaimagem;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_laudo", referencedColumnName = "codigo")
	public Laudo getLaudo() {
		return laudo;
	}
	
	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
	}
	
	@Enumerated(EnumType.STRING)
	public EnumStatusProcedimento getStatus() {
		return status;
	}
	
	public void setStatus(EnumStatusProcedimento status) {
		this.status = status;
	}
	
	@Transient
	public Long getCodigoatdteste() {
		return codigoatdteste;
	}
	
	public void setCodigoatdteste(Long codigoatdteste) {
		this.codigoatdteste = codigoatdteste;
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
		ProcedimentoAtendimento other = (ProcedimentoAtendimento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProcedimentoAtendimento [codigo=" + codigo + ", profexecutante=" + profexecutante
				+ ", procedimentomedico=" + procedimentomedico + ", valorpaciente=" + valorpaciente + ", valorconvenio="
				+ valorconvenio + ", preventregalaudo=" + preventregalaudo + ", dataexecucao=" + dataexecucao
				+ ", atendimento=" + atendimento + ", paginadeimagens=" + paginadeimagens + ", listaimagem="
				+ listaimagem + ", laudo=" + laudo + ", codigoatdteste=" + codigoatdteste + "]";
	}
}
