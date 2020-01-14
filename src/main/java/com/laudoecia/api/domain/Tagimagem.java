package com.laudoecia.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Tagimagem {
	private Long codigo;
	private String imagetype;
	private String sopclassuid;
	private String sopinstanceuid;
	private String studydate;
	private String seriesdate;
	private String acquisitiondate;
	private String contentdate;
	private String studytime;
	private String seriestime;
	private String acquisitiontime;
	private String contenttime;
	private String accessionnumber;
	private String modality;
	private String presentationintenttype;
	private String manufacturer;
	private String institutionname;
	private String institutionaddress;
	private String referringphysiciansname;
	private String stationname;
	private String studydescription;
	private String seriesdescription;
	private String institutionaldepartmentname;
	private String performingphysiciansname;
	private String operatorsname;
	private String manufacturersmodelname;
	private String referencedpatientsequence;
	private String anatomicregionsequence;
	private String primaryAnatomicstructuresequence;
	private String patientsname;
	private String patientid;
	private String softwareversions;
	private String imagerpixelspacing;
	private String positionertype;
	private String detectortype;
	private String detectordescription;
	private String detectormode;
	private String timeoflastdetectorcalibration;
	private String samplesperpixel;
	private String photometricinterpretation;
	private String rows;
	private String columns;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(length = 50)
	public String getImagetype() {
		return imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}

	@Column(length = 50)
	public String getSopclassuid() {
		return sopclassuid;
	}

	public void setSopclassuid(String sopclassuid) {
		this.sopclassuid = sopclassuid;
	}

	@Column(length = 50)
	public String getSopinstanceuid() {
		return sopinstanceuid;
	}

	public void setSopinstanceuid(String sopinstanceuid) {
		this.sopinstanceuid = sopinstanceuid;
	}

	@Column(length = 50)
	public String getStudydate() {
		return studydate;
	}

	public void setStudydate(String studydate) {
		this.studydate = studydate;
	}

	@Column(length = 50)
	public String getSeriesdate() {
		return seriesdate;
	}

	public void setSeriesdate(String seriesdate) {
		this.seriesdate = seriesdate;
	}

	@Column(length = 50)
	public String getAcquisitiondate() {
		return acquisitiondate;
	}

	public void setAcquisitiondate(String acquisitiondate) {
		this.acquisitiondate = acquisitiondate;
	}

	@Column(length = 50)
	public String getContentdate() {
		return contentdate;
	}

	public void setContentdate(String contentdate) {
		this.contentdate = contentdate;
	}

	@Column(length = 50)
	public String getStudytime() {
		return studytime;
	}

	public void setStudytime(String studytime) {
		this.studytime = studytime;
	}

	@Column(length = 50)
	public String getSeriestime() {
		return seriestime;
	}

	public void setSeriestime(String seriestime) {
		this.seriestime = seriestime;
	}

	@Column(length = 50)
	public String getAcquisitiontime() {
		return acquisitiontime;
	}

	public void setAcquisitiontime(String acquisitiontime) {
		this.acquisitiontime = acquisitiontime;
	}

	@Column(length = 50)
	public String getContenttime() {
		return contenttime;
	}

	public void setContenttime(String contenttime) {
		this.contenttime = contenttime;
	}

	@Column(length = 50)
	public String getAccessionnumber() {
		return accessionnumber;
	}

	public void setAccessionnumber(String accessionnumber) {
		this.accessionnumber = accessionnumber;
	}

	@Column(length = 50)
	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	@Column(length = 50)
	public String getPresentationintenttype() {
		return presentationintenttype;
	}

	public void setPresentationintenttype(String presentationintenttype) {
		this.presentationintenttype = presentationintenttype;
	}

	@Column(length = 50)
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(length = 50)
	public String getInstitutionname() {
		return institutionname;
	}

	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}

	@Column(length = 50)
	public String getInstitutionaddress() {
		return institutionaddress;
	}

	public void setInstitutionaddress(String institutionaddress) {
		this.institutionaddress = institutionaddress;
	}

	@Column(length = 50)
	public String getReferringphysiciansname() {
		return referringphysiciansname;
	}

	public void setReferringphysiciansname(String referringphysiciansname) {
		this.referringphysiciansname = referringphysiciansname;
	}

	@Column(length = 50)
	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	@Column(length = 50)
	public String getStudydescription() {
		return studydescription;
	}

	public void setStudydescription(String studydescription) {
		this.studydescription = studydescription;
	}

	@Column(length = 50)
	public String getSeriesdescription() {
		return seriesdescription;
	}

	public void setSeriesdescription(String seriesdescription) {
		this.seriesdescription = seriesdescription;
	}

	@Column(length = 50)
	public String getInstitutionaldepartmentname() {
		return institutionaldepartmentname;
	}

	public void setInstitutionaldepartmentname(String institutionaldepartmentname) {
		this.institutionaldepartmentname = institutionaldepartmentname;
	}

	@Column(length = 50)
	public String getPerformingphysiciansname() {
		return performingphysiciansname;
	}

	public void setPerformingphysiciansname(String performingphysiciansname) {
		this.performingphysiciansname = performingphysiciansname;
	}

	@Column(length = 50)
	public String getOperatorsname() {
		return operatorsname;
	}

	public void setOperatorsname(String operatorsname) {
		this.operatorsname = operatorsname;
	}

	@Column(length = 50)
	public String getManufacturersmodelname() {
		return manufacturersmodelname;
	}

	public void setManufacturersmodelname(String manufacturersmodelname) {
		this.manufacturersmodelname = manufacturersmodelname;
	}

	@Column(length = 50)
	public String getReferencedpatientsequence() {
		return referencedpatientsequence;
	}

	public void setReferencedpatientsequence(String referencedpatientsequence) {
		this.referencedpatientsequence = referencedpatientsequence;
	}

	@Column(length = 50)
	public String getAnatomicregionsequence() {
		return anatomicregionsequence;
	}

	public void setAnatomicregionsequence(String anatomicregionsequence) {
		this.anatomicregionsequence = anatomicregionsequence;
	}

	@Column(length = 50)
	public String getPrimaryAnatomicstructuresequence() {
		return primaryAnatomicstructuresequence;
	}

	public void setPrimaryAnatomicstructuresequence(String primaryAnatomicstructuresequence) {
		this.primaryAnatomicstructuresequence = primaryAnatomicstructuresequence;
	}

	@Column(length = 50)
	public String getPatientsname() {
		return patientsname;
	}

	public void setPatientsname(String patientsname) {
		this.patientsname = patientsname;
	}

	@Column(length = 50)
	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	@Column(length = 50)
	public String getSoftwareversions() {
		return softwareversions;
	}

	public void setSoftwareversions(String softwareversions) {
		this.softwareversions = softwareversions;
	}

	@Column(length = 50)
	public String getImagerpixelspacing() {
		return imagerpixelspacing;
	}

	public void setImagerpixelspacing(String imagerpixelspacing) {
		this.imagerpixelspacing = imagerpixelspacing;
	}

	@Column(length = 50)
	public String getPositionertype() {
		return positionertype;
	}

	public void setPositionertype(String positionertype) {
		this.positionertype = positionertype;
	}

	@Column(length = 50)
	public String getDetectortype() {
		return detectortype;
	}

	public void setDetectortype(String detectortype) {
		this.detectortype = detectortype;
	}

	@Column(length = 50)
	public String getDetectordescription() {
		return detectordescription;
	}

	public void setDetectordescription(String detectordescription) {
		this.detectordescription = detectordescription;
	}

	@Column(length = 50)
	public String getDetectormode() {
		return detectormode;
	}

	public void setDetectormode(String detectormode) {
		this.detectormode = detectormode;
	}

	@Column(length = 50)
	public String getTimeoflastdetectorcalibration() {
		return timeoflastdetectorcalibration;
	}

	public void setTimeoflastdetectorcalibration(String timeoflastdetectorcalibration) {
		this.timeoflastdetectorcalibration = timeoflastdetectorcalibration;
	}

	@Column(length = 50)
	public String getSamplesperpixel() {
		return samplesperpixel;
	}

	public void setSamplesperpixel(String samplesperpixel) {
		this.samplesperpixel = samplesperpixel;
	}

	@Column(length = 50)
	public String getPhotometricinterpretation() {
		return photometricinterpretation;
	}

	public void setPhotometricinterpretation(String photometricinterpretation) {
		this.photometricinterpretation = photometricinterpretation;
	}

	@Column(length = 50)
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	@Column(length = 50)
	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

}
