package com.laudoecia.api.worklistes;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;

import com.laudoecia.api.domain.AttributesBlob;
import com.laudoecia.api.domain.CodeEntity;
import com.laudoecia.api.domain.IssuerEntity;
import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.utilities.BlobCorruptedException;

@Entity
@Table
public class UPS {

    public static final String FIND_BY_PATIENT = "UPS.findByPatient";
    public static final String FIND_BY_IUID = "UPS.findByIUID";
    public static final String FIND_BY_IUID_EAGER = "UPS.findByIUIDEager";
    public static final String FIND_WO_DELETION_LOCK = "UPS.findWithoutDeletionLock";
    public static final String DELETE_BY_PATIENT = "UPS.deleteByPatient";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "pk")
    private long pk;

    @Version
    @Column(name = "version")
    private long version;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", updatable = false)
    private Date createdTime;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    @Basic(optional = false)
    @Column(name = "ups_iuid", updatable = false)
    private String upsInstanceUID;

    @Basic(optional = false)
    @Column(name = "ups_priority")
    private UPSPriority upsPriority;

    @Basic(optional = false)
    @Column(name = "ups_label")
    private String upsLabel;

    @Basic(optional = false)
    @Column(name = "worklist_label")
    private String worklistLabel;

    @Basic(optional = false)
    @Column(name = "start_date_time")
    private String scheduledStartDateAndTime;

    @Basic(optional = false)
    @Column(name = "expiration_date_time")
    private String scheduledProcedureStepExpirationDateTime;

    @Basic(optional = false)
    @Column(name = "expected_end_date_time")
    private String expectedCompletionDateAndTime;

    @Basic(optional = false)
    @Column(name = "input_readiness_state")
    private InputReadinessState inputReadinessState;

    @Basic(optional = false)
    @Column(name = "admission_id")
    private String admissionID;

    @Basic(optional = false)
    @Column(name = "replaced_iuid")
    private String replacedSOPInstanceUID;

    @Basic(optional = false)
    @Column(name = "ups_state")
    private UPSState procedureStepState;

    @Column(name = "transaction_iuid")
    private String transactionUID;

    @Column(name = "performer_aet")
    private String performerAET;

    @ManyToOne
    @JoinColumn(name = "ups_code_fk")
    private CodeEntity scheduledWorkitemCode;

    @ManyToOne
    @JoinColumn(name = "station_name_fk")
    private CodeEntity scheduledStationNameCode;

    @ManyToOne
    @JoinColumn(name = "station_class_fk")
    private CodeEntity scheduledStationClassCode;

    @ManyToOne
    @JoinColumn(name = "station_location_fk")
    private CodeEntity scheduledStationGeographicLocationCode;

    @ManyToOne
    @JoinColumn(name = "admission_issuer_fk")
    private IssuerEntity issuerOfAdmissionID;

    @ManyToMany
    @JoinTable(name = "rel_ups_perf_code",
            joinColumns = @JoinColumn(name = "ups_fk", referencedColumnName = "pk"),
            inverseJoinColumns = @JoinColumn(name = "perf_code_fk", referencedColumnName = "pk"))
    private Collection<CodeEntity> humanPerformerCodes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ups_fk")
    private Collection<UPSRequest> referencedRequests;

    @OneToMany(mappedBy = "ups")
    private Collection<Subscription> subscriptions;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_fk")
    private Patient patient;

    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "dicomattrs_fk")
    private AttributesBlob attributesBlob;

    @Override
    public String toString() {
        return "UPS[pk=" + pk
                + ", uid=" + upsInstanceUID
                + ", state=" + procedureStepState
                + "]";
    }

    @PrePersist
    public void onPrePersist() {
        Date now = new Date();
        createdTime = now;
        updatedTime = now;
    }

    @PreUpdate
    public void onPreUpdate() {
        Date now = new Date();
        updatedTime = now;
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

    public String getUPSInstanceUID() {
        return upsInstanceUID;
    }

    public void setUpsInstanceUID(String upsInstanceUID) {
        this.upsInstanceUID = upsInstanceUID;
    }

    public UPSState getProcedureStepState() {
        return procedureStepState;
    }

    public String getTransactionUID() {
        return transactionUID;
    }

    public void setTransactionUID(String transactionUID) {
        this.transactionUID = transactionUID;
    }

    public String getPerformerAET() {
        return performerAET;
    }

    public void setPerformerAET(String performerAET) {
        this.performerAET = performerAET;
    }

    public AttributesBlob getAttributesBlob() {
        return attributesBlob;
    }

    public Attributes getAttributes() throws BlobCorruptedException {
        return attributesBlob.getAttributes();
    }

    public void setAttributes(Attributes attrs, AttributeFilter filter) {
        upsPriority = UPSPriority.valueOf(attrs.getString(Tag.ScheduledProcedureStepPriority, "MEDIUM"));
        upsLabel = attrs.getString(Tag.ProcedureStepLabel);
        worklistLabel = attrs.getString(Tag.WorklistLabel);
        scheduledStartDateAndTime = attrs.getString(Tag.ScheduledProcedureStepStartDateTime);
        scheduledProcedureStepExpirationDateTime = attrs.getString(Tag.ScheduledProcedureStepExpirationDateTime, "*");
        expectedCompletionDateAndTime = attrs.getString(Tag.ExpectedCompletionDateTime, "*");
        inputReadinessState = InputReadinessState.valueOf(attrs.getString(Tag.InputReadinessState, "READY"));
        admissionID = attrs.getString(Tag.AdmissionID, "*");
        replacedSOPInstanceUID = getString(attrs.getNestedDataset(Tag.ReplacedProcedureStepSequence),
                Tag.ReferencedSOPInstanceUID, "*");
        procedureStepState = UPSState.fromString(attrs.getString(Tag.ProcedureStepState, "SCHEDULED"));
        if (attributesBlob == null)
            attributesBlob = new AttributesBlob(new Attributes(attrs, filter.getSelection()));
        else
            attributesBlob.setAttributes(new Attributes(attrs, filter.getSelection()));

        updatedTime = new Date();
    }

    private static String getString(Attributes item, int tag, String defVal) {
        return item != null ? item.getString(tag, defVal) : defVal;
    }

    public void setScheduledWorkitemCode(CodeEntity scheduledWorkitemCode) {
        this.scheduledWorkitemCode = scheduledWorkitemCode;
    }

    public void setScheduledStationNameCode(CodeEntity scheduledStationNameCode) {
        this.scheduledStationNameCode = scheduledStationNameCode;
    }

    public void setScheduledStationClassCode(CodeEntity scheduledStationClassCode) {
        this.scheduledStationClassCode = scheduledStationClassCode;
    }

    public void setScheduledStationGeographicLocationCode(CodeEntity scheduledStationGeographicLocationCode) {
        this.scheduledStationGeographicLocationCode = scheduledStationGeographicLocationCode;
    }

    public void setIssuerOfAdmissionID(IssuerEntity issuerOfAdmissionID) {
        this.issuerOfAdmissionID = issuerOfAdmissionID;
    }

    public Collection<CodeEntity> getHumanPerformerCodes() {
        if (humanPerformerCodes == null) {
            humanPerformerCodes = new ArrayList<>();
        }
        return humanPerformerCodes;
    }

    public Collection<UPSRequest> getReferencedRequests() {
        if (referencedRequests == null) {
            referencedRequests = new ArrayList<>();
        }
        return referencedRequests;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
