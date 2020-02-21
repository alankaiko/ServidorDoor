package com.laudoecia.api.server;

import java.io.IOException;

import org.dcm4che3.data.Tag;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.media.DicomDirReader;
import org.dcm4che3.media.RecordFactory;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.util.StringUtils;

class StudyQueryTask extends PatientQueryTask {

    protected final String[] studyIUIDs;
    protected Attributes studyRec;

    public StudyQueryTask(Association as, PresentationContext pc, Attributes rq, Attributes keys, DicomServer qrscp) throws DicomServiceException {
        super(as, pc, rq, keys, qrscp);
        studyIUIDs = StringUtils.maskNull(keys.getStrings(Tag.StudyInstanceUID));
        wrappedFindNextStudy();
    }

    @Override
    public boolean hasMoreMatches() throws DicomServiceException {
        return studyRec != null;
    }

    @Override
    public Attributes nextMatch() throws DicomServiceException {
        Attributes ret = new Attributes(patRec.size() + studyRec.size());
        ret.addAll(patRec);
        ret.addAll(studyRec, true);
        wrappedFindNextStudy();
        return ret;
    }

    private void wrappedFindNextStudy() throws DicomServiceException {
        try {
            findNextStudy();
        } catch (IOException e) {
            throw new DicomServiceException(Status.UnableToProcess, e);
        }
    }

    protected boolean findNextStudy() throws IOException {
        if (patRec == null)
            return false;

        if (studyRec == null)
            studyRec = ddr.findStudyRecord(patRec, keys, recFact, ignoreCaseOfPN, matchNoValue);
        else if (studyIUIDs.length == 1)
            studyRec = null;
        else
            studyRec = ddr.findNextStudyRecord(studyRec, keys, recFact, ignoreCaseOfPN, matchNoValue);

        while (studyRec == null && super.findNextPatient())
            studyRec = ddr.findStudyRecord(patRec, keys, recFact, ignoreCaseOfPN, matchNoValue);

        return studyRec != null;
    }
}