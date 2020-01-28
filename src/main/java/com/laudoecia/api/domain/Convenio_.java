package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Convenio.class)
public abstract class Convenio_ {

	public static volatile SingularAttribute<Convenio, String> observacoes;
	public static volatile SingularAttribute<Convenio, Long> codigo;
	public static volatile SingularAttribute<Convenio, String> telefone;
	public static volatile SingularAttribute<Convenio, Boolean> ativo;
	public static volatile SingularAttribute<Convenio, Integer> numcopiasdolaudo;
	public static volatile SingularAttribute<Convenio, String> nomedocontato;
	public static volatile SingularAttribute<Convenio, Endereco> endereco;
	public static volatile SingularAttribute<Convenio, String> nome;
	public static volatile SingularAttribute<Convenio, String> fax;
	public static volatile SingularAttribute<Convenio, String> email;

	public static final String OBSERVACOES = "observacoes";
	public static final String CODIGO = "codigo";
	public static final String TELEFONE = "telefone";
	public static final String ATIVO = "ativo";
	public static final String NUMCOPIASDOLAUDO = "numcopiasdolaudo";
	public static final String NOMEDOCONTATO = "nomedocontato";
	public static final String ENDERECO = "endereco";
	public static final String NOME = "nome";
	public static final String FAX = "fax";
	public static final String EMAIL = "email";

}

