package com.laudoecia.api.worklist;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;

import com.laudoecia.api.domain.MWLItem;

public class ConverterParaAtributo {

	public Attributes ConverterPadaAtributos(MWLItem work) {
		Attributes atributos = new Attributes();

		atributos.setString(Tag.StudyDate, VR.DA, "20200101");	
		atributos.setString(Tag.StudyTime, VR.TM, "050505");	
		atributos.setString(Tag.AccessionNumber, VR.SH, "US");	
		atributos.setString(Tag.InstitutionName, VR.LO, "US");
		atributos.setString(Tag.InstitutionAddress, VR.ST, "US");
		atributos.setString(Tag.ReferringPhysicianName, VR.PN, "US");	
		atributos.setString(Tag.AdmittingDiagnosesDescription, VR.LO, "US");	
		atributos.setString(Tag.PatientName, VR.PN, "VIVALDI^ANTONIO");	
		atributos.setString(Tag.PatientID, VR.LO, "US");
		atributos.setString(Tag.IssuerOfPatientID, VR.LO, "US");	
		atributos.setString(Tag.PatientBirthDate, VR.DA, "16780304");
		atributos.setString(Tag.PatientSex, VR.CS, "M");
		atributos.setString(Tag.OtherPatientNames, VR.PN, "US");	
		atributos.setString(Tag.PatientSize, VR.DS, "US");
		atributos.setString(Tag.PatientWeight, VR.DS, "US");	
		atributos.setString(Tag.PatientAddress, VR.LO, "US");	
		atributos.setString(Tag.MilitaryRank, VR.LO, "US");	
		atributos.setString(Tag.MedicalAlerts, VR.LO, "US");
		//atributos.setString(Tag.Allergies, VR.LO, "US");	
		atributos.setString(Tag.EthnicGroup, VR.SH, "US");	
		atributos.setString(Tag.SmokingStatus, VR.CS, "US");
		atributos.setString(Tag.AdditionalPatientHistory, VR.LT, "US");
		atributos.setString(Tag.PregnancyStatus, VR.US, "2");
		atributos.setString(Tag.LastMenstrualDate, VR.DA, "US");
		atributos.setString(Tag.ResponsiblePerson, VR.SH, "US");
		atributos.setString(Tag.ResponsiblePersonRole, VR.SH, "US");
		atributos.setString(Tag.PatientComments, VR.LT, "US");	
		atributos.setString(Tag.StudyInstanceUID, VR.UI, "1.2.276.0.7230010.3.2.101");
		atributos.setString(Tag.RequestingPhysician, VR.PN, "SMITH");
		atributos.setString(Tag.RequestingService, VR.LO, "US");	
		atributos.setString(Tag.RequestedProcedureDescription, VR.LO, "EXAM6");
		atributos.setString(Tag.AdmissionID, VR.LO, "assdfasf");
		atributos.setString(Tag.IssuerOfAdmissionID, VR.LO, "US");	
		atributos.setString(Tag.SpecialNeeds, VR.LO, "US");	
		atributos.setString(Tag.CurrentPatientLocation, VR.LO, "US");	
		atributos.setString(Tag.PatientState, VR.LO, "US");	
		atributos.setString(Tag.CommentsOnTheScheduledProcedureStep, VR.LT, "US");		
		atributos.setString(Tag.RequestedProcedureID, VR.SH, "RP454G234");
		atributos.setString(Tag.ReasonForTheRequestedProcedure, VR.LO, "US");
		atributos.setString(Tag.RequestedProcedurePriority, VR.SH, "US");	
		atributos.setString(Tag.PatientTransportArrangements, VR.LO, "US");
		atributos.setString(Tag.RequestedProcedureLocation, VR.LO, "US");	
		atributos.setString(Tag.ConfidentialityCode, VR.LO, "US");	
		atributos.setString(Tag.ReportingPriority, VR.SH, "US");	
		atributos.setString(Tag.NamesOfIntendedRecipientsOfResults, VR.PN, "US");
		atributos.setString(Tag.RequestedProcedureComments, VR.LT, "US");	
		atributos.setString(Tag.ReasonForTheImagingServiceRequest, VR.LO, "US");
		atributos.setString(Tag.IssueDateOfImagingServiceRequest, VR.DA, "US");	
		atributos.setString(Tag.IssueTimeOfImagingServiceRequest, VR.TM, "US");	
		atributos.setString(Tag.OrderEnteredBy, VR.PN, "US");	
		//atributos.setString(Tag.OrderEnterersLocation, VR.SH, "US");
		atributos.setString(Tag.OrderCallbackPhoneNumber, VR.SH, "US");	
		atributos.setString(Tag.PlacerOrderNumberImagingServiceRequest, VR.LO, "US");	
		atributos.setString(Tag.FillerOrderNumberImagingServiceRequest, VR.LO, "US");	
		atributos.setString(Tag.ImagingServiceRequestComments, VR.LT, "US");
		atributos.setString(Tag.ConfidentialityConstraintOnPatientDataDescription, VR.LO, "US");
		
		//atributos.setString(Tag.ReferencedStudySequence, VR.SQ, sequencia);	
			Attributes studosequence = new Attributes();
			studosequence.setString(Tag.ReferencedSOPClassUID, VR.UI, "US");
			studosequence.setString(Tag.ReferencedSOPInstanceUID, VR.UI, "US");	
			atributos.newSequence(Tag.ReferencedStudySequence, 1).add(studosequence);
			
		//atributos.setString(Tag.ReferencedPatientSequence, VR.SQ, "US");	
			Attributes pacientesequencia = new Attributes();
			pacientesequencia.setString(Tag.ReferencedSOPClassUID, VR.UI, "US");	
			pacientesequencia.setString(Tag.ReferencedSOPInstanceUID, VR.UI, "US");	
			atributos.newSequence(Tag.ReferencedPatientSequence, 1).add(pacientesequencia);
		
		//atributos.setString(Tag.RequestedProcedureCodeSequence, VR.SQ, "US");	
			Attributes procsequence = new Attributes();
			procsequence.setString(Tag.CodeValue, VR.SH, "US");
			procsequence.setString(Tag.CodingSchemeDesignator, VR.SH, "US");
			procsequence.setString(Tag.CodingSchemeVersion, VR.SH, "US");	
			procsequence.setString(Tag.CodeMeaning, VR.LO, "US");	
			atributos.newSequence(Tag.RequestedProcedureCodeSequence, 1).add(procsequence);
		
		//atributos.setString(Tag.ScheduledProcedureStepSequence, VR.SQ, "US");	
			Attributes stepsequence = new Attributes();
			stepsequence.setString(Tag.Modality, VR.CS, "US");	
			stepsequence.setString(Tag.RequestedContrastAgent, VR.LO, "US");
			stepsequence.setString(Tag.ScheduledStationAETitle, VR.AE, "sssfsdf");
			stepsequence.setString(Tag.ScheduledProcedureStepStartDate, VR.DA, "20210115");
			stepsequence.setString(Tag.ScheduledProcedureStepStartTime, VR.TM, "040404");
			stepsequence.setString(Tag.ScheduledProcedureStepEndDate, VR.DA, "20210115");
			stepsequence.setString(Tag.ScheduledProcedureStepEndTime, VR.TM, "020304");
			stepsequence.setString(Tag.ScheduledPerformingPhysicianName, VR.PN, "wwejej");
			stepsequence.setString(Tag.ScheduledProcedureStepDescription, VR.LO, "sssaaasa");
			stepsequence.setString(Tag.ScheduledProcedureStepID, VR.SH, "US");	
			stepsequence.setString(Tag.ScheduledStationName, VR.SH, "US");	
			stepsequence.setString(Tag.ScheduledProcedureStepLocation, VR.SH, "US");	
			stepsequence.setString(Tag.PreMedication, VR.LO, "US");	
			stepsequence.setString(Tag.ScheduledProcedureStepStatus, VR.CS, "US");	
			stepsequence.setString(Tag.CommentsOnTheScheduledProcedureStep, VR.LT, "US");	
			
			//atributos.setString(Tag.ScheduledProtocolCodeSequence, VR.SQ, "US");
				Attributes protsequence = new Attributes();
				protsequence.setString(Tag.CodeValue, VR.SH, "US");
				protsequence.setString(Tag.CodingSchemeDesignator, VR.SH, "US");
				protsequence.setString(Tag.CodingSchemeVersion, VR.SH, "US");	
				protsequence.setString(Tag.CodeMeaning, VR.LO, "US");
				stepsequence.newSequence(Tag.ScheduledProtocolCodeSequence, 1).add(protsequence);
				
			atributos.newSequence(Tag.ScheduledProcedureStepSequence, 1).add(stepsequence);
		
		return atributos;
	}
}
