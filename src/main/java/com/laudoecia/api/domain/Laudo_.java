package com.laudoecia.api.domain;

import com.laudoecia.api.domain.enuns.STATUS_LAUDO;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Laudo.class)
public abstract class Laudo_ {

	public static volatile SingularAttribute<Laudo, Long> codigo;
	public static volatile SingularAttribute<Laudo, ParametroDoLaudo> laudosalvo;
	public static volatile SingularAttribute<Laudo, SubcategoriaCid10> cidresultadodoexame;
	public static volatile SingularAttribute<Laudo, ModeloDeLaudoDoProc> modelodelaudo;
	public static volatile SingularAttribute<Laudo, STATUS_LAUDO> status;

	public static final String CODIGO = "codigo";
	public static final String LAUDOSALVO = "laudosalvo";
	public static final String CIDRESULTADODOEXAME = "cidresultadodoexame";
	public static final String MODELODELAUDO = "modelodelaudo";
	public static final String STATUS = "status";

}

