package com.laudoecia.api.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="dispositive")
public class Equipamento implements Serializable {
	private static final long serialVersionUID = 6245262777427705812L;

	private Long codigo;
	private String instituicao;
	private String endereco;
	private String departamento;
	private String nomemodality;
	private String conversiontype;
	private String fabricante;
	private String modelo;
	private String stationname;
	private String serial;
	private String softwareversion;
	private Date datacriacao;
	private Date datamodificacao;
	private Serie series;

	public Equipamento() {
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

	@Column(length = 220)
	public String getInstituicao() {
		return instituicao;
	}
	
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(length = 180)
	public String getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Column(length = 165)
	public String getNomemodality() {
		return nomemodality;
	}
	
	public void setNomemodality(String nomemodality) {
		this.nomemodality = nomemodality;
	}

	@Column(length = 145)
	public String getConversiontype() {
		return conversiontype;
	}

	public void setConversiontype(String conversiontype) {
		this.conversiontype = conversiontype;
	}

	@Column(length = 230)
	public String getFabricante() {
		return fabricante;
	}
	
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	@Column(length = 230)
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column(length = 170)
	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	@Column(length = 200)
	public String getSerial() {
		return serial;
	}
	
	public void setSerial(String serial) {
		this.serial = serial;
	}

	@Column(length = 200)
	public String getSoftwareversion() {
		return softwareversion;
	}

	public void setSoftwareversion(String softwareversion) {
		this.softwareversion = softwareversion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatacriacao() {
		return datacriacao;
	}
	
	public void setDatacriacao(Date datacriacao) {
		this.datacriacao = datacriacao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatamodificacao() {
		return datamodificacao;
	}
	
	public void setDatamodificacao(Date datamodificacao) {
		this.datamodificacao = datamodificacao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	public Serie getSeries() {
		return series;
	}

	public void setSeries(Serie param) {
		this.series = param;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		datamodificacao = new Date();
		
		if (datacriacao == null) 
			datacriacao = new Date();
	}
	
}
