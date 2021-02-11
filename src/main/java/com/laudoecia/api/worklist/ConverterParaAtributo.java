package com.laudoecia.api.worklist;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;

import com.laudoecia.api.modelo.Estudo;
import com.laudoecia.api.modelo.MWLItem;

public class ConverterParaAtributo {

	public Attributes ConverterParaAtributos(MWLItem work) {
		Attributes atributos = new Attributes();

		try {
			atributos.setString(Tag.AccessionNumber, VR.SH, work.getAccessionnumber());	
			atributos.setString(Tag.ReferringPhysicianName, VR.PN, work.getPaciente().getStudyes().get(0).getReferringphysicianname());
			atributos.setString(Tag.PatientName, VR.PN, work.getPaciente().getNome());	
			atributos.setString(Tag.PatientID, VR.LO, work.getPaciente().getCodigo());
			atributos.setString(Tag.PatientBirthDate, VR.DA, work.getPaciente().getDatanasc().toString());
			atributos.setString(Tag.PatientSex, VR.CS, work.getPaciente().getSexo());
			atributos.setString(Tag.PatientSize, VR.DS, work.getPaciente().getTamanho());
			atributos.setString(Tag.PatientWeight, VR.DS, work.getPaciente().getPeso());	
			atributos.setString(Tag.LastMenstrualDate, VR.DA, work.getPaciente().getDatamenstruacao().toString());
			atributos.setString(Tag.StudyInstanceUID, VR.UI, work.getStudyinstanceuid());
			atributos.setString(Tag.RequestedProcedureID, VR.SH, work.getRequestedprocedureid());
			
			for(Estudo estudo : work.getPaciente().getStudyes()) {
				//atributos.setString(Tag.ReferencedStudySequence, VR.SQ, sequencia);	
					Attributes studosequence = new Attributes();
					studosequence.setString(Tag.ReferencedSOPClassUID, VR.UI,"");
					studosequence.setString(Tag.ReferencedSOPInstanceUID, VR.UI, estudo.getStudyinstanceuid());	
				atributos.newSequence(Tag.ReferencedStudySequence, 1).add(studosequence);		
			}
			
				
			//atributos.setString(Tag.RequestedProcedureCodeSequence, VR.SQ, "US");	
				Attributes procsequence = new Attributes();
				procsequence.setString(Tag.CodeValue, VR.SH, "");
				procsequence.setString(Tag.CodingSchemeDesignator, VR.SH, "");
				procsequence.setString(Tag.CodingSchemeVersion, VR.SH, "");	
				procsequence.setString(Tag.CodeMeaning, VR.LO, "");	
				procsequence.setString(Tag.MappingResource, VR.CS, "");
				procsequence.setString(Tag.ContextGroupVersion, VR.DT, "");
				procsequence.setString(Tag.ContextGroupLocalVersion, VR.DT, "");
				procsequence.setString(Tag.ContextGroupExtensionFlag, VR.CS, "");
				procsequence.setString(Tag.ContextGroupExtensionCreatorUID, VR.UI, "");
				procsequence.setString(Tag.ContextIdentifier, VR.CS, "");
				procsequence.setString(Tag.ContextUID, VR.UI, "");
				procsequence.setString(Tag.MappingResourceUID, VR.UI, "");
				procsequence.setString(Tag.LongCodeValue, VR.UC, "");
				procsequence.setString(Tag.URNCodeValue, VR.UR, "");
				procsequence.setString(Tag.MappingResourceName, VR.LO, "");
				
				//atributos.setString(Tag.EquivalentCodeSequence, VR.SQ, "US");	
					Attributes equivacodesequence = new Attributes();
					equivacodesequence.setString(Tag.CodeValue, VR.SH, "");
					equivacodesequence.setString(Tag.CodingSchemeDesignator, VR.SH, "");
					equivacodesequence.setString(Tag.CodingSchemeVersion, VR.SH, "");	
					equivacodesequence.setString(Tag.CodeMeaning, VR.LO, "");	
					equivacodesequence.setString(Tag.MappingResource, VR.CS, "");
					equivacodesequence.setString(Tag.ContextGroupVersion, VR.DT, "");
					equivacodesequence.setString(Tag.ContextGroupLocalVersion, VR.DT, "");
					equivacodesequence.setString(Tag.ContextGroupExtensionFlag, VR.CS, "");
					equivacodesequence.setString(Tag.ContextGroupExtensionCreatorUID, VR.UI, "");
					equivacodesequence.setString(Tag.ContextIdentifier, VR.CS, "");
					equivacodesequence.setString(Tag.ContextUID, VR.UI, "");
					equivacodesequence.setString(Tag.MappingResourceUID, VR.UI, "");
					equivacodesequence.setString(Tag.LongCodeValue, VR.UC, "");
					equivacodesequence.setString(Tag.URNCodeValue, VR.UR, "");
					equivacodesequence.setString(Tag.MappingResourceName, VR.LO, "");
					procsequence.newSequence(Tag.RequestedProcedureCodeSequence, 1).add(equivacodesequence);
			atributos.newSequence(Tag.EquivalentCodeSequence, 1).add(procsequence);
					
			//atributos.setString(Tag.ScheduledProcedureStepSequence, VR.SQ, "US");	
				Attributes stepsequence = new Attributes();
				stepsequence.setString(Tag.Modality, VR.CS, work.getModality());	
				stepsequence.setString(Tag.ScheduledStationAETitle, VR.AE, work.getScheduledstationaets().get(0).getAetitle());
				stepsequence.setString(Tag.ScheduledProcedureStepStartDate, VR.DA, work.getScheduledstartdate());
				stepsequence.setString(Tag.ScheduledProcedureStepStartTime, VR.TM, work.getScheduledstarttime());
				stepsequence.setString(Tag.ScheduledPerformingPhysicianName, VR.PN, work.getPaciente().getPersonname().getSobrenome());
				stepsequence.setString(Tag.ScheduledProcedureStepDescription, VR.LO, "nao achei");
				stepsequence.setString(Tag.ScheduledProcedureStepID, VR.SH, work.getScheduledprocedurestepid());
				stepsequence.setString(Tag.ScheduledStationName, VR.SH, work.getScheduledstationaets().get(0).getAetitle());	
				stepsequence.setString(Tag.ScheduledProcedureStepLocation, VR.SH, "nao achei");
				stepsequence.setString(Tag.RequestedContrastAgent, VR.LO, "US");
				stepsequence.setString(Tag.ScheduledProcedureStepEndDate, VR.DA, "20210115");
				stepsequence.setString(Tag.ScheduledProcedureStepEndTime, VR.TM, "020304");	
				stepsequence.setString(Tag.PreMedication, VR.LO, "US");	
				stepsequence.setString(Tag.ScheduledProcedureStepStatus, VR.CS, "US");	
				stepsequence.setString(Tag.CommentsOnTheScheduledProcedureStep, VR.LT, "US");	
				
				//atributos.setString(Tag.ScheduledProtocolCodeSequence, VR.SQ, "US");
					Attributes protsequence = new Attributes();
					protsequence.setString(Tag.CodeValue, VR.SH, "");
					protsequence.setString(Tag.CodingSchemeDesignator, VR.SH, "");
					protsequence.setString(Tag.CodingSchemeVersion, VR.SH, "");	
					protsequence.setString(Tag.CodeMeaning, VR.LO, "");
					stepsequence.newSequence(Tag.ScheduledProtocolCodeSequence, 1).add(protsequence);
			
				atributos.newSequence(Tag.ScheduledProcedureStepSequence, 1).add(stepsequence);
		} catch (Exception e) {
			e.printStackTrace();
		}
					
		return atributos;
		
	}
	
//	public Attributes ConverterParaAtributos(MWLItem work) {
//		Attributes atributos = new Attributes();
//
//		try {
//			atributos.setString(Tag.AccessionNumber, VR.SH, work.getAccessionnumber());	
//			atributos.setString(Tag.ReferringPhysicianName, VR.PN, work.getPatient().getStudyes().get(0).getReferringphysicianname());
//			atributos.setString(Tag.PatientName, VR.PN, work.getPatient().getPatientname());	
//			atributos.setString(Tag.PatientID, VR.LO, work.getPatient().getPatientid());
//			atributos.setString(Tag.PatientBirthDate, VR.DA, work.getPatient().getBirthday().toString());
//			atributos.setString(Tag.PatientSex, VR.CS, work.getPatient().getPatientsex());
//			atributos.setString(Tag.PatientSize, VR.DS, work.getPatient().getPatientesize());
//			atributos.setString(Tag.PatientWeight, VR.DS, work.getPatient().getPatientweight());	
//			atributos.setString(Tag.LastMenstrualDate, VR.DA, work.getPatient().getLastmentrualdate().toString());
//			atributos.setString(Tag.StudyInstanceUID, VR.UI, work.getStudyinstanceuid());
//			atributos.setString(Tag.RequestedProcedureID, VR.SH, work.getRequestedprocedureid());
//			
//			atributos.setString(Tag.StudyDate, VR.DA, "20200101");	
//			atributos.setString(Tag.StudyTime, VR.TM, "050505");	
//			atributos.setString(Tag.InstitutionName, VR.LO, "US");
//			atributos.setString(Tag.InstitutionAddress, VR.ST, "US");	
//			atributos.setString(Tag.AdmittingDiagnosesDescription, VR.LO, "US");	
//			atributos.setString(Tag.IssuerOfPatientID, VR.LO, "US");	
//			atributos.setString(Tag.OtherPatientNames, VR.PN, "US");		
//			atributos.setString(Tag.PatientAddress, VR.LO, "US");	
//			atributos.setString(Tag.MilitaryRank, VR.LO, "US");	
//			atributos.setString(Tag.MedicalAlerts, VR.LO, "US");
//			//atributos.setString(Tag.Allergies, VR.LO, "US");	
//			atributos.setString(Tag.EthnicGroup, VR.SH, "US");	
//			atributos.setString(Tag.SmokingStatus, VR.CS, "US");
//			atributos.setString(Tag.AdditionalPatientHistory, VR.LT, "US");
//			atributos.setString(Tag.PregnancyStatus, VR.US, "2");
//			atributos.setString(Tag.ResponsiblePerson, VR.SH, "US");
//			atributos.setString(Tag.ResponsiblePersonRole, VR.SH, "US");
//			atributos.setString(Tag.PatientComments, VR.LT, "US");	
//			atributos.setString(Tag.RequestingPhysician, VR.PN, "SMITH");
//			atributos.setString(Tag.RequestingService, VR.LO, "US");	
//			atributos.setString(Tag.RequestedProcedureDescription, VR.LO, "EXAM6");
//			atributos.setString(Tag.AdmissionID, VR.LO, "aff maria");
//			atributos.setString(Tag.IssuerOfAdmissionID, VR.LO, "US");	
//			atributos.setString(Tag.SpecialNeeds, VR.LO, "US");	
//			atributos.setString(Tag.CurrentPatientLocation, VR.LO, "US");	
//			atributos.setString(Tag.PatientState, VR.LO, "US");	
//			atributos.setString(Tag.CommentsOnTheScheduledProcedureStep, VR.LT, "US");		
//			atributos.setString(Tag.ReasonForTheRequestedProcedure, VR.LO, "US");
//			atributos.setString(Tag.RequestedProcedurePriority, VR.SH, "US");	
//			atributos.setString(Tag.PatientTransportArrangements, VR.LO, "US");
//			atributos.setString(Tag.RequestedProcedureLocation, VR.LO, "US");	
//			atributos.setString(Tag.ConfidentialityCode, VR.LO, "US");	
//			atributos.setString(Tag.ReportingPriority, VR.SH, "US");	
//			atributos.setString(Tag.NamesOfIntendedRecipientsOfResults, VR.PN, "US");
//			atributos.setString(Tag.RequestedProcedureComments, VR.LT, "US");	
//			atributos.setString(Tag.ReasonForTheImagingServiceRequest, VR.LO, "US");
//			atributos.setString(Tag.IssueDateOfImagingServiceRequest, VR.DA, "US");	
//			atributos.setString(Tag.IssueTimeOfImagingServiceRequest, VR.TM, "US");	
//			atributos.setString(Tag.OrderEnteredBy, VR.PN, "US");	
//			//atributos.setString(Tag.OrderEnterersLocation, VR.SH, "US");
//			atributos.setString(Tag.OrderCallbackPhoneNumber, VR.SH, "US");	
//			atributos.setString(Tag.PlacerOrderNumberImagingServiceRequest, VR.LO, "US");	
//			atributos.setString(Tag.FillerOrderNumberImagingServiceRequest, VR.LO, "US");	
//			atributos.setString(Tag.ImagingServiceRequestComments, VR.LT, "US");
//			atributos.setString(Tag.ConfidentialityConstraintOnPatientDataDescription, VR.LO, "US");
//			
//
//			
//			//atributos.setString(Tag.ReferencedStudySequence, VR.SQ, sequencia);	
//				Attributes studosequence = new Attributes();
//				studosequence.setString(Tag.ReferencedSOPClassUID, VR.UI, "US");
//				studosequence.setString(Tag.ReferencedSOPInstanceUID, VR.UI, "US");	
//				atributos.newSequence(Tag.ReferencedStudySequence, 1).add(studosequence);		
//				
//			//atributos.setString(Tag.ReferencedPatientSequence, VR.SQ, "US");	
//				Attributes pacientesequencia = new Attributes();
//				pacientesequencia.setString(Tag.ReferencedSOPClassUID, VR.UI, "US");	
//				pacientesequencia.setString(Tag.ReferencedSOPInstanceUID, VR.UI, "US");	
//				atributos.newSequence(Tag.ReferencedPatientSequence, 1).add(pacientesequencia);
//						
//			//atributos.setString(Tag.RequestedProcedureCodeSequence, VR.SQ, "US");	
//				Attributes procsequence = new Attributes();
//				procsequence.setString(Tag.CodeValue, VR.SH, "US");
//				procsequence.setString(Tag.CodingSchemeDesignator, VR.SH, "US");
//				procsequence.setString(Tag.CodingSchemeVersion, VR.SH, "US");	
//				procsequence.setString(Tag.CodeMeaning, VR.LO, "US");	
//				atributos.newSequence(Tag.RequestedProcedureCodeSequence, 1).add(procsequence);
//			
//			//atributos.setString(Tag.ScheduledProcedureStepSequence, VR.SQ, "US");	
//				Attributes stepsequence = new Attributes();
//				stepsequence.setString(Tag.Modality, VR.CS, work.getModality());	
//				stepsequence.setString(Tag.ScheduledStationAETitle, VR.AE, work.getScheduledstationaets().get(0).getAetitle());
//				stepsequence.setString(Tag.ScheduledProcedureStepStartDate, VR.DA, work.getScheduledstartdate());
//				stepsequence.setString(Tag.ScheduledProcedureStepStartTime, VR.TM, work.getScheduledstarttime());
//				stepsequence.setString(Tag.ScheduledPerformingPhysicianName, VR.PN, work.getScheduledperformingphysicianname().getNameprefix());
//				stepsequence.setString(Tag.ScheduledProcedureStepDescription, VR.LO, "nao achei");
//				stepsequence.setString(Tag.ScheduledProcedureStepID, VR.SH, work.getScheduledprocedurestepid());
//				stepsequence.setString(Tag.ScheduledStationName, VR.SH, work.getScheduledstationaets().get(0).getAetitle());	
//				stepsequence.setString(Tag.ScheduledProcedureStepLocation, VR.SH, "nao achei");
//				
//				stepsequence.setString(Tag.RequestedContrastAgent, VR.LO, "US");
//				stepsequence.setString(Tag.ScheduledProcedureStepEndDate, VR.DA, "20210115");
//				stepsequence.setString(Tag.ScheduledProcedureStepEndTime, VR.TM, "020304");	
//				stepsequence.setString(Tag.PreMedication, VR.LO, "US");	
//				stepsequence.setString(Tag.ScheduledProcedureStepStatus, VR.CS, "US");	
//				stepsequence.setString(Tag.CommentsOnTheScheduledProcedureStep, VR.LT, "US");	
//				
//				//atributos.setString(Tag.ScheduledProtocolCodeSequence, VR.SQ, "US");
//					Attributes protsequence = new Attributes();
//					protsequence.setString(Tag.CodeValue, VR.SH, "");
//					protsequence.setString(Tag.CodingSchemeDesignator, VR.SH, "");
//					protsequence.setString(Tag.CodingSchemeVersion, VR.SH, "");	
//					protsequence.setString(Tag.CodeMeaning, VR.LO, "");
//					protsequence.setString(Tag.MappingResource, VR.LO, "");
//					protsequence.setString(Tag.ContextGroupVersion, VR.LO, "");
//					protsequence.setString(Tag.ContextGroupLocalVersion, VR.LO, "");
//					protsequence.setString(Tag.ContextGroupExtensionFlag, VR.LO, "");
//					protsequence.setString(Tag.ContextGroupExtensionCreatorUID, VR.LO, "");
//					protsequence.setString(Tag.ContextIdentifier, VR.LO, "");
//					protsequence.setString(Tag.ContextUID, VR.LO, "");
//					protsequence.setString(Tag.MappingResourceUID, VR.LO, "");
//					protsequence.setString(Tag.LongCodeValue, VR.LO, "");
//					protsequence.setString(Tag.URNCodeValue, VR.LO, "");
//					protsequence.setString(Tag.MappingResourceName, VR.LO, "");
//					
//					//atributos.setString(Tag.EquivalentCodeSequence, VR.SQ, sequencia);	
//						Attributes equivcodesequence = new Attributes();
//						equivcodesequence.setString(Tag.CodeValue, VR.UI, "");
//						equivcodesequence.setString(Tag.CodingSchemeDesignator, VR.UI, "");
//						equivcodesequence.setString(Tag.CodingSchemeVersion, VR.UI, "");
//						equivcodesequence.setString(Tag.CodeMeaning, VR.UI, "");
//						equivcodesequence.setString(Tag.MappingResource, VR.UI, "");
//						equivcodesequence.setString(Tag.ContextGroupVersion, VR.UI, "");
//						equivcodesequence.setString(Tag.ContextGroupLocalVersion, VR.UI, "");
//						equivcodesequence.setString(Tag.ContextGroupExtensionFlag, VR.UI, "");
//						equivcodesequence.setString(Tag.ContextGroupExtensionCreatorUID, VR.UI, "");
//						equivcodesequence.setString(Tag.ContextIdentifier, VR.UI, "");
//						equivcodesequence.setString(Tag.ContextUID, VR.UI, "");
//						equivcodesequence.setString(Tag.MappingResourceUID, VR.UI, "");
//						equivcodesequence.setString(Tag.LongCodeValue, VR.UI, "");
//						equivcodesequence.setString(Tag.URNCodeValue, VR.UI, "");
//						equivcodesequence.setString(Tag.MappingResourceName, VR.UI, "");
//						protsequence.newSequence(Tag.EquivalentCodeSequence, 1).add(equivcodesequence);		
//						
//					//atributos.setString(Tag.ProtocolContextSequence, VR.SQ, sequencia);	
//						Attributes protocolcontextsequence = new Attributes();
//						protocolcontextsequence.setString(Tag.ObservationDateTime, VR.UI, "");
//						protocolcontextsequence.setString(Tag.ValueType, VR.UI, "");
//						protocolcontextsequence.setString(Tag.DateTime, VR.UI, "");
//						protocolcontextsequence.setString(Tag.Date, VR.UI, "");
//						protocolcontextsequence.setString(Tag.Time, VR.UI, "");
//						protocolcontextsequence.setString(Tag.PersonName, VR.UI, "");
//						protocolcontextsequence.setString(Tag.UID, VR.UI, "");
//						protocolcontextsequence.setString(Tag.TextValue, VR.UI, "");
//						protocolcontextsequence.setString(Tag.FloatingPointValue, VR.UI, "");
//						protocolcontextsequence.setString(Tag.RationalNumeratorValue, VR.UI, "");
//						protocolcontextsequence.setString(Tag.RationalDenominatorValue, VR.UI, "");
//						protocolcontextsequence.setString(Tag.NumericValue, VR.UI, "");
//						protsequence.newSequence(Tag.ProtocolContextSequence, 1).add(protocolcontextsequence);	
//						
//						//atributos.setString(Tag.ReferencedSOPSequence, VR.SQ, sequencia);	
//							Attributes refersopsequence = new Attributes();
//							refersopsequence.setString(Tag.ReferencedSOPClassUID, VR.UI, "");
//							refersopsequence.setString(Tag.ReferencedSOPInstanceUID, VR.UI, "");	
//							refersopsequence.setString(Tag.ReferencedFrameNumber, VR.UI, "");
//							refersopsequence.setString(Tag.ReferencedSegmentNumber, VR.UI, "");	
//							protocolcontextsequence.newSequence(Tag.ReferencedSOPSequence, 1).add(refersopsequence);
//					
//					stepsequence.newSequence(Tag.ScheduledProtocolCodeSequence, 1).add(protsequence);
//				atributos.newSequence(Tag.ScheduledProcedureStepSequence, 1).add(stepsequence);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//						
//		return atributos;
//		
//	}
}




























