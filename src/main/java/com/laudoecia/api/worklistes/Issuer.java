package com.laudoecia.api.worklistes;

import java.io.Serializable;

import org.dcm4che3.data.Attributes;

public class Issuer implements Serializable {

	private static final long serialVersionUID = -5050458184841995777L;

	public static final String FIND_BY_ENTITY_ID = "Issuer.findByEntityID";

	public static final String FIND_BY_ENTITY_UID = "Issuer.findByEntityUID";

	public static final String FIND_BY_ENTITY_ID_OR_UID = "Issuer.findByEntityIDorUID";

	private long pk;

	private String entityID;

	private String entityUID;

	private String entityUIDType;

	public Issuer() {
	}

	public Issuer(String entityID, String entityUID, String entityUIDType) {
		this.entityID = entityID;
		this.entityUID = entityUID;
		this.entityUIDType = entityUIDType;
	}

	public long getPk() {
		return pk;
	}

	public String getLocalNamespaceEntityID() {
		return entityID;
	}

	public void setLocalNamespaceEntityID(String entityId) {
		this.entityID = entityId;
	}

	public String getUniversalEntityID() {
		return entityUID;
	}

	public void setUniversalEntityID(String entityUid) {
		this.entityUID = entityUid;
	}

	public String getUniversalEntityIDType() {
		return entityUIDType;
	}

	public void setUniversalEntityIDType(String entityUidType) {
		this.entityUIDType = entityUidType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("Issuer[pk=").append(pk);
		sb.append(", id=");
		if (entityID != null)
			sb.append(entityID);
		sb.append(", uid=");
		if (entityUID != null)
			sb.append(entityUID);
		sb.append(", type=");
		if (entityUIDType != null)
			sb.append(entityUIDType);
		sb.append("]");
		return sb.toString();
	}

	public static Issuer issuerOfPatientIDOf(Attributes keys) {
		// TODO Auto-generated method stub
		return null;
	}

}
