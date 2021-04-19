package com.laudoecia.api.sistemdicom.worklist;

import java.util.HashMap;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.IDWithIssuer;
import org.dcm4che3.data.Tag;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.service.QueryRetrieveLevel2;
import org.dcm4che3.util.ReverseDNS;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;

import com.laudoecia.api.sistemdicom.interfaces.QueryService;


class QueryContextImpl implements QueryContext {
    private Association as;
    private HttpServletRequestInfo httpRequest;
    private final ApplicationEntity ae;
    private final QueryParam queryParam;
    private final QueryService queryService;
    private QueryRetrieveLevel2 qrLevel;
    private IDWithIssuer[] patientIDs = {};
    private Attributes queryKeys;
    private Attributes coercedQueryKeys = new Attributes();
    private Attributes returnKeys;
    private boolean returnPrivate;
    private String sopClassUID;
    private String searchMethod;
    private final HashMap<String, Storage> storageMap = new HashMap<>();

    QueryContextImpl(ApplicationEntity ae, QueryParam queryParam, QueryService queryService) {
        this.ae = ae;
        this.queryService = queryService;
        this.queryParam = queryParam;
    }

    QueryContextImpl find(Association as, String sopClassUID) {
        this.as = as;
        this.sopClassUID = sopClassUID;
        return this;
    }

    QueryContextImpl qido(HttpServletRequestInfo httpRequest, String searchMethod) {
        this.httpRequest = httpRequest;
        this.searchMethod = searchMethod;
        return this;
    }

    @Override
    public QueryRetrieveLevel2 getQueryRetrieveLevel() {
        return qrLevel;
    }

    @Override
    public void setQueryRetrieveLevel(QueryRetrieveLevel2 qrLevel) {
        this.qrLevel = qrLevel;
    }

    @Override
    public Association getAssociation() {
        return as;
    }

    @Override
    public String getSOPClassUID() {
        return sopClassUID;
    }

    @Override
    public void setSOPClassUID(String sopClassUID) {
        this.sopClassUID = sopClassUID;
    }

    @Override
    public String getSearchMethod() {
        return searchMethod;
    }

    @Override
    public HttpServletRequestInfo getHttpRequest() {
        return httpRequest;
    }

    @Override
    public ApplicationEntity getLocalApplicationEntity() {
        return ae;
    }

    @Override
    public String getCalledAET() {
        return as != null ? as.getCalledAET() : ae.getAETitle();
    }

    @Override
    public String getCallingAET() {
        return as != null ? as.getCallingAET() : null;
    }

    @Override
    public String getRemoteHostName() {
        return httpRequest != null ? httpRequest.requesterHost : ReverseDNS.hostNameOf(as.getSocket().getInetAddress());
    }

    @Override
    public String getLocalHostName() {
        return httpRequest != null ? httpRequest.localHost : ReverseDNS.hostNameOf(as.getSocket().getLocalAddress());
    }

    @Override
    public ArchiveAEExtension getArchiveAEExtension() {
        //return ae.getAEExtension(ArchiveAEExtension.class);
    	return new ArchiveAEExtension();
    }

    @Override
    public IDWithIssuer[] getPatientIDs() {
        return patientIDs;
    }

    @Override
    public void setPatientIDs(IDWithIssuer... patientIDs) {
        this.patientIDs = patientIDs != null ? patientIDs : IDWithIssuer.EMPTY;
    }

    @Override
    public QueryService getQueryService() {
        return queryService;
    }

    @Override
    public Attributes getReturnKeys() {
        return returnKeys;
    }

    @Override
    public void setReturnKeys(Attributes returnKeys) {
        this.returnKeys = returnKeys;
    }

    @Override
    public Attributes getQueryKeys() {
        return queryKeys;
    }

    @Override
    public void setQueryKeys(Attributes keys) {
        this.queryKeys = keys;
    }

    @Override
    public Attributes getCoercedQueryKeys() {
        return coercedQueryKeys;
    }

    @Override
    public boolean isReturnPrivate() {
        return returnPrivate;
    }

    @Override
    public void setReturnPrivate(boolean returnPrivate) {
        this.returnPrivate = returnPrivate;
    }

    @Override
    public QueryParam getQueryParam() {
        return queryParam;
    }

    @Override
    public boolean containsUniqueKey() {
        return patientIDs.length > 0
                || queryKeys.containsValue(Tag.StudyInstanceUID)
                || qrLevel.compareTo(QueryRetrieveLevel2.SERIES) >= 0 && (queryKeys.containsValue(Tag.SeriesInstanceUID)
                || qrLevel == QueryRetrieveLevel2.IMAGE && queryKeys.containsValue(Tag.SOPInstanceUID));
    }


    @Override
    public boolean isConsiderPurgedInstances() {
        return qrLevel == QueryRetrieveLevel2.IMAGE
                && getArchiveDeviceExtension().isPurgeInstanceRecords()
                && queryKeys.containsValue(Tag.SeriesInstanceUID);
    }

    private ArchiveDeviceExtension getArchiveDeviceExtension() {
        return ae.getDevice().getDeviceExtension(ArchiveDeviceExtension.class);
    }

    @Override
    public Storage getStorage(String storageID) {
        return storageMap.get(storageID);
    }

    @Override
    public void putStorage(String storageID, Storage storage) {
        storageMap.put(storageID, storage);
    }

    @Override
    public void close() {
//        for (Storage storage : storageMap.values())
//            SafeClose.close(storage);
    }
}
