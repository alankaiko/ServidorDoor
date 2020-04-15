package com.laudoecia.api.repository.filtro;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PatientFilter {
	private String patientid;
	private String patientname;
	private String patientage;
	private String patientsex;
	private boolean servidor;
	private LocalDate birthday;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
