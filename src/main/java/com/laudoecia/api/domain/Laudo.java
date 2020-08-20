
package com.laudoecia.api.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import com.laudoecia.api.domain.enuns.STATUS_LAUDO;





@Entity
@Table(name = "laudo")
public class Laudo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private ProcedimentoAtendimento procedimentoatendimento;
	private STATUS_LAUDO status = STATUS_LAUDO.PENDENTE;
	private List<ParametroDoLaudo> paramslaudo;
	private ModeloDeLaudoDoProc modelodelaudo;
	private SubcategoriaCid10 cidresultadodoexame;
	private List<Runnable> listenersplanilha;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@JoinColumn(name = "modelodelaudo_codigo", referencedColumnName = "codigo", nullable = false)
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	public ModeloDeLaudoDoProc getModelodelaudo() {
		return modelodelaudo;
	}
	
	public void setModelodelaudo(ModeloDeLaudoDoProc modelodelaudo) {
		this.modelodelaudo = modelodelaudo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "laudo", orphanRemoval = true)
	public List<ParametroDoLaudo> getParamslaudo() {
		return paramslaudo;
	}
	
	public void setParamslaudo(List<ParametroDoLaudo> paramslaudo) {
		this.paramslaudo = paramslaudo;
	}

	public void setParamsLaudo(List<ParametroDoLaudo> paramsLaudo) {
		this.paramslaudo.clear();
		if (paramsLaudo == null) {
		} else {
			this.paramslaudo.addAll(paramsLaudo);
		}
	}

	@JoinColumn(name = "procdoatd_codigo", referencedColumnName = "codigo", nullable = false)
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	public ProcedimentoAtendimento getProcedimentoatendimento() {
		return procedimentoatendimento;
	}

	public void setProcedimentoatendimento(ProcedimentoAtendimento procedimentoatendimento) {
		this.procedimentoatendimento = procedimentoatendimento;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	public STATUS_LAUDO getStatus() {
		return status;
	}
	
	public void setStatus(STATUS_LAUDO status) {
		this.status = status;
	}

	@JoinColumn(name = "cid_resultado_ex_codigo", referencedColumnName = "codigo", nullable = true)
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	public SubcategoriaCid10 getCidresultadodoexame() {
		return cidresultadodoexame;
	}
	
	public void setCidresultadodoexame(SubcategoriaCid10 cidresultadodoexame) {
		this.cidresultadodoexame = cidresultadodoexame;
	}

	@Override
	public String toString() {
		return "{" + "codigo=" + codigo + '}';
	}

	public Laudo(ModeloDeLaudoDoProc modelodelaudo) {
		this.modelodelaudo = modelodelaudo;
	}

	public Laudo(Laudo prototipo) {
		cidresultadodoexame = prototipo.getCidresultadodoexame();
		codigo = prototipo.getCodigo();
		modelodelaudo = prototipo.getModelodelaudo();
		this.procedimentoatendimento = prototipo.getProcedimentoatendimento();
		if (prototipo.getParamslaudo() == null) {
		} else {
			paramslaudo.addAll(prototipo.getParamslaudo());
		}
	}

	@Transient
	public void addListener(Runnable runn) {
		listenersplanilha.add(runn);
	}

	public void removeListener(Runnable runn) {
		listenersplanilha.remove(runn);
	}

}
