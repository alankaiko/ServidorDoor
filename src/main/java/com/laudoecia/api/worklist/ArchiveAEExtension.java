package com.laudoecia.api.worklist;

import java.io.File;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.io.BulkDataDescriptor;
import org.dcm4che3.net.AEExtension;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.TransferCapability;
import org.dcm4che3.util.StringUtils;

public class ArchiveAEExtension extends AEExtension {
    private String defaultCharacterSet;
    private String upsWorklistLabel;
    private String[] upsEventSCUs = {};
    private int upsEventSCUKeepAlive;
    private String[] objectStorageIDs = {};
    private int objectStorageCount = 1;
    private String[] metadataStorageIDs = {};
    private String bulkDataDescriptorID;
    private String storeAccessControlID;
    private String[] accessControlIDs = {};
    private Boolean recordAttributeModification;
    private String bulkDataSpoolDirectory;
    private String queryRetrieveViewID;
    private Boolean validateCallingAEHostname;
    private Boolean personNameComponentOrderInsensitiveMatching;
    private Boolean sendPendingCGet;
    private String wadoThumbnailViewPort;
    private String wadoZIPEntryNameFormat;
    private String wadoSR2HtmlTemplateURI;
    private String wadoSR2TextTemplateURI;
    private String wadoCDA2HtmlTemplateURI;
    private String[] mppsForwardDestinations = {};
    private String[] ianDestinations = {};
    private Boolean ianOnTimeout;
    private String spanningCFindSCP;
    private String[] spanningCFindSCPRetrieveAETitles = {};
    private Integer fallbackCMoveSCPRetries;
    private String fallbackCMoveSCP;
    private String fallbackCMoveSCPDestination;
    private String fallbackCMoveSCPCallingAET;
    private String fallbackCMoveSCPLeadingCFindSCP;
    private String fallbackCMoveSCPStudyOlderThan;
    private String externalRetrieveAEDestination;
    private String alternativeCMoveSCP;
    private Integer queryMaxNumberOfResults;
    private Integer qidoMaxNumberOfResults;
    private String storePermissionServiceURL;
    private String storePermissionServiceResponse;
    private Pattern storePermissionServiceResponsePattern;
    private Pattern storePermissionServiceExpirationDatePattern;
    private Pattern storePermissionServiceErrorCommentPattern;
    private Pattern storePermissionServiceErrorCodePattern;
    private String[] retrieveAETitles = {};
    private String[] returnRetrieveAETitles = {};
    private String hl7PSUSendingApplication;
    private String[] hl7PSUReceivingApplications = {};
    private Boolean hl7PSUOnTimeout;
    private Boolean hl7PSUMWL;
    private Boolean hl7PSUForRequestedProcedure;
    private Boolean hl7PSUPIDPV1;
    private String hl7PSURequestedProcedureID;
    private String hl7PSUAccessionNumber;
    private String hl7PSUFillerOrderNumber;
    private String hl7PSUPlacerOrderNumber;
    private Attributes.UpdatePolicy copyMoveUpdatePolicy;
    private Attributes.UpdatePolicy linkMWLEntryUpdatePolicy;
    private Boolean storageVerificationUpdateLocationStatus;
    private String[] storageVerificationStorageIDs = {};
    private Period storageVerificationInitialDelay;
    private Boolean updateLocationStatusOnRetrieve;
    private Boolean storageVerificationOnRetrieve;
    private Boolean relationalQueryNegotiationLenient;
    private Boolean relationalRetrieveNegotiationLenient;
    private Boolean stowRetiredTransferSyntax;
    private Boolean stowExcludeAPPMarkers;
    private Boolean restrictRetrieveSilently;
    private Boolean stowQuicktime2MP4;
    private int[] rejectConflictingPatientAttribute = {};
    private final LinkedHashSet<String> acceptedMoveDestinations = new LinkedHashSet<>();
    private final List<ArchiveAttributeCoercion> attributeCoercions = new ArrayList<>();

    public String getDefaultCharacterSet() {
        return defaultCharacterSet;
    }

    public void setDefaultCharacterSet(String defaultCharacterSet) {
        this.defaultCharacterSet = defaultCharacterSet;
    }

    public String defaultCharacterSet() {
        return defaultCharacterSet != null
                ? defaultCharacterSet
                : getArchiveDeviceExtension().getDefaultCharacterSet();
    }

    public String getUPSWorklistLabel() {
        return upsWorklistLabel;
    }

    public void setUPSWorklistLabel(String upsWorklistLabel) {
        this.upsWorklistLabel = upsWorklistLabel;
    }

    public String upsWorklistLabel() {
        return defaultCharacterSet != null
                ? upsWorklistLabel
                : StringUtils.maskNull(getArchiveDeviceExtension().getUPSWorklistLabel(), ae.getAETitle());
    }

    public String[] getUPSEventSCUs() {
        return upsEventSCUs;
    }

    public void setUPSEventSCUs(String[] upsEventSCUs) {
        this.upsEventSCUs = upsEventSCUs;
    }

    public String[] upsEventSCUs() {
        return upsEventSCUs.length > 0
                ? upsEventSCUs
                : getArchiveDeviceExtension().getUPSEventSCUs();
    }

    public boolean isUPSEventSCU(String aet) {
        return Stream.of(upsEventSCUs()).anyMatch(aet::equals);
    }

    public int getUPSEventSCUKeepAlive() {
        return upsEventSCUKeepAlive;
    }

    public void setUPSEventSCUKeepAlive(int upsEventSCUKeepAlive) {
        this.upsEventSCUKeepAlive = upsEventSCUKeepAlive;
    }

