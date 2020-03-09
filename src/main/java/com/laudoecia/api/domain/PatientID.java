package com.laudoecia.api.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.dcm4che3.data.IDWithIssuer;

@Entity
@Table(name = "patientid")
public class PatientID implements Serializable {
    private static final long serialVersionUID = -473286258988921273L;

    private Long codigo;
    private long version;        
    private String patid;
    private String identifiertypecode;
    private Issuer issuer;
    private Patient patient;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getCodigo() {
		return codigo;
	}
    
    public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
   

    @Basic(optional=false)
    public String getPatid() {
		return patid;
	}
    
    public void setPatid(String patid) {
		this.patid = patid;
	}

    public String getIdentifiertypecode() {
		return identifiertypecode;
	}

    public void setIdentifiertypecode(String identifiertypecode) {
		this.identifiertypecode = identifiertypecode;
	}

    @ManyToOne
    @JoinColumn(name = "patient_fk")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne
    @JoinColumn(name = "issuer_fk")
    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }
    
    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    
    @Override
    public String toString() {
        return "PatientID[pk=" + codigo
                + ", id=" + patid
                + ", issuer=" + issuer
                + "]";
    }

    public IDWithIssuer toIDWithIssuer() {
        IDWithIssuer result = new IDWithIssuer(patid, issuer);
        result.setIdentifierTypeCode(identifiertypecode);
        return result;
    }
}
