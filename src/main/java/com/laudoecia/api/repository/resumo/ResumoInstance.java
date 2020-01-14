package com.laudoecia.api.repository.resumo;

import com.laudoecia.api.domain.Tagimagem;

public class ResumoInstance {
	private Long idinstance;
	private String mediastoragesopinstanceuid;
	private Tagimagem tagimagem;

	public ResumoInstance(Long idinstance, String mediastoragesopinstanceuid, Tagimagem tagimagem) {
		this.idinstance = idinstance;
		this.mediastoragesopinstanceuid = mediastoragesopinstanceuid;
		this.tagimagem = tagimagem;
	}

	public Long getIdinstance() {
		return idinstance;
	}

	public void setIdinstance(Long idinstance) {
		this.idinstance = idinstance;
	}

	public String getMediastoragesopinstanceuid() {
		return mediastoragesopinstanceuid;
	}

	public void setMediastoragesopinstanceuid(String mediastoragesopinstanceuid) {
		this.mediastoragesopinstanceuid = mediastoragesopinstanceuid;
	}

	public Tagimagem getTagimagem() {
		return tagimagem;
	}

	public void setTagimagem(Tagimagem tagimagem) {
		this.tagimagem = tagimagem;
	}


}
