package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PatientID.class)
public abstract class PatientID_ {

	public static volatile SingularAttribute<PatientID, String> identifiertypecode;
	public static volatile SingularAttribute<PatientID, Long> codigo;
	public static volatile SingularAttribute<PatientID, String> id;
	public static volatile SingularAttribute<PatientID, Long> version;
	public static volatile SingularAttribute<PatientID, IssuerEntity> issuer;

	public static final String IDENTIFIERTYPECODE = "identifiertypecode";
	public static final String CODIGO = "codigo";
	public static final String ID = "id";
	public static final String VERSION = "version";
	public static final String ISSUER = "issuer";

}

