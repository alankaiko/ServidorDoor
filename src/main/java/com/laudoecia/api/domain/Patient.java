package com.laudoecia.api.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@idpatient")
public class Patient implements Serializable {
	private static final long serialVersionUID = 6430339764844147679L;
    public static final String FIND_BY_PATIENT_FAMILY_NAME = "Patient.findByPatientFamilyName";

	private Long idpatient;
	private String patientid;
	private String patientname;
	private LocalDate birthday;
	private String patientage;
	private String patientsex;
	private LocalDate datecreate;
	private LocalDate datemodify;
	private List<Study> studyes;
	private Endereco endereco;
	private Contato contato;
    private PatientExtension extension;
    private long version;
    private boolean nopatientid;
    private String patientcustomattribute1;
    private String patientcustomattribute2;
    private String patientcustomattribute3;
    private AttributesBlob attributesblob;
    private PersonName patientpersonname;
    private List<Patient> previous;
    private List<PatientID> patientids;
    private List<MPPS> modalityperformedproceduresteps;
    private List<MWLItem> modalityworklistitems;
    private Patient mergedwith;
    private Collection<PatientID> linkedpatientids;

	public Patient() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdpatient() {
		return idpatient;
	}

	public void setIdpatient(Long idpatient) {
		this.idpatient = idpatient;
	}

	@Column(length = 200)
	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	@Column(length = 200)
	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	@Column(length = 20)
	public String getPatientsex() {
		return patientsex;
	}

	public void setPatientsex(String patientsex) {
		this.patientsex = patientsex;
	}

	public LocalDate getBirthday() {
		return birthday;
	}
	
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	@Column(length = 10)
	public String getPatientage() {
		return patientage;
	}

	public void setPatientage(String patientage) {
		this.patientage = patientage;
	}

	public LocalDate getDatecreate() {
		return datecreate;
	}
	
	public void setDatecreate(LocalDate datecreate) {
		this.datecreate = datecreate;
	}

	public LocalDate getDatemodify() {
		return datemodify;
	}
	
	public void setDatemodify(LocalDate datemodify) {
		this.datemodify = datemodify;
	}

	@OneToMany(mappedBy = "patient")
	public List<Study> getStudyes() {
		return studyes;
	}

	public void setStudyes(List<Study> studyes) {
		this.studyes = studyes;
	}
	

	@Embedded
	public Endereco getEndereco() {
		if(this.endereco == null)
			this.endereco = new Endereco();
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Embedded
	public Contato getContato() {
		if(this.contato == null)
			this.contato = new Contato();
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	@Embedded
	public PatientExtension getExtension() {
		return extension;
	}

	public void setExtension(PatientExtension extension) {
		this.extension = extension;
	}

	@Version
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Column(name = "no_pat_id")
	public boolean isNopatientid() {
		return nopatientid;
	}

	public void setNopatientid(boolean nopatientid) {
		this.nopatientid = nopatientid;
	}

	@Column(name = "pat_custom1")
	public String getPatientcustomattribute1() {
		return patientcustomattribute1;
	}

	public void setPatientcustomattribute1(String patientcustomattribute1) {
		this.patientcustomattribute1 = patientcustomattribute1;
	}

	@Column(name = "pat_custom2")
	public String getPatientcustomattribute2() {
		return patientcustomattribute2;
	}

	public void setPatientcustomattribute2(String patientcustomattribute2) {
		this.patientcustomattribute2 = patientcustomattribute2;
	}

	@Column(name = "pat_custom3")
	public String getPatientcustomattribute3() {
		return patientcustomattribute3;
	}

	public void setPatientcustomattribute3(String patientcustomattribute3) {
		this.patientcustomattribute3 = patientcustomattribute3;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "dicomattrs_fk")
	public AttributesBlob getAttributesblob() {
		return attributesblob;
	}

	public void setAttributesblob(AttributesBlob attributesblob) {
		this.attributesblob = attributesblob;
	}

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pat_name_fk")
	public PersonName getPatientpersonname() {
		return patientpersonname;
	}

	public void setPatientpersonname(PersonName patientpersonname) {
		this.patientpersonname = patientpersonname;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merge_fk", referencedColumnName = "idpatient")
	public Patient getMergedwith() {
		return mergedwith;
	}

	public void setMergedwith(Patient mergedwith) {
		this.mergedwith = mergedwith;
	}

	@OneToMany(mappedBy = "mergedwith", orphanRemoval = true)
	public List<Patient> getPrevious() {
		return previous;
	}

	public void setPrevious(List<Patient> previous) {
		this.previous = previous;
	}

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<PatientID> getPatientids() {
		return patientids;
	}

	public void setPatientids(List<PatientID> patientids) {
		this.patientids = patientids;
	}

	@ManyToMany
    @JoinTable(name = "rel_linked_patient_id", joinColumns = @JoinColumn(name = "patient_fk", referencedColumnName = "idpatient"), inverseJoinColumns = @JoinColumn(name = "patient_id_fk", referencedColumnName = "codigo"))
	public Collection<PatientID> getLinkedpatientids() {
		return linkedpatientids;
	}

	public void setLinkedpatientids(Collection<PatientID> linkedpatientids) {
		this.linkedpatientids = linkedpatientids;
	}

	@OneToMany(mappedBy = "patient", orphanRemoval = true)
	public List<MPPS> getModalityperformedproceduresteps() {
		return modalityperformedproceduresteps;
	}
	
	public void setModalityperformedproceduresteps(List<MPPS> modalityperformedproceduresteps) {
		this.modalityperformedproceduresteps = modalityperformedproceduresteps;
	}

	@OneToMany(mappedBy = "patient", orphanRemoval = true)
	public List<MWLItem> getModalityworklistitems() {
		return modalityworklistitems;
	}

	public void setModalityworklistitems(List<MWLItem> modalityworklistitems) {
		this.modalityworklistitems = modalityworklistitems;
	}

}
