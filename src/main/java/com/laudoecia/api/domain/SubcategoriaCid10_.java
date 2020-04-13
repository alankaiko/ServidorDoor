package com.laudoecia.api.domain;

import com.laudoecia.api.domain.enuns.CID_CAUSA_OBITO;
import com.laudoecia.api.domain.enuns.CID_CLASSIFICACAO;
import com.laudoecia.api.domain.enuns.RESTRICAO_SEXO;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SubcategoriaCid10.class)
public abstract class SubcategoriaCid10_ {

	public static volatile SingularAttribute<SubcategoriaCid10, String> nome50;
	public static volatile SingularAttribute<SubcategoriaCid10, CategoriaCID10> categoriacid10;
	public static volatile SingularAttribute<SubcategoriaCid10, Long> codigo;
	public static volatile SingularAttribute<SubcategoriaCid10, String> codigotexto;
	public static volatile SingularAttribute<SubcategoriaCid10, CID_CAUSA_OBITO> causaobito;
	public static volatile SingularAttribute<SubcategoriaCid10, String> nome;
	public static volatile SingularAttribute<SubcategoriaCid10, CID_CLASSIFICACAO> classificacao;
	public static volatile SingularAttribute<SubcategoriaCid10, String> excluidos;
	public static volatile SingularAttribute<SubcategoriaCid10, RESTRICAO_SEXO> restrsexo;
	public static volatile SingularAttribute<SubcategoriaCid10, String> referencia;

	public static final String NOME50 = "nome50";
	public static final String CATEGORIACID10 = "categoriacid10";
	public static final String CODIGO = "codigo";
	public static final String CODIGOTEXTO = "codigotexto";
	public static final String CAUSAOBITO = "causaobito";
	public static final String NOME = "nome";
	public static final String CLASSIFICACAO = "classificacao";
	public static final String EXCLUIDOS = "excluidos";
	public static final String RESTRSEXO = "restrsexo";
	public static final String REFERENCIA = "referencia";

}

