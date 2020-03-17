package com.laudoecia.api.worklistes;

import java.util.List;

import org.dcm4che3.data.Issuer;
import org.dcm4che3.net.ApplicationEntity;

import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.utilities.FuzzyStr;


public class QueryParam {
    private final ArchiveAEExtension arcAE;
    private final ArchiveDeviceExtension arcDev;
    private final QueryRetrieveView qrView;
    private boolean combinedDatetimeMatching;
    private boolean fuzzySemanticMatching;
    private boolean allOfModalitiesInStudy;
    private boolean onlyWithStudies;
    private boolean incomplete;
    private boolean retrieveFailed;
    private boolean storageVerificationFailed;
    private boolean metadataUpdateFailed;
    private boolean compressionfailed;
    private String externalRetrieveAET;
    private String externalRetrieveAETNot;
    private String subscriberAET;
    private String notSubscribedByAET;
    //private Patient.VerificationStatus patientVerificationStatus;
    private ExpirationState[] expirationState;
    private String expirationDate;
    private List<String> studyStorageIDs;
    private long minStudySize;
    private long maxStudySize;

    public QueryParam(ApplicationEntity ae) {
    	if(ae.getAEExtension(ArchiveAEExtension.class) == null) {
    		this.arcAE = new ArchiveAEExtension();
            this.arcDev = new ArchiveDeviceExtension();
            this.qrView = new QueryRetrieveView();
    	}else {
    		this.arcAE = ae.getAEExtensionNotNull(ArchiveAEExtension.class);
            this.arcDev = arcAE.getArchiveDeviceExtension();
            this.qrView = arcAE.getQueryRetrieveView();
    	}        
    }

    public String getAETitle() {
        return arcAE.getApplicationEntity().getAETitle();
    }

    public String[] getAccessControlIDs() {
        return arcAE.getAccessControlIDs();
    }

    public SPSStatus[] getHideSPSWithStatusFromMWL() {
        return arcAE.hideSPSWithStatusFromMWL();
    }

    public FuzzyStr getFuzzyStr() {
        return arcDev.getFuzzyStr();
    }

    public boolean isPersonNameComponentOrderInsensitiveMatching() {
        return arcAE.personNameComponentOrderInsensitiveMatching();
    }

    public boolean isHideNotRejectedInstances() {
        return qrView.isHideNotRejectedInstances();
    }

    public AttributeFilter getAttributeFilter(Entity entity) {
        return arcDev.getAttributeFilter(entity);
    }

    public String getViewID() {
        return qrView.getViewID();
    }

    public QueryRetrieveView getQueryRetrieveView() {
        return qrView;
    }

    public Issuer getDefaultIssuerOfAccessionNumber() {
        return null;
    }

    public boolean isCombinedDatetimeMatching() {
        return combinedDatetimeMatching;
    }

    public void setCombinedDatetimeMatching(boolean combinedDatetimeMatching) {
        this.combinedDatetimeMatching = combinedDatetimeMatching;
    }

    public boolean isFuzzySemanticMatching() {
        return fuzzySemanticMatching;
    }

    public void setFuzzySemanticMatching(boolean fuzzySemanticMatching) {
        this.fuzzySemanticMatching = fuzzySemanticMatching;
    }

    public boolean isAllOfModalitiesInStudy() {
        return allOfModalitiesInStudy;
    }

    public void setAllOfModalitiesInStudy(boolean allOfModalitiesInStudy) {
        this.allOfModalitiesInStudy = allOfModalitiesInStudy;
    }

    public boolean isOnlyWithStudies() {
        return onlyWithStudies;
    }

    public void setOnlyWithStudies(boolean onlyWithStudies) {
        this.onlyWithStudies = onlyWithStudies;
    }

    public boolean isIncomplete() {
        return incomplete;
    }

    public void setIncomplete(boolean incomplete) {
        this.incomplete = incomplete;
    }

    public boolean isRetrieveFailed() {
        return retrieveFailed;
    }

    public void setRetrieveFailed(boolean retrieveFailed) {
        this.retrieveFailed = retrieveFailed;
    }

    public boolean isStorageVerificationFailed() {
        return storageVerificationFailed;
    }

    public void setStorageVerificationFailed(boolean storageVerificationFailed) {
        this.storageVerificationFailed = storageVerificationFailed;
    }

    public boolean isMetadataUpdateFailed() {
        return metadataUpdateFailed;
    }

    public void setMetadataUpdateFailed(boolean metadataUpdateFailed) {
        this.metadataUpdateFailed = metadataUpdateFailed;
    }

    public boolean isCompressionFailed() {
        return compressionfailed;
    }

    public void setCompressionFailed(boolean compressionfailed) {
        this.compressionfailed = compressionfailed;
    }

    public String getExternalRetrieveAET() {
        return externalRetrieveAET;
    }

    public void setExternalRetrieveAET(String externalRetrieveAET) {
        this.externalRetrieveAET = externalRetrieveAET;
    }

    public String getExternalRetrieveAETNot() {
        return externalRetrieveAETNot;
    }

    public void setExternalRetrieveAETNot(String externalRetrieveAETNot) {
        this.externalRetrieveAETNot = externalRetrieveAETNot;
    }

    public String getNotSubscribedByAET() {
        return notSubscribedByAET;
    }

    public void setNotSubscribedByAET(String notSubscribedByAET) {
        this.notSubscribedByAET = notSubscribedByAET;
    }

//    public Patient.VerificationStatus getPatientVerificationStatus() {
//        return patientVerificationStatus;
//    }
//
//    public void setPatientVerificationStatus(Patient.VerificationStatus patientVerificationStatus) {
//        this.patientVerificationStatus = patientVerificationStatus;
//    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<String> getStudyStorageIDs() {
        return studyStorageIDs;
    }

    public void setStudyStorageIDs(List<String> studyStorageIDs) {
        this.studyStorageIDs = studyStorageIDs;
    }

    public boolean noMatches() {
        return studyStorageIDs != null && studyStorageIDs.isEmpty();
    }

    public long getMinStudySize() {
        return minStudySize;
    }

    public long getMaxStudySize() {
        return maxStudySize;
    }

    public void setStudySizeRange(String studySizeInKB) {
        if (studySizeInKB == null || studySizeInKB.isEmpty())
            return;

        int delim = studySizeInKB.indexOf('-');
        if (delim == -1) {
            long size = Long.parseLong(studySizeInKB) * 1000;
            minStudySize = size;
            maxStudySize = size + 999;
        } else {
            if (delim > 0)
                minStudySize =  Long.parseLong(studySizeInKB.substring(0, delim)) * 1000;
            if (delim < studySizeInKB.length() - 1)
                maxStudySize =  (Long.parseLong(studySizeInKB.substring(delim+1)) * 1000) + 999;
        }
    }

    public ExpirationState[] getExpirationState() {
        return expirationState;
    }

    public void setExpirationState(ExpirationState... expirationState) {
        this.expirationState = expirationState;
    }
}
