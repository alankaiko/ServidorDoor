package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfissionalExecutante.class)
public abstract class ProfissionalExecutante_ {

	public static volatile SingularAttribute<ProfissionalExecutante, Long> codigo;
	public static volatile SingularAttribute<ProfissionalExecutante, Endereco> endereco;
	public static volatile SingularAttribute<ProfissionalExecutante, String> nome;
	public static volatile SingularAttribute<ProfissionalExecutante, Contato> contato;
	public static volatile SingularAttribute<ProfissionalExecutante, TISS_Conselho> conselho;
	public static volatile SingularAttribute<ProfissionalExecutante, String> numnoconselho;

	public static final String CODIGO = "codigo";
	public static final String ENDERECO = "endereco";
	public static final String NOME = "nome";
	public static final String CONTATO = "contato";
	public static final String CONSELHO = "conselho";
	public static final String NUMNOCONSELHO = "numnoconselho";

}

