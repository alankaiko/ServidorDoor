package com.laudoecia.api.modelo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scheduledstationaetitle")
public class ScheduledStationAETitle {
	private Long codigo;
	private String aetitle;
	private MWLItem mwlitem;

	public ScheduledStationAETitle() {}

	public ScheduledStationAETitle(String aetitle) {
		this.aetitle = aetitle;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Basic(optional = false)
	public String getAetitle() {
		return aetitle;
	}

	public void setAetitle(String aetitle) {
		this.aetitle = aetitle;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "mwlitem_id", nullable = true)
	public MWLItem getMwlitem() {
		return mwlitem;
	}

	public void setMwlitem(MWLItem mwlitem) {
		this.mwlitem = mwlitem;
	}
}