package com.laudoecia.api.service;

import java.util.List;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.utils.Utils;

@Service
public class WorklistService {	
	@Autowired
	private PatientService service;
	private List<Attributes> lista;
	private List<Patient> paciente;
	private Attributes atributo;
	
	public List<Attributes> Listar() {
		this.CriarLista();
		return this.lista;
	}
	
	private void CriarLista() {
		this.paciente = this.service.Listar();
		
		for(Patient pac : this.paciente) {
			this.atributo = new Attributes();
			atributo.setString(Tag.PatientID, VR.LO, pac.getPatientid());
			atributo.setString(Tag.PatientName, VR.PN, pac.getPatientname());
			atributo.setString(Tag.PatientSex, VR.CS, pac.getSexo().getValor());
			atributo.setString(Tag.PatientAge, VR.AS, pac.getPatientage());
			atributo.setDate(Tag.PatientBirthDate,Utils.ConverterToDate(pac.getBirthday()));			
			
			this.lista.add(atributo);
		}
	}
	
//	private void CriarStudy(List<Study> listaEstudo) {
//		for(Study estudo : listaEstudo) {
//			this.atributo.setString(Tag.AccessionNumber, VR.SH, estudo.getAccessionnumber());
//			this.atributo.setString(Tag.AdditionalPatientHistory, null, estudo.getAdditionalpatienthistory());
//			this.atributo.setString(Tag.AdmittingDiagnosesDescription, null, estudo.getAdmittingdiagnosesdescription());
//			this.atributo.setString(Tag.ReferringPhysicianName, VR.PN, estudo.getReferringphysicianname());
//			this.atributo.setDate(Tag.StudyDateAndTime, null, estudo.getStudydatetime());
//			this.atributo.setString(Tag.StudyID, null, estudo.getStudyid());
//			this.atributo.setString(Tag.StudyDescription, VR.LO, estudo.getStudydescription());
//			this.atributo.setString(Tag.StudyInstanceUID, VR.UI, estudo.getStudyinstanceuid());
//			this.atributo.setString(Tag.StudyPriorityID, null, estudo.getStudypriorityid());
//			
//			this.atributo.add
//		}
//	}
	
	

}
