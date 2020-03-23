package com.laudoecia.api.repository.filtro;

import java.time.LocalDate;

public class PatientFilter {
	private String patientid;
	private String patientname;
	private LocalDate birthday;
	private String patientage;
	private String patientsex;
	private boolean servidor;

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
	
	public boolean isServidor() {
		return servidor;
	}
	
	public void setServidor(boolean servidor) {
		this.servidor = servidor;
	}

}
