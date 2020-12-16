package com.laudoecia.api.domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.laudoecia.api.domain.enuns.VerificationStatus;
import com.laudoecia.api.utilities.AttributeFilter;
import com.laudoecia.api.utilities.BlobCorruptedException;
import com.laudoecia.api.utilities.FuzzyStr;
import com.laudoecia.api.utils.Utils;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@idpatient")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

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
	private LocalDate verificationtime;
    private long version;
    private VerificationStatus verificationStatus = VerificationStatus.UNVERIFIED;
    private int failedverifications;
    private String patientcustomattribute1;
    private String patientcustomattribute2;
    private String patientcustomattribute3;
    private int numberofstudies;
    private AttributesBlob attributesblob;
    private PersonName personname;
    private PersonName responsibleperson;
    private Patient mergedwith;
    private PatientID patientidclasse;
    private String observacoes;

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

	public LocalDate getVerificationtime() {
		return verificationtime;
	}

	public void setVerificationtime(LocalDate verificationtime) {
		this.verificationtime = verificationtime;
	}

	@Version
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

    @Enumerated(EnumType.ORDINAL)
	public VerificationStatus getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(VerificationStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public int getFailedverifications() {
		return failedverifications;
	}

	public void setFailedverifications(int failedverifications) {
		this.failedverifications = failedverifications;
	}

	public String getPatientcustomattribute1() {
		return patientcustomattribute1;
	}

	public void setPatientcustomattribute1(String patientcustomattribute1) {
		this.patientcustomattribute1 = patientcustomattribute1;
	}

	public String getPatientcustomattribute2() {
		return patientcustomattribute2;
	}

	public void setPatientcustomattribute2(String patientcustomattribute2) {
		this.patientcustomattribute2 = patientcustomattribute2;
	}

	public String getPatientcustomattribute3() {
		return patientcustomattribute3;
	}

	public void setPatientcustomattribute3(String patientcustomattribute3) {
		this.patientcustomattribute3 = patientcustomattribute3;
	}

	public int getNumberofstudies() {
		return numberofstudies;
	}

	public void setNumberofstudies(int numberofstudies) {
		this.numberofstudies = numberofstudies;
	}

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	public AttributesBlob getAttributesblob() {
		return attributesblob;
	}

	public void setAttributesblob(AttributesBlob attributesblob) {
		this.attributesblob = attributesblob;
	}

	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personname_fk")
	public PersonName getPersonname() {
		return personname;
	}

	public void setPersonname(PersonName personname) {
		this.personname = personname;
	}

	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resp_person_fk")
	public PersonName getResponsibleperson() {
		return responsibleperson;
	}

	public void setResponsibleperson(PersonName responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	@ManyToOne
    @JoinColumn(name = "merge_fk")
	public Patient getMergedwith() {
		return mergedwith;
	}

	public void setMergedwith(Patient mergedwith) {
		this.mergedwith = mergedwith;
	}

	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_id_fk")
	public PatientID getPatientidclasse() {
		return patientidclasse;
	}

	public void setPatientidclasse(PatientID patientidclasse) {
		this.patientidclasse = patientidclasse;
	}
	
	
	public Attributes pegarAttributes() throws BlobCorruptedException {
        return attributesblob.getAttributes();
    }
	
	public String getObservacoes() {
		return observacoes;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	
    public void insereAttributes(Attributes attrs, AttributeFilter filter, FuzzyStr fuzzyStr) {
        //patientname = PersonName.valueOf(attrs.getString(Tag.PatientName), fuzzyStr, patientname);
        String datanasc = attrs.getString(Tag.PatientBirthDate, "*");
        Date datan = Utils.TransformandoEmDate(datanasc);
        birthday = Utils.ConverterToLocalDate(datan);

        patientcustomattribute1 =  AttributeFilter.selectStringValue(attrs, filter.getCustomAttribute1(), "*");
        patientcustomattribute2 = AttributeFilter.selectStringValue(attrs, filter.getCustomAttribute2(), "*");
        patientcustomattribute3 = AttributeFilter.selectStringValue(attrs, filter.getCustomAttribute3(), "*");

        Attributes blobAttrs = new Attributes(attrs, filter.getSelection());
        if (attributesblob == null)
            attributesblob = new AttributesBlob(blobAttrs);
        else
            attributesblob.setAttributes(blobAttrs);

        responsibleperson = PersonName.valueOf(attrs.getString(Tag.ResponsiblePerson), fuzzyStr, responsibleperson);
    }

	@Override
	public String toString() {
		return "Patient [idpatient=" + idpatient + ", patientid=" + patientid + ", patientname=" + patientname
				+ ", birthday=" + birthday + ", patientage=" + patientage + ", patientsex=" + patientsex
				+ ", datecreate=" + datecreate + ", datemodify=" + datemodify + "]";
	}

	

    
}