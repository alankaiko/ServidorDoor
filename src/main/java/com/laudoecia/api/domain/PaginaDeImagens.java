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
import javax.persistence.Table;

import com.laudoecia.api.domain.enuns.LAYOUT_IMG;


@Entity
@Table(name = "paginadeimagens")
public class PaginaDeImagens implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long codigo;
	private LAYOUT_IMG layout;
	private List<ImagemImpressa> imagens;
	private ProcedimentoAtendimento procedimentoatendimento;

	public PaginaDeImagens(LAYOUT_IMG layout) {
		this.layout = layout;
	}

	public PaginaDeImagens() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "pagina")
	public List<ImagemImpressa> getImagens() {
		return imagens;
	}

	public void setImagens(List<ImagemImpressa> imagens) {
		this.imagens.clear();
		if (imagens == null) {
		} else {
			this.imagens.addAll(imagens);
		}
	}

	public void addImagem(ImagemImpressa imagem) {
		imagens.add(imagem);
	}

	public void removeImagem(int index) {
		imagens.remove(index);
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "layout", nullable = false)
	public LAYOUT_IMG getLayout() {
		return layout;
	}

	public void setLayout(LAYOUT_IMG layout) {
		this.layout = layout;
	}

	@JoinColumn(name = "procdoatd_codigo", referencedColumnName = "codigo")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public ProcedimentoAtendimento getProcedimentoatendimento() {
		return procedimentoatendimento;
	}
	
	public void setProcedimentoatendimento(ProcedimentoAtendimento procedimentoatendimento) {
		this.procedimentoatendimento = procedimentoatendimento;
	}
}
