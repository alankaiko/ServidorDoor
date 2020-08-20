package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModeloDeLaudoDoProc.class)
public abstract class ModeloDeLaudoDoProc_ {

	public static volatile SingularAttribute<ModeloDeLaudoDoProc, Long> codigo;
	public static volatile SingularAttribute<ModeloDeLaudoDoProc, ModeloDeLaudo> modelodeLaudo;
	public static volatile SingularAttribute<ModeloDeLaudoDoProc, Integer> prioridade;
	public static volatile SingularAttribute<ModeloDeLaudoDoProc, String> customString;
	public static volatile SingularAttribute<ModeloDeLaudoDoProc, String> descricao;
	public static volatile SingularAttribute<ModeloDeLaudoDoProc, ProcedimentoMedico> procedimentomedico;

	public static final String CODIGO = "codigo";
	public static final String MODELODE_LAUDO = "modelodeLaudo";
	public static final String PRIORIDADE = "prioridade";
	public static final String CUSTOM_STRING = "customString";
	public static final String DESCRICAO = "descricao";
	public static final String PROCEDIMENTOMEDICO = "procedimentomedico";

}

