package com.laudoecia.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dcm4che3.data.Issuer;

@Entity
@Table(name = "issuerentity")
public class IssuerEntity {
	private Long codigo;
	private String localnamespaceentityid;
	private String universalentityid;
	private String universalentityidtype;

	protected IssuerEntity() {
	} // for JPA

	public IssuerEntity(Issuer issuer) {
		setIssuer(issuer);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getLocalnamespaceentityid() {
		return localnamespaceentityid;
	}

	public void setLocalnamespaceentityid(String localnamespaceentityid) {
		this.localnamespaceentityid = localnamespaceentityid;
	}

	public String getUniversalentityid() {
		return universalentityid;
	}

	public void setUniversalentityid(String universalentityid) {
		this.universalentityid = universalentityid;
	}

	public String getUniversalentityidtype() {
		return universalentityidtype;
	}

	public void setUniversalentityidtype(String universalentityidtype) {
		this.universalentityidtype = universalentityidtype;
	}

	public Issuer getIssuer() {
		return new Issuer(localnamespaceentityid, universalentityid, universalentityidtype);
	}

	public void setIssuer(Issuer issuer) {
		this.localnamespaceentityid = issuer.getLocalNamespaceEntityID();
		this.universalentityid = issuer.getUniversalEntityID();
		this.universalentityidtype = issuer.getUniversalEntityIDType();
	}

	@Override
	public String toString() {
		return getIssuer().toString();
	}

	public void merge(Issuer other) {
		Issuer issuer = getIssuer();
		issuer.merge(other);
		setIssuer(issuer);
	}
}
