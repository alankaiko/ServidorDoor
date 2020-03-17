package com.laudoecia.api.worklistes;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subscription.class)
public abstract class Subscription_ {

	public static volatile SingularAttribute<Subscription, Boolean> deletionLock;
	public static volatile SingularAttribute<Subscription, String> subscriberAET;
	public static volatile SingularAttribute<Subscription, UPS> ups;
	public static volatile SingularAttribute<Subscription, Long> pk;

	public static final String DELETION_LOCK = "deletionLock";
	public static final String SUBSCRIBER_AE_T = "subscriberAET";
	public static final String UPS = "ups";
	public static final String PK = "pk";

}

