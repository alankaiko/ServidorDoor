package com.laudoecia.api.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="dispositive")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@iddispositive")
public class Dispositive implements Serializable {
	private static final long serialVersionUID = 6245262777427705812L;

	private Long iddispositive;
	private String institutionname;
	private String institutionaddress;
	private String institutionaldepartmentname;
	private String modality;
	private String conversiontype;
	private String manufacturer;
	private String manufacturermodelname;
	private String stationname;
	private String deviceserialnumber;
	private String softwareversion;
	private Date datecreation;
	private Date datemodify;
	private Series series;

	public Dispositive() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIddispositive() {
		return iddispositive;
	}

	public void setIddispositive(Long iddispositive) {
		this.iddispositive = iddispositive;
	}

	@Column(length = 220)
	public String getInstitutionname() {
		return institutionname;
	}

	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}

	@Column(length = 265)
	public String getInstitutionaddress() {
		return institutionaddress;
	}

	public void setInstitutionaddress(String institutionaddress) {
		this.institutionaddress = institutionaddress;
	}

	@Column(length = 180)
	public String getInstitutionaldepartmentname() {
		return institutionaldepartmentname;
	}

	public void setInstitutionaldepartmentname(String institutionaldepartmentname) {
		this.institutionaldepartmentname = institutionaldepartmentname;
	}

	@Column(length = 165)
	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	@Column(length = 145)
	public String getConversiontype() {
		return conversiontype;
	}

	public void setConversiontype(String conversiontype) {
		this.conversiontype = conversiontype;
	}

	@Column(length = 230)
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(length = 230)
	public String getManufacturermodelname() {
		return manufacturermodelname;
	}

	public void setManufacturermodelname(String manufacturermodelname) {
		this.manufacturermodelname = manufacturermodelname;
	}

	@Column(length = 170)
	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	@Column(length = 200)
	public String getDeviceserialnumber() {
		return deviceserialnumber;
	}

	public void setDeviceserialnumber(String deviceserialnumber) {
		this.deviceserialnumber = deviceserialnumber;
	}

	@Column(length = 200)
	public String getSoftwareversion() {
		return softwareversion;
	}

	public void setSoftwareversion(String softwareversion) {
		this.softwareversion = softwareversion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatemodify() {
		return datemodify;
	}

	public void setDatemodify(Date datemodify) {
		this.datemodify = datemodify;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	public Series getSeries() {
		return series;
	}

	public void setSeries(Series param) {
		this.series = param;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		datemodify = new Date();
		
		if (datecreation == null) 
			datecreation = new Date();
	}
	
}
