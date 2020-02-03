package com.laudoecia.api.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@idpatient")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idpatient;
	private String patientid;
	private String patientname;
	private Date birthday;
	private String patientage;
	private String patientsex;
	private LocalDate datecreate;
	private LocalDate datemodify;
	private List<Study> studyes;
	private Endereco endereco;
	private Contato contato;

	public Patient() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdpatient() {
		return idpatient;
	}

	public void setIdpatient(Long idpatient) {
		this.idpatient = idpatient;
	}

	@Column(length = 200)
	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	@Column(length = 200)
	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	@Column(length = 20)
	public String getPatientsex() {
		return patientsex;
	}

	public void setPatientsex(String patientsex) {
		this.patientsex = patientsex;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(length = 10)
	public String getPatientage() {
		return patientage;
	}

	public void setPatientage(String patientage) {
		this.patientage = patientage;
	}

	public LocalDate getDatecreate() {
		return datecreate;
	}
	
	public void setDatecreate(LocalDate datecreate) {
		this.datecreate = datecreate;
	}

	public LocalDate getDatemodify() {
		return datemodify;
	}
	
	public void setDatemodify(LocalDate datemodify) {
		this.datemodify = datemodify;
	}

	@OneToMany(mappedBy = "patient")
	public List<Study> getStudyes() {
		return studyes;
	}

	public void setStudyes(List<Study> studyes) {
		this.studyes = studyes;
	}

	@Embedded
	public Endereco getEndereco() {
		if(this.endereco == null)
			this.endereco = new Endereco();
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Embedded
	public Contato getContato() {
		if(this.contato == null)
			this.contato = new Contato();
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
