package com.laudoecia.api.sistemdicom.tasks;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.util.StringUtils;

import com.laudoecia.api.sistemdicom.DicomServer;

import java.io.IOException;

public class InstanceQueryTask extends SeriesQueryTask {

    protected final String[] sopIUIDs;
    protected Attributes instRec;

    public InstanceQueryTask(Association as, PresentationContext pc, Attributes rq, Attributes keys, DicomServer qrscp) throws DicomServiceException {
        super(as, pc, rq, keys, qrscp);
        sopIUIDs = StringUtils.maskNull(keys.getStrings(Tag.SOPInstanceUID));
        wrappedFindNextInstance();
    }

    @Override
    public boolean hasMoreMatches() throws DicomServiceException {
        return instRec != null;
    }

    @Override
    public Attributes nextMatch() throws DicomServiceException {
        Attributes ret = new Attributes(patRec.size()
            + studyRec.size()
            + seriesRec.size()
            + instRec.size());
        ret.addAll(patRec);
        ret.addAll(studyRec, true);
        ret.addAll(seriesRec, true);
        ret.addAll(instRec, true);
        wrappedFindNextInstance();
        return ret;
    }

    private void wrappedFindNextInstance() throws DicomServiceException {
        try {
            findNextInstance();
        } catch (IOException e) {
            throw new DicomServiceException(Status.UnableToProcess, e);
        }
    }

    protected boolean findNextInstance() throws IOException {
        if (seriesRec == null)
            return false;

        if (instRec == null)
            instRec = ddr.findLowerInstanceRecord(seriesRec, keys, recFact, ignoreCaseOfPN, matchNoValue);
        else if (sopIUIDs.length == 1)
            instRec = null;
        else
            instRec = ddr.findNextInstanceRecord(instRec, keys, recFact, ignoreCaseOfPN, matchNoValue);

        while (instRec == null && super.findNextSeries())
            instRec = ddr.findLowerInstanceRecord(seriesRec, keys, recFact, ignoreCaseOfPN, matchNoValue);

        return instRec != null;
    }
}