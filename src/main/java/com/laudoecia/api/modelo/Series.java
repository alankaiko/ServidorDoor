package com.laudoecia.api.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="series")
public class Series {
	private Long codigo;
	private String seriesinstanceuid;
	private String seriesdescription;
	private Integer seriesnumber;
	private String patientposition;
	private String bodypartexamined;
	private String laterality;
	private String operatorsname;
	private String protocolname;
	private Date seriesdatetime;
	private Date datacriacao;
	private Date datamodificacao;
	private Estudo estudo;
	private  List<Instancia> instancia;
	private Equipamento equipamento;
	
	public Series() {
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

	@Column(length = 200)
	public String getSeriesinstanceuid() {
		return seriesinstanceuid;
	}

	public void setSeriesinstanceuid(String seriesinstanceuid) {
		this.seriesinstanceuid = seriesinstanceuid;
	}

	@Column(length = 200)
	public String getSeriesdescription() {
		return seriesdescription;
	}

	public void setSeriesdescription(String seriesdescription) {
		this.seriesdescription = seriesdescription;
	}

	public Integer getSeriesnumber() {
		return seriesnumber;
	}

	public void setSeriesnumber(Integer seriesnumber) {
		this.seriesnumber = seriesnumber;
	}

	@Column(length = 130)
	public String getPatientposition() {
		return patientposition;
	}

	public void setPatientposition(String patientposition) {
		this.patientposition = patientposition;
	}

	@Column(length = 200)
	public String getBodypartexamined() {
		return bodypartexamined;
	}

	public void setBodypartexamined(String bodypartexamined) {
		this.bodypartexamined = bodypartexamined;
	}

	@Column(length = 140)
	public String getLaterality() {
		return laterality;
	}

	public void setLaterality(String laterality) {
		this.laterality = laterality;
	}

	@Column(length = 150)
	public String getOperatorsname() {
		return operatorsname;
	}

	public void setOperatorsname(String operatorsname) {
		this.operatorsname = operatorsname;
	}

	@Column(length = 200)
	public String getProtocolname() {
		return protocolname;
	}

	public void setProtocolname(String protocolname) {
		this.protocolname = protocolname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getSeriesdatetime() {
		return seriesdatetime;
	}

	public void setSeriesdatetime(Date seriesdatetime) {
		this.seriesdatetime = seriesdatetime;
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	public Estudo getEstudo() {
		return estudo;
	}
	
	public void setEstudo(Estudo estudo) {
		this.estudo = estudo;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "series")
	public List<Instancia> getInstancia() {
		return instancia;
	}
	
	public void setInstancia(List<Instancia> instancia) {
		this.instancia = instancia;
	}

	@OneToOne(mappedBy = "series")
	public Equipamento getEquipamento() {
		return equipamento;
	}
	
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		datamodificacao = new Date();
		
		if (datacriacao == null) 
			datacriacao = new Date();
	}


}
