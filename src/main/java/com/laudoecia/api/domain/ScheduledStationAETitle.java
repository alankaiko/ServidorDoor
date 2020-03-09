package com.laudoecia.api.domain;

import java.io.Serializable;

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
public class ScheduledStationAETitle implements Serializable {
	private static final long serialVersionUID = -145604862103707241L;

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
	@JoinColumn(name = "mwl_item_fk")
	public MWLItem getMwlitem() {
		return mwlitem;
	}

	public void setMwlitem(MWLItem mwlitem) {
		this.mwlitem = mwlitem;
	}
}
