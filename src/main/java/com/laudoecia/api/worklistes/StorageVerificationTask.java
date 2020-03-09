package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.csv.CSVPrinter;
import org.dcm4che3.util.StringUtils;

@Entity
@Table(name = "stgver_task",
        indexes = {
                @Index(columnList = "created_time"),
                @Index(columnList = "updated_time"),
                @Index(columnList = "study_iuid, series_iuid, sop_iuid")}
)
@NamedQueries({
        @NamedQuery(name = StorageVerificationTask.UPDATE_RESULT_BY_PK,
                query = "update StorageVerificationTask o set " +
                        "o.updatedTime=current_timestamp, " +
                        "o.completed=?2, " +
                        "o.failed=?3 " +
                        "where pk=?1")
})
public class StorageVerificationTask {
    public static final String UPDATE_RESULT_BY_PK = "StorageVerificationTask.UpdateResultByPk";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "pk")
    private long pk;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", updatable = false)
    private Date createdTime;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    @Basic(optional = false)
    @Column(name = "local_aet", updatable = false)
    private String localAET;

    @Column(name = "stgcmt_policy", updatable = false)
    private StorageVerificationPolicy storageVerificationPolicy;

    @Column(name = "update_location_status", updatable = false)
    private Boolean updateLocationStatus;

    @Column(name = "storage_ids", updatable = false)
    private String storageIDs;

    @Basic(optional = false)
    @Column(name = "study_iuid", updatable = false)
    private String studyInstanceUID;

    @Column(name = "series_iuid", updatable = false)
    private String seriesInstanceUID;

    @Column(name = "sop_iuid", updatable = false)
    private String sopInstanceUID;

    @Basic(optional = false)
    @Column(name = "completed")
    private int completed;

    @Basic(optional = false)
    @Column(name = "failed")
    private int failed;

    @OneToOne(cascade= CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "queue_msg_fk", updatable = false)
    private QueueMessage queueMessage;

    @PrePersist
    public void onPrePersist() {
        Date now = new Date();
        createdTime = now;
        updatedTime = now;
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdatedTime();
    }

    public void setUpdatedTime() {
        updatedTime = new Date();
    }

    public long getPk() {
        return pk;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public String getLocalAET() {
        return localAET;
    }

    public void setLocalAET(String localAET) {
        this.localAET = localAET;
    }

    public StorageVerificationPolicy getStorageVerificationPolicy() {
        return storageVerificationPolicy;
    }

    public void setStorageVerificationPolicy(StorageVerificationPolicy storageVerificationPolicy) {
        this.storageVerificationPolicy = storageVerificationPolicy;
    }

    public Boolean getUpdateLocationStatus() {
        return updateLocationStatus;
    }

    public void setUpdateLocationStatus(Boolean updateLocationStatus) {
        this.updateLocationStatus = updateLocationStatus;
    }

    public String getStorageIDsAsString() {
        return storageIDs;
    }

    public String[] getStorageIDs() {
        return StringUtils.split(storageIDs, '\\');
    }

    public void setStorageIDs(String[] storageIDs) {
        if (storageIDs != null && storageIDs.length > 0) {
            Arrays.sort(storageIDs);
            this.storageIDs = StringUtils.concat(storageIDs, '\\');
        } else {
            this.storageIDs = null;
        }
    }

    public String getStudyInstanceUID() {
        return studyInstanceUID;
    }

    public void setStudyInstanceUID(String studyInstanceUID) {
        this.studyInstanceUID = studyInstanceUID;
    }

    public String getSeriesInstanceUID() {
        return seriesInstanceUID;
    }

    public void setSeriesInstanceUID(String seriesInstanceUID) {
        this.seriesInstanceUID = seriesInstanceUID;
    }

    public String getSOPInstanceUID() {
        return sopInstanceUID;
    }

    public void setSOPInstanceUID(String sopInstanceUID) {
        this.sopInstanceUID = sopInstanceUID;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public QueueMessage getQueueMessage() {
        return queueMessage;
    }

    public void setQueueMessage(QueueMessage queueMessage) {
        this.queueMessage = queueMessage;
    }


    public static final String[] header = {
            "pk",
            "createdTime",
            "updatedTime",
            "LocalAET",
            "StgCmtPolicy",
            "UpdateLocationStatus",
            "StorageID",
            "StudyInstanceUID",
            "SeriesInstanceUID",
            "SOPInstanceUID",
            "completed",
            "failed",
            "JMSMessageID",
            "queue",
            "dicomDeviceName",
            "status",
            "scheduledTime",
            "failures",
            "batchID",
            "processingStartTime",
            "processingEndTime",
            "errorMessage",
            "outcomeMessage"
    };

    public void printRecord(CSVPrinter printer) throws IOException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        printer.printRecord(
                String.valueOf(pk),
                df.format(createdTime),
                df.format(updatedTime),
                localAET,
                storageVerificationPolicy,
                String.valueOf(updateLocationStatus),
                storageIDs,
                studyInstanceUID,
                seriesInstanceUID,
                sopInstanceUID,
                String.valueOf(completed),
                String.valueOf(failed),
                queueMessage.getMessageID(),
                queueMessage.getQueueName(),
                queueMessage.getDeviceName(),
                queueMessage.getStatus().toString(),
                queueMessage.getScheduledTime(),
                queueMessage.getNumberOfFailures() > 0 ? String.valueOf(queueMessage.getNumberOfFailures()) : null,
                queueMessage.getBatchID(),
                queueMessage.getProcessingStartTime() != null ? df.format(queueMessage.getProcessingStartTime()) : null,
                queueMessage.getProcessingEndTime() != null ? df.format(queueMessage.getProcessingEndTime()) : null,
                queueMessage.getErrorMessage(),
                queueMessage.getOutcomeMessage());
    }

    @Override
    public String toString() {
        return "StgVerTask[pk=" + pk
                + ", LocalAET=" + localAET
                + ", StudyIUID=" + studyInstanceUID
                + ", SeriesIUID=" + seriesInstanceUID
                + "]";
    }
}
