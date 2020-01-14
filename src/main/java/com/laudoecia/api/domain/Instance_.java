package com.laudoecia.api.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Instance.class)
public abstract class Instance_ {

	public static volatile SingularAttribute<Instance, String> windowcenter;
	public static volatile SingularAttribute<Instance, Long> idinstance;
	public static volatile SingularAttribute<Instance, String> imagetype;
	public static volatile SingularAttribute<Instance, Integer> xraytubecurrent;
	public static volatile SingularAttribute<Instance, Float> slicethickness;
	public static volatile SingularAttribute<Instance, String> imageposition;
	public static volatile SingularAttribute<Instance, String> kvp;
	public static volatile SingularAttribute<Instance, String> sopclassuid;
	public static volatile SingularAttribute<Instance, String> sopinstanceuid;
	public static volatile SingularAttribute<Instance, String> transfersyntaxuid;
	public static volatile SingularAttribute<Instance, String> mediastoragesopinstanceuid;
	public static volatile SingularAttribute<Instance, Date> datecreate;
	public static volatile SingularAttribute<Instance, String> patientorientation;
	public static volatile SingularAttribute<Instance, Integer> instancenumber;
	public static volatile SingularAttribute<Instance, Date> datemodify;
	public static volatile SingularAttribute<Instance, Integer> exposuretime;
	public static volatile SingularAttribute<Instance, Date> contentdatetime;
	public static volatile SingularAttribute<Instance, Float> slicelocation;
	public static volatile SingularAttribute<Instance, Series> series;
	public static volatile SingularAttribute<Instance, String> windowwidth;
	public static volatile SingularAttribute<Instance, Date> acquisitiondatetime;
	public static volatile SingularAttribute<Instance, Tagimagem> tagimagem;
	public static volatile SingularAttribute<Instance, Float> pixelspacing;
	public static volatile SingularAttribute<Instance, String> imageorientation;

	public static final String WINDOWCENTER = "windowcenter";
	public static final String IDINSTANCE = "idinstance";
	public static final String IMAGETYPE = "imagetype";
	public static final String XRAYTUBECURRENT = "xraytubecurrent";
	public static final String SLICETHICKNESS = "slicethickness";
	public static final String IMAGEPOSITION = "imageposition";
	public static final String KVP = "kvp";
	public static final String SOPCLASSUID = "sopclassuid";
	public static final String SOPINSTANCEUID = "sopinstanceuid";
	public static final String TRANSFERSYNTAXUID = "transfersyntaxuid";
	public static final String MEDIASTORAGESOPINSTANCEUID = "mediastoragesopinstanceuid";
	public static final String DATECREATE = "datecreate";
	public static final String PATIENTORIENTATION = "patientorientation";
	public static final String INSTANCENUMBER = "instancenumber";
	public static final String DATEMODIFY = "datemodify";
	public static final String EXPOSURETIME = "exposuretime";
	public static final String CONTENTDATETIME = "contentdatetime";
	public static final String SLICELOCATION = "slicelocation";
	public static final String SERIES = "series";
	public static final String WINDOWWIDTH = "windowwidth";
	public static final String ACQUISITIONDATETIME = "acquisitiondatetime";
	public static final String TAGIMAGEM = "tagimagem";
	public static final String PIXELSPACING = "pixelspacing";
	public static final String IMAGEORIENTATION = "imageorientation";

}