    public int upsEventSCUKeepAlive() {
        return upsEventSCUKeepAlive > 0
                ? upsEventSCUKeepAlive
                : getArchiveDeviceExtension().getUPSEventSCUKeepAlive();
    }


    public String[] getObjectStorageIDs() {
        return objectStorageIDs;
    }

    public void setObjectStorageIDs(String... objectStorageIDs) {
        Arrays.sort(this.objectStorageIDs = objectStorageIDs);
    }

    public int getObjectStorageCount() {
        return objectStorageCount;
    }

    public void setObjectStorageCount(int objectStorageCount) {
        this.objectStorageCount = objectStorageCount;
    }

    public String[] getMetadataStorageIDs() {
        return metadataStorageIDs;
    }

    public void setMetadataStorageIDs(String... metadataStorageIDs) {
        Arrays.sort(this.metadataStorageIDs = metadataStorageIDs);
    }

    public String getBulkDataDescriptorID() {
        return bulkDataDescriptorID;
    }

    public void setBulkDataDescriptorID(String bulkDataDescriptorID) {
        this.bulkDataDescriptorID = bulkDataDescriptorID;
    }

    public BulkDataDescriptor getBulkDataDescriptor() {
        ArchiveDeviceExtension arcdev = getArchiveDeviceExtension();
        return arcdev.getBulkDataDescriptor(bulkDataDescriptorID != null
                ? bulkDataDescriptorID
                : arcdev.getBulkDataDescriptorID());
    }

    public String getStoreAccessControlID() {
        return storeAccessControlID;
    }

    public void setStoreAccessControlID(String storeAccessControlID) {
        this.storeAccessControlID = storeAccessControlID;
    }

    public String[] getAccessControlIDs() {
        return accessControlIDs;
    }

    public void setAccessControlIDs(String[] accessControlIDs) {
        this.accessControlIDs = accessControlIDs;
    }


    public Boolean getRecordAttributeModification() {
        return recordAttributeModification;
    }

    public void setRecordAttributeModification(Boolean recordAttributeModification) {
        this.recordAttributeModification = recordAttributeModification;
    }

    public boolean recordAttributeModification() {
        return recordAttributeModification != null
                ? recordAttributeModification
                : getArchiveDeviceExtension().isRecordAttributeModification();
    }

    public String getBulkDataSpoolDirectory() {
        return bulkDataSpoolDirectory;
    }

    public void setBulkDataSpoolDirectory(String bulkDataSpoolDirectory) {
        this.bulkDataSpoolDirectory = bulkDataSpoolDirectory;
    }

    public String bulkDataSpoolDirectory() {
        return bulkDataSpoolDirectory != null
                ? bulkDataSpoolDirectory
                : getArchiveDeviceExtension().getBulkDataSpoolDirectory();
    }

    public File getBulkDataSpoolDirectoryFile() {
        return new File(StringUtils.replaceSystemProperties(bulkDataSpoolDirectory()));
    }

    public String getQueryRetrieveViewID() {
        return queryRetrieveViewID;
    }

    public void setQueryRetrieveViewID(String queryRetrieveViewID) {
        this.queryRetrieveViewID = queryRetrieveViewID;
    }

    public Boolean getValidateCallingAEHostname() {
        return validateCallingAEHostname;
    }

    public void setValidateCallingAEHostname(Boolean validateCallingAEHostname) {
        this.validateCallingAEHostname = validateCallingAEHostname;
    }

    public boolean validateCallingAEHostname() {
        return validateCallingAEHostname != null
                ? validateCallingAEHostname
                : getArchiveDeviceExtension().isValidateCallingAEHostname();
    }

    public Boolean getPersonNameComponentOrderInsensitiveMatching() {
        return personNameComponentOrderInsensitiveMatching;
    }

    public void setPersonNameComponentOrderInsensitiveMatching(Boolean personNameComponentOrderInsensitiveMatching) {
        this.personNameComponentOrderInsensitiveMatching = personNameComponentOrderInsensitiveMatching;
    }

    public boolean personNameComponentOrderInsensitiveMatching() {
        return personNameComponentOrderInsensitiveMatching != null
                ? personNameComponentOrderInsensitiveMatching
                : getArchiveDeviceExtension().isPersonNameComponentOrderInsensitiveMatching();
    }

    public Boolean getSendPendingCGet() {
        return sendPendingCGet;
    }

    public void setSendPendingCGet(Boolean sendPendingCGet) {
        this.sendPendingCGet = sendPendingCGet;
    }

    public boolean sendPendingCGet() {
        return sendPendingCGet != null
                ? sendPendingCGet
                : getArchiveDeviceExtension().isSendPendingCGet();
    }

    public String getWadoThumbnailViewPort() {
        return wadoThumbnailViewPort;
    }

    public void setWadoThumbnailViewPort(String wadoThumbnailViewPort) {
        this.wadoThumbnailViewPort = wadoThumbnailViewPort;
    }

    public String wadoThumbnailViewPort() {
        return wadoThumbnailViewPort != null
                ? wadoThumbnailViewPort
                : getArchiveDeviceExtension().getWadoThumbnailViewPort();
    }

    public String getWadoZIPEntryNameFormat() {
        return wadoZIPEntryNameFormat;
    }

    public void setWadoZIPEntryNameFormat(String wadoZIPEntryNameFormat) {
        this.wadoZIPEntryNameFormat = wadoZIPEntryNameFormat;
    }

    public String wadoZIPEntryNameFormat() {
        return wadoZIPEntryNameFormat != null
                ? wadoZIPEntryNameFormat
                : getArchiveDeviceExtension().getWadoZIPEntryNameFormat();
    }

    public String getWadoSR2HtmlTemplateURI() {
        return wadoSR2HtmlTemplateURI;
    }

