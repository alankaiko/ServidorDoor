package com.laudoecia.api.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.laudoecia.api.modelo.enuns.LAYOUT_IMG;


@Entity
@Table(name = "paginadeimagens")
public class PaginaDeImagens{
	private Long codigo;
	private LAYOUT_IMG layout;
	private List<ImagemImpressa> imagemimpressa;
	private ProcedimentoAtendimento procedimentoatendimento;
	

	public PaginaDeImagens(LAYOUT_IMG layout) {
		this.layout = layout;
	}

	public PaginaDeImagens() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@JsonIgnoreProperties("paginadeimagens")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paginadeimagens", orphanRemoval = true)
	public List<ImagemImpressa> getImagemimpressa() {
		return imagemimpressa;
	}
	
	public void setImagemimpressa(List<ImagemImpressa> imagemimpressa) {
		this.imagemimpressa = imagemimpressa;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "layout", nullable = true)
	public LAYOUT_IMG getLayout() {
		return layout;
	}

	public void setLayout(LAYOUT_IMG layout) {
		this.layout = layout;
	}

	@ManyToOne
	@JoinColumn(name = "proc_codigo", nullable = true)
	public ProcedimentoAtendimento getProcedimentoatendimento() {
		return procedimentoatendimento;
	}
	
	public void setProcedimentoatendimento(ProcedimentoAtendimento procedimentoatendimento) {
		this.procedimentoatendimento = procedimentoatendimento;
	}
	
}
