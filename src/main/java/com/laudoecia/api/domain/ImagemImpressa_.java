package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ImagemImpressa.class)
public abstract class ImagemImpressa_ {

	public static volatile SingularAttribute<ImagemImpressa, PaginaDeImagens> pagina;
	public static volatile SingularAttribute<ImagemImpressa, Integer> codigo;
	public static volatile SingularAttribute<ImagemImpressa, Integer> indice;
	public static volatile SingularAttribute<ImagemImpressa, Imagem> imagem;
	public static volatile SingularAttribute<ImagemImpressa, String> caminhoimagemjpeg;

	public static final String PAGINA = "pagina";
	public static final String CODIGO = "codigo";
	public static final String INDICE = "indice";
	public static final String IMAGEM = "imagem";
	public static final String CAMINHOIMAGEMJPEG = "caminhoimagemjpeg";

}