    public void setWadoSR2HtmlTemplateURI(String wadoSR2HtmlTemplateURI) {
        this.wadoSR2HtmlTemplateURI = wadoSR2HtmlTemplateURI;
    }

    public String wadoSR2HtmlTemplateURI() {
        return wadoSR2HtmlTemplateURI != null
                ? wadoSR2HtmlTemplateURI
                : getArchiveDeviceExtension().getWadoSR2HtmlTemplateURI();
    }

    public String getWadoSR2TextTemplateURI() {
        return wadoSR2TextTemplateURI;
    }

    public void setWadoSR2TextTemplateURI(String wadoSR2TextTemplateURI) {
        this.wadoSR2TextTemplateURI = wadoSR2TextTemplateURI;
    }

    public String wadoSR2TextTemplateURI() {
        return wadoSR2TextTemplateURI != null
                ? wadoSR2TextTemplateURI
                : getArchiveDeviceExtension().getWadoSR2TextTemplateURI();
    }

    public String getWadoCDA2HtmlTemplateURI() {
        return wadoCDA2HtmlTemplateURI;
    }

    public void setWadoCDA2HtmlTemplateURI(String wadoCDA2HtmlTemplateURI) {
        this.wadoCDA2HtmlTemplateURI = wadoCDA2HtmlTemplateURI;
    }

    public String wadoCDA2HtmlTemplateURI() {
        return wadoCDA2HtmlTemplateURI != null
                ? wadoCDA2HtmlTemplateURI
                : getArchiveDeviceExtension().getWadoCDA2HtmlTemplateURI();
    }

    public String[] getMppsForwardDestinations() {
        return mppsForwardDestinations;
    }

    public void setMppsForwardDestinations(String... mppsForwardDestinations) {
        this.mppsForwardDestinations = mppsForwardDestinations;
    }

    public String[] mppsForwardDestinations() {
        return mppsForwardDestinations.length > 0
                ? mppsForwardDestinations
                : getArchiveDeviceExtension().getMppsForwardDestinations();
    }

    public String[] getIanDestinations() {
        return ianDestinations;
    }

    public void setIanDestinations(String... ianDestinations) {
        this.ianDestinations = ianDestinations;
    }

    public String[] ianDestinations() {
        return ianDestinations.length > 0
                ? ianDestinations
                : getArchiveDeviceExtension().getIanDestinations();
    }

    public Boolean getIanOnTimeout() {
        return ianOnTimeout;
    }

    public void setIanOnTimeout(Boolean ianOnTimeout) {
        this.ianOnTimeout = ianOnTimeout;
    }

    public boolean ianOnTimeout() {
        return ianOnTimeout != null
                ? ianOnTimeout
                : getArchiveDeviceExtension().isIanOnTimeout();
    }

    public String getSpanningCFindSCP() {
        return spanningCFindSCP;
    }

    public void setSpanningCFindSCP(String spanningCFindSCP) {
        this.spanningCFindSCP = spanningCFindSCP;
    }

    public String spanningCFindSCP() {
        return spanningCFindSCP != null
                ? spanningCFindSCP
                : getArchiveDeviceExtension().getSpanningCFindSCP();
    }

    public String[] getSpanningCFindSCPRetrieveAETitles() {
        return spanningCFindSCPRetrieveAETitles;
    }

    public void setSpanningCFindSCPRetrieveAETitles(String[] spanningCFindSCPRetrieveAETitles) {
        this.spanningCFindSCPRetrieveAETitles = spanningCFindSCPRetrieveAETitles;
    }

    public String[] spanningCFindSCPRetrieveAETitles() {
        return spanningCFindSCPRetrieveAETitles.length > 0
                ? spanningCFindSCPRetrieveAETitles
                : getArchiveDeviceExtension().getSpanningCFindSCPRetrieveAETitles();
    }

    public String getFallbackCMoveSCP() {
        return fallbackCMoveSCP;
    }

    public void setFallbackCMoveSCP(String fallbackCMoveSCP) {
        this.fallbackCMoveSCP = fallbackCMoveSCP;
    }

    public String fallbackCMoveSCP() {
        return fallbackCMoveSCP != null
                ? fallbackCMoveSCP
                : getArchiveDeviceExtension().getFallbackCMoveSCP();
    }

    public String getFallbackCMoveSCPDestination() {
        return fallbackCMoveSCPDestination;
    }

    public void setFallbackCMoveSCPDestination(String fallbackCMoveSCPDestination) {
        this.fallbackCMoveSCPDestination = fallbackCMoveSCPDestination;
    }

    public String fallbackCMoveSCPDestination() {
        return fallbackCMoveSCPDestination != null
                ? fallbackCMoveSCPDestination
                : getArchiveDeviceExtension().getFallbackCMoveSCPDestination();
    }

    public String getFallbackCMoveSCPCallingAET() {
        return fallbackCMoveSCPCallingAET;
    }

    public void setFallbackCMoveSCPCallingAET(String fallbackCMoveSCPCallingAET) {
        this.fallbackCMoveSCPCallingAET = fallbackCMoveSCPCallingAET;
    }

    public String fallbackCMoveSCPCallingAET(Association as) {
        String aet = fallbackCMoveSCPCallingAET != null
                ? fallbackCMoveSCPCallingAET
                : getArchiveDeviceExtension().getFallbackCMoveSCPCallingAET();
        return aet != null ? aet : as.getCallingAET();
    }

    public String getFallbackCMoveSCPLeadingCFindSCP() {
        return fallbackCMoveSCPLeadingCFindSCP;
    }

    public void setFallbackCMoveSCPLeadingCFindSCP(String fallbackCMoveSCPLeadingCFindSCP) {
        this.fallbackCMoveSCPLeadingCFindSCP = fallbackCMoveSCPLeadingCFindSCP;
    }

