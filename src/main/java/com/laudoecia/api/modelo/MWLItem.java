package com.laudoecia.api.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name = "mwlitem")
public class MWLItem {
    private Long codigo;
    private long version;    
    private Date datacriacao;
    private Date datamodificacao;
    private String scheduledprocedurestepid;
    private String requestedprocedureid;
    private String studyinstanceuid;
    private String accessionnumber;
    private String modality;
    private String scheduledstartdate;
    private String scheduledstarttime;
    private String status;
    private String admissionid;
    private String institutionaldepartmentname;
    private String institutionname;
    private String localaet;
    private List<ScheduledStationAETitle> scheduledstationaets; 
    private Paciente paciente;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Version
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Date getDatacriacao() {
		return datacriacao;
	}
	
	public void setDatacriacao(Date datacriacao) {
		this.datacriacao = datacriacao;
	}

	public Date getDatamodificacao() {
		return datamodificacao;
	}
	
	public void setDatamodificacao(Date datamodificacao) {
		this.datamodificacao = datamodificacao;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(mappedBy = "mwlitem", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ScheduledStationAETitle> getScheduledstationaets() {
		return scheduledstationaets;
	}

	public void setScheduledstationaets(List<ScheduledStationAETitle> scheduledstationaets) {
		this.scheduledstationaets = scheduledstationaets;
	}

	@ManyToOne
    @JoinColumn(name = "patient_codigo")
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public String getAdmissionid() {
		return admissionid;
	}
	
	public void setAdmissionid(String admissionid) {
		this.admissionid = admissionid;
	}
	
	public String getInstitutionaldepartmentname() {
		return institutionaldepartmentname;
	}
	
	public void setInstitutionaldepartmentname(String institutionaldepartmentname) {
		this.institutionaldepartmentname = institutionaldepartmentname;
	}
	
	public String getInstitutionname() {
		return institutionname;
	}
	
	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}
	
	public String getLocalaet() {
		return localaet;
	}
	
	public void setLocalaet(String localaet) {
		this.localaet = localaet;
	}
	
	@PrePersist
    public void GravarDatas() {
        Date now = new Date();
        datacriacao = now;
        datamodificacao = now;
    }

    @PreUpdate
    public void AtualizaData() {
    	datamodificacao = new Date();
    }

}
