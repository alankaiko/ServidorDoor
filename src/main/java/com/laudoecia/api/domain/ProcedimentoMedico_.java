package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProcedimentoMedico.class)
public abstract class ProcedimentoMedico_ {

	public static volatile SingularAttribute<ProcedimentoMedico, Long> codigo;
	public static volatile SingularAttribute<ProcedimentoMedico, EnumRestricaoSexo> restricaosexo;
	public static volatile SingularAttribute<ProcedimentoMedico, GrupoProcedimento> grupo;
	public static volatile SingularAttribute<ProcedimentoMedico, String> nome;
	public static volatile SingularAttribute<ProcedimentoMedico, String> caminhoimagem2;
	public static volatile SingularAttribute<ProcedimentoMedico, String> caminhoimagem1;
	public static volatile SingularAttribute<ProcedimentoMedico, Boolean> laudomodelo;

	public static final String CODIGO = "codigo";
	public static final String RESTRICAOSEXO = "restricaosexo";
	public static final String GRUPO = "grupo";
	public static final String NOME = "nome";
	public static final String CAMINHOIMAGEM2 = "caminhoimagem2";
	public static final String CAMINHOIMAGEM1 = "caminhoimagem1";
	public static final String LAUDOMODELO = "laudomodelo";

}

