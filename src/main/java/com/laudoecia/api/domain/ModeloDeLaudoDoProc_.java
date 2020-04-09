package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModeloDeLaudoDoProc.class)
public abstract class ModeloDeLaudoDoProc_ {

	public static volatile SingularAttribute<ModeloDeLaudoDoProc, Long> codigo;
	public static volatile SingularAttribute<ModeloDeLaudoDoProc, Integer> prioridade;
	public static volatile SingularAttribute<ModeloDeLaudoDoProc, String> customstring;
	public static volatile SingularAttribute<ModeloDeLaudoDoProc, ProcedimentoMedico> procedimentomedico;
	public static volatile SingularAttribute<ModeloDeLaudoDoProc, String> descricao;

	public static final String CODIGO = "codigo";
	public static final String PRIORIDADE = "prioridade";
	public static final String CUSTOMSTRING = "customstring";
	public static final String PROCEDIMENTOMEDICO = "procedimentomedico";
	public static final String DESCRICAO = "descricao";

}

