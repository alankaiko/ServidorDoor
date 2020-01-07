package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfissionalSolicitante.class)
public abstract class ProfissionalSolicitante_ {

	public static volatile SingularAttribute<ProfissionalSolicitante, Long> codigo;
	public static volatile SingularAttribute<ProfissionalSolicitante, String> nome;
	public static volatile SingularAttribute<ProfissionalSolicitante, TISS_Conselho> conselho;
	public static volatile SingularAttribute<ProfissionalSolicitante, String> numnoconselho;

	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";
	public static final String CONSELHO = "conselho";
	public static final String NUMNOCONSELHO = "numnoconselho";

}

