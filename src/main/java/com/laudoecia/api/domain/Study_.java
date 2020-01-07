package com.laudoecia.api.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Study.class)
public abstract class Study_ {

	public static volatile SingularAttribute<Study, String> additionalpatienthistory;
	public static volatile SingularAttribute<Study, String> admittingdiagnosesdescription;
	public static volatile SingularAttribute<Study, Date> datecreate;
	public static volatile SingularAttribute<Study, Date> datemodify;
	public static volatile SingularAttribute<Study, Long> idstudy;
	public static volatile SingularAttribute<Study, String> accessionnumber;
	public static volatile SingularAttribute<Study, String> studystatusid;
	public static volatile SingularAttribute<Study, Date> studydatetime;
	public static volatile SingularAttribute<Study, Patient> patient;
	public static volatile ListAttribute<Study, Series> series;
	public static volatile SingularAttribute<Study, String> studypriorityid;
	public static volatile SingularAttribute<Study, String> studydescription;
	public static volatile SingularAttribute<Study, String> referringphysicianname;
	public static volatile SingularAttribute<Study, String> studyid;
	public static volatile SingularAttribute<Study, String> studyinstanceuid;

	public static final String ADDITIONALPATIENTHISTORY = "additionalpatienthistory";
	public static final String ADMITTINGDIAGNOSESDESCRIPTION = "admittingdiagnosesdescription";
	public static final String DATECREATE = "datecreate";
	public static final String DATEMODIFY = "datemodify";
	public static final String IDSTUDY = "idstudy";
	public static final String ACCESSIONNUMBER = "accessionnumber";
	public static final String STUDYSTATUSID = "studystatusid";
	public static final String STUDYDATETIME = "studydatetime";
	public static final String PATIENT = "patient";
	public static final String SERIES = "series";
	public static final String STUDYPRIORITYID = "studypriorityid";
	public static final String STUDYDESCRIPTION = "studydescription";
	public static final String REFERRINGPHYSICIANNAME = "referringphysicianname";
	public static final String STUDYID = "studyid";
	public static final String STUDYINSTANCEUID = "studyinstanceuid";

}

