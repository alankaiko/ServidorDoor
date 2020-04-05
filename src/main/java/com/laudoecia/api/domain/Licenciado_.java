package com.laudoecia.api.domain;

import com.laudoecia.api.domain.enuns.TIPO_LICENCA;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Licenciado.class)
public abstract class Licenciado_ {

	public static volatile SingularAttribute<Licenciado, Long> codigo;
	public static volatile SingularAttribute<Licenciado, String> telefone1;
	public static volatile SingularAttribute<Licenciado, Endereco> endereco;
	public static volatile SingularAttribute<Licenciado, String> cnpj;
	public static volatile SingularAttribute<Licenciado, String> telefone2;
	public static volatile SingularAttribute<Licenciado, Long> version;
	public static volatile SingularAttribute<Licenciado, String> razaosocial;
	public static volatile SingularAttribute<Licenciado, String> site;
	public static volatile SingularAttribute<Licenciado, String> serial;
	public static volatile SingularAttribute<Licenciado, String> cnes;
	public static volatile SingularAttribute<Licenciado, String> cpf;
	public static volatile SingularAttribute<Licenciado, String> licenciadopara;
	public static volatile SingularAttribute<Licenciado, TIPO_LICENCA> tipodelicenca;
	public static volatile SingularAttribute<Licenciado, String> email;
	public static volatile SingularAttribute<Licenciado, Integer> qtdeacessos;

	public static final String CODIGO = "codigo";
	public static final String TELEFONE1 = "telefone1";
	public static final String ENDERECO = "endereco";
	public static final String CNPJ = "cnpj";
	public static final String TELEFONE2 = "telefone2";
	public static final String VERSION = "version";
	public static final String RAZAOSOCIAL = "razaosocial";
	public static final String SITE = "site";
	public static final String SERIAL = "serial";
	public static final String CNES = "cnes";
	public static final String CPF = "cpf";
	public static final String LICENCIADOPARA = "licenciadopara";
	public static final String TIPODELICENCA = "tipodelicenca";
	public static final String EMAIL = "email";
	public static final String QTDEACESSOS = "qtdeacessos";

}

