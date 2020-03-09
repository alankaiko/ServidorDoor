package com.laudoecia.api.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.DatePrecision;
import org.dcm4che3.data.Tag;

import com.laudoecia.api.utilities.BlobCorruptedException;

@Entity
@Table(name = "mpps")
public class MPPS implements Serializable {
	private static final long serialVersionUID = -599495313070741738L;

	public static final String FIND_BY_SOP_INSTANCE_UID = "MPPS.findBySOPInstanceUID";
	public static final String FIND_BY_SOP_INSTANCE_UID_EAGER = "MPPS.findBySOPInstanceUIDEager";
	public static final String FIND_BY_SOP_INSTANCE_UIDs = "MPPS.findBySOPInstanceUIDs";
	public static final String DELETE_BY_SOP_INSTANCE_UIDs = "MPPS.deleteBySOPInstanceUIDs";
	public static final String IN_PROGRESS = "IN PROGRESS";
	public static final String COMPLETED = "COMPLETED";
	public static final String DISCONTINUED = "DISCONTINUED";

	public boolean discontinuedForReason(Code reasonCode) {
		return getStatus() == Status.DISCONTINUED && getDiscontinuationreasoncode() != null
				&& getDiscontinuationreasoncode().equals(reasonCode);
	}

	public enum Status {
		IN_PROGRESS, COMPLETED, DISCONTINUED;
	}

	private Long codigo;
	private long version;
	private Date createdtime;
	private Date updatedtime;
	private String sopinstanceuid;
	private Date startdatetime;
	private String performedstationaet;
	private String modality;
	private String accessionnumber;
	private Status status;
	private AttributesBlob attributesblob;
	private Code discontinuationreasoncode;
	private Patient patient;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(updatable = false)
	public Date getCreatedtime() {
		return createdtime;
	}

	public Date getUpdatedtime() {
		return updatedtime;
	}

	@Column(updatable = true, unique = true)
	public String getSopinstanceuid() {
		return sopinstanceuid;
	}

	public void setSopinstanceuid(String sopinstanceuid) {
		this.sopinstanceuid = sopinstanceuid;
	}

	@Column(nullable = true)
	public Date getStartdatetime() {
		return startdatetime;
	}

	public String getPerformedstationaet() {
		return performedstationaet;
	}

	public String getModality() {
		return modality;
	}

	public String getAccessionnumber() {
		return accessionnumber;
	}

	public void setAccessionnumber(String accessionnumber) {
		this.accessionnumber = accessionnumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public void setUpdatedtime(Date updatedtime) {
		this.updatedtime = updatedtime;
	}

	public void setStartdatetime(Date startdatetime) {
		this.startdatetime = startdatetime;
	}

	public void setPerformedstationaet(String performedstationaet) {
		this.performedstationaet = performedstationaet;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setAttributesblob(AttributesBlob attributesblob) {
		this.attributesblob = attributesblob;
	}

	@ManyToOne
	@JoinColumn(name = "drcode_fk")
	public Code getDiscontinuationreasoncode() {
		return discontinuationreasoncode;
	}

	public void setDiscontinuationreasoncode(Code discontinuationreasoncode) {
		this.discontinuationreasoncode = discontinuationreasoncode;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "patient_fk")
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Version
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "MPPS[codigo=" + codigo + ", iuid=" + sopinstanceuid + ", status=" + status + ", accno="
				+ accessionnumber + ", startDateTime=" + startdatetime.toString() + ", mod=" + modality + ", aet="
				+ performedstationaet + "]";
	}

	@PrePersist
	public void onPrePersist() {
		Date now = new Date();
		createdtime = now;
		updatedtime = now;
	}

	@PreUpdate
	public void onPreUpdate() {
		updatedtime = new Date();
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "dicomattrs_fk")
	public AttributesBlob getAttributesblob() {
		return attributesblob;
	}

	public Attributes pegaAttributes() throws BlobCorruptedException {
		return attributesblob.getAttributes();
	}

	public void setarAttributes(Attributes attrs, String nullValue) {
		Date dt = attrs.getDate(Tag.PerformedProcedureStepStartDateAndTime, new DatePrecision(Calendar.SECOND));
		if (dt != null) {
			Calendar adjustedDateTimeCal = new GregorianCalendar();
			adjustedDateTimeCal.setTime(dt);
			adjustedDateTimeCal.set(Calendar.MILLISECOND, 0);
			startdatetime = adjustedDateTimeCal.getTime();
		}
		this.performedstationaet = attrs.getString(Tag.PerformedStationAETitle);
		this.modality = attrs.getString(Tag.Modality);
		Attributes ssa = attrs.getNestedDataset(Tag.ScheduledStepAttributesSequence);
		if (ssa != null)
			this.accessionnumber = ssa.getString(Tag.AccessionNumber);

		Status status = getMPPSStatus(attrs);

		if (status != null)
			this.status = status;

		if (attributesblob == null)
			attributesblob = new AttributesBlob(attrs);
		else
			attributesblob.setAttributes(attrs);
	}

	public static Status getMPPSStatus(Attributes attrs) {
		String s = attrs.getString(Tag.PerformedProcedureStepStatus);
		Status status = null;
		if (s != null)
			status = Status.valueOf(s.replace(' ', '_'));
		return status;
	}
}
