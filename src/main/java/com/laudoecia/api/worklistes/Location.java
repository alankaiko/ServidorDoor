package com.laudoecia.api.worklistes;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dcm4che3.util.TagUtils;

import com.laudoecia.api.domain.Instance;

@Entity
@Table
public class Location {

    public static final String FIND_BY_STORAGE_ID_AND_STATUS = "Location.FindByStorageIDAndStatus";
    public static final String FIND_BY_STUDY_PK = "Location.FindByStudyPk";
    public static final String FIND_BY_SERIES_PK = "Location.FindBySeriesPk";
    public static final String FIND_BY_STUDY_PK_AND_STORAGE_IDS = "Location.FindByStudyPkAndStorageIDs";
    public static final String FIND_BY_SOP_IUID_AND_STORAGE_ID = "Location.FindBySOPIUIDAndStorageID";
    public static final String INSTANCE_PKS_BY_STUDY_PK_AND_STORAGE_IDS = "Location.InstancePksByStudyPkAndStorageIDs";
    public static final String INSTANCE_PKS_BY_STUDY_PK_AND_STORAGE_IDS_AND_STATUS = "Location.InstancePksByStudyPkAndStorageIDsAndStatus";
    public static final String STORAGE_IDS_BY_STUDY_PK_AND_OBJECT_TYPE = "Location.StorageIDsByStudyPkAndObjectType";
    public static final String FIND_BY_REJECTION_CODE = "Location.FindByRejectionCode";
    public static final String FIND_BY_CONCEPT_NAME_CODE = "Location.FindByConceptNameCode";
    public static final String FIND_BY_REJECTION_CODE_BEFORE = "Location.FindByRejectionCodeBefore";
    public static final String FIND_BY_CONCEPT_NAME_CODE_BEFORE = "Location.FindByConceptNameCodeBefore";
    public static final String COUNT_BY_MULTI_REF = "Location.CountByMultiRef";
    public static final String COUNT_BY_UIDMAP = "Location.CountByUIDMap";
    public static final String SET_DIGEST = "Location.SetDigest";
    public static final String SET_STATUS = "Location.SetStatus";
    public static final String UPDATE_STATUS_FROM = "Location.UpdateStatusFrom";
    public static final String DELETE_BY_PK = "Location.DeleteByPk";
    public static final String SIZE_OF_SERIES = "Location.SizeOfSeries";
    public static final String EXISTS = "Location.Exists";

    public enum Status {
        OK,                         // 0
        TO_DELETE,                  // 1
        FAILED_TO_DELETE,           // 2
        MISSING_OBJECT,             // 3
        FAILED_TO_FETCH_METADATA,   // 4
        FAILED_TO_FETCH_OBJECT,     // 5
        DIFFERING_OBJECT_SIZE,      // 6
        DIFFERING_OBJECT_CHECKSUM,  // 7
        DIFFERING_S3_MD5SUM,        // 8
        FAILED_TO_DELETE2           // 9
    }

    public enum ObjectType { DICOM_FILE, METADATA }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @Basic(optional = true)
    @Column(name = "tsuid", updatable = false)
    private String transferSyntaxUID;

    @Basic(optional = false)
    @Column(name = "object_size", updatable = false)
    private long size;

    @Basic(optional = true)
    @Column(name = "digest", updatable = false)
    private String digest;

    @Basic(optional = false)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", updatable = true)
    private Status status;

    @Basic(optional = false)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "object_type", updatable = false)
    private ObjectType objectType;

    @Column(name = "multi_ref", updatable = true)
    private Integer multiReference;

    @ManyToOne
    @JoinColumn(name = "uidmap_fk", updatable = false)
    private UIDMap uidMap;

    @ManyToOne
    @JoinColumn(name = "instance_fk", updatable = true)
    private Instance instance;

    public static boolean isDicomFile(Location location) {
        return location.objectType == ObjectType.DICOM_FILE;
    }

    public static final class Builder {
        private long pk;
        private String storageID;
        private String storagePath;
        private String transferSyntaxUID;
        private long size;
        private String digest;
        private Status status = Status.OK;
        private ObjectType objectType = ObjectType.DICOM_FILE;

        public Builder pk(long pk) {
            this.pk = pk;
            return this;
        }

        public Builder storageID(String storageID) {
            this.storageID = storageID;
            return this;
        }

        public Builder storagePath(String storagePath) {
            this.storagePath = storagePath;
            return this;
        }

        public Builder transferSyntaxUID(String transferSyntaxUID) {
            this.transferSyntaxUID = transferSyntaxUID;
            return this;
        }

        public Builder size(long size) {
            this.size = size;
            return this;
        }

        public Builder digest(String digest) {
            this.digest = digest;
            return this;
        }

        public Builder digest(byte[] digest) {
            return digest(digest != null ? TagUtils.toHexString(digest) : null);
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder status(String status) {
            this.status = status != null ? Status.valueOf(status) : Status.OK;
            return this;
        }

        public Builder objectType(ObjectType objectType) {
            this.objectType = objectType;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }

    public Location() {}

    private Location(Builder builder) {
        pk = builder.pk;
        storageID = builder.storageID;
        storagePath = builder.storagePath;
        transferSyntaxUID = builder.transferSyntaxUID;
        size = builder.size;
        digest = builder.digest;
        status = builder.status;
        objectType = builder.objectType;
    }

    public Location(Location other) {
        this.createdTime = other.createdTime;
        this.storageID = other.storageID;
        this.storagePath = other.storagePath;
        this.transferSyntaxUID = other.transferSyntaxUID;
        this.size = other.size;
        this.digest = other.digest;
        this.status = other.status;
        this.objectType = other.objectType;
        this.multiReference = other.multiReference;
    }

    @PrePersist
    public void onPrePersist() {
        createdTime = new Date();
    }

    public long getPk() {
        return pk;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getStorageID() {
        return storageID;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public String getTransferSyntaxUID() {
        return transferSyntaxUID;
    }

    public long getSize() {
        return size;
    }

    public byte[] getDigest() {
        return digest != null ? TagUtils.fromHexString(digest) : null;
    }

    public String getDigestAsHexString() {
        return digest;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isStatusOK() {
        return status == Status.OK;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public Integer getMultiReference() {
        return multiReference;
    }

    public void setMultiReference(Integer multiReference) {
        this.multiReference = multiReference;
    }

    public UIDMap getUidMap() {
        return uidMap;
    }

    public void setUidMap(UIDMap uidMap) {
        this.uidMap = uidMap;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    @Override
    public String toString() {
        return "Location[pk=" + pk
                + ", systemID=" + storageID
                + ", path=" + storagePath
                + ", tsuid=" + transferSyntaxUID
                + ", size=" + size
                + ", status=" + status
                + ", objectType=" + objectType
                + "]";
    }

}
