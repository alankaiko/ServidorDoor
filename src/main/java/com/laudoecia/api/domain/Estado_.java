package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estado.class)
public abstract class Estado_ {

	public static volatile SingularAttribute<Estado, String> uf;
	public static volatile SingularAttribute<Estado, Long> codigo;
	public static volatile SingularAttribute<Estado, String> descricao;

	public static final String UF = "uf";
	public static final String CODIGO = "codigo";
	public static final String DESCRICAO = "descricao";

}

