package com.laudoecia.api.worklistes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.dcm4che3.data.Issuer;

@NamedQueries({
		@NamedQuery(name = IssuerEntity.FIND_BY_ENTITY_ID, query = "select issuer from IssuerEntity issuer where issuer.localNamespaceEntityID = ?1"),
		@NamedQuery(name = IssuerEntity.FIND_BY_ENTITY_UID, query = "select issuer from IssuerEntity issuer "
				+ "where issuer.universalEntityID = ?1 and issuer.universalEntityIDType = ?2"),
		@NamedQuery(name = IssuerEntity.FIND_BY_ENTITY_ID_OR_UID, query = "select issuer from IssuerEntity issuer where issuer.localNamespaceEntityID = ?1 "
				+ "or (issuer.universalEntityID = ?2 and issuer.universalEntityIDType = ?3)") })
@Entity
@Table(name = "issuer", uniqueConstraints = { @UniqueConstraint(columnNames = "entity_id"),
		@UniqueConstraint(columnNames = { "entity_uid", "entity_uid_type" }) })
public class IssuerEntity {

	public static final String FIND_BY_ENTITY_ID = "IssuerEntity.findByEntityID";
	public static final String FIND_BY_ENTITY_UID = "IssuerEntity.findByEntityUID";
	public static final String FIND_BY_ENTITY_ID_OR_UID = "IssuerEntity.findByEntityIDorUID";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk")
	private long pk;

	@Column(name = "entity_id")
	private String localNamespaceEntityID;

	@Column(name = "entity_uid")
	private String universalEntityID;

	@Column(name = "entity_uid_type")
	private String universalEntityIDType;

	protected IssuerEntity() {
	} // for JPA

	public IssuerEntity(Issuer issuer) {
		setIssuer(issuer);
	}

	public long getPk() {
		return pk;
	}

	public Issuer getIssuer() {
		return new Issuer(localNamespaceEntityID, universalEntityID, universalEntityIDType);
	}

	public void setIssuer(Issuer issuer) {
		this.localNamespaceEntityID = issuer.getLocalNamespaceEntityID();
		this.universalEntityID = issuer.getUniversalEntityID();
		this.universalEntityIDType = issuer.getUniversalEntityIDType();
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
