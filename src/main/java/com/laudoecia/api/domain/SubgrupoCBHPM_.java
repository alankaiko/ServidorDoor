package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SubgrupoCBHPM.class)
public abstract class SubgrupoCBHPM_ {

	public static volatile SingularAttribute<SubgrupoCBHPM, Long> codigo;
	public static volatile SingularAttribute<SubgrupoCBHPM, String> subgrupo;
	public static volatile SingularAttribute<SubgrupoCBHPM, GrupoCBHPM> grupo;
	public static volatile SingularAttribute<SubgrupoCBHPM, String> sku;

	public static final String CODIGO = "codigo";
	public static final String SUBGRUPO = "subgrupo";
	public static final String GRUPO = "grupo";
	public static final String SKU = "sku";

}

