package com.laudoecia.api.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="instance")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@idinstance")
public class Instance implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private Integer instancenumber;
	private String patientorientation;
	private String mediastoragesopinstanceuid;
	private String sopinstanceuid;
	private String sopclassuid;
	private String transfersyntaxuid;
	private Date acquisitiondatetime;
	private String imagetype;
	private Float pixelspacing;
	private String imageorientation;
	private Integer xraytubecurrent;
	private Integer exposuretime;
	private String kvp;
	private Float slicelocation;
	private Float slicethickness;
	private String imageposition;
	private String windowcenter;
	private String windowwidth;
	private Date contentdatetime;
	private Date datacriacao;
	private Date datamodificacao;
	private Series series;
	private Tagimagem tagimagem;

	public Instance() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getInstancenumber() {
		return instancenumber;
	}

	public void setInstancenumber(Integer instancenumber) {
		this.instancenumber = instancenumber;
	}

	@Column(length = 150)
	public String getPatientorientation() {
		return patientorientation;
	}

	public void setPatientorientation(String patientorientation) {
		this.patientorientation = patientorientation;
	}

	@Column(length = 210)
	public String getMediastoragesopinstanceuid() {
		return mediastoragesopinstanceuid;
	}

	public void setMediastoragesopinstanceuid(String mediastoragesopinstanceuid) {
		this.mediastoragesopinstanceuid = mediastoragesopinstanceuid;
	}

	@Column(length = 210)
	public String getSopinstanceuid() {
		return sopinstanceuid;
	}

	public void setSopinstanceuid(String sopinstanceuid) {
		this.sopinstanceuid = sopinstanceuid;
	}

	@Column(length = 210)
	public String getSopclassuid() {
		return sopclassuid;
	}

	public void setSopclassuid(String sopclassuid) {
		this.sopclassuid = sopclassuid;
	}

	@Column(length = 210)
	public String getTransfersyntaxuid() {
		return transfersyntaxuid;
	}

	public void setTransfersyntaxuid(String transfersyntaxuid) {
		this.transfersyntaxuid = transfersyntaxuid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getAcquisitiondatetime() {
		return acquisitiondatetime;
	}

	public void setAcquisitiondatetime(Date acquisitiondatetime) {
		this.acquisitiondatetime = acquisitiondatetime;
	}

	@Column(length = 160)
	public String getImagetype() {
		return imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}

	public Float getPixelspacing() {
		return pixelspacing;
	}

	public void setPixelspacing(Float pixelspacing) {
		this.pixelspacing = pixelspacing;
	}

	@Column(length = 160)
	public String getImageorientation() {
		return imageorientation;
	}

	public void setImageorientation(String imageorientation) {
		this.imageorientation = imageorientation;
	}

	public Integer getXraytubecurrent() {
		return xraytubecurrent;
	}

	public void setXraytubecurrent(Integer xraytubecurrent) {
		this.xraytubecurrent = xraytubecurrent;
	}

	public Integer getExposuretime() {
		return exposuretime;
	}

	public void setExposuretime(Integer exposuretime) {
		this.exposuretime = exposuretime;
	}

	@Column(length = 160)
	public String getKvp() {
		return kvp;
	}

	public void setKvp(String kvp) {
		this.kvp = kvp;
	}

	public Float getSlicelocation() {
		return slicelocation;
	}

	public void setSlicelocation(Float slicelocation) {
		this.slicelocation = slicelocation;
	}

	public Float getSlicethickness() {
		return slicethickness;
	}

	public void setSlicethickness(Float slicethickness) {
		this.slicethickness = slicethickness;
	}

	@Column(length = 200)
	public String getImageposition() {
		return imageposition;
	}

	public void setImageposition(String imageposition) {
		this.imageposition = imageposition;
	}

	@Column(length = 170)
	public String getWindowcenter() {
		return windowcenter;
	}

	public void setWindowcenter(String windowcenter) {
		this.windowcenter = windowcenter;
	}

	@Column(length = 170)
	public String getWindowwidth() {
		return windowwidth;
	}

	public void setWindowwidth(String windowwidth) {
		this.windowwidth = windowwidth;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getContentdatetime() {
		return contentdatetime;
	}

	public void setContentdatetime(Date contentdatetime) {
		this.contentdatetime = contentdatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatacriacao() {
		return datacriacao;
	}
	
	public void setDatacriacao(Date datacriacao) {
		this.datacriacao = datacriacao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatamodificacao() {
		return datamodificacao;
	}
	
	public void setDatamodificacao(Date datamodificacao) {
		this.datamodificacao = datamodificacao;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	public Series getSeries() {
		return series;
	}

	public void setSeries(Series param) {
		this.series = param;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_tagimagem_id", referencedColumnName = "codigo")
	public Tagimagem getTagimagem() {
		return tagimagem;
	}
	
	public void setTagimagem(Tagimagem tagimagem) {
		this.tagimagem = tagimagem;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		datamodificacao = new Date();

		if (datacriacao == null)
			datacriacao = new Date();
	}
	
}