    public String fallbackCMoveSCPLeadingCFindSCP() {
        return fallbackCMoveSCPLeadingCFindSCP != null
                ? fallbackCMoveSCPLeadingCFindSCP
                : getArchiveDeviceExtension().getFallbackCMoveSCPLeadingCFindSCP();
    }

    public void setFallbackCMoveSCPRetries(Integer fallbackCMoveSCPRetries) {
        this.fallbackCMoveSCPRetries = fallbackCMoveSCPRetries;
    }

    public Integer getFallbackCMoveSCPRetries() {
        return fallbackCMoveSCPRetries;
    }

    public int fallbackCMoveSCPRetries() {
        return fallbackCMoveSCPRetries != null
                ? fallbackCMoveSCPRetries
                : getArchiveDeviceExtension().getFallbackCMoveSCPRetries();
    }

    public String getExternalRetrieveAEDestination() {
        return externalRetrieveAEDestination;
    }

    public void setExternalRetrieveAEDestination(String externalRetrieveAEDestination) {
        this.externalRetrieveAEDestination = externalRetrieveAEDestination;
    }

    public String externalRetrieveAEDestination() {
        return externalRetrieveAEDestination != null
                ? externalRetrieveAEDestination
                : getArchiveDeviceExtension().getExternalRetrieveAEDestination();
    }

    public String getAlternativeCMoveSCP() {
        return alternativeCMoveSCP;
    }

    public void setAlternativeCMoveSCP(String alternativeCMoveSCP) {
        this.alternativeCMoveSCP = alternativeCMoveSCP;
    }

    public String alternativeCMoveSCP() {
        return alternativeCMoveSCP != null
                ? alternativeCMoveSCP
                : getArchiveDeviceExtension().getAlternativeCMoveSCP();
    }

    public String fallbackCMoveSCPStudyOlderThan() {
        return fallbackCMoveSCPStudyOlderThan != null
                ? fallbackCMoveSCPStudyOlderThan
                : getArchiveDeviceExtension().getFallbackCMoveSCPStudyOlderThan();
    }

    public Integer getQueryMaxNumberOfResults() {
        return queryMaxNumberOfResults;
    }

    public void setQueryMaxNumberOfResults(Integer queryMaxNumberOfResults) {
        this.queryMaxNumberOfResults = queryMaxNumberOfResults;
    }

    public int queryMaxNumberOfResults() {
        return queryMaxNumberOfResults != null
                ? queryMaxNumberOfResults
                : getArchiveDeviceExtension().getQueryMaxNumberOfResults();
    }

    public Integer getQidoMaxNumberOfResults() {
        return qidoMaxNumberOfResults;
    }

    public void setQidoMaxNumberOfResults(Integer qidoMaxNumberOfResults) {
        this.qidoMaxNumberOfResults = qidoMaxNumberOfResults;
    }

    public int qidoMaxNumberOfResults() {
        return qidoMaxNumberOfResults != null
                ? qidoMaxNumberOfResults
                : getArchiveDeviceExtension().getQidoMaxNumberOfResults();
    }

    public String getFallbackCMoveSCPStudyOlderThan() {
        return fallbackCMoveSCPStudyOlderThan;
    }

    public void setFallbackCMoveSCPStudyOlderThan(String fallbackCMoveSCPStudyOlderThan) {
        this.fallbackCMoveSCPStudyOlderThan = fallbackCMoveSCPStudyOlderThan;
    }

    public String getStorePermissionServiceURL() {
        return storePermissionServiceURL;
    }

    public void setStorePermissionServiceURL(String storePermissionServiceURL) {
        this.storePermissionServiceURL = storePermissionServiceURL;
    }

    public String storePermissionServiceURL() {
        return storePermissionServiceURL != null
                ? storePermissionServiceURL
                : getArchiveDeviceExtension().getStorePermissionServiceURL();
    }

    public Pattern getStorePermissionServiceResponsePattern() {
        return storePermissionServiceResponsePattern;
    }

    public void setStorePermissionServiceResponsePattern(Pattern storePermissionServiceResponsePattern) {
        this.storePermissionServiceResponsePattern = storePermissionServiceResponsePattern;
    }

    public Pattern storePermissionServiceResponsePattern() {
        return storePermissionServiceResponsePattern != null
                ? storePermissionServiceResponsePattern
                : getArchiveDeviceExtension().getStorePermissionServiceResponsePattern();
    }

    public String getStorePermissionServiceResponse() {
        return storePermissionServiceResponse;
    }

    public void setStorePermissionServiceResponse(String storePermissionServiceResponse) {
        this.storePermissionServiceResponse = storePermissionServiceResponse;
    }

    public String storePermissionServiceResponse() {
        return storePermissionServiceResponse != null
                ? storePermissionServiceResponse
                : getArchiveDeviceExtension().getStorePermissionServiceResponse();
    }

    public Pattern getStorePermissionServiceExpirationDatePattern() {
        return storePermissionServiceExpirationDatePattern;
    }

    public void setStorePermissionServiceExpirationDatePattern(Pattern storePermissionServiceExpirationDatePattern) {
        this.storePermissionServiceExpirationDatePattern = storePermissionServiceExpirationDatePattern;
    }

    public Pattern storePermissionServiceExpirationDatePattern() {
        return storePermissionServiceExpirationDatePattern != null
                ? storePermissionServiceExpirationDatePattern
                : getArchiveDeviceExtension().getStorePermissionServiceExpirationDatePattern();
    }

    public Pattern getStorePermissionServiceErrorCommentPattern() {
        return storePermissionServiceErrorCommentPattern;
    }

