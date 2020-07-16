package com.laudoecia.api.domain;

import com.laudoecia.api.domain.enuns.CONTROLE_DE_ACESSO;
import com.laudoecia.api.domain.enuns.LAYOUT_IMG;
import com.laudoecia.api.domain.enuns.VERSAO_DO_SW;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ParametrosDoSistema.class)
public abstract class ParametrosDoSistema_ {

	public static volatile SingularAttribute<ParametrosDoSistema, VERSAO_DO_SW> versaoDoSw;
	public static volatile SingularAttribute<ParametrosDoSistema, Double> leftMarginLogomarca;
	public static volatile SingularAttribute<ParametrosDoSistema, CONTROLE_DE_ACESSO> tipoControleDeAcesso;
	public static volatile SingularAttribute<ParametrosDoSistema, LAYOUT_IMG> layoutImagem;
	public static volatile SingularAttribute<ParametrosDoSistema, String> defprinterlaudo;
	public static volatile SingularAttribute<ParametrosDoSistema, String> defprinterimagens;
	public static volatile SingularAttribute<ParametrosDoSistema, byte[]> logomarcaLaudo;
	public static volatile SingularAttribute<ParametrosDoSistema, Double> logomarcaWidth;
	public static volatile SingularAttribute<ParametrosDoSistema, Integer> bgColor;
	public static volatile SingularAttribute<ParametrosDoSistema, String> diretorioDeVideos;
	public static volatile SingularAttribute<ParametrosDoSistema, Double> rightMargin;
	public static volatile SingularAttribute<ParametrosDoSistema, String> textoCabecalhoLaudo;
	public static volatile SingularAttribute<ParametrosDoSistema, Boolean> imprimePaginacaoLaudo;
	public static volatile SingularAttribute<ParametrosDoSistema, Boolean> backupAutomaticoEnabled;
	public static volatile SingularAttribute<ParametrosDoSistema, Double> paperHeight;
	public static volatile SingularAttribute<ParametrosDoSistema, Long> codigo;
	public static volatile SingularAttribute<ParametrosDoSistema, Double> leftMargin;
	public static volatile SingularAttribute<ParametrosDoSistema, Boolean> imprimirsolcon;
	public static volatile SingularAttribute<ParametrosDoSistema, String> textoRodapeLaudo;
	public static volatile SingularAttribute<ParametrosDoSistema, Integer> tamanhoSepRodape;
	public static volatile SingularAttribute<ParametrosDoSistema, Boolean> imprimirAtd;
	public static volatile SingularAttribute<ParametrosDoSistema, Double> logomarcaHeight;
	public static volatile SingularAttribute<ParametrosDoSistema, Long> version;
	public static volatile SingularAttribute<ParametrosDoSistema, String> fraseRodapeLaudo;
	public static volatile SingularAttribute<ParametrosDoSistema, Double> topMargin;
	public static volatile SingularAttribute<ParametrosDoSistema, Double> bottomMargin;
	public static volatile SingularAttribute<ParametrosDoSistema, Double> paperWidth;
	public static volatile SingularAttribute<ParametrosDoSistema, Boolean> imprimeCabecalhoLaudo;
	public static volatile ListAttribute<ParametrosDoSistema, BackupAutomatico> backups;
	public static volatile SingularAttribute<ParametrosDoSistema, Boolean> imprimeRodapeLaudo;

	public static final String VERSAO_DO_SW = "versaoDoSw";
	public static final String LEFT_MARGIN_LOGOMARCA = "leftMarginLogomarca";
	public static final String TIPO_CONTROLE_DE_ACESSO = "tipoControleDeAcesso";
	public static final String LAYOUT_IMAGEM = "layoutImagem";
	public static final String DEFPRINTERLAUDO = "defprinterlaudo";
	public static final String DEFPRINTERIMAGENS = "defprinterimagens";
	public static final String LOGOMARCA_LAUDO = "logomarcaLaudo";
	public static final String LOGOMARCA_WIDTH = "logomarcaWidth";
	public static final String BG_COLOR = "bgColor";
	public static final String DIRETORIO_DE_VIDEOS = "diretorioDeVideos";
	public static final String RIGHT_MARGIN = "rightMargin";
	public static final String TEXTO_CABECALHO_LAUDO = "textoCabecalhoLaudo";
	public static final String IMPRIME_PAGINACAO_LAUDO = "imprimePaginacaoLaudo";
	public static final String BACKUP_AUTOMATICO_ENABLED = "backupAutomaticoEnabled";
	public static final String PAPER_HEIGHT = "paperHeight";
	public static final String CODIGO = "codigo";
	public static final String LEFT_MARGIN = "leftMargin";
	public static final String IMPRIMIRSOLCON = "imprimirsolcon";
	public static final String TEXTO_RODAPE_LAUDO = "textoRodapeLaudo";
	public static final String TAMANHO_SEP_RODAPE = "tamanhoSepRodape";
	public static final String IMPRIMIR_ATD = "imprimirAtd";
	public static final String LOGOMARCA_HEIGHT = "logomarcaHeight";
	public static final String VERSION = "version";
	public static final String FRASE_RODAPE_LAUDO = "fraseRodapeLaudo";
	public static final String TOP_MARGIN = "topMargin";
	public static final String BOTTOM_MARGIN = "bottomMargin";
	public static final String PAPER_WIDTH = "paperWidth";
	public static final String IMPRIME_CABECALHO_LAUDO = "imprimeCabecalhoLaudo";
	public static final String BACKUPS = "backups";
	public static final String IMPRIME_RODAPE_LAUDO = "imprimeRodapeLaudo";

}

