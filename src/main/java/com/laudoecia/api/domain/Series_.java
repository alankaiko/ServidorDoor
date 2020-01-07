package com.laudoecia.api.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Series.class)
public abstract class Series_ {

	public static volatile SingularAttribute<Series, Study> study;
	public static volatile SingularAttribute<Series, String> bodypartexamined;
	public static volatile ListAttribute<Series, Instance> instance;
	public static volatile SingularAttribute<Series, Dispositive> dispotive;
	public static volatile SingularAttribute<Series, String> protocolname;
	public static volatile SingularAttribute<Series, Integer> seriesnumber;
	public static volatile SingularAttribute<Series, String> operatorsname;
	public static volatile SingularAttribute<Series, Date> datecreate;
	public static volatile SingularAttribute<Series, String> laterality;
	public static volatile SingularAttribute<Series, String> patientposition;
	public static volatile SingularAttribute<Series, Date> seriesdatetime;
	public static volatile SingularAttribute<Series, Date> datemodify;
	public static volatile SingularAttribute<Series, String> seriesinstanceuid;
	public static volatile SingularAttribute<Series, String> seriesdescription;
	public static volatile SingularAttribute<Series, Long> idseries;

	public static final String STUDY = "study";
	public static final String BODYPARTEXAMINED = "bodypartexamined";
	public static final String INSTANCE = "instance";
	public static final String DISPOTIVE = "dispotive";
	public static final String PROTOCOLNAME = "protocolname";
	public static final String SERIESNUMBER = "seriesnumber";
	public static final String OPERATORSNAME = "operatorsname";
	public static final String DATECREATE = "datecreate";
	public static final String LATERALITY = "laterality";
	public static final String PATIENTPOSITION = "patientposition";
	public static final String SERIESDATETIME = "seriesdatetime";
	public static final String DATEMODIFY = "datemodify";
	public static final String SERIESINSTANCEUID = "seriesinstanceuid";
	public static final String SERIESDESCRIPTION = "seriesdescription";
	public static final String IDSERIES = "idseries";

}

