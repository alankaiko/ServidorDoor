
package com.laudoecia.api.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.util.DateUtils;

import com.laudoecia.api.utilities.BlobCorruptedException;
import com.laudoecia.api.utilities.FuzzyStr;
import com.laudoecia.api.utils.UtilsWorklist;

@Entity
@Table(name = "mwlitem")
public class MWLItem implements Serializable {
    public static final String SCHEDULED = "SCHEDULED";
    public static final String ARRIVED = "ARRIVED";
    public static final String READY = "READY";
    public static final String STARTED = "STARTED";
    public static final String DEPARTED = "DEPARTED";
    public static final String COMPLETED = "COMPLETED";
    public static final String DISCONTINUED = "DISCONTINUED";

    private static final long serialVersionUID = 5655030469102270878L;

    private Long codigo; 
    private long version;       
    private Date createdtime;
    private Date updatedtime;
    private String scheduledprocedurestepid;
    private String requestedprocedureid;
    private String studyinstanceuid;
    private String accessionnumber;
    private String modality;
    private String scheduledstartdate;
    private String scheduledstarttime;
    private String status;
    private AttributesBlob attributesblob;    
    private Attributes cachedattributes;
    private PersonName scheduledperformingphysicianname;
    private Collection<ScheduledStationAETitle> scheduledstationaets;
    private Patient patient;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getCodigo() {
		return codigo;
	}
    
    public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

    @Column(updatable = false)
    public Date getCreatedtime() {
		return createdtime;
	}
    
    public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

    public Date getUpdatedtime() {
		return updatedtime;
	}
    
    public void setUpdatedtime(Date updatedtime) {
		this.updatedtime = updatedtime;
	}

    public String getScheduledprocedurestepid() {
		return scheduledprocedurestepid;
	}
    
    public void setScheduledprocedurestepid(String scheduledprocedurestepid) {
		this.scheduledprocedurestepid = scheduledprocedurestepid;
	}

    public String getRequestedprocedureid() {
		return requestedprocedureid;
	}
    
    public void setRequestedprocedureid(String requestedprocedureid) {
		this.requestedprocedureid = requestedprocedureid;
	}

    public String getStudyinstanceuid() {
		return studyinstanceuid;
	}
    
    public void setStudyinstanceuid(String studyinstanceuid) {
		this.studyinstanceuid = studyinstanceuid;
	}

    public String getAccessionnumber() {
		return accessionnumber;
	}
    
    public void setAccessionnumber(String accessionnumber) {
		this.accessionnumber = accessionnumber;
	}

    public String getModality() {
        return modality;
    }
    
    public void setModality(String modality) {
		this.modality = modality;
	}

    public String getScheduledstartdate() {
		return scheduledstartdate;
	}
    
    public void setScheduledstartdate(String scheduledstartdate) {
		this.scheduledstartdate = scheduledstartdate;
	}
    
    public String getScheduledstarttime() {
		return scheduledstarttime;
	}
    
    public void setScheduledstarttime(String scheduledstarttime) {
		this.scheduledstarttime = scheduledstarttime;
	}

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "perf_phys_name_fk")
    public PersonName getScheduledperformingphysicianname() {
		return scheduledperformingphysicianname;
	}
    
    public void setScheduledperformingphysicianname(PersonName scheduledperformingphysicianname) {
		this.scheduledperformingphysicianname = scheduledperformingphysicianname;
	}

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
		this.status = status;
	}
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_fk")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @OneToMany(mappedBy = "mwlitem", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<ScheduledStationAETitle> getScheduledstationaets() {
		return scheduledstationaets;
	}
    
    public void setScheduledstationaets(Collection<ScheduledStationAETitle> scheduledstationaets) {
		this.scheduledstationaets = scheduledstationaets;
	}

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "dicomattrs_fk")
    public AttributesBlob getAttributesblob() {
		return attributesblob;
	}
    
    public void setAttributesblob(AttributesBlob attributesblob) {
		this.attributesblob = attributesblob;
	}
    
    @Transient
    public Attributes getCachedattributes() {
		return cachedattributes;
	}
    
    public void setCachedattributes(Attributes cachedattributes) {
		this.cachedattributes = cachedattributes;
	}
    
    public Attributes PegaAttributes() throws BlobCorruptedException {
        return attributesblob.getAttributes();
    }

    public void InserirAttributes(Attributes attrs, FuzzyStr fuzzyStr, String nullValue) {
        Attributes spsItem = attrs.getNestedDataset(Tag.ScheduledProcedureStepSequence);
        if (spsItem == null)
            throw new IllegalArgumentException("Missing Scheduled Procedure Step Sequence (0040,0100) Item");
        
        scheduledprocedurestepid = spsItem.getString(Tag.ScheduledProcedureStepID);
        modality = UtilsWorklist.upper(spsItem.getString(Tag.Modality, nullValue));
        Date dt = spsItem.getDate(Tag.ScheduledProcedureStepStartDateAndTime);
        
        if (dt != null) {
            scheduledstartdate = DateUtils.formatDA(null, dt);
            scheduledstarttime = spsItem.containsValue(Tag.ScheduledProcedureStepStartTime) ? DateUtils.formatTM(null, dt) : nullValue;
        } else {
            scheduledstartdate = nullValue;
            scheduledstarttime = nullValue;
        }
        scheduledperformingphysicianname = PersonName.valueOf(attrs.getString(Tag.ScheduledPerformingPhysicianName), fuzzyStr, nullValue, scheduledperformingphysicianname);
        status = spsItem.getString(Tag.ScheduledProcedureStepStatus, SCHEDULED);
        
        requestedprocedureid = attrs.getString(Tag.RequestedProcedureID);
        studyinstanceuid = attrs.getString(Tag.StudyInstanceUID);
        accessionnumber = attrs.getString(Tag.AccessionNumber);

        if (attributesblob == null)
            attributesblob = new AttributesBlob(attrs);
        else
            attributesblob.setAttributes(attrs);
    }
    
   
    
    @Override
    public String toString() {
        return "MWLItem[codigo=" + codigo
                + ", spsid=" + scheduledprocedurestepid
                + ", rpid=" + requestedprocedureid
                + ", suid=" + studyinstanceuid
                + ", accno=" + accessionnumber
                + ", modality=" + modality
                + ", performer=" + scheduledperformingphysicianname
                + ", start=" + scheduledstartdate + scheduledstarttime
                + ", status=" + status
                + "]";
    }

    @PrePersist
    public void onPrePersist() {
        Date now = new Date();
        createdtime = now;
        updatedtime = now;
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedtime = new Date();
    }
}
