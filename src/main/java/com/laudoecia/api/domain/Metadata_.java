package com.laudoecia.api.domain;

import com.laudoecia.api.domain.Metadata.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Metadata.class)
public abstract class Metadata_ {

	public static volatile SingularAttribute<Metadata, Long> size;
	public static volatile SingularAttribute<Metadata, String> digest;
	public static volatile SingularAttribute<Metadata, Date> createdTime;
	public static volatile SingularAttribute<Metadata, String> storagePath;
	public static volatile SingularAttribute<Metadata, Long> pk;
	public static volatile SingularAttribute<Metadata, String> storageID;
	public static volatile SingularAttribute<Metadata, Status> status;

	public static final String SIZE = "size";
	public static final String DIGEST = "digest";
	public static final String CREATED_TIME = "createdTime";
	public static final String STORAGE_PATH = "storagePath";
	public static final String PK = "pk";
	public static final String STORAGE_ID = "storageID";
	public static final String STATUS = "status";

}

