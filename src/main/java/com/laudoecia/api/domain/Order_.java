package com.laudoecia.api.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, String> orderUuid;
	public static volatile SingularAttribute<Order, String> result;
	public static volatile SingularAttribute<Order, OrderType> orderType;
	public static volatile SingularAttribute<Order, String> creator;
	public static volatile SingularAttribute<Order, Date> dateCreated;
	public static volatile SingularAttribute<Order, String> orderNumber;
	public static volatile SingularAttribute<Order, String> modifier;
	public static volatile SingularAttribute<Order, Date> dateModified;
	public static volatile SingularAttribute<Order, String> comment;
	public static volatile SingularAttribute<Order, Integer> id;
	public static volatile SingularAttribute<Order, String> testUuid;
	public static volatile SingularAttribute<Order, String> testName;

	public static final String ORDER_UUID = "orderUuid";
	public static final String RESULT = "result";
	public static final String ORDER_TYPE = "orderType";
	public static final String CREATOR = "creator";
	public static final String DATE_CREATED = "dateCreated";
	public static final String ORDER_NUMBER = "orderNumber";
	public static final String MODIFIER = "modifier";
	public static final String DATE_MODIFIED = "dateModified";
	public static final String COMMENT = "comment";
	public static final String ID = "id";
	public static final String TEST_UUID = "testUuid";
	public static final String TEST_NAME = "testName";

}

