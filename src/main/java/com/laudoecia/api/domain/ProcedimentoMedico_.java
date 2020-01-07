package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProcedimentoMedico.class)
public abstract class ProcedimentoMedico_ {

	public static volatile SingularAttribute<ProcedimentoMedico, Integer> diasparaentregadolaudo;
	public static volatile SingularAttribute<ProcedimentoMedico, Long> codigo;
	public static volatile SingularAttribute<ProcedimentoMedico, Integer> margemtop;
	public static volatile SingularAttribute<ProcedimentoMedico, String> imagem2;
	public static volatile SingularAttribute<ProcedimentoMedico, RESTRICAO_SEXO> restricaosexo;
	public static volatile SingularAttribute<ProcedimentoMedico, GrupoProcedimento> grupo;
	public static volatile SingularAttribute<ProcedimentoMedico, String> nome;
	public static volatile SingularAttribute<ProcedimentoMedico, String> imagem1;
	public static volatile SingularAttribute<ProcedimentoMedico, Boolean> laudomodelo;
	public static volatile SingularAttribute<ProcedimentoMedico, Integer> margembottom;

	public static final String DIASPARAENTREGADOLAUDO = "diasparaentregadolaudo";
	public static final String CODIGO = "codigo";
	public static final String MARGEMTOP = "margemtop";
	public static final String IMAGEM2 = "imagem2";
	public static final String RESTRICAOSEXO = "restricaosexo";
	public static final String GRUPO = "grupo";
	public static final String NOME = "nome";
	public static final String IMAGEM1 = "imagem1";
	public static final String LAUDOMODELO = "laudomodelo";
	public static final String MARGEMBOTTOM = "margembottom";

}

