
package com.laudoecia.api.domain;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.laudoecia.api.domain.enuns.STATUS_LAUDO;



@Entity
@Table
public class Laudo {	
	private Long codigo;
	private STATUS_LAUDO status;
	private ParametroDoLaudo laudosalvo;
	private ModeloDeLaudoDoProc modelodelaudo;
	private SubcategoriaCid10 cidresultadodoexame;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	public STATUS_LAUDO getStatus() {
		return status;
	}
	
	public void setStatus(STATUS_LAUDO status) {
		this.status = status;
	}
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "laudosalvo_codigo", referencedColumnName = "codigo", nullable = true)
	public ParametroDoLaudo getLaudosalvo() {
		return laudosalvo;
	}
	
	public void setLaudosalvo(ParametroDoLaudo laudosalvo) {
		this.laudosalvo = laudosalvo;
	}
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "modelodelaudo_codigo", referencedColumnName = "codigo", nullable = true)
	public ModeloDeLaudoDoProc getModelodelaudo() {
		return modelodelaudo;
	}
	
	public void setModelodelaudo(ModeloDeLaudoDoProc modelodelaudo) {
		this.modelodelaudo = modelodelaudo;
	}
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "cid_result_codigo", referencedColumnName = "codigo", nullable = true)
	public SubcategoriaCid10 getCidresultadodoexame() {
		return cidresultadodoexame;
	}
	
	public void setCidresultadodoexame(SubcategoriaCid10 cidresultadodoexame) {
		this.cidresultadodoexame = cidresultadodoexame;
	}

//	@Override
//	public String toString() {
//		return "Laudo [codigo=" + codigo + ", status=" + status + ", paramslaudo=" + paramslaudo + ", modelodelaudo="
//				+ modelodelaudo + ", cidresultadodoexame=" + cidresultadodoexame + "]";
//	}

	
}
