package com.laudoecia.api.sistemdicom.worklist;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;

import com.laudoecia.api.modelo.Atendimento;
import com.laudoecia.api.modelo.ProcedimentoAtendimento;

public class ConverterParaAtributo {

	public Attributes ConverterParaAtributos(Atendimento atendimento) {
		Attributes atributos = new Attributes();
		
		try {
			atributos.setString(Tag.PatientName, VR.PN, atendimento.getPaciente().getNome());
			//atributos.setString(Tag.PatientID, VR.LO, atendimento.getPaciente().getPacienteid());
			
			if(atendimento.getPaciente().getDatanasc() != null)
				atributos.setString(Tag.PatientBirthDate, VR.DA, atendimento.getPaciente().getDatanasc().toString());
			
			if(atendimento.getPaciente().getSexo() != null)
				atributos.setString(Tag.PatientSex, VR.CS, atendimento.getPaciente().getSexo().getValor());
			
			if(atendimento.getPaciente().getTamanho() != null)
				atributos.setString(Tag.PatientSize, VR.DS, atendimento.getPaciente().getTamanho());
			
			if(atendimento.getPaciente().getPeso() != null)
				atributos.setString(Tag.PatientWeight, VR.DS, atendimento.getPaciente().getPeso());	
			
			if(atendimento.getPaciente().getDatamenstruacao() != null)
				atributos.setString(Tag.LastMenstrualDate, VR.DA, atendimento.getPaciente().getDatamenstruacao().toString());
			
			atributos.setString(Tag.AccessionNumber, VR.SH, "atend" + atendimento.getCodigo());
			//atributos.setString(Tag.InstitutionName, VR.LO, equipamento.getInstituicao());
			//atributos.setString(Tag.InstitutionalDepartmentName, VR.LO, equipamento.getDepartamento());
			//atributos.setString(Tag.ScheduledStationAETitle, VR.AE, equipamento.get);
			//atributos.setString(Tag.StudyInstanceUID, VR.UI, "1.2.156.112601.2.4.825791.8323328.2532.1575142321.124");	
			//atributos.setString(Tag.AdmissionID, VR.LO, work.getAdmissionid());
		    
		    for(ProcedimentoAtendimento procedimento : atendimento.getProcedimentos()) {
		    	Attributes stepsequence = new Attributes();
		    	stepsequence.setString(Tag.ScheduledProcedureStepID, VR.SH, procedimento.getProcedimentomedico().getNome());
		    	stepsequence.setString(Tag.RequestedProcedureID, VR.SH, procedimento.getProcedimentomedico().getNome());
		    	stepsequence.setString(Tag.ScheduledProcedureStepDescription, VR.LO, procedimento.getProcedimentomedico().getNome());
		    	stepsequence.setString(Tag.ScheduledProcedureStepStartDate, VR.DA, procedimento.getDataexecucao().toString());
		    	
		    	if(procedimento.getHoraexecucao() != null)
		    		stepsequence.setString(Tag.ScheduledProcedureStepStartTime, VR.TM, procedimento.getHoraexecucao().toString());
		    	
		    	if(procedimento.getProfexecutante() != null)
		    		stepsequence.setString(Tag.ScheduledPerformingPhysicianName, VR.PN, procedimento.getProfexecutante().getNome());
		    	
		    	if(procedimento.getProfexecutante() != null)
		    		stepsequence.setString(Tag.ReferringPhysicianName, VR.PN, procedimento.getProfexecutante().getNome());

				if(procedimento.getStatus() != null)
					stepsequence.setString(Tag.Status, VR.CS, procedimento.getStatus().getDescricao());		
				//stepsequence.setString(Tag.Modality, VR.CS, equipamento.getModelo());
				atributos.ensureSequence(Tag.ScheduledProcedureStepSequence, 1).add(stepsequence);
		    }						    
		} catch (Exception e) {
			e.printStackTrace();
		}
					
		return atributos;		
	}

}




























