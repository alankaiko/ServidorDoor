package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderType.class)
public abstract class OrderType_ {

	public static volatile SingularAttribute<OrderType, Modality> modality;
	public static volatile SingularAttribute<OrderType, String> name;
	public static volatile SingularAttribute<OrderType, Integer> id;

	public static final String MODALITY = "modality";
	public static final String NAME = "name";
	public static final String ID = "id";

}