    public void setStorePermissionServiceErrorCommentPattern(Pattern storePermissionServiceErrorCommentPattern) {
        this.storePermissionServiceErrorCommentPattern = storePermissionServiceErrorCommentPattern;
    }

    public Pattern storePermissionServiceErrorCommentPattern() {
        return storePermissionServiceErrorCommentPattern != null
                ? storePermissionServiceErrorCommentPattern
                : getArchiveDeviceExtension().getStorePermissionServiceErrorCommentPattern();
    }

    public Pattern getStorePermissionServiceErrorCodePattern() {
        return storePermissionServiceErrorCodePattern;
    }

    public void setStorePermissionServiceErrorCodePattern(Pattern storePermissionServiceErrorCodePattern) {
        this.storePermissionServiceErrorCodePattern = storePermissionServiceErrorCodePattern;
    }

    public Pattern storePermissionServiceErrorCodePattern() {
        return storePermissionServiceErrorCodePattern != null
                ? storePermissionServiceErrorCodePattern
                : getArchiveDeviceExtension().getStorePermissionServiceErrorCodePattern();
    }

    public String[] getAcceptedMoveDestinations() {
        return acceptedMoveDestinations.toArray(new String[acceptedMoveDestinations.size()]);
    }

    public void setAcceptedMoveDestinations(String... aets) {
        acceptedMoveDestinations.clear();
        for (String name : aets)
            acceptedMoveDestinations.add(name);
    }

    public boolean isAcceptedMoveDestination(String aet) {
        return acceptedMoveDestinations.isEmpty() || acceptedMoveDestinations.contains(aet);
    }

    public void removeAttributeCoercion(ArchiveAttributeCoercion coercion) {
        attributeCoercions.remove(coercion);
    }

    public void clearAttributeCoercions() {
        attributeCoercions.clear();
    }

    public void addAttributeCoercion(ArchiveAttributeCoercion coercion) {
        attributeCoercions.add(coercion);
    }

    public Collection<ArchiveAttributeCoercion> getAttributeCoercions() {
        return attributeCoercions;
    }

    public String[] getRetrieveAETitles() {
        return retrieveAETitles;
    }

    public void setRetrieveAETitles(String... retrieveAETitles) {
        this.retrieveAETitles = retrieveAETitles;
    }

    public String[] retrieveAETitles() {
        return retrieveAETitles.length > 0 ? retrieveAETitles : getArchiveDeviceExtension().getRetrieveAETitles();
    }

    public String[] getReturnRetrieveAETitles() {
        return returnRetrieveAETitles;
    }

    public void setReturnRetrieveAETitles(String[] returnRetrieveAETitles) {
        this.returnRetrieveAETitles = returnRetrieveAETitles;
    }

    public String[] returnRetrieveAETitles() {
        return returnRetrieveAETitles.length > 0
                ? returnRetrieveAETitles
                : getArchiveDeviceExtension().getReturnRetrieveAETitles();
    }

    public String getHL7PSUSendingApplication() {
        return hl7PSUSendingApplication;
    }

    public void setHL7PSUSendingApplication(String hl7PSUSendingApplication) {
        this.hl7PSUSendingApplication = hl7PSUSendingApplication;
    }

    public String hl7PSUSendingApplication() {
        return hl7PSUSendingApplication != null
                ? hl7PSUSendingApplication
                : getArchiveDeviceExtension().getHL7PSUSendingApplication();
    }

    public String[] getHL7PSUReceivingApplications() {
        return hl7PSUReceivingApplications;
    }

    public void setHL7PSUReceivingApplications(String[] hl7PSUReceivingApplications) {
        this.hl7PSUReceivingApplications = hl7PSUReceivingApplications;
    }

    public String[] hl7PSUReceivingApplications() {
        return hl7PSUReceivingApplications.length > 0
                ? hl7PSUReceivingApplications
                : getArchiveDeviceExtension().getHL7PSUReceivingApplications();
    }

    public Boolean getHL7PSUOnTimeout() {
        return hl7PSUOnTimeout;
    }

    public void setHL7PSUOnTimeout(Boolean hl7PSUOnTimeout) {
        this.hl7PSUOnTimeout = hl7PSUOnTimeout;
    }

    public boolean hl7PSUOnTimeout() {
        return hl7PSUOnTimeout != null
                ? hl7PSUOnTimeout
                : getArchiveDeviceExtension().isHL7PSUOnTimeout();
    }

    public Boolean getHL7PSUMWL() {
        return hl7PSUMWL;
    }

    public void setHL7PSUMWL(Boolean hl7PSUMWL) {
        this.hl7PSUMWL = hl7PSUMWL;
    }

    public boolean hl7PSUMWL() {
        return hl7PSUMWL != null
                ? hl7PSUMWL
                : getArchiveDeviceExtension().isHL7PSUMWL();
    }

    public Boolean getHl7PSUForRequestedProcedure() {
        return hl7PSUForRequestedProcedure;
    }

    public void setHl7PSUForRequestedProcedure(Boolean hl7PSUForRequestedProcedure) {
        this.hl7PSUForRequestedProcedure = hl7PSUForRequestedProcedure;
    }

    public boolean hl7PSUForRequestedProcedure() {
        return hl7PSUForRequestedProcedure != null
                ? hl7PSUForRequestedProcedure
                : getArchiveDeviceExtension().isHl7PSUForRequestedProcedure();
    }

    public Boolean getHl7PSUPIDPV1() {
        return hl7PSUPIDPV1;
    }

    public void setHl7PSUPIDPV1(Boolean hl7PSUPIDPV1) {
        this.hl7PSUPIDPV1 = hl7PSUPIDPV1;
    }

