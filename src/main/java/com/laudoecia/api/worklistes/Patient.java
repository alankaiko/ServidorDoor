package com.laudoecia.api.worklistes;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.IDWithIssuer;
import org.dcm4che3.data.Tag;

import com.laudoecia.api.domain.AttributesBlob;
import com.laudoecia.api.utilities.BlobCorruptedException;
import com.laudoecia.api.utilities.FuzzyStr;

public class Patient {

	public static final String FIND_BY_PATIENT_ID = "Patient.findByPatientID";
	public static final String FIND_BY_PATIENT_ID_EAGER = "Patient.findByPatientIDEager";
	public static final String FIND_BY_PATIENT_FAMILY_NAME = "Patient.findByPatientFamilyName";
	public static final String FIND_BY_PATIENT_FAMILY_NAME_EAGER = "Patient.findByPatientFamilyNameEager";
	public static final String FIND_BY_MERGED_WITH = "Patient.findByMergedWith";
	public static final String COUNT_BY_MERGED_WITH = "Patient.CountByMergedWith";
	public static final String FIND_BY_VERIFICATION_STATUS = "Patient.findByVerificationStatus";
	public static final String FIND_BY_VERIFICATION_STATUS_AND_TIME = "Patient.findByVerificationStatusAndTime";
	public static final String FIND_BY_VERIFICATION_STATUS_AND_TIME_AND_MAX_RETRIES = "Patient.findByVerificationStatusAndTimeAndMaxRetries";
	public static final String CLAIM_PATIENT_VERIFICATION = "Patient.ClaimPatientVerification";

	public enum VerificationStatus {
		UNVERIFIED, VERIFIED, NOT_FOUND, VERIFICATION_FAILED, IN_PROCESS
	}

	public static class IDWithPkAndVerificationStatus {
		public final IDWithIssuer idWithIssuer;
		public final Long pk;
		public final VerificationStatus verificationStatus;

		public IDWithPkAndVerificationStatus(PatientID patientID, Long pk, VerificationStatus verificationStatus) {
			this.idWithIssuer = patientID.getIDWithIssuer();
			this.pk = pk;
			this.verificationStatus = verificationStatus;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(32);
			sb.append("Patient[pk=").append(pk);
			if (showPatientInfo != ShowPatientInfo.HASH_NAME_AND_ID)
				sb.append(", id=").append(idWithIssuer);
			sb.append(']');
			return sb.toString();
		}
	}

	private long pk;
	private long version;
	private Date createdTime;
	private Date updatedTime;
	private Date verificationTime;
	private VerificationStatus verificationStatus = VerificationStatus.UNVERIFIED;
	private int failedVerifications;
	private String patientBirthDate;
	private String patientSex;
	private String patientCustomAttribute1;
	private String patientCustomAttribute2;
	private String patientCustomAttribute3;
	private int numberOfStudies;
	private AttributesBlob attributesBlob;
	private PersonName patientName;
	private PersonName responsiblePerson;
	private Patient mergedWith;
	private PatientID patientID;

	private static ShowPatientInfo showPatientInfo = ShowPatientInfo.PLAIN_TEXT;

	public static ShowPatientInfo getShowPatientInfo() {
		return showPatientInfo;
	}

	public static void setShowPatientInfo(ShowPatientInfo showPatientInfo) {
		Patient.showPatientInfo = showPatientInfo;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(256);
		sb.append("Patient[pk=").append(pk);
		if (showPatientInfo == ShowPatientInfo.HASH_NAME_AND_ID && patientID != null)
			sb.append(", id=#").append(patientID.toString().hashCode());
		else
			sb.append(", id=").append(patientID);
		if (showPatientInfo != ShowPatientInfo.PLAIN_TEXT && patientName != null)
			sb.append(", name=#").append(patientName.toString().hashCode());
		else
			sb.append(", name=").append(patientName);
		sb.append(']');
		return sb.toString();
	}


	public long getPk() {
		return pk;
	}

	public long getVersion() {
		return version;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public Date getVerificationTime() {
		return verificationTime;
	}

	public void setVerificationTime(Date verificationTime) {
		this.verificationTime = verificationTime;
	}

	public VerificationStatus getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(VerificationStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public int getFailedVerifications() {
		return failedVerifications;
	}

	public void setFailedVerifications(int failedVerifications) {
		this.failedVerifications = failedVerifications;
	}

	public void resetFailedVerifications() {
		this.failedVerifications = 0;
	}

	public void incrementFailedVerifications() {
		this.failedVerifications++;
	}

	public String getPatientBirthDate() {
		return patientBirthDate;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public String getPatientCustomAttribute1() {
		return patientCustomAttribute1;
	}

	public String getPatientCustomAttribute2() {
		return patientCustomAttribute2;
	}

	public String getPatientCustomAttribute3() {
		return patientCustomAttribute3;
	}

	public int getNumberOfStudies() {
		return numberOfStudies;
	}

	public void setNumberOfStudies(int numberOfStudies) {
		this.numberOfStudies = numberOfStudies;
	}

	public void incrementNumberOfStudies() {
		numberOfStudies++;
	}

	public void decrementNumberOfStudies() {
		numberOfStudies = Math.max(numberOfStudies - 1, 0);
	}

	public Patient getMergedWith() {
		return mergedWith;
	}

	public void setMergedWith(Patient mergedWith) {
		this.mergedWith = mergedWith;
	}

	public PersonName getPatientName() {
		return patientName;
	}

	public PatientID getPatientID() {
		return patientID;
	}

	public void setPatientID(PatientID patientID) {
		this.patientID = patientID;
	}

	public PersonName getResponsiblePerson() {
		return responsiblePerson;
	}

	public Attributes getAttributes() throws BlobCorruptedException {
		return attributesBlob.getAttributes();
	}

	public void setAttributes(Attributes attrs, AttributeFilter filter, FuzzyStr fuzzyStr) {
		patientName = PersonName.valueOf(attrs.getString(Tag.PatientName), fuzzyStr, patientName);
		patientBirthDate = attrs.getString(Tag.PatientBirthDate, "*");
		patientSex = attrs.getString(Tag.PatientSex, "*").toUpperCase();

		patientCustomAttribute1 = AttributeFilter.selectStringValue(attrs, filter.getCustomAttribute1(), "*");
		patientCustomAttribute2 = AttributeFilter.selectStringValue(attrs, filter.getCustomAttribute2(), "*");
		patientCustomAttribute3 = AttributeFilter.selectStringValue(attrs, filter.getCustomAttribute3(), "*");

		Attributes blobAttrs = new Attributes(attrs, filter.getSelection(true));
		if (attributesBlob == null)
			attributesBlob = new AttributesBlob(blobAttrs);
		else
			attributesBlob.setAttributes(blobAttrs);

		responsiblePerson = PersonName.valueOf(attrs.getString(Tag.ResponsiblePerson), fuzzyStr, responsiblePerson);

		updatedTime = new Date();
	}
}
