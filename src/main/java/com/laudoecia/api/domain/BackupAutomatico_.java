package com.laudoecia.api.domain;

import com.laudoecia.api.domain.enuns.DIA_DA_SEMANA;
import com.laudoecia.api.domain.enuns.TIPO_BACKUP;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BackupAutomatico.class)
public abstract class BackupAutomatico_ {

	public static volatile SingularAttribute<BackupAutomatico, Long> codigo;
	public static volatile SingularAttribute<BackupAutomatico, TIPO_BACKUP> tipo;
	public static volatile SingularAttribute<BackupAutomatico, Date> horario;
	public static volatile SingularAttribute<BackupAutomatico, DIA_DA_SEMANA> diaDaSemana;
	public static volatile SingularAttribute<BackupAutomatico, String> diretorioDoArquivo;
	public static volatile SingularAttribute<BackupAutomatico, ParametrosDoSistema> parametrosDoSistema;

	public static final String CODIGO = "codigo";
	public static final String TIPO = "tipo";
	public static final String HORARIO = "horario";
	public static final String DIA_DA_SEMANA = "diaDaSemana";
	public static final String DIRETORIO_DO_ARQUIVO = "diretorioDoArquivo";
	public static final String PARAMETROS_DO_SISTEMA = "parametrosDoSistema";

}