    public boolean hl7PSUPIDPV1() {
        return hl7PSUPIDPV1 != null
                ? hl7PSUPIDPV1
                : getArchiveDeviceExtension().isHl7PSUPIDPV1();
    }

    public String getHl7PSURequestedProcedureID() {
        return hl7PSURequestedProcedureID;
    }

    public void setHl7PSURequestedProcedureID(String hl7PSURequestedProcedureID) {
        this.hl7PSURequestedProcedureID = hl7PSURequestedProcedureID;
    }

    public String hl7PSURequestedProcedureID() {
        return hl7PSURequestedProcedureID != null
                ? hl7PSURequestedProcedureID
                : getArchiveDeviceExtension().getHl7PSURequestedProcedureID();
    }

    public String getHl7PSUAccessionNumber() {
        return hl7PSUAccessionNumber;
    }

    public void setHl7PSUAccessionNumber(String hl7PSUAccessionNumber) {
        this.hl7PSUAccessionNumber = hl7PSUAccessionNumber;
    }

    public String hl7PSUAccessionNumber() {
        return hl7PSUAccessionNumber != null
                ? hl7PSUAccessionNumber
                : getArchiveDeviceExtension().getHl7PSUAccessionNumber();
    }

    public String getHl7PSUFillerOrderNumber() {
        return hl7PSUFillerOrderNumber;
    }

    public void setHl7PSUFillerOrderNumber(String hl7PSUFillerOrderNumber) {
        this.hl7PSUFillerOrderNumber = hl7PSUFillerOrderNumber;
    }

    public String hl7PSUFillerOrderNumber() {
        return hl7PSUFillerOrderNumber != null
                ? hl7PSUFillerOrderNumber
                : getArchiveDeviceExtension().getHl7PSUFillerOrderNumber();
    }

    public String getHl7PSUPlacerOrderNumber() {
        return hl7PSUPlacerOrderNumber;
    }

    public void setHl7PSUPlacerOrderNumber(String hl7PSUPlacerOrderNumber) {
        this.hl7PSUPlacerOrderNumber = hl7PSUPlacerOrderNumber;
    }

    public String hl7PSUPlacerOrderNumber() {
        return hl7PSUPlacerOrderNumber != null
                ? hl7PSUPlacerOrderNumber
                : getArchiveDeviceExtension().getHl7PSUPlacerOrderNumber();
    }

    public Attributes.UpdatePolicy getCopyMoveUpdatePolicy() {
        return copyMoveUpdatePolicy;
    }

    public void setCopyMoveUpdatePolicy(Attributes.UpdatePolicy copyMoveUpdatePolicy) {
        this.copyMoveUpdatePolicy = copyMoveUpdatePolicy;
    }

    public Attributes.UpdatePolicy copyMoveUpdatePolicy() {
        return copyMoveUpdatePolicy != null
                ? copyMoveUpdatePolicy
                : getArchiveDeviceExtension().getCopyMoveUpdatePolicy();
    }

    public Attributes.UpdatePolicy getLinkMWLEntryUpdatePolicy() {
        return linkMWLEntryUpdatePolicy;
    }

    public void setLinkMWLEntryUpdatePolicy(Attributes.UpdatePolicy linkMWLEntryUpdatePolicy) {
        this.linkMWLEntryUpdatePolicy = linkMWLEntryUpdatePolicy;
    }

    public Attributes.UpdatePolicy linkMWLEntryUpdatePolicy() {
        return linkMWLEntryUpdatePolicy != null
                ? linkMWLEntryUpdatePolicy
                : getArchiveDeviceExtension().getLinkMWLEntryUpdatePolicy();
    }

    public Boolean getStorageVerificationUpdateLocationStatus() {
        return storageVerificationUpdateLocationStatus;
    }

    public void setStorageVerificationUpdateLocationStatus(Boolean storageVerificationUpdateLocationStatus) {
        this.storageVerificationUpdateLocationStatus = storageVerificationUpdateLocationStatus;
    }

    public boolean storageVerificationUpdateLocationStatus() {
        return storageVerificationUpdateLocationStatus != null
                ? storageVerificationUpdateLocationStatus
                : getArchiveDeviceExtension().isStorageVerificationUpdateLocationStatus();
    }

    public String[] getStorageVerificationStorageIDs() {
        return storageVerificationStorageIDs;
    }

    public void setStorageVerificationStorageIDs(String... storageVerificationStorageIDs) {
        this.storageVerificationStorageIDs = storageVerificationStorageIDs;
    }

    public String[] storageVerificationStorageIDs() {
        return storageVerificationStorageIDs.length > 0
                ? storageVerificationStorageIDs
                : getArchiveDeviceExtension().getStorageVerificationStorageIDs();
    }

    public Period getStorageVerificationInitialDelay() {
        return storageVerificationInitialDelay;
    }

    public void setStorageVerificationInitialDelay(Period storageVerificationInitialDelay) {
        this.storageVerificationInitialDelay = storageVerificationInitialDelay;
    }

    public Period storageVerificationInitialDelay() {
        ArchiveDeviceExtension arcdev = getArchiveDeviceExtension();
        return storageVerificationInitialDelay != null
                ? storageVerificationInitialDelay
                : arcdev.getStorageVerificationInitialDelay();
    }

    public Boolean getUpdateLocationStatusOnRetrieve() {
        return updateLocationStatusOnRetrieve;
    }

    public void setUpdateLocationStatusOnRetrieve(Boolean updateLocationStatusOnRetrieve) {
        this.updateLocationStatusOnRetrieve = updateLocationStatusOnRetrieve;
    }

    public boolean updateLocationStatusOnRetrieve() {
        return updateLocationStatusOnRetrieve != null
                ? updateLocationStatusOnRetrieve
                : getArchiveDeviceExtension().isUpdateLocationStatusOnRetrieve();
    }

