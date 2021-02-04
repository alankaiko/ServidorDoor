package com.laudoecia.api.worklistes;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.springframework.util.StringUtils;

import com.laudoecia.api.domain.MWLItem;
import com.laudoecia.api.domain.MWLItem_;
import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.domain.Patient_;
import com.laudoecia.api.utils.Utils;

public class MWLQuery extends AbstractQuery {

	private Root<MWLItem> root;
	private Join<MWLItem, Patient> rootpatient;
//	private Path<byte[]> patientAttrBlob;
//	private Path<byte[]> mwlAttrBlob;

	public MWLQuery(QueryContext context, EntityManager em) {
		super(context, em);
	}

	@Override
	protected CriteriaQuery<Tuple> multiselect() {
		try {			
			CriteriaQuery<Tuple> querie = cb.createTupleQuery();
			this.root = querie.from(MWLItem.class);
			this.rootpatient = root.join(MWLItem_.patient);
			
			
			//this.patientAttrBlob = patient.join(Patient_.attributesblob).get(AttributesBlob_.encodedattributes);
			//this.mwlAttrBlob = mwlItem.join(MWLItem_.attributesblob).get(AttributesBlob_.encodedattributes);

			//restrict(querie, patient, mwlItem);
			//order(querie);

			querie.multiselect(root.get(MWLItem_.version), root.get(MWLItem_.createdtime), root.get(MWLItem_.updatedtime),
				root.get(MWLItem_.scheduledprocedurestepid), root.get(MWLItem_.requestedprocedureid),
				root.get(MWLItem_.studyinstanceuid), root.get(MWLItem_.accessionnumber), root.get(MWLItem_.modality),
				root.get(MWLItem_.scheduledstartdate), root.get(MWLItem_.scheduledstarttime), root.get(MWLItem_.status),
				root.get(MWLItem_.admissionid), root.get(MWLItem_.institutionaldepartmentname), root.get(MWLItem_.institutionname),
				root.get(MWLItem_.localaet),

				rootpatient.get(Patient_.patientid), rootpatient.get(Patient_.patientname), rootpatient.get(Patient_.birthday),
				rootpatient.get(Patient_.patientage), rootpatient.get(Patient_.patientsex), rootpatient.get(Patient_.datecreate),
				rootpatient.get(Patient_.datemodify), rootpatient.get(Patient_.verificationtime), rootpatient.get(Patient_.version),
				rootpatient.get(Patient_.failedverifications), rootpatient.get(Patient_.patientcustomattribute1),
				rootpatient.get(Patient_.patientcustomattribute2), rootpatient.get(Patient_.patientcustomattribute3),
				rootpatient.get(Patient_.numberofstudies), rootpatient.get(Patient_.observacoes),
				rootpatient.get(Patient_.patientfamilynamesoundex), rootpatient.get(Patient_.patientgivennamesoundex),
				rootpatient.get(Patient_.patientideographicname), rootpatient.get(Patient_.patientphoneticname)	
			);

			
			return querie;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected CriteriaQuery<Long> count() {
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<MWLItem> mwlItem = q.from(MWLItem.class);
		Join<MWLItem, Patient> patient = mwlItem.join(MWLItem_.patient);
		return restrict(q, patient, mwlItem).select(cb.count(mwlItem));
	}

	@Override
	protected Attributes toAttributes(Tuple results) {
		try {
			Attributes atributos = this.Testando(results);
			return atributos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		try {
//			Attributes mwlAttrs = this.AtributosModality(results);
//			Attributes patAttrs = this.AtributosPaciente(results);
//			Attributes.unifyCharacterSets(patAttrs, mwlAttrs);
//			Attributes attrs = new Attributes(patAttrs.size() + mwlAttrs.size() + 1);
//			attrs.addAll(patAttrs);
//			attrs.addAll(mwlAttrs);
//			attrs.setInt(Tag.NumberOfPatientRelatedStudies, VR.IS, results.get(rootpatient.get(Patient_.numberofstudies)));
//			return attrs;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
		
	}
	
	private Attributes Testando(Tuple item) {
		Attributes atributos = new Attributes();

		atributos.setString(Tag.StudyDate, VR.DA, "20200101");	
		atributos.setString(Tag.StudyTime, VR.TM, "050505");	
		atributos.setString(Tag.AccessionNumber, VR.SH, "US");	
		atributos.setString(Tag.InstitutionName, VR.LO, "US");
		atributos.setString(Tag.InstitutionAddress, VR.ST, "US");
		atributos.setString(Tag.ReferringPhysicianName, VR.PN, "US");	
		atributos.setString(Tag.AdmittingDiagnosesDescription, VR.LO, "US");			
		
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
		
		//atributos.setString(Tag.RequestedProcedureCodeSequence, VR.SQ, "US");	
			Attributes procsequence = new Attributes();
			procsequence.setString(Tag.CodeValue, VR.SH, "US");
			procsequence.setString(Tag.CodingSchemeDesignator, VR.SH, "US");
			procsequence.setString(Tag.CodingSchemeVersion, VR.SH, "US");	
			procsequence.setString(Tag.CodeMeaning, VR.LO, "US");	
			atributos.newSequence(Tag.RequestedProcedureCodeSequence, 1).add(procsequence);
			
		atributos.setString(Tag.AdmissionID, VR.LO, item.get(15).toString());
		atributos.setString(Tag.IssuerOfAdmissionID, VR.LO, "US");	
		atributos.setString(Tag.SpecialNeeds, VR.LO, "US");	
		atributos.setString(Tag.CurrentPatientLocation, VR.LO, "US");	
		atributos.setString(Tag.PatientState, VR.LO, "US");	
		
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
		
		return atributos;
	}
	
//	private Attributes Testando(Tuple item) {
//		Attributes atributos = new Attributes();
//		atributos.setString(Tag.StudyDate, VR.DA, "20200101");	
//		atributos.setString(Tag.StudyTime, VR.TM, "050505");	
//		atributos.setString(Tag.AccessionNumber, VR.SH, "US");	
//		atributos.setString(Tag.InstitutionName, VR.LO, "US");
//		atributos.setString(Tag.InstitutionAddress, VR.ST, "US");
//		atributos.setString(Tag.ReferringPhysicianName, VR.PN, "US");	
//		atributos.setString(Tag.AdmittingDiagnosesDescription, VR.LO, "US");			
//		//atributos.setString(Tag.ReferencedStudySequence, VR.SQ, "US");	
//			atributos.setString(Tag.ReferencedSOPClassUID, VR.UI, "US");
//			atributos.setString(Tag.ReferencedSOPInstanceUID, VR.UI, "US");	
//		//atributos.setString(Tag.ReferencedPatientSequence, VR.SQ, "US");	
//			atributos.setString(Tag.ReferencedSOPClassUID, VR.UI, "US");	
//			atributos.setString(Tag.ReferencedSOPInstanceUID, VR.UI, "US");	
//		atributos.setString(Tag.PatientName, VR.PN, "VIVALDI^ANTONIO");	
//		atributos.setString(Tag.PatientID, VR.LO, "US");
//		atributos.setString(Tag.IssuerOfPatientID, VR.LO, "US");	
//		atributos.setString(Tag.PatientBirthDate, VR.DA, "16780304");
//		atributos.setString(Tag.PatientSex, VR.CS, "M");
//		atributos.setString(Tag.OtherPatientNames, VR.PN, "US");	
//		atributos.setString(Tag.PatientSize, VR.DS, "US");
//		atributos.setString(Tag.PatientWeight, VR.DS, "US");	
//		atributos.setString(Tag.PatientAddress, VR.LO, "US");	
//		atributos.setString(Tag.MilitaryRank, VR.LO, "US");	
//		atributos.setString(Tag.MedicalAlerts, VR.LO, "US");
//		//atributos.setString(Tag.Allergies, VR.LO, "US");	
//		atributos.setString(Tag.EthnicGroup, VR.SH, "US");	
//		atributos.setString(Tag.SmokingStatus, VR.CS, "US");
//		atributos.setString(Tag.AdditionalPatientHistory, VR.LT, "US");
//		atributos.setString(Tag.PregnancyStatus, VR.US, "2");
//		atributos.setString(Tag.LastMenstrualDate, VR.DA, "US");
//		atributos.setString(Tag.ResponsiblePerson, VR.SH, "US");
//		atributos.setString(Tag.ResponsiblePersonRole, VR.SH, "US");
//		atributos.setString(Tag.PatientComments, VR.LT, "US");	
//		atributos.setString(Tag.StudyInstanceUID, VR.UI, "1.2.276.0.7230010.3.2.101");
//		atributos.setString(Tag.RequestingPhysician, VR.PN, "SMITH");
//		atributos.setString(Tag.RequestingService, VR.LO, "US");	
//		atributos.setString(Tag.RequestedProcedureDescription, VR.LO, "EXAM6");
//		//atributos.setString(Tag.RequestedProcedureCodeSequence, VR.SQ, "US");	
//			atributos.setString(Tag.CodeValue, VR.SH, "US");
//			atributos.setString(Tag.CodingSchemeDesignator, VR.SH, "US");
//			atributos.setString(Tag.CodingSchemeVersion, VR.SH, "US");	
//			atributos.setString(Tag.CodeMeaning, VR.LO, "US");	
//		atributos.setString(Tag.AdmissionID, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.IssuerOfAdmissionID, VR.LO, "US");	
//		atributos.setString(Tag.SpecialNeeds, VR.LO, "US");	
//		atributos.setString(Tag.CurrentPatientLocation, VR.LO, "US");	
//		atributos.setString(Tag.PatientState, VR.LO, "US");	
//		//atributos.setString(Tag.ScheduledProcedureStepSequence, VR.SQ, "US");	
//			atributos.setString(Tag.Modality, VR.CS, "US");	
//			atributos.setString(Tag.RequestedContrastAgent, VR.LO, "US");
//			atributos.setString(Tag.ScheduledStationAETitle, VR.AE, "sssfsdf");
//			atributos.setString(Tag.ScheduledProcedureStepStartDate, VR.DA, "20210115");
//			atributos.setString(Tag.ScheduledProcedureStepStartTime, VR.TM, "040404");
//			atributos.setString(Tag.ScheduledProcedureStepEndDate, VR.DA, "20210115");
//			atributos.setString(Tag.ScheduledProcedureStepEndTime, VR.TM, "020304");
//			atributos.setString(Tag.ScheduledPerformingPhysicianName, VR.PN, "wwejej");
//			atributos.setString(Tag.ScheduledProcedureStepDescription, VR.LO, "sssaaasa");
//			//atributos.setString(Tag.ScheduledProtocolCodeSequence, VR.SQ, "US");
//				atributos.setString(Tag.CodeValue, VR.SH, "US");
//				atributos.setString(Tag.CodingSchemeDesignator, VR.SH, "US");
//				atributos.setString(Tag.CodingSchemeVersion, VR.SH, "US");	
//				atributos.setString(Tag.CodeMeaning, VR.LO, "US");
//			atributos.setString(Tag.ScheduledProcedureStepID, VR.SH, "US");	
//			atributos.setString(Tag.ScheduledStationName, VR.SH, "US");	
//			atributos.setString(Tag.ScheduledProcedureStepLocation, VR.SH, "US");	
//			atributos.setString(Tag.PreMedication, VR.LO, "US");	
//			atributos.setString(Tag.ScheduledProcedureStepStatus, VR.CS, "US");	
//			atributos.setString(Tag.CommentsOnTheScheduledProcedureStep, VR.LT, "US");	
//		atributos.setString(Tag.RequestedProcedureID, VR.SH, "RP454G234");
//		atributos.setString(Tag.ReasonForTheRequestedProcedure, VR.LO, "US");
//		atributos.setString(Tag.RequestedProcedurePriority, VR.SH, "US");	
//		atributos.setString(Tag.PatientTransportArrangements, VR.LO, "US");
//		atributos.setString(Tag.RequestedProcedureLocation, VR.LO, "US");	
//		atributos.setString(Tag.ConfidentialityCode, VR.LO, "US");	
//		atributos.setString(Tag.ReportingPriority, VR.SH, "US");	
//		atributos.setString(Tag.NamesOfIntendedRecipientsOfResults, VR.PN, "US");
//		atributos.setString(Tag.RequestedProcedureComments, VR.LT, "US");	
//		atributos.setString(Tag.ReasonForTheImagingServiceRequest, VR.LO, "US");
//		atributos.setString(Tag.IssueDateOfImagingServiceRequest, VR.DA, "US");	
//		atributos.setString(Tag.IssueTimeOfImagingServiceRequest, VR.TM, "US");	
//		atributos.setString(Tag.OrderEnteredBy, VR.PN, "US");	
//		//atributos.setString(Tag.OrderEnterersLocation, VR.SH, "US");
//		atributos.setString(Tag.OrderCallbackPhoneNumber, VR.SH, "US");	
//		atributos.setString(Tag.PlacerOrderNumberImagingServiceRequest, VR.LO, "US");	
//		atributos.setString(Tag.FillerOrderNumberImagingServiceRequest, VR.LO, "US");	
//		atributos.setString(Tag.ImagingServiceRequestComments, VR.LT, "US");
//		atributos.setString(Tag.ConfidentialityConstraintOnPatientDataDescription, VR.LO, "US");
//		
//		return atributos;
//	}
	
//	private Attributes Testandobase(Tuple item) {
//		Attributes atributos = new Attributes();
//		atributos.setString(Tag.AccessionNumber, VR.SH, item.get(15).toString());
//		atributos.setString(Tag.ReferringPhysicianName, VR.PN, item.get(15).toString());
//		atributos.setString(Tag.AdmittingDiagnosesDescription, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.ReferencedStudySequence, VR.SQ, item.get(15).toString());
//		atributos.setString(Tag.ReferencedPatientSequence, VR.SQ, item.get(15).toString());
//		atributos.setString(Tag.PatientName, VR.PN, item.get(15).toString());
//		atributos.setString(Tag.PatientID, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.PatientBirthDate, VR.DA, "20210115-20210115");
//		atributos.setString(Tag.PatientBirthTime, VR.TM, item.get(15).toString());
//		atributos.setString(Tag.PatientSex, VR.CS, item.get(15).toString());
//		atributos.setString(Tag.OtherPatientIDs, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.PatientSize, VR.DS, item.get(15).toString());
//		atributos.setString(Tag.PatientWeight, VR.DS, item.get(15).toString());
//		atributos.setString(Tag.MedicalAlerts, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.Allergies, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.EthnicGroup, VR.SH, item.get(15).toString());
//		atributos.setString(Tag.AdditionalPatientHistory, VR.LT, item.get(15).toString());
//		atributos.setString(Tag.LastMenstrualDate, VR.DA, "20210115-20210115");
//		atributos.setString(Tag.PatientComments, VR.LT, item.get(15).toString());
//		atributos.setString(Tag.StudyInstanceUID, VR.UI, item.get(15).toString());
//		atributos.setString(Tag.RequestingPhysician, VR.PN, item.get(15).toString());
//		atributos.setString(Tag.RequestingService, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.RequestedProcedureDescription, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.RequestedProcedureCodeSequence, VR.SQ, "2");
//		atributos.setString(Tag.AdmissionID, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.SpecialNeeds, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.CurrentPatientLocation, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.PatientState, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.ScheduledProcedureStepSequence, VR.SQ, "1");
//		
//		atributos.setString(Tag.Modality, VR.CS, item.get(15).toString());
//		atributos.setString(Tag.RequestedContrastAgent, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.ScheduledStationAETitle, VR.AE, item.get(15).toString());
//		atributos.setString(Tag.ScheduledProcedureStepStartDate, VR.DA, "20210115-20210115");
//		atributos.setString(Tag.ScheduledProcedureStepStartTime, VR.TM, item.get(15).toString());
//		atributos.setString(Tag.ScheduledProcedureStepEndDate, VR.DA, "20210115-20210115");
//		atributos.setString(Tag.ScheduledProcedureStepEndTime, VR.TM, item.get(15).toString());
//		atributos.setString(Tag.ScheduledPerformingPhysicianName, VR.PN, item.get(15).toString());
//		atributos.setString(Tag.ScheduledProcedureStepDescription, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.ScheduledProtocolCodeSequence, VR.SQ, item.get(15).toString());
//		atributos.setString(Tag.ScheduledProcedureStepID, VR.SH, item.get(15).toString());
//		atributos.setString(Tag.ScheduledStationName, VR.SH, item.get(15).toString());
//		atributos.setString(Tag.ScheduledProcedureStepLocation, VR.SH, item.get(15).toString());
//		atributos.setString(Tag.PreMedication, VR.LO, item.get(15).toString());
//		atributos.setString(Tag.ScheduledProcedureStepStatus, VR.CS, item.get(15).toString());
//		atributos.setString(Tag.CommentsOnTheScheduledProcedureStep, VR.LT, item.get(15).toString());
//		atributos.setString(Tag.RequestedProcedureID, VR.SH, item.get(15).toString());
//		atributos.setString(Tag.RequestedProcedurePriority, VR.SH, item.get(15).toString());
//		atributos.setString(Tag.PatientTransportArrangements, VR.LO, item.get(15).toString());
//		
//		return atributos;
//	}
	
	private Attributes AtributosModality(Tuple item) {
		Attributes atributos = new Attributes();
		
		if(!StringUtils.isEmpty(item.get(0)))
			atributos.setDate(Tag.DateTime, VR.DT, Utils.TransformaDoBancoEmDate(item.get(0).toString()));
		
		if(!StringUtils.isEmpty(item.get(1)))
			atributos.setString(Tag.Modality, VR.UI, item.get(1).toString());
		
		if(!StringUtils.isEmpty(item.get(2)))
			atributos.setString(Tag.RequestedProcedureID, VR.UI, item.get(2).toString());
		
		if(!StringUtils.isEmpty(item.get(3)))
			atributos.setDate(Tag.ScheduledProcedureStepStartDate, VR.DA, Utils.TransformaDoBancoEmDate(item.get(3).toString()));
		
		if(!StringUtils.isEmpty(item.get(4)))
			atributos.setString(Tag.ApplicationVersion, VR.UI, item.get(4).toString());
		
		if(!StringUtils.isEmpty(item.get(5)))
			atributos.setString(Tag.AccessionNumber, VR.SH, item.get(5).toString());
		
		if(!StringUtils.isEmpty(item.get(6)))
			atributos.setDate(Tag.ScheduledAdmissionTime, VR.DA, Utils.TransformaDoBancoEmDate(item.get(6).toString()));
		
		if(!StringUtils.isEmpty(item.get(7)))
			atributos.setString(Tag.CreationTime, VR.UI,item.get(7).toString());
		
		if(!StringUtils.isEmpty(item.get(8)))
			atributos.setString(Tag.ScheduledProcedureStepID, VR.DA, item.get(8).toString());
		
		if(!StringUtils.isEmpty(item.get(9)))
			atributos.setString(Tag.StudyInstanceUID, VR.UI, item.get(9).toString());
		
		if(!StringUtils.isEmpty(item.get(10)))
			atributos.setString(Tag.Status, VR.UI, item.get(10).toString());
		
		if(!StringUtils.isEmpty(item.get(11)))
			atributos.setString(Tag.PatientBirthDate, VR.UI, item.get(11).toString());
		
		if(!StringUtils.isEmpty(item.get(12)))
			atributos.setString(Tag.NumberOfStudyRelatedInstances, VR.UI, item.get(12).toString());
		
		return atributos;
	}
	
	private Attributes AtributosPaciente(Tuple item) {
		Attributes atributos = new Attributes();
		
		if(!StringUtils.isEmpty(item.get(13)))
			atributos.setDate(Tag.CreationDate, VR.DA, Utils.TransformaDoBancoEmDate(item.get(13).toString()));
		
		if(!StringUtils.isEmpty(item.get(14)))
			atributos.setString(Tag.FailedSOPSequence, VR.UI, item.get(14).toString());
		
		if(!StringUtils.isEmpty(item.get(15)))
			atributos.setString(Tag.PatientID, VR.LO, item.get(15).toString());
		
		if(!StringUtils.isEmpty(item.get(16)))
			atributos.setString(Tag.PatientSex, VR.CS, item.get(16).toString());
		
		if(!StringUtils.isEmpty(item.get(17)))
			atributos.setString(Tag.ContextGroupVersion, VR.DA, item.get(17).toString());
		
//		if(!StringUtils.isEmpty(item.get(18)))
//			atributos.setDate(Tag.ModifiedImageDate, VR.UI, Utils.TransformaDoBancoEmDate(item.get(18).toString()));
		
		if(!StringUtils.isEmpty(item.get(19)))
			atributos.setString(Tag.PatientName, VR.PN, item.get(19).toString());
		
		if(!StringUtils.isEmpty(item.get(20)))
			atributos.setString(Tag.ObservationDescription, VR.UI, item.get(20).toString());
		
		if(!StringUtils.isEmpty(item.get(21)))
			atributos.setDate(Tag.VerificationDateTime, VR.UI, Utils.TransformaDoBancoEmDate(item.get(21).toString()));
		
		if(!StringUtils.isEmpty(item.get(22)))
			atributos.setString(Tag.PatientBirthDate, VR.DA, item.get(22).toString());
		
		return atributos;
	}

	@Override
	public boolean isOptionalKeysNotSupported() {
		return false;
	}

	private CriteriaQuery<Tuple> order(CriteriaQuery<Tuple> q) {
		if (context.getOrderByTags() != null)
			q.orderBy(builder.orderMWLItems(rootpatient, root, context.getOrderByTags()));
		return q;
	}

	private <T> CriteriaQuery<T> restrict(CriteriaQuery<T> q, Join<MWLItem, Patient> patient, Root<MWLItem> mwlItem) {
		List<Predicate> predicates = builder.mwlItemPredicates(q, patient, mwlItem, context.getPatientIDs(),
				context.getQueryKeys(), context.getQueryParam());
		if (!predicates.isEmpty())
			q.where(predicates.toArray(new Predicate[0]));

		return q;
	}

}
