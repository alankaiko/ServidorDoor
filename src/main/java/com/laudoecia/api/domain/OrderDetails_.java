package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderDetails.class)
public abstract class OrderDetails_ {

	public static volatile SingularAttribute<OrderDetails, String> hl7Request;
	public static volatile SingularAttribute<OrderDetails, Integer> id;
	public static volatile SingularAttribute<OrderDetails, String> hl7Response;
	public static volatile SingularAttribute<OrderDetails, Order> order;

	public static final String HL7_REQUEST = "hl7Request";
	public static final String ID = "id";
	public static final String HL7_RESPONSE = "hl7Response";
	public static final String ORDER = "order";

}

