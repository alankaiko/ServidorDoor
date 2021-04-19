package com.laudoecia.api.sistemdicom.tasks;

import java.io.IOException;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.util.StringUtils;

import com.laudoecia.api.sistemdicom.DicomServer;

public class SeriesQueryTask extends StudyQueryTask {

    protected final String[] seriesIUIDs;
    protected Attributes seriesRec;

    public SeriesQueryTask(Association as, PresentationContext pc, Attributes rq, Attributes keys, DicomServer qrscp) throws DicomServiceException {
        super(as, pc, rq, keys, qrscp);
        seriesIUIDs = StringUtils.maskNull(keys.getStrings(Tag.SeriesInstanceUID));
        wrappedFindNextSeries();
   }

    @Override
    public boolean hasMoreMatches() throws DicomServiceException {
        return seriesRec != null;
    }

    @Override
    public Attributes nextMatch() throws DicomServiceException {
        Attributes ret = new Attributes(patRec.size() + studyRec.size() + seriesRec.size());
        ret.addAll(patRec);
        ret.addAll(studyRec, true);
        ret.addAll(seriesRec, true);
        wrappedFindNextSeries();
        return ret;
    }

    private void wrappedFindNextSeries() throws DicomServiceException {
        try {
            findNextSeries();
        } catch (IOException e) {
            throw new DicomServiceException(Status.UnableToProcess, e);
        }
    }

    protected boolean findNextSeries() throws IOException {
        if (studyRec == null)
            return false;

        if (seriesRec == null)
            seriesRec = ddr.findSeriesRecord(studyRec, keys, recFact, ignoreCaseOfPN, matchNoValue);
        else if (seriesIUIDs.length == 1)
            seriesRec = null;
        else
            seriesRec = ddr.findNextSeriesRecord(seriesRec, keys, recFact, ignoreCaseOfPN, matchNoValue);

        while (seriesRec == null && super.findNextStudy())
            seriesRec = ddr.findSeriesRecord(studyRec, keys, recFact, ignoreCaseOfPN, matchNoValue);

        return seriesRec != null;
    }
}