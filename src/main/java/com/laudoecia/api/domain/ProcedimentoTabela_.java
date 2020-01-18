package com.laudoecia.api.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProcedimentoTabela.class)
public abstract class ProcedimentoTabela_ {

	public static volatile SingularAttribute<ProcedimentoTabela, Long> codigo;
	public static volatile SingularAttribute<ProcedimentoTabela, TabelaDeProcedimentos> tabeladeprocedimentos;
	public static volatile SingularAttribute<ProcedimentoTabela, BigDecimal> valorpaciente;
	public static volatile SingularAttribute<ProcedimentoTabela, ProcedimentoMedico> procedimentomedico;
	public static volatile SingularAttribute<ProcedimentoTabela, BigDecimal> valornoconvenio;

	public static final String CODIGO = "codigo";
	public static final String TABELADEPROCEDIMENTOS = "tabeladeprocedimentos";
	public static final String VALORPACIENTE = "valorpaciente";
	public static final String PROCEDIMENTOMEDICO = "procedimentomedico";
	public static final String VALORNOCONVENIO = "valornoconvenio";

}

