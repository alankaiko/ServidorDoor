package com.laudoecia.api.repository.filtro;

import java.io.Serializable;
import java.util.Date;

public class ResumoPatient implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idpatient;
	private String patientid;
	private String patientname;
	private Date birthday;
	private String patientage;
	private String patientsex;
	private Date datecreate;

	public ResumoPatient(Long idpatient, String patientid, String patientname, Date birthday, String patientage, String patientsex, Date datecreate) {
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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

	public Date getDatecreate() {
		return datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}

}
