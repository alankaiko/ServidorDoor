package com.laudoecia.api.server;

import java.io.IOException;
import java.util.List;

import org.dcm4che3.data.Tag;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomInputStream.IncludeBulkData;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.DataWriter;
import org.dcm4che3.net.DataWriterAdapter;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicRetrieveTask;
import org.dcm4che3.net.service.InstanceLocator;
import org.dcm4che3.util.SafeClose;

class RetrieveTaskImpl extends BasicRetrieveTask {

    private final boolean withoutBulkData;
    private final int delayCStore;

    public RetrieveTaskImpl(Dimse rq, Association rqas, PresentationContext pc,
                            Attributes rqCmd, List<InstanceLocator> matches,
                            Association storeas, boolean withoutBulkData, int delayCStore) {
        super(rq, rqas, pc, rqCmd, matches, storeas);
        this.withoutBulkData = withoutBulkData;
        this.delayCStore = delayCStore;
    }

    @Override
    protected DataWriter createDataWriter(InstanceLocator inst, String tsuid)
            throws IOException {
        Attributes attrs;
        DicomInputStream in = new DicomInputStream(inst.getFile());
        try {
            if (withoutBulkData) {
                in.setIncludeBulkData(IncludeBulkData.NO);
                attrs = in.readDataset(-1, Tag.PixelData);
            } else {
                in.setIncludeBulkData(IncludeBulkData.URI);
                attrs = in.readDataset(-1, -1);
            }
        } finally {
            SafeClose.close(in);
        }
        if (delayCStore > 0)
            try {
                Thread.sleep(delayCStore);
            } catch (InterruptedException ignore) {}
        return new DataWriterAdapter(attrs);
    }

 }
