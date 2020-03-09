package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
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
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.csv.CSVPrinter;

import com.laudoecia.api.domain.AttributesBlob;

@Entity
@Table(name = "diff_task",
        indexes = {
                @Index(columnList = "local_aet"),
                @Index(columnList = "primary_aet"),
                @Index(columnList = "secondary_aet"),
                @Index(columnList = "created_time"),
                @Index(columnList = "updated_time"),
                @Index(columnList = "check_missing"),
                @Index(columnList = "check_different"),
                @Index(columnList = "compare_fields") }
)
@NamedQueries({
        @NamedQuery(name = DiffTask.FIND_ATTRS_BY_PK,
                query = "select attrs.encodedAttributes from DiffTask o join o.diffTaskAttributes attrs where o.pk=?1"),
        @NamedQuery(name = DiffTask.COUNT_BY_BATCH_ID,
                query = "select count(o) from DiffTask o where o.queueMessage.batchID=?1")
})
public class DiffTask {

    public static final String FIND_ATTRS_BY_PK = "DiffTask.FindAttrsByPk";
    public static final String COUNT_BY_BATCH_ID = "DiffTask.CountByBatchId";

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

    @Basic(optional = false)
    @Column(name = "primary_aet", updatable = false)
    private String primaryAET;

    @Basic(optional = false)
    @Column(name = "secondary_aet", updatable = false)
    private String secondaryAET;

    @Basic(optional = false)
    @Column(name = "query_str", updatable = false)
    private String queryString;

    @Basic(optional = false)
    @Column(name = "check_missing", updatable = false)
    private boolean checkMissing;

    @Basic(optional = false)
    @Column(name = "check_different", updatable = false)
    private boolean checkDifferent;

    @Column(name = "compare_fields", updatable = false)
    private String compareFields;

    @Basic(optional = false)
    @Column(name = "matches")
    private int matches;

    @Basic(optional = false)
    @Column(name = "missing")
    private int missing;

    @Basic(optional = false)
    @Column(name = "different")
    private int different;

    @OneToOne(cascade= CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "queue_msg_fk", updatable = false)
    private QueueMessage queueMessage;

    @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "diff_task_attrs",
            joinColumns = @JoinColumn(
                    name = "diff_task_fk",
                    referencedColumnName = "pk"),
            inverseJoinColumns = @JoinColumn(
                    name = "dicomattrs_fk",
                    referencedColumnName = "pk")
    )
    private Collection<AttributesBlob> diffTaskAttributes;

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

    public String getPrimaryAET() {
        return primaryAET;
    }

    public void setPrimaryAET(String primaryAET) {
        this.primaryAET = primaryAET;
    }

    public String getSecondaryAET() {
        return secondaryAET;
    }

    public void setSecondaryAET(String secondaryAET) {
        this.secondaryAET = secondaryAET;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public boolean isCheckMissing() {
        return checkMissing;
    }

    public void setCheckMissing(boolean checkMissing) {
        this.checkMissing = checkMissing;
    }

    public boolean isCheckDifferent() {
        return checkDifferent;
    }

    public void setCheckDifferent(boolean checkDifferent) {
        this.checkDifferent = checkDifferent;
    }

    public String getCompareFields() {
        return compareFields;
    }

    public void setCompareFields(String compareFields) {
        this.compareFields = compareFields;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getMissing() {
        return missing;
    }

    public void setMissing(int missing) {
        this.missing = missing;
    }

    public int getDifferent() {
        return different;
    }

    public void setDifferent(int different) {
        this.different = different;
    }

    public void reset() {
        matches = 0;
        missing = 0;
        different = 0;
    }

    public QueueMessage getQueueMessage() {
        return queueMessage;
    }

    public void setQueueMessage(QueueMessage queueMessage) {
        this.queueMessage = queueMessage;
    }

    public Collection<AttributesBlob> getDiffTaskAttributes() {
        return diffTaskAttributes;
    }

   
    public static final String[] header = {
            "pk",
            "LocalAET",
            "PrimaryAET",
            "SecondaryAET",
            "QueryString",
            "checkMissing",
            "checkDifferent",
            "matches",
            "missing",
            "different",
            "comparefield",
            "createdTime",
            "updatedTime",
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
                localAET,
                primaryAET,
                secondaryAET,
                queryString,
                String.valueOf(checkMissing),
                String.valueOf(checkDifferent),
                matches,
                missing,
                different,
                compareFields,
                df.format(createdTime),
                df.format(updatedTime),
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
        return "DiffTask[pk=" + pk
                + ", PrimaryAET=" + primaryAET
                + ", DestinationAET=" + secondaryAET
                + ", QueryString=" + queryString
                + "]";
    }
}
