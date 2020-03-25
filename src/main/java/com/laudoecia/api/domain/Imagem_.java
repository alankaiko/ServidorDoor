package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Imagem.class)
public abstract class Imagem_ {

	public static volatile SingularAttribute<Imagem, String> extensao;
	public static volatile SingularAttribute<Imagem, ProcedimentoAtendimento> procedimentoatendimento;
	public static volatile SingularAttribute<Imagem, String> nomeimagem;
	public static volatile SingularAttribute<Imagem, Long> codigo;
	public static volatile SingularAttribute<Imagem, String> caminho;

	public static final String EXTENSAO = "extensao";
	public static final String PROCEDIMENTOATENDIMENTO = "procedimentoatendimento";
	public static final String NOMEIMAGEM = "nomeimagem";
	public static final String CODIGO = "codigo";
	public static final String CAMINHO = "caminho";

}

