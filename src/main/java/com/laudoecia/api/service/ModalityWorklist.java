package com.laudoecia.api.service;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;


public class ModalityWorklist {
	private Attributes atributos;
	private String PatientWeight;
	private String MedicalAlerts;
	private String Allergies;
	private String PregnancyStatus;
	private String RequestingPhysician;
	private String RequestingService;
	private String RequestedProcedureDescription;
	private String RequestedProcedureCodeSequence;
	private String AdmissionID;
	private String IssuerOfAdmissionIDSequence;
	private String SpecialNeeds;
	private String CurrentPatientLocation;
	private String PatientState;
	private String RequestedProcedureID;
	private String RequestedProcedurePriority;
	private String PatientTransportArrangements;
	private String PlacerOrderNumberImagingServiceRequest;
	private String FillerOrderNumberImagingServiceRequest;
	private String ConfidentialityConstraintOnPatientDataDescription;

	// Attributes in Scheduled Procedure Step Sequence
	private static final int[] sps = { Tag.ScheduledProcedureStepSequence };
	private String Modality;
	private String RequestedContrastAgent;
	private String ScheduledStationAETitle;
	private String ScheduledProcedureStepStartDate;
	private String ScheduledProcedureStepStartTime;
	private String ScheduledPerformingPhysicianName;
	private String ScheduledProcedureStepDescription;
	private String ScheduledProtocolCodeSequence;
	private String ScheduledProcedureStepID;
	private String ScheduledStationName;
	private String ScheduledProcedureStepLocation;
	private String PreMedication;
	private String ScheduledProcedureStepStatus;

	// Attributes in Scheduled Procedure Step Sequence / Scheduled Protocol Code
	// Sequence
	private static final int[] spc = { Tag.ScheduledProcedureStepSequence, Tag.ScheduledProtocolCodeSequence };
	private String ScheduledProtocolCodeMeaning;

//	public void Test() {
//		VR.UN
//		this.atributos.unifyCharacterSets(attrsList);
//	}

	public String getPatientID() {
		this.atributos.setString(Tag.PatientID, null, PreMedication);
		this.atributos.getVR(Tag.PatientID);
		return this.atributos.getString(Tag.PatientID);
	}
}
