package com.laudoecia.api.repository.resumo;

import java.io.Serializable;
import java.time.LocalDate;

public class ResumoPatient implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idpatient;
	private String patientid;
	private String patientname;
	private LocalDate birthday;
	private String patientage;
	private String patientsex;
	private LocalDate datecreate;

	public ResumoPatient(Long idpatient, String patientid, String patientname, LocalDate birthday, String patientage, String patientsex, LocalDate datecreate) {
		this.idpatient = idpatient;
		this.patientid = patientid;
		this.patientname = patientname;
		this.birthday = birthday;
		this.patientage = patientage;
		this.patientsex = patientsex;
		this.datecreate = datecreate;
	}

	public Long getIdpatient() {
		return idpatient;
	}

	public void setIdpatient(Long idpatient) {
		this.idpatient = idpatient;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}
	
	
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getPatientage() {
		return patientage;
	}

	public void setPatientage(String patientage) {
		this.patientage = patientage;
	}

	public String getPatientsex() {
		return patientsex;
	}
	
	public void setPatientsex(String patientsex) {
		this.patientsex = patientsex;
	}

	public LocalDate getDatecreate() {
		return datecreate;
	}
	
	public void setDatecreate(LocalDate datecreate) {
		this.datecreate = datecreate;
	}

}
