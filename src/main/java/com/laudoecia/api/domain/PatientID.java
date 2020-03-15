package com.laudoecia.api.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.dcm4che3.data.IDWithIssuer;

@Entity
@Table(name = "patientid")
public class PatientID {
	private Long codigo;
	private long version;
	private String id;
	private String identifiertypecode;
	private IssuerEntity issuer;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Version
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentifiertypecode() {
		return identifiertypecode;
	}

	public void setIdentifiertypecode(String identifiertypecode) {
		this.identifiertypecode = identifiertypecode;
	}

	@ManyToOne
	@JoinColumn(name = "issuer_fk")
	public IssuerEntity getIssuer() {
		return issuer;
	}

	public void setIssuer(IssuerEntity issuer) {
		this.issuer = issuer;
	}

//	public IDWithIssuer getIDWithIssuer() {
//		return new IDWithIssuer(id, issuer != null ? issuer.getIssuer() : null);
//	}

	@Override
	public String toString() {
		return "PatientID[codigo=" + codigo + ", id=" + id + ", issuer=" + issuer + "]";
	}
}
