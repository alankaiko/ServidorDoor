package com.laudoecia.api.repository.filtro;

import java.util.Date;

public class PatientFilter {
	private String patientid;
	private String patientname;
	private Date birthday;
	private String patientage;
	private String patientsex;

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

}
