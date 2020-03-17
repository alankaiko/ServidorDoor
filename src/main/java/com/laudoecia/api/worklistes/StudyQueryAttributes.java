package com.laudoecia.api.worklistes;

import com.laudoecia.api.domain.Study;

public class StudyQueryAttributes {

	public static final String DELETE_FOR_STUDY = "StudyQueryAttributes.deleteForStudy";
	public static final String VIEW_IDS_FOR_STUDY_PK = "StudyQueryAttributes.viewIDsForStudyPk";

	private long pk;

	private String viewID;

	private int numberOfSeries;

	private int numberOfInstances;

	private String modalitiesInStudy;

	private String sopClassesInStudy;

	private String retrieveAETs;

	private Availability availability;

	private Study study;

	public long getPk() {
		return pk;
	}

	public String getViewID() {
		return viewID;
	}

	public void setViewID(String viewID) {
		this.viewID = viewID;
	}

	public int getNumberOfSeries() {
		return numberOfSeries;
	}

	public void setNumberOfSeries(int numberOfSeries) {
		this.numberOfSeries = numberOfSeries;
	}

	public int getNumberOfInstances() {
		return numberOfInstances;
	}

	public void setNumberOfInstances(int numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}

	public String getModalitiesInStudy() {
		return modalitiesInStudy;
	}

	public void setModalitiesInStudy(String modalitiesInStudy) {
		this.modalitiesInStudy = modalitiesInStudy;
	}

	public String getSOPClassesInStudy() {
		return sopClassesInStudy;
	}

	public void setSOPClassesInStudy(String sopClassesInStudy) {
		this.sopClassesInStudy = sopClassesInStudy;
	}

	public String getRetrieveAETs() {
		return retrieveAETs;
	}

	public void setRetrieveAETs(String retrieveAETs) {
		this.retrieveAETs = retrieveAETs;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}
}
