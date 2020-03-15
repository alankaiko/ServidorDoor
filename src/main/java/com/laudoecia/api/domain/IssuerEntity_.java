package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.dcm4che3.data.Issuer;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IssuerEntity.class)
public abstract class IssuerEntity_ {

	public static volatile SingularAttribute<IssuerEntity, Long> codigo;
	public static volatile SingularAttribute<IssuerEntity, String> universalentityid;
	public static volatile SingularAttribute<IssuerEntity, String> universalentityidtype;
	public static volatile SingularAttribute<IssuerEntity, String> localnamespaceentityid;
	public static volatile SingularAttribute<IssuerEntity, Issuer> issuer;

	public static final String CODIGO = "codigo";
	public static final String UNIVERSALENTITYID = "universalentityid";
	public static final String UNIVERSALENTITYIDTYPE = "universalentityidtype";
	public static final String LOCALNAMESPACEENTITYID = "localnamespaceentityid";
	public static final String ISSUER = "issuer";

}

