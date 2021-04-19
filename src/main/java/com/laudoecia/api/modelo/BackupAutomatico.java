package com.laudoecia.api.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.laudoecia.api.modelo.enuns.DIA_DA_SEMANA;
import com.laudoecia.api.modelo.enuns.TIPO_BACKUP;

@Entity
@Table(name = "backupautomatico")
public class BackupAutomatico {
	private Long codigo;
	private ParametrosDoSistema parametrosdosistema;
	private DIA_DA_SEMANA diadasemana;
	private TIPO_BACKUP tipo;
	private Date horario;
	private String diretoriodoarquivo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public DIA_DA_SEMANA getDiadasemana() {
		return diadasemana;
	}
	
	public void setDiadasemana(DIA_DA_SEMANA diadasemana) {
		this.diadasemana = diadasemana;
	}

	@Column(nullable = false)
	public String getDiretoriodoarquivo() {
		return diretoriodoarquivo;
	}
	
	public void setDiretoriodoarquivo(String diretoriodoarquivo) {
		this.diretoriodoarquivo = diretoriodoarquivo;
		if (!(diretoriodoarquivo == null || diretoriodoarquivo.isEmpty())) {
			if (diretoriodoarquivo.endsWith("/") || diretoriodoarquivo.endsWith("\\")) {
			} else {
				this.diretoriodoarquivo += "\\";
			}
		}
	}

	@Column(nullable = true)
	@Temporal(TemporalType.TIME)
	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	@JoinColumn(name = "parametrosdosistema_codigo", referencedColumnName = "codigo", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public ParametrosDoSistema getParametrosdosistema() {
		return parametrosdosistema;
	}
	
	public void setParametrosdosistema(ParametrosDoSistema parametrosdosistema) {
		this.parametrosdosistema = parametrosdosistema;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	public TIPO_BACKUP getTipo() {
		return tipo;
	}

	public void setTipo(TIPO_BACKUP tipo) {
		this.tipo = tipo;
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
		BackupAutomatico other = (BackupAutomatico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
}
