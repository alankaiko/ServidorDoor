package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Modality.class)
public abstract class Modality_ {

	public static volatile SingularAttribute<Modality, Long> codigo;
	public static volatile SingularAttribute<Modality, Integer> port;
	public static volatile SingularAttribute<Modality, String> ip;
	public static volatile SingularAttribute<Modality, String> name;
	public static volatile SingularAttribute<Modality, String> description;
	public static volatile SingularAttribute<Modality, Integer> timeout;

	public static final String CODIGO = "codigo";
	public static final String PORT = "port";
	public static final String IP = "ip";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String TIMEOUT = "timeout";

}

