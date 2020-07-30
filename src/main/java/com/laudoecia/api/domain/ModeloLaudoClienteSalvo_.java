package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModeloLaudoClienteSalvo.class)
public abstract class ModeloLaudoClienteSalvo_ {

	public static volatile ListAttribute<ModeloLaudoClienteSalvo, PaginaImagens> paginas;
	public static volatile SingularAttribute<ModeloLaudoClienteSalvo, Long> codigo;
	public static volatile SingularAttribute<ModeloLaudoClienteSalvo, Integer> prioridade;
	public static volatile SingularAttribute<ModeloLaudoClienteSalvo, String> customstring;
	public static volatile SingularAttribute<ModeloLaudoClienteSalvo, ProcedimentoMedico> procedimentomedico;
	public static volatile SingularAttribute<ModeloLaudoClienteSalvo, String> descricao;

	public static final String PAGINAS = "paginas";
	public static final String CODIGO = "codigo";
	public static final String PRIORIDADE = "prioridade";
	public static final String CUSTOMSTRING = "customstring";
	public static final String PROCEDIMENTOMEDICO = "procedimentomedico";
	public static final String DESCRICAO = "descricao";

}

