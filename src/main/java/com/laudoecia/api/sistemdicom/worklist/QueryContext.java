package com.laudoecia.api.sistemdicom.worklist;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.IDWithIssuer;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.service.QueryRetrieveLevel2;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;

import com.laudoecia.api.sistemdicom.interfaces.QueryService;

public interface QueryContext {
    QueryRetrieveLevel2 getQueryRetrieveLevel();
    void setQueryRetrieveLevel(QueryRetrieveLevel2 qrLevel);
    Association getAssociation();
    HttpServletRequestInfo getHttpRequest();
    ApplicationEntity getLocalApplicationEntity();
    String getCalledAET();
    String getCallingAET();
    String getRemoteHostName();
    String getSOPClassUID();
    void setSOPClassUID(String sopClassUID);
    String getSearchMethod();
    String getLocalHostName();
    ArchiveAEExtension getArchiveAEExtension();
    Attributes getQueryKeys();
    void setQueryKeys(Attributes keys);
    Attributes getCoercedQueryKeys();
    boolean isReturnPrivate();
    void setReturnPrivate(boolean returnPrivate);
    QueryParam getQueryParam();
    IDWithIssuer[] getPatientIDs();
    void setPatientIDs(IDWithIssuer... pids);
    QueryService getQueryService();
    Attributes getReturnKeys();
    void setReturnKeys(Attributes returnKeys);
    boolean containsUniqueKey();
    boolean isConsiderPurgedInstances();
    Storage getStorage(String storageID);
    void putStorage(String storageID, Storage storage);
    void close();
}
