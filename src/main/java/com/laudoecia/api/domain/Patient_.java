package com.laudoecia.api.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Patient.class)
public abstract class Patient_ {

	public static volatile SingularAttribute<Patient, Long> idpatient;
	public static volatile SingularAttribute<Patient, String> patientname;
	public static volatile SingularAttribute<Patient, Date> birthday;
	public static volatile SingularAttribute<Patient, String> patientid;
	public static volatile SingularAttribute<Patient, String> patientsex;
	public static volatile SingularAttribute<Patient, String> patientage;
	public static volatile ListAttribute<Patient, Study> studyes;
	public static volatile SingularAttribute<Patient, Date> datecreate;
	public static volatile SingularAttribute<Patient, Date> datemodify;

	public static final String IDPATIENT = "idpatient";
	public static final String PATIENTNAME = "patientname";
	public static final String BIRTHDAY = "birthday";
	public static final String PATIENTID = "patientid";
	public static final String PATIENTSEX = "patientsex";
	public static final String PATIENTAGE = "patientage";
	public static final String STUDYES = "studyes";
	public static final String DATECREATE = "datecreate";
	public static final String DATEMODIFY = "datemodify";

}