    public Boolean getStorageVerificationOnRetrieve() {
        return storageVerificationOnRetrieve;
    }

    public void setStorageVerificationOnRetrieve(Boolean storageVerificationOnRetrieve) {
        this.storageVerificationOnRetrieve = storageVerificationOnRetrieve;
    }

    public boolean storageVerificationOnRetrieve() {
        return storageVerificationOnRetrieve != null
                ? storageVerificationOnRetrieve
                : getArchiveDeviceExtension().isStorageVerificationOnRetrieve();
    }

    public Boolean getRelationalQueryNegotiationLenient() {
        return relationalQueryNegotiationLenient;
    }

    public void setRelationalQueryNegotiationLenient(Boolean relationalQueryNegotiationLenient) {
        this.relationalQueryNegotiationLenient = relationalQueryNegotiationLenient;
    }

    public boolean relationalQueryNegotiationLenient() {
        return relationalQueryNegotiationLenient != null
                ? relationalQueryNegotiationLenient
                : getArchiveDeviceExtension().isRelationalQueryNegotiationLenient();
    }

    public Boolean getRelationalRetrieveNegotiationLenient() {
        return relationalRetrieveNegotiationLenient;
    }

    public void setRelationalRetrieveNegotiationLenient(Boolean relationalRetrieveNegotiationLenient) {
        this.relationalRetrieveNegotiationLenient = relationalRetrieveNegotiationLenient;
    }

    public boolean relationalRetrieveNegotiationLenient() {
        return relationalRetrieveNegotiationLenient != null
                ? relationalRetrieveNegotiationLenient
                : getArchiveDeviceExtension().isRelationalRetrieveNegotiationLenient();
    }

    public int[] getRejectConflictingPatientAttribute() {
        return rejectConflictingPatientAttribute;
    }

    public void setRejectConflictingPatientAttribute(int[] rejectConflictingPatientAttribute) {
        Arrays.sort(this.rejectConflictingPatientAttribute = rejectConflictingPatientAttribute);
    }

    public int[] rejectConflictingPatientAttribute() {
        return rejectConflictingPatientAttribute.length > 0
                ? rejectConflictingPatientAttribute
                : getArchiveDeviceExtension().getRejectConflictingPatientAttribute();
    }

    public Boolean getStowRetiredTransferSyntax() {
        return stowRetiredTransferSyntax;
    }

    public void setStowRetiredTransferSyntax(Boolean stowRetiredTransferSyntax) {
        this.stowRetiredTransferSyntax = stowRetiredTransferSyntax;
    }

    public boolean stowRetiredTransferSyntax() {
        return stowRetiredTransferSyntax != null
                ? stowRetiredTransferSyntax
                : getArchiveDeviceExtension().isStowRetiredTransferSyntax();
    }

    public Boolean getStowExcludeAPPMarkers() {
        return stowExcludeAPPMarkers;
    }

    public void setStowExcludeAPPMarkers(Boolean stowExcludeAPPMarkers) {
        this.stowExcludeAPPMarkers = stowExcludeAPPMarkers;
    }

    public boolean stowExcludeAPPMarkers() {
        return stowExcludeAPPMarkers != null
                ? stowExcludeAPPMarkers
                : getArchiveDeviceExtension().isStowExcludeAPPMarkers();
    }

    public Boolean getRestrictRetrieveSilently() {
        return restrictRetrieveSilently;
    }

    public void setRestrictRetrieveSilently(Boolean restrictRetrieveSilently) {
        this.restrictRetrieveSilently = restrictRetrieveSilently;
    }

    public boolean restrictRetrieveSilently() {
        return restrictRetrieveSilently != null
                ? restrictRetrieveSilently.booleanValue()
                : getArchiveDeviceExtension().isRestrictRetrieveSilently();
    }

    public Boolean getStowQuicktime2MP4() {
        return stowQuicktime2MP4;
    }

    public void setStowQuicktime2MP4(Boolean stowQuicktime2MP4) {
        this.stowQuicktime2MP4 = stowQuicktime2MP4;
    }

    public boolean stowQuicktime2MP4() {
        return stowQuicktime2MP4 != null
                ? stowQuicktime2MP4
                : getArchiveDeviceExtension().isStowQuicktime2MP4();
    }

