package com.laudoecia.api.servidor;

import java.io.IOException;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.media.DicomDirReader;
import org.dcm4che3.media.RecordFactory;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicQueryTask;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.util.StringUtils;

import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.service.PacienteService;
import com.laudoecia.api.utils.Utils;

class PatientQueryTask extends BasicQueryTask {

	protected final String[] patIDs;
	protected final DicomDirReader ddr;
	protected final RecordFactory recFact;
	protected final String availability;
	protected final boolean ignoreCaseOfPN;
	protected final boolean matchNoValue;
	protected final int delayCFind;
	protected Attributes patRec;

	public PatientQueryTask(Association as, PresentationContext pc, Attributes rq, Attributes keys, DicomServer qrscp) throws DicomServiceException {
		super(as, pc, rq, keys);
		this.patIDs = StringUtils.maskNull(keys.getStrings(Tag.PatientID));
		this.ddr = qrscp.getDicomDirReader();
		this.recFact = qrscp.getRecordFactory();
		this.availability = qrscp.getInstanceAvailability();
		this.ignoreCaseOfPN = qrscp.isIgnoreCaseOfPN();
		this.matchNoValue = qrscp.isMatchNoValue();
		this.delayCFind = qrscp.getDelayCFind();
		wrappedFindNextPatient();
	}

	@Override
	public boolean hasMoreMatches() throws DicomServiceException {
		return patRec != null;
	}

	@Override
	public Attributes nextMatch() throws DicomServiceException {
		Attributes tmp = patRec;
		wrappedFindNextPatient();
		return tmp;
	}

	@Override
	protected Attributes adjust(Attributes match) {
		Attributes adjust = super.adjust(match);
		adjust.remove(Tag.DirectoryRecordType);
		if (keys.contains(Tag.SOPClassUID))
			adjust.setString(Tag.SOPClassUID, VR.UI, match.getString(Tag.ReferencedSOPClassUIDInFile));
		if (keys.contains(Tag.SOPInstanceUID))
			adjust.setString(Tag.SOPInstanceUID, VR.UI, match.getString(Tag.ReferencedSOPInstanceUIDInFile));
		adjust.setString(Tag.QueryRetrieveLevel, VR.CS, keys.getString(Tag.QueryRetrieveLevel));
		adjust.setString(Tag.RetrieveAETitle, VR.AE, as.getCalledAET());
		if (availability != null)
			adjust.setString(Tag.InstanceAvailability, VR.CS, availability);
		 //adjust.setString(Tag.StorageMediaFileSetID, VR.SH, ddr.getFileSetID());
		 //adjust.setString(Tag.StorageMediaFileSetUID, VR.UI, ddr.getFileSetUID());
		match.setString(Tag.SOPClassUID, VR.UI, match.getString(Tag.ReferencedSOPClassUIDInFile));
		match.setString(Tag.SOPInstanceUID, VR.UI, match.getString(Tag.ReferencedSOPInstanceUIDInFile));
		if (delayCFind > 0)
			try {
				Thread.sleep(delayCFind);
			} catch (InterruptedException ignore) {
			}
		return adjust;
	}

	private void wrappedFindNextPatient() throws DicomServiceException {
		try {
			findNextPatient();
		} catch (IOException e) {
			throw new DicomServiceException(Status.UnableToProcess, e);
		}
	}

	
	protected boolean findNextPatient() throws IOException {
        if (patRec == null)
            patRec = ddr.findPatientRecord(keys, recFact, ignoreCaseOfPN, matchNoValue);
        else if (patIDs.length == 1)
            patRec = null;
        else
            patRec = ddr.findNextPatientRecord(patRec, keys, recFact, ignoreCaseOfPN, matchNoValue);

		return patRec != null;
	}
}