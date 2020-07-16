package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CodeEntity.class)
public abstract class CodeEntity_ {

	public static volatile SingularAttribute<CodeEntity, String> codingSchemeVersion;
	public static volatile SingularAttribute<CodeEntity, Long> pk;
	public static volatile SingularAttribute<CodeEntity, String> codingSchemeDesignator;
	public static volatile SingularAttribute<CodeEntity, String> codeValue;
	public static volatile SingularAttribute<CodeEntity, String> codeMeaning;

	public static final String CODING_SCHEME_VERSION = "codingSchemeVersion";
	public static final String PK = "pk";
	public static final String CODING_SCHEME_DESIGNATOR = "codingSchemeDesignator";
	public static final String CODE_VALUE = "codeValue";
	public static final String CODE_MEANING = "codeMeaning";

}

