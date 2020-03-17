package com.laudoecia.api.worklistes;

import javax.persistence.EntityManager;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicQueryTask;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.net.service.QueryTask;

import com.laudoecia.api.domain.MWLItem;
import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.utils.Utils;

public class MinhaQueryTaskPropria extends BasicQueryTask implements QueryTask {
	private Patient item;

	public MinhaQueryTaskPropria(Association as, PresentationContext pc, Attributes rq, Attributes keys) {
		super(as, pc, rq, keys);
		
		try {
			this.BuscarItens();
			Attributes at = this.Tentativa(this.item);
			
			
			this.adjust(at);
			this.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void BuscarItens() {
		EntityManager em = null;
		em = Utils.entidade.createEntityManager();
		this.item = em.find(Patient.class, 1L);
		em.close();
	}
	
	private Attributes Tentativa(Patient item) {
		Attributes at = new Attributes();
		at.setString(Tag.PatientID, VR.UI, item.getPatientid());
		at.setString(Tag.PatientName, VR.UI, item.getPatientname());
		at.setString(Tag.PatientSex, VR.UI, item.getPatientsex());
		at.setString(Tag.PatientAge, VR.UI, item.getPatientage());
		at.setDate(Tag.PatientBirthDate, VR.DA, Utils.ConverterToDate(item.getBirthday()));
		
		
		return at;
	}
	
	
//	private static Attributes toAttributes(String study) {
//        String instanceSeparator = ",";
//        char beginReferencedObj = '[';
//        String seriesSplitterRegex = "(],)";
//        Attributes at = new Attributes();
//        at.setString(Tag.PatientID, VR.UI, "ave");
//        
//        Attributes attrs = new Attributes();
//        Attributes refStudy = new Attributes();
//        int seriesListStart = study.indexOf(beginReferencedObj);
//        refStudy.setString(Tag.StudyInstanceUID, VR.UI, study.substring(0, seriesListStart));
//        String[] seriesList = study.substring(seriesListStart+1, study.length() -1).split(seriesSplitterRegex);
//        Sequence refSeriesSequence = refStudy.newSequence(Tag.ReferencedSeriesSequence, seriesList.length);
//        for (String series : seriesList) {
//            int instanceListStart = series.indexOf(beginReferencedObj);
//            Attributes refSeries = new Attributes();
//            refSeries.setString(Tag.SeriesInstanceUID, VR.UI, series.substring(0, instanceListStart));
//            String[] instances = series.substring(instanceListStart + 1).replace(']', ' ').split(instanceSeparator);
//            Sequence refSopSequence = refSeries.newSequence(Tag.ReferencedSOPSequence, instances.length);
//            for (String instance : instances) {
//                Attributes refSop = new Attributes();
//                refSop.setString(Tag.ReferencedSOPInstanceUID, VR.UI, instance);
//                refSopSequence.add(refSop);
//            }
//            refSeriesSequence.add(refSeries);
//        }
//        attrs.newSequence(Tag.CurrentRequestedProcedureEvidenceSequence, 1).add(refStudy);
//        return attrs;
//    }

}
