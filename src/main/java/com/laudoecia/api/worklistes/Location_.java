package com.laudoecia.api.worklistes;

import com.laudoecia.api.domain.Instance;
import com.laudoecia.api.worklistes.Location.ObjectType;
import com.laudoecia.api.worklistes.Location.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Location.class)
public abstract class Location_ {

	public static volatile SingularAttribute<Location, UIDMap> uidMap;
	public static volatile SingularAttribute<Location, Integer> multiReference;
	public static volatile SingularAttribute<Location, Instance> instance;
	public static volatile SingularAttribute<Location, Long> size;
	public static volatile SingularAttribute<Location, String> digest;
	public static volatile SingularAttribute<Location, Date> createdTime;
	public static volatile SingularAttribute<Location, String> storagePath;
	public static volatile SingularAttribute<Location, Long> pk;
	public static volatile SingularAttribute<Location, String> transferSyntaxUID;
	public static volatile SingularAttribute<Location, String> storageID;
	public static volatile SingularAttribute<Location, Status> status;
	public static volatile SingularAttribute<Location, ObjectType> objectType;

	public static final String UID_MAP = "uidMap";
	public static final String MULTI_REFERENCE = "multiReference";
	public static final String INSTANCE = "instance";
	public static final String SIZE = "size";
	public static final String DIGEST = "digest";
	public static final String CREATED_TIME = "createdTime";
	public static final String STORAGE_PATH = "storagePath";
	public static final String PK = "pk";
	public static final String TRANSFER_SYNTAX_UI_D = "transferSyntaxUID";
	public static final String STORAGE_ID = "storageID";
	public static final String STATUS = "status";
	public static final String OBJECT_TYPE = "objectType";

}

