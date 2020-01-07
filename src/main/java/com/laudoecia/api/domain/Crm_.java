package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Crm.class)
public abstract class Crm_ {

	public static volatile SingularAttribute<Crm, UF> uf;
	public static volatile SingularAttribute<Crm, Long> codigo;
	public static volatile ListAttribute<Crm, EspecialidadeMedica> especialidades;
	public static volatile SingularAttribute<Crm, String> nome;
	public static volatile SingularAttribute<Crm, String> crm;

	public static final String UF = "uf";
	public static final String CODIGO = "codigo";
	public static final String ESPECIALIDADES = "especialidades";
	public static final String NOME = "nome";
	public static final String CRM = "crm";

}

