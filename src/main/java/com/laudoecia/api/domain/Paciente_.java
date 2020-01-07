package com.laudoecia.api.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Paciente.class)
public abstract class Paciente_ {

	public static volatile SingularAttribute<Paciente, Long> codigo;
	public static volatile SingularAttribute<Paciente, Date> datanasc;
	public static volatile SingularAttribute<Paciente, Endereco> endereco;
	public static volatile SingularAttribute<Paciente, String> rg;
	public static volatile SingularAttribute<Paciente, String> cpf;
	public static volatile SingularAttribute<Paciente, Date> datacad;
	public static volatile SingularAttribute<Paciente, String> nome;
	public static volatile SingularAttribute<Paciente, EnumSexo> sexo;
	public static volatile SingularAttribute<Paciente, Contato> contato;

	public static final String CODIGO = "codigo";
	public static final String DATANASC = "datanasc";
	public static final String ENDERECO = "endereco";
	public static final String RG = "rg";
	public static final String CPF = "cpf";
	public static final String DATACAD = "datacad";
	public static final String NOME = "nome";
	public static final String SEXO = "sexo";
	public static final String CONTATO = "contato";

}