    @Override
    public void reconfigure(AEExtension from) {
        ArchiveAEExtension aeExt = (ArchiveAEExtension) from;
        defaultCharacterSet = aeExt.defaultCharacterSet;
        upsWorklistLabel = aeExt.upsWorklistLabel;
        upsEventSCUs = aeExt.upsEventSCUs;
        upsEventSCUKeepAlive = aeExt.upsEventSCUKeepAlive;
        objectStorageIDs = aeExt.objectStorageIDs;
        objectStorageCount = aeExt.objectStorageCount;
        metadataStorageIDs = aeExt.metadataStorageIDs;
        bulkDataDescriptorID = aeExt.bulkDataDescriptorID;
        storeAccessControlID = aeExt.storeAccessControlID;
        accessControlIDs = aeExt.accessControlIDs;
        recordAttributeModification = aeExt.recordAttributeModification;
        bulkDataSpoolDirectory = aeExt.bulkDataSpoolDirectory;
        queryRetrieveViewID = aeExt.queryRetrieveViewID;
        validateCallingAEHostname = aeExt.validateCallingAEHostname;
        personNameComponentOrderInsensitiveMatching = aeExt.personNameComponentOrderInsensitiveMatching;
        sendPendingCGet = aeExt.sendPendingCGet;
        wadoThumbnailViewPort = aeExt.wadoThumbnailViewPort;
        wadoZIPEntryNameFormat = aeExt.wadoZIPEntryNameFormat;
        wadoSR2HtmlTemplateURI = aeExt.wadoSR2HtmlTemplateURI;
        wadoSR2TextTemplateURI = aeExt.wadoSR2TextTemplateURI;
        wadoCDA2HtmlTemplateURI = aeExt.wadoCDA2HtmlTemplateURI;
        mppsForwardDestinations = aeExt.mppsForwardDestinations;
        ianDestinations = aeExt.ianDestinations;
        ianOnTimeout = aeExt.ianOnTimeout;
        spanningCFindSCP = aeExt.spanningCFindSCP;
        spanningCFindSCPRetrieveAETitles = aeExt.spanningCFindSCPRetrieveAETitles;
        fallbackCMoveSCP = aeExt.fallbackCMoveSCP;
        fallbackCMoveSCPDestination = aeExt.fallbackCMoveSCPDestination;
        fallbackCMoveSCPCallingAET = aeExt.fallbackCMoveSCPCallingAET;
        fallbackCMoveSCPLeadingCFindSCP = aeExt.fallbackCMoveSCPLeadingCFindSCP;
        fallbackCMoveSCPRetries = aeExt.fallbackCMoveSCPRetries;
        externalRetrieveAEDestination = aeExt.externalRetrieveAEDestination;
        alternativeCMoveSCP = aeExt.alternativeCMoveSCP;
        queryMaxNumberOfResults = aeExt.queryMaxNumberOfResults;
        qidoMaxNumberOfResults = aeExt.qidoMaxNumberOfResults;
        fallbackCMoveSCPStudyOlderThan = aeExt.fallbackCMoveSCPStudyOlderThan;
        storePermissionServiceURL = aeExt.storePermissionServiceURL;
        storePermissionServiceResponse = aeExt.storePermissionServiceResponse;
        storePermissionServiceResponsePattern = aeExt.storePermissionServiceResponsePattern;
        storePermissionServiceExpirationDatePattern = aeExt.storePermissionServiceExpirationDatePattern;
        storePermissionServiceErrorCommentPattern = aeExt.storePermissionServiceErrorCommentPattern;
        storePermissionServiceErrorCodePattern = aeExt.storePermissionServiceErrorCodePattern;
        copyMoveUpdatePolicy = aeExt.copyMoveUpdatePolicy;
        linkMWLEntryUpdatePolicy = aeExt.linkMWLEntryUpdatePolicy;
        retrieveAETitles = aeExt.retrieveAETitles;
        returnRetrieveAETitles = aeExt.returnRetrieveAETitles;
        hl7PSUSendingApplication = aeExt.hl7PSUSendingApplication;
        hl7PSUReceivingApplications = aeExt.hl7PSUReceivingApplications;
        hl7PSUOnTimeout = aeExt.hl7PSUOnTimeout;
        hl7PSUMWL = aeExt.hl7PSUMWL;
        hl7PSUForRequestedProcedure = aeExt.hl7PSUForRequestedProcedure;
        hl7PSUPIDPV1 = aeExt.hl7PSUPIDPV1;
        hl7PSURequestedProcedureID = aeExt.hl7PSURequestedProcedureID;
        hl7PSUAccessionNumber = aeExt.hl7PSUAccessionNumber;
        hl7PSUFillerOrderNumber = aeExt.hl7PSUFillerOrderNumber;
        hl7PSUPlacerOrderNumber = aeExt.hl7PSUPlacerOrderNumber;
        storageVerificationUpdateLocationStatus = aeExt.storageVerificationUpdateLocationStatus;
        storageVerificationStorageIDs = aeExt.storageVerificationStorageIDs;
        storageVerificationInitialDelay = aeExt.storageVerificationInitialDelay;
        updateLocationStatusOnRetrieve = aeExt.updateLocationStatusOnRetrieve;
        storageVerificationOnRetrieve = aeExt.storageVerificationOnRetrieve;
        relationalQueryNegotiationLenient = aeExt.relationalQueryNegotiationLenient;
        relationalRetrieveNegotiationLenient = aeExt.relationalRetrieveNegotiationLenient;
        rejectConflictingPatientAttribute = aeExt.rejectConflictingPatientAttribute;
        stowRetiredTransferSyntax = aeExt.stowRetiredTransferSyntax;
        stowExcludeAPPMarkers = aeExt.stowExcludeAPPMarkers;
        restrictRetrieveSilently = aeExt.restrictRetrieveSilently;
        stowQuicktime2MP4 = aeExt.stowQuicktime2MP4;
        acceptedMoveDestinations.clear();
        acceptedMoveDestinations.addAll(aeExt.acceptedMoveDestinations);
        attributeCoercions.clear();
        attributeCoercions.addAll(aeExt.attributeCoercions);
    }

    public ArchiveDeviceExtension getArchiveDeviceExtension() {
        //return ae.getDevice().getDeviceExtension(ArchiveDeviceExtension.class);
        return new ArchiveDeviceExtension();
    }



    public ArchiveAttributeCoercion findAttributeCoercion(Dimse dimse, TransferCapability.Role role, String sopClass,
            String sendingHost, String sendingAET, String receivingHost, String receivingAET, Attributes attrs) {
        ArchiveAttributeCoercion coercion1 = null;
        for (Collection<ArchiveAttributeCoercion> coercions
                : new Collection[]{ attributeCoercions, getArchiveDeviceExtension().getAttributeCoercions() })
            for (ArchiveAttributeCoercion coercion : coercions)
                if (coercion.match(dimse, role, sopClass, sendingHost, sendingAET, receivingHost, receivingAET, attrs))
                    if (coercion1 == null || coercion1.getPriority() < coercion.getPriority())
                        coercion1 = coercion;
        return coercion1;
    }

   
}
