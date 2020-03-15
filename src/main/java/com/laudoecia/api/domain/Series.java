package com.laudoecia.api.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="series")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@idseries")
public class Series implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_SERIES_IUID = "Series.findBySeriesIUID";
    public static final String FIND_SERIES_OF_STUDY_BY_STUDY_IUID_EAGER = "Series.findSeriesOfStudyByStudyIUIDEager";
    public static final String FIND_BY_SERIES_IUID_EAGER = "Series.findBySeriesIUIDEager";
    public static final String COUNT_SERIES_OF_STUDY = "Series.countSeriesOfStudy";
    public static final String SERIES_PKS_OF_STUDY_WITH_UNKNOWN_SIZE = "Series.seriesPKsOfStudyWithUnknownSize";
    public static final String SIZE_OF_STUDY="Series.sizeOfStudy";
    public static final String SET_SERIES_SIZE = "Series.SetSeriesSize";
    public static final String SET_COMPLETENESS = "Series.SetCompleteness";
    public static final String SET_COMPLETENESS_OF_STUDY = "Series.SetCompletenessOfStudy";
    public static final String INCREMENT_FAILED_RETRIEVES = "Series.IncrementFailedRetrieves";
    public static final String GET_EXPIRED_SERIES = "Series.GetExpiredSeries";
    public static final String CLAIM_EXPIRED_SERIES = "Series.ClaimExpiredSeries";
    public static final String EXPIRE_SERIES = "Series.ExpireSeries";
    public static final String FIND_SERIES_OF_STUDY = "Series.FindSeriesOfStudy";
    public static final String FIND_SERIES_OF_STUDY_BY_INSTANCE_PURGE_STATE = "Series.FindSeriesOfStudyByInstancePurgeState";
    public static final String FIND_BY_SERIES_IUID_AND_INSTANCE_PURGE_STATE = "Series.FindBySeriesIUIDAndInstancePurgeState";
    public static final String COUNT_SERIES_OF_STUDY_WITH_OTHER_REJECTION_STATE = "Series.countSeriesOfStudyWithOtherRejectionState";
    public static final String SERIES_IUIDS_OF_STUDY = "Series.seriesIUIDsOfStudy";
    public static final String SCHEDULED_METADATA_UPDATE = "Series.scheduledMetadataUpdate";
    public static final String SCHEDULED_PURGE_INSTANCES = "Series.scheduledPurgeInstances";
    public static final String SCHEDULE_METADATA_UPDATE_FOR_PATIENT = "Series.scheduleMetadataUpdateForPatient";
    public static final String SCHEDULE_METADATA_UPDATE_FOR_STUDY = "Series.scheduleMetadataUpdateForStudy";
    public static final String SCHEDULE_METADATA_UPDATE_FOR_SERIES = "Series.scheduleMetadataUpdateForSeries";
    public static final String SCHEDULE_METADATA_UPDATE_FOR_SERIES_UID = "Series.scheduleMetadataUpdateForSeriesUID";
    public static final String UPDATE_INSTANCE_PURGE_STATE = "Series.updateInstancePurgeState";
    public static final String FIND_DISTINCT_MODALITIES = "Series.findDistinctModalities";
    public static final String FIND_BY_STUDY_PK_AND_INSTANCE_PURGE_STATE = "Series.findByStudyPkAndInstancePurgeState";
    public static final String UPDATE_STGVER_FAILURES = "Series.updateStgVerFailures";
    public static final String SCHEDULED_STORAGE_VERIFICATION = "Series.scheduledStorageVerification";
    public static final String SCHEDULED_COMPRESSION = "Series.scheduledCompression";
    public static final String INCREMENT_METADATA_UPDATE_FAILURES = "Series.setMetadataScheduledUpdateTime";
    public static final String CLAIM_STORAGE_VERIFICATION = "Series.claimStorageVerification";
    public static final String CLAIM_COMPRESSION = "Series.claimCompression";
    public static final String CLAIM_UPDATE_METADATA = "Series.claimUpdateMetadata";
    public static final String CLAIM_PURGE_INSTANCE_RECORDS = "Series.claimPurgeInstanceRecords";
    public static final String UPDATE_COMPRESSION_FAILURES = "Series.updateCompressionFailures";
    public static final String UPDATE_COMPRESSION_FAILURES_AND_TSUID = "Series.updateCompressionFailuresAndTSUID";
    public static final String UPDATE_COMPRESSION_COMPLETED = "Series.updateCompressionCompleted";

	private Long idseries;
	private String seriesinstanceuid;
	private String seriesdescription;
	private Integer seriesnumber;
	private String patientposition;
	private String bodypartexamined;
	private String laterality;
	private String operatorsname;
	private String protocolname;
	private Date seriesdatetime;
	private Date datecreate;
	private Date datemodify;
	private Study study;
	private  List<Instance> instance;
	private Dispositive dispotive;

	public Series() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdseries() {
		return idseries;
	}

	public void setIdseries(Long idseries) {
		this.idseries = idseries;
	}

	@Column(length = 200)
	public String getSeriesinstanceuid() {
		return seriesinstanceuid;
	}

	public void setSeriesinstanceuid(String seriesinstanceuid) {
		this.seriesinstanceuid = seriesinstanceuid;
	}

	@Column(length = 200)
	public String getSeriesdescription() {
		return seriesdescription;
	}

	public void setSeriesdescription(String seriesdescription) {
		this.seriesdescription = seriesdescription;
	}

	public Integer getSeriesnumber() {
		return seriesnumber;
	}

	public void setSeriesnumber(Integer seriesnumber) {
		this.seriesnumber = seriesnumber;
	}

	@Column(length = 130)
	public String getPatientposition() {
		return patientposition;
	}

	public void setPatientposition(String patientposition) {
		this.patientposition = patientposition;
	}

	@Column(length = 200)
	public String getBodypartexamined() {
		return bodypartexamined;
	}

	public void setBodypartexamined(String bodypartexamined) {
		this.bodypartexamined = bodypartexamined;
	}

	@Column(length = 140)
	public String getLaterality() {
		return laterality;
	}

	public void setLaterality(String laterality) {
		this.laterality = laterality;
	}

	@Column(length = 150)
	public String getOperatorsname() {
		return operatorsname;
	}

	public void setOperatorsname(String operatorsname) {
		this.operatorsname = operatorsname;
	}

	@Column(length = 200)
	public String getProtocolname() {
		return protocolname;
	}

	public void setProtocolname(String protocolname) {
		this.protocolname = protocolname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getSeriesdatetime() {
		return seriesdatetime;
	}

	public void setSeriesdatetime(Date seriesdatetime) {
		this.seriesdatetime = seriesdatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatecreate() {
		return datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatemodify() {
		return datemodify;
	}

	public void setDatemodify(Date datemodify) {
		this.datemodify = datemodify;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	@OneToMany(mappedBy = "series")
	public List<Instance> getInstance() {
		return instance;
	}

	public void setInstance(List<Instance> instance) {
		this.instance = instance;
	}

	@OneToOne(mappedBy = "series")
	public Dispositive getDispotive() {
		return dispotive;
	}

	public void setDispotive(Dispositive dispotive) {
		this.dispotive = dispotive;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		datemodify = new Date();
		
		if (datecreate == null) 
			datecreate = new Date();
	}


}
