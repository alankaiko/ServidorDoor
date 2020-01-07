package com.laudoecia.api.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dispositive.class)
public abstract class Dispositive_ {

	public static volatile SingularAttribute<Dispositive, String> institutionname;
	public static volatile SingularAttribute<Dispositive, String> manufacturermodelname;
	public static volatile SingularAttribute<Dispositive, String> modality;
	public static volatile SingularAttribute<Dispositive, String> softwareversion;
	public static volatile SingularAttribute<Dispositive, Long> iddispositive;
	public static volatile SingularAttribute<Dispositive, String> stationname;
	public static volatile SingularAttribute<Dispositive, String> institutionaddress;
	public static volatile SingularAttribute<Dispositive, String> manufacturer;
	public static volatile SingularAttribute<Dispositive, Date> datemodify;
	public static volatile SingularAttribute<Dispositive, Series> series;
	public static volatile SingularAttribute<Dispositive, String> institutionaldepartmentname;
	public static volatile SingularAttribute<Dispositive, String> deviceserialnumber;
	public static volatile SingularAttribute<Dispositive, Date> datecreation;
	public static volatile SingularAttribute<Dispositive, String> conversiontype;

	public static final String INSTITUTIONNAME = "institutionname";
	public static final String MANUFACTURERMODELNAME = "manufacturermodelname";
	public static final String MODALITY = "modality";
	public static final String SOFTWAREVERSION = "softwareversion";
	public static final String IDDISPOSITIVE = "iddispositive";
	public static final String STATIONNAME = "stationname";
	public static final String INSTITUTIONADDRESS = "institutionaddress";
	public static final String MANUFACTURER = "manufacturer";
	public static final String DATEMODIFY = "datemodify";
	public static final String SERIES = "series";
	public static final String INSTITUTIONALDEPARTMENTNAME = "institutionaldepartmentname";
	public static final String DEVICESERIALNUMBER = "deviceserialnumber";
	public static final String DATECREATION = "datecreation";
	public static final String CONVERSIONTYPE = "conversiontype";

}

