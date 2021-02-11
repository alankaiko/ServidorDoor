package com.laudoecia.api.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.laudoecia.api.modelo.enuns.CID_CAUSA_OBITO;
import com.laudoecia.api.modelo.enuns.CID_CLASSIFICACAO;
import com.laudoecia.api.modelo.enuns.RESTRICAO_SEXO;

@Entity
@Table(name = "subcategoriacid10")
public class SubcategoriaCid10 implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String codigotexto;
	private String nome;
	private String nome50;
	private RESTRICAO_SEXO restrsexo;
	private CID_CLASSIFICACAO classificacao;
	private CategoriaCID10 categoriacid10;
	private CID_CAUSA_OBITO causaobito;
	private String referencia;
	private String excluidos;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCodigotexto() {
		return codigotexto;
	}

	public void setCodigotexto(String codigotexto) {
		this.codigotexto = codigotexto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome50() {
		return nome50;
	}

	public void setNome50(String nome50) {
		this.nome50 = nome50;
	}

	@Enumerated(EnumType.STRING)
	public RESTRICAO_SEXO getRestrsexo() {
		return restrsexo;
	}

	@Enumerated()
	public void setRestrsexo(RESTRICAO_SEXO restrsexo) {
		this.restrsexo = restrsexo;
	}

	@Enumerated(EnumType.STRING)
	public CID_CLASSIFICACAO getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(CID_CLASSIFICACAO classificacao) {
		this.classificacao = classificacao;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_categoriadic_codigo", referencedColumnName = "codigo")
	public CategoriaCID10 getCategoriacid10() {
		return categoriacid10;
	}

	public void setCategoriacid10(CategoriaCID10 categoriacid10) {
		this.categoriacid10 = categoriacid10;
	}

	@Enumerated(EnumType.STRING)
	public CID_CAUSA_OBITO getCausaobito() {
		return causaobito;
	}

	public void setCausaobito(CID_CAUSA_OBITO causaobito) {
		this.causaobito = causaobito;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getExcluidos() {
		return excluidos;
	}

	public void setExcluidos(String excluidos) {
		this.excluidos = excluidos;
	}

	
	
}
