package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PatientID.class)
public abstract class PatientID_ {

	public static volatile SingularAttribute<PatientID, String> identifiertypecode;
	public static volatile SingularAttribute<PatientID, Long> codigo;
	public static volatile SingularAttribute<PatientID, String> patid;
	public static volatile SingularAttribute<PatientID, Patient> patient;
	public static volatile SingularAttribute<PatientID, Long> version;
	public static volatile SingularAttribute<PatientID, Issuer> issuer;

	public static final String IDENTIFIERTYPECODE = "identifiertypecode";
	public static final String CODIGO = "codigo";
	public static final String PATID = "patid";
	public static final String PATIENT = "patient";
	public static final String VERSION = "version";
	public static final String ISSUER = "issuer";

}

