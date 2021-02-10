package com.laudoecia.api.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.laudoecia.api.domain.enuns.CONTROLE_DE_ACESSO;
import com.laudoecia.api.domain.enuns.LAYOUT_IMG;
import com.laudoecia.api.domain.enuns.VERSAO_DO_SW;


@Entity
@Table(name = "parametrosdosistema")
public class ParametrosDoSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private List<BackupAutomatico> backups;
	private boolean backupautomaticoenabled = true;
	private double topmargin = 1.0;
	private double bottommargin = 1.0;
	private double leftmargin = 1.5;
	private double rightmargin = 1.0;
	private double paperwidth = 21.0;
	private double paperheight = 29.7;
	private boolean imprimecabecalholaudo = true;
	private int tamanhoseprodape = 1;
	private byte[] logomarcalaudo;
	private double logomarcawidth = 1.0;
	private double logomarcaheight = 1.0;
	private double leftmarginlogomarca = 0.0;
	private String textocabecalholaudo = "";
	private boolean imprimerodapelaudo = true;
	private String fraserodapelaudo = "";
	private String textorodapelaudo = "";
	private boolean imprimepaginacaolaudo = true;
	private int bgcolor = 0;
	private LAYOUT_IMG layoutimagem;
	private CONTROLE_DE_ACESSO tipodecontroledeacesso = CONTROLE_DE_ACESSO.SEM_CONTROLE;
	private String diretoriodevideos = "C:\\videos";
	private VERSAO_DO_SW versaodosw = VERSAO_DO_SW.V1;
	private String defprinterlaudo = "";
	private String defprinterimagens = "";
	private boolean imprimirsolcon = true;
	private boolean imprimiratd = true;
	private long version = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parametrosdosistema")
	public List<BackupAutomatico> getBackups() {
		return Collections.unmodifiableList(backups);
	}

	public void setBackups(List<BackupAutomatico> backups) {
		this.backups.clear();
		if (backups == null) {
		} else {
			this.backups.addAll(backups);
		}
	}

	@Column(name = "fraserodapelaudo", nullable = true, length = 10000)
	public String getFraserodapelaudo() {
		return fraserodapelaudo;
	}
	
	public void setFraserodapelaudo(String fraserodapelaudo) {
		this.fraserodapelaudo = fraserodapelaudo;
	}

	@Column(name = "logomarcalaudo", nullable = true)
	public byte[] getLogomarcalaudo() {
		if (logomarcalaudo == null) {
			return null;
		} else {
			return Arrays.copyOf(logomarcalaudo, logomarcalaudo.length);
		}
	}
	
	public void setLogomarcalaudo(byte[] logomarcalaudo) {
		if (logomarcalaudo == null) {
			this.logomarcalaudo = null;
		} else {
			this.logomarcalaudo = Arrays.copyOf(logomarcalaudo, logomarcalaudo.length);
		}
	}
	
	@Column(name = "logomarcaheight", nullable = false)
	public double getLogomarcaheight() {
		return logomarcaheight;
	}
	
	public void setLogomarcaheight(double logomarcaheight) {
		this.logomarcaheight = logomarcaheight;
	}

	@Column(name = "logomarcawidth", nullable = false)
	public double getLogomarcawidth() {
		return logomarcawidth;
	}
	
	public void setLogomarcawidth(double logomarcawidth) {
		this.logomarcawidth = logomarcawidth;
	}

	@Column(name = "leftmarginlogomarca", nullable = false)
	public double getLeftmarginlogomarca() {
		return leftmarginlogomarca;
	}
	
	public void setLeftmarginlogomarca(double leftmarginlogomarca) {
		this.leftmarginlogomarca = leftmarginlogomarca;
	}

	@Column(name = "bottommargin", nullable = false)
	public double getBottommargin() {
		return bottommargin;
	}
	
	public void setBottommargin(double bottommargin) {
		this.bottommargin = bottommargin;
	}

	@Column(name = "leftmargin", nullable = false)
	public double getLeftmargin() {
		return leftmargin;
	}
	
	public void setLeftmargin(double leftmargin) {
		this.leftmargin = leftmargin;
	}

	@Column(name = "rightmargin", nullable = false)
	public double getRightmargin() {
		return rightmargin;
	}
	
	public void setRightmargin(double rightmargin) {
		this.rightmargin = rightmargin;
	}

	@Column(name = "topmargin", nullable = false)
	public double getTopmargin() {
		return topmargin;
	}
	
	public void setTopmargin(double topmargin) {
		this.topmargin = topmargin;
	}

	@Column(name = "paperheight", nullable = false)
	public double getPaperheight() {
		return paperheight;
	}
	
	public void setPaperheight(double paperheight) {
		this.paperheight = paperheight;
	}

	@Column(name = "paperwidth", nullable = false)
	public double getPaperwidth() {
		return paperwidth;
	}
	
	public void setPaperwidth(double paperwidth) {
		this.paperwidth = paperwidth;
	}

	@Column(name = "backupautomaticoenabled", nullable = false)
	public boolean isBackupautomaticoenabled() {
		return backupautomaticoenabled;
	}
	
	public void setBackupautomaticoenabled(boolean backupautomaticoenabled) {
		this.backupautomaticoenabled = backupautomaticoenabled;
	}

	@Column(name = "imprimecabecalholaudo", nullable = false)
	public boolean isImprimecabecalholaudo() {
		return imprimecabecalholaudo;
	}
	
	public void setImprimecabecalholaudo(boolean imprimecabecalholaudo) {
		this.imprimecabecalholaudo = imprimecabecalholaudo;
	}

	@Column(name = "imprimepaginacaolaudo", nullable = false)
	public boolean isImprimepaginacaolaudo() {
		return imprimepaginacaolaudo;
	}
	
	public void setImprimepaginacaolaudo(boolean imprimepaginacaolaudo) {
		this.imprimepaginacaolaudo = imprimepaginacaolaudo;
	}

	@Column(name = "imprimerodapelaudo", nullable = false)
	public boolean isImprimerodapelaudo() {
		return imprimerodapelaudo;
	}
	
	public void setImprimerodapelaudo(boolean imprimerodapelaudo) {
		this.imprimerodapelaudo = imprimerodapelaudo;
	}

	@Column(name = "textocabecalholaudo", nullable = true, length = 10000)
	public String getTextocabecalholaudo() {
		return textocabecalholaudo;
	}
	
	public void setTextocabecalholaudo(String textocabecalholaudo) {
		this.textocabecalholaudo = textocabecalholaudo;
	}

	@Column(name = "textorodapelaudo", nullable = true, length = 10000)
	public String getTextorodapelaudo() {
		return textorodapelaudo;
	}
	
	public void setTextorodapelaudo(String textorodapelaudo) {
		this.textorodapelaudo = textorodapelaudo;
	}

	@Column(name = "bgcolor", nullable = true)
	public int getBgcolor() {
		return bgcolor;
	}
	
	public void setBgcolor(int bgcolor) {
		this.bgcolor = bgcolor;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "layoutimagem", nullable = true)
	public LAYOUT_IMG getLayoutimagem() {
		return layoutimagem;
	}
	
	public void setLayoutimagem(LAYOUT_IMG layoutimagem) {
		this.layoutimagem = layoutimagem;
	}

	@Column(name = "diretoriodevideos", nullable = false)
	public String getDiretoriodevideos() {
		return diretoriodevideos;
	}
	
	public void setDiretoriodevideos(String diretoriodevideos) {
		this.diretoriodevideos = diretoriodevideos;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tipodecontroledeacesso", nullable = false)
	public CONTROLE_DE_ACESSO getTipodecontroledeacesso() {
		return tipodecontroledeacesso;
	}
	
	public void setTipodecontroledeacesso(CONTROLE_DE_ACESSO tipodecontroledeacesso) {
		this.tipodecontroledeacesso = tipodecontroledeacesso;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "versaodosw", nullable = false)
	public VERSAO_DO_SW getVersaodosw() {
		return versaodosw;
	}
	
	public void setVersaodosw(VERSAO_DO_SW versaodosw) {
		this.versaodosw = versaodosw;
	}

	@Column(name = "tamanhoseprodape", nullable = false)
	public int getTamanhoseprodape() {
		return tamanhoseprodape;
	}
	
	public void setTamanhoseprodape(int tamanhoseprodape) {
		this.tamanhoseprodape = tamanhoseprodape;
	}

	@Column(name = "defprinterlaudo", nullable = true)
	public String getDefprinterlaudo() {
		return defprinterlaudo;
	}
	
	public void setDefprinterlaudo(String defprinterlaudo) {
		this.defprinterlaudo = defprinterlaudo;
	}

	@Column(name = "defprinterimagens", nullable = true)
	public String getDefprinterimagens() {
		return defprinterimagens;
	}
	
	public void setDefprinterimagens(String defprinterimagens) {
		this.defprinterimagens = defprinterimagens;
	}

	@Column(name = "imprimirsolcon", nullable = false)
	public boolean isImprimirsolcon() {
		return imprimirsolcon;
	}

	public void setImprimirsolcon(boolean imprimirsolcon) {
		this.imprimirsolcon = imprimirsolcon;
	}
	
	@Column(name = "imprimiratd", nullable = false)
	public boolean isImprimiratd() {
		return imprimiratd;
	}
	
	public void setImprimiratd(boolean imprimiratd) {
		this.imprimiratd = imprimiratd;
	}
	
	@Version
	public long getVersion() {
		return version;
	}
	
	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParametrosDoSistema other = (ParametrosDoSistema) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PARAMETROS DO SISTEMA";
	}

}
