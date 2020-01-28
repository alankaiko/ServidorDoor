package com.laudoecia.api.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProcedimentoAtendimento.class)
public abstract class ProcedimentoAtendimento_ {

	public static volatile SingularAttribute<ProcedimentoAtendimento, LocalDate> dataexecucao;
	public static volatile SingularAttribute<ProcedimentoAtendimento, LocalDate> preventregalaudo;
	public static volatile SingularAttribute<ProcedimentoAtendimento, Long> codigo;
	public static volatile SingularAttribute<ProcedimentoAtendimento, BigDecimal> valorconvenio;
	public static volatile SingularAttribute<ProcedimentoAtendimento, Atendimento> atendimento;
	public static volatile SingularAttribute<ProcedimentoAtendimento, ProfissionalExecutante> profexecutante;
	public static volatile SingularAttribute<ProcedimentoAtendimento, BigDecimal> valorpaciente;
	public static volatile SingularAttribute<ProcedimentoAtendimento, ProcedimentoMedico> procedimentomedico;

	public static final String DATAEXECUCAO = "dataexecucao";
	public static final String PREVENTREGALAUDO = "preventregalaudo";
	public static final String CODIGO = "codigo";
	public static final String VALORCONVENIO = "valorconvenio";
	public static final String ATENDIMENTO = "atendimento";
	public static final String PROFEXECUTANTE = "profexecutante";
	public static final String VALORPACIENTE = "valorpaciente";
	public static final String PROCEDIMENTOMEDICO = "procedimentomedico";

}

