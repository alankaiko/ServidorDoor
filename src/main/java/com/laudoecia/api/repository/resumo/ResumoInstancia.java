package com.laudoecia.api.repository.resumo;

public class ResumoInstancia {
	private Long idinstance;
	private String mediastoragesopinstanceuid;
	private Long tagimagem;

	public ResumoInstancia() {
	}

	public ResumoInstancia(Long idinstance, String mediastoragesopinstanceuid, Long tagimagem) {
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

	public Long getTagimagem() {
		return tagimagem;
	}
	
	public void setTagimagem(Long tagimagem) {
		this.tagimagem = tagimagem;
	}

}
