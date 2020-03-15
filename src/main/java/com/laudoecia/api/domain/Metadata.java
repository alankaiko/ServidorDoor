package com.laudoecia.api.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dcm4che3.util.TagUtils;

@Table
@Entity
public class Metadata {

	public static final String FIND_BY_STORAGE_ID_AND_STATUS = "Metadata.FindByStorageIDAndStatus";
	public static final String FIND_BY_SERIES_IUID_AND_STORAGE_ID = "Metadata.FindBySeriesIUIDAndStorageID";
	public static final String UPDATE_STATUS_FROM = "Metadata.UpdateStatusFrom";
	public static final String DELETE_BY_PK = "Metadata.DeleteByPk";

	public enum Status {
		OK, // 0
		TO_DELETE, // 1
		FAILED_TO_DELETE, // 2
		MISSING_OBJECT, // 3
		FAILED_TO_FETCH_METADATA, // 4
		FAILED_TO_FETCH_OBJECT, // 5
		DIFFERING_OBJECT_SIZE, // 6
		DIFFERING_OBJECT_CHECKSUM, // 7
		DIFFERING_S3_MD5SUM, // 8
		FAILED_TO_DELETE2 // 9
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk")
	private long pk;

	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", updatable = false)
	private Date createdTime;

	@Basic(optional = false)
	@Column(name = "storage_id", updatable = false)
	private String storageID;

	@Basic(optional = false)
	@Column(name = "storage_path", updatable = false)
	private String storagePath;

	@Basic(optional = false)
	@Column(name = "object_size", updatable = false)
	private long size;

	@Basic(optional = true)
	@Column(name = "digest", updatable = false)
	private String digest;

	@Basic(optional = false)
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status", updatable = true)
	private Status status = Status.OK;

	public long getPk() {
		return pk;
	}

	public String getStorageID() {
		return storageID;
	}

	public void setStorageID(String storageID) {
		this.storageID = storageID;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public byte[] getDigest() {
		return digest != null ? TagUtils.fromHexString(digest) : null;
	}

	public void setDigest(byte[] digest) {
		this.digest = digest != null ? TagUtils.toHexString(digest) : null;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Metadata[pk=" + pk + ", storageID=" + storageID + ", path=" + storagePath + ", size=" + size
				+ ", status=" + status + "]";
	}

	@PrePersist
	public void onPrePersist() {
		createdTime = new Date();
	}
}
