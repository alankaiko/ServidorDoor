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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Estudo {
	private Long codigo;
	private String accessionnumber;
	private String studyid;
	private String studyinstanceuid;
	private String studydescription;
	private Date studydatetime;
	private String referringphysicianname;
	private String studypriorityid;
	private String studystatusid;
	private String additionalpatienthistory;
	private String admittingdiagnosesdescription;
	private Date datacriacao;
	private Date datamodificacao;
	private Paciente paciente;
	private List<Series> series;

	public Estudo() {
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

	@Column(length = 160)
	public String getStudyid() {
		return studyid;
	}

	public void setStudyid(String studyid) {
		this.studyid = studyid;
	}

	@Column(length = 350)
	public String getStudydescription() {
		return studydescription;
	}

	public void setStudydescription(String studydescription) {
		this.studydescription = studydescription;
	}

	@Column(length = 220)
	public String getStudyinstanceuid() {
		return studyinstanceuid;
	}

	public void setStudyinstanceuid(String studyinstanceuid) {
		this.studyinstanceuid = studyinstanceuid;
	}

	@Column(length = 150)
	public String getAccessionnumber() {
		return accessionnumber;
	}

	public void setAccessionnumber(String accessionnumber) {
		this.accessionnumber = accessionnumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getStudydatetime() {
		return studydatetime;
	}

	public void setStudydatetime(Date studydatetime) {
		this.studydatetime = studydatetime;
	}

	@Column(length = 220)
	public String getReferringphysicianname() {
		return referringphysicianname;
	}

	public void setReferringphysicianname(String referringphysicianname) {
		this.referringphysicianname = referringphysicianname;
	}

	@Column(length = 350)
	public String getAdditionalpatienthistory() {
		return additionalpatienthistory;
	}

	public void setAdditionalpatienthistory(String additionalpatienthistory) {
		this.additionalpatienthistory = additionalpatienthistory;
	}

	@Column(length = 250)
	public String getAdmittingdiagnosesdescription() {
		return admittingdiagnosesdescription;
	}

	public void setAdmittingdiagnosesdescription(String admittingdiagnosesdescription) {
		this.admittingdiagnosesdescription = admittingdiagnosesdescription;
	}

	@Column(length = 180)
	public String getStudystatusid() {
		return studystatusid;
	}

	public void setStudystatusid(String studystatusid) {
		this.studystatusid = studystatusid;
	}

	@Column(length = 180)
	public String getStudypriorityid() {
		return studypriorityid;
	}

	public void setStudypriorityid(String studypriorityid) {
		this.studypriorityid = studypriorityid;
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
	@JoinColumn(name = "tbl_paciente_id", referencedColumnName = "codigo")
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "estudo")
	public List<Series> getSeries() {
		return series;
	}
	
	public void setSeries(List<Series> series) {
		this.series = series;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		datamodificacao = new Date();

		if (datacriacao == null)
			datacriacao = new Date();
	}

	@Override
	public String toString() {
		return "Estudo [codigo=" + codigo + ", accessionnumber=" + accessionnumber + ", studyid=" + studyid
				+ ", studyinstanceuid=" + studyinstanceuid + ", studydescription=" + studydescription
				+ ", studydatetime=" + studydatetime + ", referringphysicianname=" + referringphysicianname
				+ ", studypriorityid=" + studypriorityid + ", studystatusid=" + studystatusid
				+ ", additionalpatienthistory=" + additionalpatienthistory + ", admittingdiagnosesdescription="
				+ admittingdiagnosesdescription + ", datacriacao=" + datacriacao + ", datamodificacao="
				+ datamodificacao + "]";
	}

	

	
}
