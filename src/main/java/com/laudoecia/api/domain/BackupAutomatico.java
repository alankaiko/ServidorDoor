package com.laudoecia.api.domain;

import java.io.Serializable;
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

import com.laudoecia.api.domain.enuns.DIA_DA_SEMANA;
import com.laudoecia.api.domain.enuns.TIPO_BACKUP;

@Entity
@Table(name = "backupautomatico")
public class BackupAutomatico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@JoinColumn(name = "parametrosdosistema_codigo", referencedColumnName = "codigo", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ParametrosDoSistema parametrosDoSistema;

	@Enumerated(EnumType.STRING)
	@Column(name = "diadasemana", nullable = false)
	private DIA_DA_SEMANA diaDaSemana;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private TIPO_BACKUP tipo;

	@Column(name = "horario", nullable = true)
	@Temporal(javax.persistence.TemporalType.TIME)
	private Date horario;

	@Column(name = "diretoriodoarquivo", nullable = false)
	private String diretorioDoArquivo;

	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public DIA_DA_SEMANA getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(DIA_DA_SEMANA diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public String getDiretorioDoArquivo() {
		return diretorioDoArquivo;
	}

	public void setDiretorioDoArquivo(String diretorioDoArquivo) {
		this.diretorioDoArquivo = diretorioDoArquivo;
		if (!(diretorioDoArquivo == null || diretorioDoArquivo.isEmpty())) {
			if (diretorioDoArquivo.endsWith("/") || diretorioDoArquivo.endsWith("\\")) {
			} else {
				this.diretorioDoArquivo += "\\";
			}
		}
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public ParametrosDoSistema getParametrosDoSistema() {
		return parametrosDoSistema;
	}

	public void setParametrosDoSistema(ParametrosDoSistema parametrosDoSistema) {
		this.parametrosDoSistema = parametrosDoSistema;
	}

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
