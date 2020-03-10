package com.laudoecia.api.domain;

import com.laudoecia.api.domain.MPPS.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MPPS.class)
public abstract class MPPS_ {

	public static volatile SingularAttribute<MPPS, Long> codigo;
	public static volatile SingularAttribute<MPPS, Code> discontinuationreasoncode;
	public static volatile SingularAttribute<MPPS, String> modality;
	public static volatile SingularAttribute<MPPS, String> performedstationaet;
	public static volatile SingularAttribute<MPPS, String> sopinstanceuid;
	public static volatile SingularAttribute<MPPS, Long> version;
	public static volatile SingularAttribute<MPPS, Date> createdtime;
	public static volatile SingularAttribute<MPPS, Date> updatedtime;
	public static volatile SingularAttribute<MPPS, String> accessionnumber;
	public static volatile SingularAttribute<MPPS, Patient> patient;
	public static volatile SingularAttribute<MPPS, AttributesBlob> attributesblob;
	public static volatile SingularAttribute<MPPS, Date> startdatetime;
	public static volatile SingularAttribute<MPPS, Status> status;

	public static final String CODIGO = "codigo";
	public static final String DISCONTINUATIONREASONCODE = "discontinuationreasoncode";
	public static final String MODALITY = "modality";
	public static final String PERFORMEDSTATIONAET = "performedstationaet";
	public static final String SOPINSTANCEUID = "sopinstanceuid";
	public static final String VERSION = "version";
	public static final String CREATEDTIME = "createdtime";
	public static final String UPDATEDTIME = "updatedtime";
	public static final String ACCESSIONNUMBER = "accessionnumber";
	public static final String PATIENT = "patient";
	public static final String ATTRIBUTESBLOB = "attributesblob";
	public static final String STARTDATETIME = "startdatetime";
	public static final String STATUS = "status";

}

