package com.laudoecia.api.worklistes;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;

import com.laudoecia.api.domain.IssuerEntity;
import com.laudoecia.api.domain.PersonName;
import com.laudoecia.api.utilities.FuzzyStr;

@Entity
@Table
public class UPSRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk")
	private long pk;

	@Basic(optional = false)
	@Column(name = "accession_no")
	private String accessionNumber;

	@Basic(optional = false)
	@Column(name = "study_iuid")
	private String studyInstanceUID;

	@Basic(optional = false)
	@Column(name = "req_proc_id")
	private String requestedProcedureID;

	@Basic(optional = false)
	@Column(name = "req_service")
	private String requestingService;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "req_phys_name_fk")
	private PersonName requestingPhysician;

	@ManyToOne
	@JoinColumn(name = "accno_issuer_fk")
	private IssuerEntity issuerOfAccessionNumber;

	public UPSRequest() {
	}

	public UPSRequest(Attributes attrs, IssuerEntity issuerOfAccessionNumber, FuzzyStr fuzzyStr) {
		studyInstanceUID = attrs.getString(Tag.StudyInstanceUID, "*");
		accessionNumber = attrs.getString(Tag.AccessionNumber, "*");
		this.issuerOfAccessionNumber = issuerOfAccessionNumber;
		requestedProcedureID = attrs.getString(Tag.RequestedProcedureID, "*");
		requestingService = attrs.getString(Tag.RequestingService, "*");
		requestingPhysician = PersonName.valueOf(attrs.getString(Tag.RequestingPhysician), fuzzyStr, null);
	}

	public long getPk() {
		return pk;
	}

	public String getStudyInstanceUID() {
		return studyInstanceUID;
	}

	public String getRequestedProcedureID() {
		return requestedProcedureID;
	}

	public String getRequestingService() {
		return requestingService;
	}

	public PersonName getRequestingPhysician() {
		return requestingPhysician;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public IssuerEntity getIssuerOfAccessionNumber() {
		return issuerOfAccessionNumber;
	}

	@Override
	public String toString() {
		return "UPSRequest[pk=" + pk + ", suid=" + studyInstanceUID + ", accno=" + accessionNumber + ", rpid="
				+ requestedProcedureID + "]";
	}

}
