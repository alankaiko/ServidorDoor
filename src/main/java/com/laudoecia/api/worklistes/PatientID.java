package com.laudoecia.api.worklistes;

import org.dcm4che3.data.IDWithIssuer;

public class PatientID {

	private long pk;
	private long version;
	private String id;
	private String identifierTypeCode;
	private IssuerEntity issuer;

	public long getPk() {
		return pk;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getIdentifierTypeCode() {
		return identifierTypeCode;
	}

	public void setIdentifierTypeCode(String identifierTypeCode) {
		this.identifierTypeCode = identifierTypeCode;
	}

	public IssuerEntity getIssuer() {
		return issuer;
	}

	public void setIssuer(IssuerEntity issuer) {
		this.issuer = issuer;
	}

	public IDWithIssuer getIDWithIssuer() {
		return new IDWithIssuer(id, issuer != null ? issuer.getIssuer() : null);
	}

	@Override
	public String toString() {
		return "PatientID[pk=" + pk + ", id=" + id + ", issuer=" + issuer + "]";
	}
}
