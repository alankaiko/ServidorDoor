package com.laudoecia.api.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.dcm4che3.data.Attributes;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Patient.class)
public abstract class Patient_ {

	public static volatile SingularAttribute<Patient, Long> idpatient;
	public static volatile SingularAttribute<Patient, String> patientname;
	public static volatile SingularAttribute<Patient, LocalDate> birthday;
	public static volatile SingularAttribute<Patient, Endereco> endereco;
	public static volatile SingularAttribute<Patient, String> patientid;
	public static volatile SingularAttribute<Patient, String> patientsex;
	public static volatile SingularAttribute<Patient, String> patientage;
	public static volatile ListAttribute<Patient, Study> studyes;
	public static volatile SingularAttribute<Patient, LocalDate> datecreate;
	public static volatile SingularAttribute<Patient, Attributes> atributo;
	public static volatile SingularAttribute<Patient, Contato> contato;
	public static volatile SingularAttribute<Patient, LocalDate> datemodify;

	public static final String IDPATIENT = "idpatient";
	public static final String PATIENTNAME = "patientname";
	public static final String BIRTHDAY = "birthday";
	public static final String ENDERECO = "endereco";
	public static final String PATIENTID = "patientid";
	public static final String PATIENTSEX = "patientsex";
	public static final String PATIENTAGE = "patientage";
	public static final String STUDYES = "studyes";
	public static final String DATECREATE = "datecreate";
	public static final String ATRIBUTO = "atributo";
	public static final String CONTATO = "contato";
	public static final String DATEMODIFY = "datemodify";

}

