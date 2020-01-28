package com.laudoecia.api.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Atendimento.class)
public abstract class Atendimento_ {

	public static volatile SingularAttribute<Atendimento, String> observacoes;
	public static volatile SingularAttribute<Atendimento, Long> codigo;
	public static volatile ListAttribute<Atendimento, ProcedimentoAtendimento> procedimentos;
	public static volatile SingularAttribute<Atendimento, Patient> patient;
	public static volatile SingularAttribute<Atendimento, ProfissionalSolicitante> solicitante;
	public static volatile SingularAttribute<Atendimento, Convenio> convenio;
	public static volatile SingularAttribute<Atendimento, LocalDate> dataatendimento;

	public static final String OBSERVACOES = "observacoes";
	public static final String CODIGO = "codigo";
	public static final String PROCEDIMENTOS = "procedimentos";
	public static final String PATIENT = "patient";
	public static final String SOLICITANTE = "solicitante";
	public static final String CONVENIO = "convenio";
	public static final String DATAATENDIMENTO = "dataatendimento";

}

