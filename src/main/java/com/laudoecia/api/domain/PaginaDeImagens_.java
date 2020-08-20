package com.laudoecia.api.domain;

import com.laudoecia.api.domain.enuns.LAYOUT_IMG;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PaginaDeImagens.class)
public abstract class PaginaDeImagens_ {

	public static volatile SingularAttribute<PaginaDeImagens, LAYOUT_IMG> layout;
	public static volatile SingularAttribute<PaginaDeImagens, ProcedimentoAtendimento> procedimentoatendimento;
	public static volatile SingularAttribute<PaginaDeImagens, Long> codigo;
	public static volatile ListAttribute<PaginaDeImagens, ImagemImpressa> imagens;

	public static final String LAYOUT = "layout";
	public static final String PROCEDIMENTOATENDIMENTO = "procedimentoatendimento";
	public static final String CODIGO = "codigo";
	public static final String IMAGENS = "imagens";

}

