package com.laudoecia.api.worklist;

import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.io.BasicBulkDataDescriptor;
import org.dcm4che3.io.BulkDataDescriptor;
import org.dcm4che3.net.DeviceExtension;
import org.dcm4che3.util.StringUtils;

import com.laudoecia.api.utilities.FuzzyStr;

public class ArchiveDeviceExtension extends DeviceExtension {

    public static final String AUDIT_UNKNOWN_STUDY_INSTANCE_UID = "1.2.40.0.13.1.15.110.3.165.1";
    public static final String AUDIT_UNKNOWN_PATIENT_ID = "<none>";
    public static final String JBOSS_SERVER_TEMP_DIR = "${jboss.server.temp.dir}";
    public static final String DEFAULT_WADO_ZIP_ENTRY_NAME_FORMAT = "DICOM/{0020000D,hash}/{0020000E,hash}/{00080018,hash}";
    public static final String WADO_THUMBNAIL_VIEWPORT = "64,64";

    private volatile String defaultCharacterSet;
    private volatile String upsWorklistLabel;
    private volatile String[] upsEventSCUs = {};
    private volatile int upsEventSCUKeepAlive;
    private volatile String fuzzyAlgorithmClass;
    private volatile String bulkDataDescriptorID;
    private volatile String[] seriesMetadataStorageIDs = {};
    private volatile int seriesMetadataFetchSize = 100;
    private volatile int seriesMetadataThreads = 1;
    private volatile int seriesMetadataMaxRetries = 0;
    private volatile boolean purgeInstanceRecords;
    private volatile int purgeInstanceRecordsFetchSize = 100;
    private volatile int deleteUPSFetchSize = 100;
    private volatile boolean recordAttributeModification = true;
    private volatile String bulkDataSpoolDirectory = JBOSS_SERVER_TEMP_DIR;
    private volatile boolean validateCallingAEHostname = false;
    private volatile boolean sendPendingCGet = false;
    private volatile boolean personNameComponentOrderInsensitiveMatching = false;
    private volatile int queryFetchSize = 100;
    private volatile int queryMaxNumberOfResults = 0;
    private volatile int qidoMaxNumberOfResults = 0;
    private volatile String wadoThumbnailViewPort = WADO_THUMBNAIL_VIEWPORT;
    private volatile String wadoZIPEntryNameFormat = DEFAULT_WADO_ZIP_ENTRY_NAME_FORMAT;
    private volatile String wadoSR2HtmlTemplateURI;
    private volatile String wadoSR2TextTemplateURI;
    private volatile String wadoCDA2HtmlTemplateURI;
    private volatile String patientUpdateTemplateURI;
    private volatile String importReportTemplateURI;
    private volatile String scheduleProcedureTemplateURI;
    private volatile String unzipVendorDataToURI;
    private volatile String outgoingPatientUpdateTemplateURI;
    private volatile String[] mppsForwardDestinations = {};
    private volatile String[] ianDestinations = {};
    private volatile boolean ianOnTimeout;
    private volatile int ianTaskFetchSize = 100;
    private volatile String spanningCFindSCP;
    private volatile String[] spanningCFindSCPRetrieveAETitles = {};
    private volatile String fallbackCMoveSCP;
    private volatile String fallbackCMoveSCPDestination;
    private volatile String fallbackCMoveSCPCallingAET;
    private volatile String fallbackCMoveSCPLeadingCFindSCP;
    private volatile int fallbackCMoveSCPRetries;
    private volatile String externalRetrieveAEDestination;
    private volatile String xdsiImagingDocumentSourceAETitle;
    private volatile String alternativeCMoveSCP;
    private volatile int exportTaskFetchSize = 100;
    private volatile int retrieveTaskFetchSize = 100;
    private volatile int deleteRejectedFetchSize = 100;
    private volatile int purgeStorageFetchSize = 100;
    private volatile int deleteStudyBatchSize = 10;
    private volatile boolean deletePatientOnDeleteLastStudy = false;
    private volatile int failedToDeleteFetchSize = 100;
    private volatile int leadingCFindSCPQueryCacheSize = 10;
    private volatile String auditSpoolDirectory = JBOSS_SERVER_TEMP_DIR;
    private volatile String stowSpoolDirectory = JBOSS_SERVER_TEMP_DIR;
    private volatile String wadoSpoolDirectory = JBOSS_SERVER_TEMP_DIR;
    private volatile int deleteMWLFetchSize = 100;
    private volatile String[] deleteMWLDelay = {};
    private volatile String hl7LogFilePattern;
    private volatile String hl7ErrorLogFilePattern;
    private volatile int rejectExpiredStudiesFetchSize = 0;
    private volatile int rejectExpiredSeriesFetchSize = 0;
    private volatile String rejectExpiredStudiesAETitle;
    private volatile String fallbackCMoveSCPStudyOlderThan;
    private volatile String storePermissionServiceURL;
    private volatile String storePermissionServiceResponse;
    private volatile Pattern storePermissionServiceResponsePattern;
    private volatile Pattern storePermissionServiceExpirationDatePattern;
    private volatile Pattern storePermissionServiceErrorCommentPattern;
    private volatile Pattern storePermissionServiceErrorCodePattern;
    private volatile int storePermissionCacheSize = 10;
    private volatile int mergeMWLCacheSize = 10;
    private volatile int storeUpdateDBMaxRetries = 1;
    private volatile int storeUpdateDBMaxRetryDelay = 1000;
    private volatile int storeUpdateDBMinRetryDelay = 500;
    private volatile String[] retrieveAETitles = {};
    private volatile String[] returnRetrieveAETitles = {};
    private volatile String remapRetrieveURL;
    private volatile String remapRetrieveURLClientHost;
    private volatile String hl7PSUSendingApplication;
    private volatile String[] hl7PSUReceivingApplications = {};
    private volatile boolean hl7PSUOnTimeout;
    private volatile int hl7PSUTaskFetchSize = 100;
    private volatile boolean hl7PSUMWL = false;
    private volatile boolean hl7PSUForRequestedProcedure = false;
    private volatile boolean hl7PSUPIDPV1 = false;
    private volatile String hl7PSURequestedProcedureID;
    private volatile String hl7PSUAccessionNumber;
    private volatile String hl7PSUFillerOrderNumber;
    private volatile String hl7PSUPlacerOrderNumber;
    private volatile String auditRecordRepositoryURL;
    private volatile String atna2JsonFhirTemplateURI;
    private volatile String atna2XmlFhirTemplateURI;
    private volatile Attributes.UpdatePolicy copyMoveUpdatePolicy = Attributes.UpdatePolicy.PRESERVE;
    private volatile Attributes.UpdatePolicy linkMWLEntryUpdatePolicy = Attributes.UpdatePolicy.PRESERVE;
    private volatile boolean hl7TrackChangedPatientID = true;
    private volatile boolean auditSoftwareConfigurationVerbose = false;
    private volatile boolean hl7UseNullValue = false;
    private volatile String[] hl7ADTReceivingApplication = {};
    private volatile String hl7ADTSendingApplication;
    private volatile int queueTasksFetchSize = 100;
    private volatile String auditUnknownStudyInstanceUID = AUDIT_UNKNOWN_STUDY_INSTANCE_UID;
    private volatile String auditUnknownPatientID = AUDIT_UNKNOWN_PATIENT_ID;
    private volatile String rejectionNoteStorageAET;
    private volatile String uiConfigurationDeviceName;
    private volatile boolean storageVerificationUpdateLocationStatus;
    private volatile String[] storageVerificationStorageIDs = {};
    private volatile String storageVerificationAETitle;
    private volatile String storageVerificationBatchID;
    private volatile Period storageVerificationInitialDelay;
    private volatile Period storageVerificationPeriod;
    private volatile int storageVerificationMaxScheduled;
    private volatile int storageVerificationFetchSize = 100;
    private volatile boolean updateLocationStatusOnRetrieve;
    private volatile boolean storageVerificationOnRetrieve;
    private volatile String compressionAETitle;
    private volatile int compressionFetchSize = 100;
    private volatile int compressionThreads = 1;
    private volatile String patientVerificationPDQServiceID;
    private volatile int patientVerificationFetchSize = 100;
    private volatile Period patientVerificationPeriod;
    private volatile Period patientVerificationPeriodOnNotFound;
    private volatile int patientVerificationMaxRetries;
    private volatile boolean patientVerificationAdjustIssuerOfPatientID;
    private volatile String hl7DicomCharacterSet;
    private volatile boolean hl7VeterinaryUsePatientName;
    private volatile int csvUploadChunkSize = 100;
    private volatile boolean validateUID = true;
    private volatile boolean relationalQueryNegotiationLenient;
    private volatile boolean relationalRetrieveNegotiationLenient;
    private volatile int[] rejectConflictingPatientAttribute = {};
    private volatile int schedulerMinStartDelay = 60;
    private volatile boolean stowRetiredTransferSyntax;
    private volatile boolean stowExcludeAPPMarkers;
    private volatile boolean restrictRetrieveSilently;
    private volatile boolean stowQuicktime2MP4;

    private final HashSet<String> wadoSupportedSRClasses = new HashSet<>();
    private final HashSet<String> wadoSupportedPRClasses = new HashSet<>();
    private final Map<String, BasicBulkDataDescriptor> bulkDataDescriptorMap = new HashMap<>();
    private final List<ArchiveAttributeCoercion> attributeCoercions = new ArrayList<>();
    private final LinkedHashSet<String> hl7NoPatientCreateMessageTypes = new LinkedHashSet<>();
    private final Map<String,String> xRoadProperties = new HashMap<>();
    private final Map<String,String> impaxReportProperties = new HashMap<>();
    private final Map<String, String> importReportTemplateParams = new HashMap<>();

    private transient FuzzyStr fuzzyStr;

    public String getDefaultCharacterSet() {
        return defaultCharacterSet;
    }

    public void setDefaultCharacterSet(String defaultCharacterSet) {
        this.defaultCharacterSet = defaultCharacterSet;
    }

    public String getUPSWorklistLabel() {
        return upsWorklistLabel;
    }

    public void setUPSWorklistLabel(String upsWorklistLabel) {
        this.upsWorklistLabel = upsWorklistLabel;
    }

    public String[] getUPSEventSCUs() {
        return upsEventSCUs;
    }

    public void setUPSEventSCUs(String[] upsEventSCUs) {
        this.upsEventSCUs = upsEventSCUs;
    }

    public int getUPSEventSCUKeepAlive() {
        return upsEventSCUKeepAlive;
    }

    public void setUPSEventSCUKeepAlive(int upsEventSCUKeepAlive) {
        this.upsEventSCUKeepAlive = upsEventSCUKeepAlive;
    }

    public String getFuzzyAlgorithmClass() {
        return fuzzyAlgorithmClass;
    }

    public void setFuzzyAlgorithmClass(String fuzzyAlgorithmClass) {
        this.fuzzyStr = fuzzyStr(fuzzyAlgorithmClass);
        this.fuzzyAlgorithmClass = fuzzyAlgorithmClass;
    }

    public FuzzyStr getFuzzyStr() {
        if (fuzzyStr == null)
            if (fuzzyAlgorithmClass == null)
                throw new IllegalStateException("No Fuzzy Algorithm Class configured");
            else
                fuzzyStr = fuzzyStr(fuzzyAlgorithmClass);
        return fuzzyStr;
    }

    private static FuzzyStr fuzzyStr(String s) {
        try {
            return (FuzzyStr) Class.forName(s).newInstance();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException(s);
        }
    }

    public boolean isRecordAttributeModification() {
        return recordAttributeModification;
    }

    public void setRecordAttributeModification(boolean recordAttributeModification) {
        this.recordAttributeModification = recordAttributeModification;
    }

    public String getBulkDataSpoolDirectory() {
        return bulkDataSpoolDirectory;
    }

    public void setBulkDataSpoolDirectory(String bulkDataSpoolDirectory) {
        this.bulkDataSpoolDirectory = Objects.requireNonNull(bulkDataSpoolDirectory, "BulkDataSpoolDirectory");
    }

    public String getBulkDataDescriptorID() {
        return bulkDataDescriptorID;
    }

    public void setBulkDataDescriptorID(String bulkDataDescriptorID) {
        this.bulkDataDescriptorID = bulkDataDescriptorID;
    }

    public String[] getSeriesMetadataStorageIDs() {
        return seriesMetadataStorageIDs;
    }

    public void setSeriesMetadataStorageIDs(String... seriesMetadataStorageIDs) {
        Arrays.sort(this.seriesMetadataStorageIDs = seriesMetadataStorageIDs);
    }

    public int getSeriesMetadataFetchSize() {
        return seriesMetadataFetchSize;
    }

    public void setSeriesMetadataFetchSize(int seriesMetadataFetchSize) {
        this.seriesMetadataFetchSize =  greaterZero(seriesMetadataFetchSize, "seriesMetadataFetchSize");
    }

    public int getSeriesMetadataThreads() {
        return seriesMetadataThreads;
    }

    public void setSeriesMetadataThreads(int seriesMetadataThreads) {
        this.seriesMetadataThreads = seriesMetadataThreads;
    }

    public int getSeriesMetadataMaxRetries() {
        return seriesMetadataMaxRetries;
    }

    public void setSeriesMetadataMaxRetries(int seriesMetadataMaxRetries) {
        this.seriesMetadataMaxRetries = seriesMetadataMaxRetries;
    }

    public boolean isPurgeInstanceRecords() {
        return purgeInstanceRecords;
    }

    public void setPurgeInstanceRecords(boolean purgeInstanceRecords) {
        this.purgeInstanceRecords = purgeInstanceRecords;
    }

    public int getPurgeInstanceRecordsFetchSize() {
        return purgeInstanceRecordsFetchSize;
    }

    public void setPurgeInstanceRecordsFetchSize(int purgeInstanceRecordsFetchSize) {
        this.purgeInstanceRecordsFetchSize =  greaterZero(purgeInstanceRecordsFetchSize, "purgeInstanceRecordsFetchSize");
    }

    public int getDeleteUPSFetchSize() {
        return deleteUPSFetchSize;
    }

    public void setDeleteUPSFetchSize(int deleteUPSFetchSize) {
        this.deleteUPSFetchSize = deleteUPSFetchSize;
    }

    public boolean isPersonNameComponentOrderInsensitiveMatching() {
        return personNameComponentOrderInsensitiveMatching;
    }

    public void setPersonNameComponentOrderInsensitiveMatching(boolean personNameComponentOrderInsensitiveMatching) {
        this.personNameComponentOrderInsensitiveMatching = personNameComponentOrderInsensitiveMatching;
    }

    public boolean isValidateCallingAEHostname() {
        return validateCallingAEHostname;
    }

    public void setValidateCallingAEHostname(boolean validateCallingAEHostname) {
        this.validateCallingAEHostname = validateCallingAEHostname;
    }

    public boolean isSendPendingCGet() {
        return sendPendingCGet;
    }

    public void setSendPendingCGet(boolean sendPendingCGet) {
        this.sendPendingCGet = sendPendingCGet;
    }


    public String[] getWadoSupportedSRClasses() {
        return wadoSupportedSRClasses.toArray(StringUtils.EMPTY_STRING);
    }

    public void setWadoSupportedSRClasses(String... wadoSupportedSRClasses) {
        this.wadoSupportedSRClasses.clear();
        this.wadoSupportedSRClasses.addAll(Arrays.asList(wadoSupportedSRClasses));
    }

    public boolean isWadoSupportedSRClass(String cuid) {
        return wadoSupportedSRClasses.contains(cuid);
    }

    public String[] getWadoSupportedPRClasses() {
        return wadoSupportedPRClasses.toArray(StringUtils.EMPTY_STRING);
    }

    public void setWadoSupportedPRClasses(String... wadoSupportedPRClasses) {
        this.wadoSupportedPRClasses.clear();
        this.wadoSupportedPRClasses.addAll(Arrays.asList(wadoSupportedPRClasses));
    }

    public boolean isWadoSupportedPRClass(String cuid) {
        return wadoSupportedPRClasses.contains(cuid);
    }

    public String getWadoThumbnailViewPort() {
        return wadoThumbnailViewPort;
    }

    public void setWadoThumbnailViewPort(String wadoThumbnailViewPort) {
        if (!Pattern.matches("[1-9]\\d{0,2},[1-9]\\d{0,2}", wadoThumbnailViewPort))
            throw new IllegalArgumentException(wadoThumbnailViewPort);
        this.wadoThumbnailViewPort = wadoThumbnailViewPort;
    }

    public String getWadoZIPEntryNameFormat() {
        return wadoZIPEntryNameFormat;
    }

    public void setWadoZIPEntryNameFormat(String wadoZIPEntryNameFormat) {
        this.wadoZIPEntryNameFormat = wadoZIPEntryNameFormat;
    }

    public String getWadoSR2HtmlTemplateURI() {
        return wadoSR2HtmlTemplateURI;
    }

    public void setWadoSR2HtmlTemplateURI(String wadoSR2HtmlTemplateURI) {
        this.wadoSR2HtmlTemplateURI = wadoSR2HtmlTemplateURI;
    }

    public String getWadoSR2TextTemplateURI() {
        return wadoSR2TextTemplateURI;
    }

    public void setWadoSR2TextTemplateURI(String wadoSR2TextTemplateURI) {
        this.wadoSR2TextTemplateURI = wadoSR2TextTemplateURI;
    }

    public String getWadoCDA2HtmlTemplateURI() {
        return wadoCDA2HtmlTemplateURI;
    }

    public void setWadoCDA2HtmlTemplateURI(String wadoCDA2HtmlTemplateURI) {
        this.wadoCDA2HtmlTemplateURI = wadoCDA2HtmlTemplateURI;
    }

    public String getPatientUpdateTemplateURI() {
        return patientUpdateTemplateURI;
    }

    public void setPatientUpdateTemplateURI(String patientUpdateTemplateURI) {
        this.patientUpdateTemplateURI = patientUpdateTemplateURI;
    }

    public String getImportReportTemplateURI() {
        return importReportTemplateURI;
    }

    public void setImportReportTemplateURI(String importReportTemplateURI) {
        this.importReportTemplateURI = importReportTemplateURI;
    }

    public String getScheduleProcedureTemplateURI() {
        return scheduleProcedureTemplateURI;
    }

    public void setScheduleProcedureTemplateURI(String scheduleProcedureTemplateURI) {
        this.scheduleProcedureTemplateURI = scheduleProcedureTemplateURI;
    }

    public String getUnzipVendorDataToURI() {
        return unzipVendorDataToURI;
    }

    public void setUnzipVendorDataToURI(String unzipVendorDataToURI) {
        this.unzipVendorDataToURI = unzipVendorDataToURI;
    }

    public String getOutgoingPatientUpdateTemplateURI() {
        return outgoingPatientUpdateTemplateURI;
    }

    public void setOutgoingPatientUpdateTemplateURI(String outgoingPatientUpdateTemplateURI) {
        this.outgoingPatientUpdateTemplateURI = outgoingPatientUpdateTemplateURI;
    }

    public String[] getMppsForwardDestinations() {
        return mppsForwardDestinations;
    }

    public void setMppsForwardDestinations(String... mppsForwardDestinations) {
        this.mppsForwardDestinations = mppsForwardDestinations;
    }

    public String[] getIanDestinations() {
        return ianDestinations;
    }

    public void setIanDestinations(String... ianDestinations) {
        this.ianDestinations = ianDestinations;
    }

    public boolean isIanOnTimeout() {
        return ianOnTimeout;
    }

    public void setIanOnTimeout(boolean ianOnTimeout) {
        this.ianOnTimeout = ianOnTimeout;
    }

   
    public int getIanTaskFetchSize() {
        return ianTaskFetchSize;
    }

    public void setIanTaskFetchSize(int ianTaskFetchSize) {
        this.ianTaskFetchSize = greaterZero(ianTaskFetchSize, "ianTaskFetchSize");
    }

    public String getSpanningCFindSCP() {
        return spanningCFindSCP;
    }

    public void setSpanningCFindSCP(String spanningCFindSCP) {
        this.spanningCFindSCP = spanningCFindSCP;
    }

    public String[] getSpanningCFindSCPRetrieveAETitles() {
        return spanningCFindSCPRetrieveAETitles;
    }

    public void setSpanningCFindSCPRetrieveAETitles(String[] spanningCFindSCPRetrieveAETitles) {
        this.spanningCFindSCPRetrieveAETitles = spanningCFindSCPRetrieveAETitles;
    }

    public String getFallbackCMoveSCP() {
        return fallbackCMoveSCP;
    }

    public void setFallbackCMoveSCP(String fallbackCMoveSCP) {
        this.fallbackCMoveSCP = fallbackCMoveSCP;
    }

    public String getFallbackCMoveSCPDestination() {
        return fallbackCMoveSCPDestination;
    }

    public void setFallbackCMoveSCPDestination(String fallbackCMoveSCPDestination) {
        this.fallbackCMoveSCPDestination = fallbackCMoveSCPDestination;
    }

    public String getFallbackCMoveSCPCallingAET() {
        return fallbackCMoveSCPCallingAET;
    }

    public void setFallbackCMoveSCPCallingAET(String fallbackCMoveSCPCallingAET) {
        this.fallbackCMoveSCPCallingAET = fallbackCMoveSCPCallingAET;
    }

    public String getFallbackCMoveSCPLeadingCFindSCP() {
        return fallbackCMoveSCPLeadingCFindSCP;
    }

    public void setFallbackCMoveSCPLeadingCFindSCP(String fallbackCMoveSCPLeadingCFindSCP) {
        this.fallbackCMoveSCPLeadingCFindSCP = fallbackCMoveSCPLeadingCFindSCP;
    }

    public int getFallbackCMoveSCPRetries() {
        return fallbackCMoveSCPRetries;
    }

    public void setFallbackCMoveSCPRetries(int fallbackCMoveSCPRetries) {
        this.fallbackCMoveSCPRetries = fallbackCMoveSCPRetries;
    }

    public String getExternalRetrieveAEDestination() {
        return externalRetrieveAEDestination;
    }

    public void setExternalRetrieveAEDestination(String externalRetrieveAEDestination) {
        this.externalRetrieveAEDestination = externalRetrieveAEDestination;
    }

    public String getXDSiImagingDocumentSourceAETitle() {
        return xdsiImagingDocumentSourceAETitle;
    }

    public void setXDSiImagingDocumentSourceAETitle(String xdsiImagingDocumentSourceAETitle) {
        this.xdsiImagingDocumentSourceAETitle = xdsiImagingDocumentSourceAETitle;
    }

    public String getAlternativeCMoveSCP() {
        return alternativeCMoveSCP;
    }

    public void setAlternativeCMoveSCP(String alternativeCMoveSCP) {
        this.alternativeCMoveSCP = alternativeCMoveSCP;
    }

    public int getQueryFetchSize() {
        return queryFetchSize;
    }

    public void setQueryFetchSize(int queryFetchSize) {
       this.queryFetchSize = greaterOrEqualsZero(queryFetchSize, "queryFetchSize");
    }

    public int getQueryMaxNumberOfResults() {
        return queryMaxNumberOfResults;
    }

    public void setQueryMaxNumberOfResults(int queryMaxNumberOfResults) {
        this.queryMaxNumberOfResults = queryMaxNumberOfResults;
    }

    public int getQidoMaxNumberOfResults() {
        return qidoMaxNumberOfResults;
    }

    public void setQidoMaxNumberOfResults(int qidoMaxNumberOfResults) {
        this.qidoMaxNumberOfResults = qidoMaxNumberOfResults;
    }

    public int getExportTaskFetchSize() {
        return exportTaskFetchSize;
    }

    public void setExportTaskFetchSize(int exportTaskFetchSize) {
        this.exportTaskFetchSize = greaterZero(exportTaskFetchSize, "exportTaskFetchSize");
    }

    public int getDeleteRejectedFetchSize() {
        return deleteRejectedFetchSize;
    }

    public void setDeleteRejectedFetchSize(int deleteRejectedFetchSize) {
        this.deleteRejectedFetchSize =  greaterZero(deleteRejectedFetchSize, "deleteRejectedFetchSize");

    }
    
    public int getPurgeStorageFetchSize() {
        return purgeStorageFetchSize;
    }

    public void setPurgeStorageFetchSize(int purgeStorageFetchSize) {
        this.purgeStorageFetchSize = greaterZero(purgeStorageFetchSize, "purgeStorageFetchSize");
    }

    public int getDeleteStudyBatchSize() {
        return deleteStudyBatchSize;
    }

    public void setDeleteStudyBatchSize(int deleteStudyBatchSize) {
        this.deleteStudyBatchSize = greaterZero(deleteStudyBatchSize, "deleteStudyBatchSize");
    }

    public boolean isDeletePatientOnDeleteLastStudy() {
        return deletePatientOnDeleteLastStudy;
    }

    public void setDeletePatientOnDeleteLastStudy(boolean deletePatientOnDeleteLastStudy) {
        this.deletePatientOnDeleteLastStudy = deletePatientOnDeleteLastStudy;
    }

    public int getFailedToDeleteFetchSize() {
        return failedToDeleteFetchSize;
    }

    public void setFailedToDeleteFetchSize(int failedToDeleteFetchSize) {
        this.failedToDeleteFetchSize = failedToDeleteFetchSize;
    }

    public int getLeadingCFindSCPQueryCacheSize() {
        return leadingCFindSCPQueryCacheSize;
    }

    public void setLeadingCFindSCPQueryCacheSize(int leadingCFindSCPQueryCacheSize) {
        this.leadingCFindSCPQueryCacheSize =
                greaterZero(leadingCFindSCPQueryCacheSize, "leadingCFindSCPQueryCacheSize");
    }

    public String getAuditSpoolDirectory() {
        return auditSpoolDirectory;
    }

    public void setAuditSpoolDirectory(String auditSpoolDirectory) {
        this.auditSpoolDirectory = Objects.requireNonNull(auditSpoolDirectory, "AuditSpoolDirectory");
    }

    public String getStowSpoolDirectory() {
        return stowSpoolDirectory;
    }

    public void setStowSpoolDirectory(String stowSpoolDirectory) {
        this.stowSpoolDirectory = Objects.requireNonNull(stowSpoolDirectory, "StowSpoolDirectory");
    }

    public String getWadoSpoolDirectory() {
        return wadoSpoolDirectory;
    }

    public void setWadoSpoolDirectory(String wadoSpoolDirectory) {
        this.wadoSpoolDirectory = Objects.requireNonNull(wadoSpoolDirectory, "WadoSpoolDirectory");
    }

    public String getHL7LogFilePattern() {
        return hl7LogFilePattern;
    }

    public void setHL7LogFilePattern(String hl7LogFilePattern) {
        this.hl7LogFilePattern = hl7LogFilePattern;
    }

    public String getHL7ErrorLogFilePattern() {
        return hl7ErrorLogFilePattern;
    }

    public void setHL7ErrorLogFilePattern(String hl7ErrorLogFilePattern) {
        this.hl7ErrorLogFilePattern = hl7ErrorLogFilePattern;
    }

    public int getRejectExpiredStudiesFetchSize() {
        return rejectExpiredStudiesFetchSize;
    }

    public void setRejectExpiredStudiesFetchSize(int rejectExpiredStudiesFetchSize) {
        this.rejectExpiredStudiesFetchSize =
                greaterOrEqualsZero(rejectExpiredStudiesFetchSize, "rejectExpiredStudiesFetchSize");
    }

    public int getRejectExpiredSeriesFetchSize() {
        return rejectExpiredSeriesFetchSize;
    }

    public void setRejectExpiredSeriesFetchSize(int rejectExpiredSeriesFetchSize) {
        this.rejectExpiredSeriesFetchSize =
                greaterOrEqualsZero(rejectExpiredSeriesFetchSize, "rejectExpiredSeriesFetchSize");
    }

    public String getRejectExpiredStudiesAETitle() {
        return rejectExpiredStudiesAETitle;
    }

    public void setRejectExpiredStudiesAETitle(String rejectExpiredStudiesAETitle) {
        this.rejectExpiredStudiesAETitle = rejectExpiredStudiesAETitle;
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

    public String getStorePermissionServiceResponse() {
        return storePermissionServiceResponse;
    }

    public void setStorePermissionServiceResponse(String storePermissionServiceResponse) {
        this.storePermissionServiceResponse = storePermissionServiceResponse;
    }

    public Pattern getStorePermissionServiceResponsePattern() {
        return storePermissionServiceResponsePattern;
    }

    public void setStorePermissionServiceResponsePattern(Pattern storePermissionServiceResponsePattern) {
        this.storePermissionServiceResponsePattern = storePermissionServiceResponsePattern;
    }

    public Pattern getStorePermissionServiceExpirationDatePattern() {
        return storePermissionServiceExpirationDatePattern;
    }

    public void setStorePermissionServiceExpirationDatePattern(Pattern storePermissionServiceExpirationDatePattern) {
        this.storePermissionServiceExpirationDatePattern = storePermissionServiceExpirationDatePattern;
    }

    public Pattern getStorePermissionServiceErrorCommentPattern() {
        return storePermissionServiceErrorCommentPattern;
    }

    public void setStorePermissionServiceErrorCommentPattern(Pattern storePermissionServiceErrorCommentPattern) {
        this.storePermissionServiceErrorCommentPattern = storePermissionServiceErrorCommentPattern;
    }

    public Pattern getStorePermissionServiceErrorCodePattern() {
        return storePermissionServiceErrorCodePattern;
    }

    public void setStorePermissionServiceErrorCodePattern(Pattern storePermissionServiceErrorCodePattern) {
        this.storePermissionServiceErrorCodePattern = storePermissionServiceErrorCodePattern;
    }

    public int getStorePermissionCacheSize() {
        return storePermissionCacheSize;
    }

    public void setStorePermissionCacheSize(int storePermissionCacheSize) {
        this.storePermissionCacheSize = greaterZero(storePermissionCacheSize, "storePermissionCacheSize");
    }

    public int getMergeMWLCacheSize() {
        return mergeMWLCacheSize;
    }

    public void setMergeMWLCacheSize(int mergeMWLCacheSize) {
        this.mergeMWLCacheSize = greaterZero(mergeMWLCacheSize, "mergeMWLCacheSize");
    }

    public int getStoreUpdateDBMaxRetries() {
        return storeUpdateDBMaxRetries;
    }

    public void setStoreUpdateDBMaxRetries(int storeUpdateDBMaxRetries) {
        this.storeUpdateDBMaxRetries = storeUpdateDBMaxRetries;
    }

    public int getStoreUpdateDBMaxRetryDelay() {
        return storeUpdateDBMaxRetryDelay;
    }

    public void setStoreUpdateDBMaxRetryDelay(int storeUpdateDBMaxRetryDelay) {
        this.storeUpdateDBMaxRetryDelay = storeUpdateDBMaxRetryDelay;
    }

    public int getStoreUpdateDBMinRetryDelay() {
        return storeUpdateDBMinRetryDelay;
    }

    public void setStoreUpdateDBMinRetryDelay(int storeUpdateDBMinRetryDelay) {
        this.storeUpdateDBMinRetryDelay = storeUpdateDBMinRetryDelay;
    }

    public int storeUpdateDBRetryDelay() {
        return storeUpdateDBMinRetryDelay + ThreadLocalRandom.current().nextInt(Math.max(1,
                (storeUpdateDBMaxRetryDelay - storeUpdateDBMinRetryDelay)));
    }

    public String getRemapRetrieveURL() {
        return remapRetrieveURLClientHost != null
                ? ('[' + remapRetrieveURLClientHost + ']' + remapRetrieveURL)
                : remapRetrieveURL;
    }

    public void setRemapRetrieveURL(String remapRetrieveURL) {
        if (remapRetrieveURL == null || remapRetrieveURL.charAt(0) != '[') {
            this.remapRetrieveURL = remapRetrieveURL;
            this.remapRetrieveURLClientHost = null;
        } else {
            String[] ss = StringUtils.split(remapRetrieveURL.substring(1), ']');
            if (ss.length != 2)
                throw new IllegalArgumentException(remapRetrieveURL);

            this.remapRetrieveURL = ss[1];
            this.remapRetrieveURLClientHost = ss[0];
        }
    }

    public StringBuffer remapRetrieveURL(HttpServletRequest request) {
        StringBuffer sb = request.getRequestURL();
        if (remap(request)) {
            sb.setLength(0);
            sb.append(remapRetrieveURL).append(request.getRequestURI());
        }
        return sb;
    }

    private boolean remap(HttpServletRequest request) {
        return remapRetrieveURL != null
                && (remapRetrieveURLClientHost == null || remapRetrieveURLClientHost.equals(
                        StringUtils.isIPAddr(remapRetrieveURLClientHost)
                                ? request.getRemoteAddr()
                                : request.getRemoteHost()));
    }

    public String getHL7PSUSendingApplication() {
        return hl7PSUSendingApplication;
    }

    public void setHL7PSUSendingApplication(String hl7PSUSendingApplication) {
        this.hl7PSUSendingApplication = hl7PSUSendingApplication;
    }

    public String[] getHL7PSUReceivingApplications() {
        return hl7PSUReceivingApplications;
    }

    public void setHL7PSUReceivingApplications(String[] hl7PSUReceivingApplications) {
        this.hl7PSUReceivingApplications = hl7PSUReceivingApplications;
    }

    public boolean isHL7PSUOnTimeout() {
        return hl7PSUOnTimeout;
    }

    public void setHL7PSUOnTimeout(boolean hl7PSUOnTimeout) {
        this.hl7PSUOnTimeout = hl7PSUOnTimeout;
    }

    public int getHL7PSUTaskFetchSize() {
        return hl7PSUTaskFetchSize;
    }

    public void setHL7PSUTaskFetchSize(int hl7PSUTaskFetchSize) {
        this.hl7PSUTaskFetchSize = hl7PSUTaskFetchSize;
    }

    public boolean isHL7PSUMWL() {
        return hl7PSUMWL;
    }

    public void setHL7PSUMWL(boolean hl7PSUMWL) {
        this.hl7PSUMWL = hl7PSUMWL;
    }

    public boolean isHl7PSUForRequestedProcedure() {
        return hl7PSUForRequestedProcedure;
    }

    public void setHl7PSUForRequestedProcedure(boolean hl7PSUForRequestedProcedure) {
        this.hl7PSUForRequestedProcedure = hl7PSUForRequestedProcedure;
    }

    public boolean isHl7PSUPIDPV1() {
        return hl7PSUPIDPV1;
    }

    public void setHl7PSUPIDPV1(boolean hl7PSUPIDPV1) {
        this.hl7PSUPIDPV1 = hl7PSUPIDPV1;
    }

    public String getHl7PSURequestedProcedureID() {
        return hl7PSURequestedProcedureID;
    }

    public void setHl7PSURequestedProcedureID(String hl7PSURequestedProcedureID) {
        this.hl7PSURequestedProcedureID = hl7PSURequestedProcedureID;
    }

    public String getHl7PSUAccessionNumber() {
        return hl7PSUAccessionNumber;
    }

    public void setHl7PSUAccessionNumber(String hl7PSUAccessionNumber) {
        this.hl7PSUAccessionNumber = hl7PSUAccessionNumber;
    }

    public String getHl7PSUFillerOrderNumber() {
        return hl7PSUFillerOrderNumber;
    }

    public void setHl7PSUFillerOrderNumber(String hl7PSUFillerOrderNumber) {
        this.hl7PSUFillerOrderNumber = hl7PSUFillerOrderNumber;
    }

    public String getHl7PSUPlacerOrderNumber() {
        return hl7PSUPlacerOrderNumber;
    }

    public void setHl7PSUPlacerOrderNumber(String hl7PSUPlacerOrderNumber) {
        this.hl7PSUPlacerOrderNumber = hl7PSUPlacerOrderNumber;
    }

    public String[] getHL7NoPatientCreateMessageTypes() {
        return hl7NoPatientCreateMessageTypes.toArray(
                new String[hl7NoPatientCreateMessageTypes.size()]);
    }

    public void setHL7NoPatientCreateMessageTypes(String... messageTypes) {
        hl7NoPatientCreateMessageTypes.clear();
        for (String messageType : messageTypes)
            hl7NoPatientCreateMessageTypes.add(messageType);
    }

    public boolean isHL7NoPatientCreateMessageType(String messageType) {
        return hl7NoPatientCreateMessageTypes.contains(messageType);
    }

    public Map<String, String> getXRoadProperties() {
        return xRoadProperties;
    }

    public void setXRoadProperty(String name, String value) {
        xRoadProperties.put(name, value);
    }

    public void setXRoadProperties(String[] ss) {
        xRoadProperties.clear();
        for (String s : ss) {
            int index = s.indexOf('=');
            if (index < 0)
                throw new IllegalArgumentException("Property in incorrect format : " + s);
            setXRoadProperty(s.substring(0, index), s.substring(index+1));
        }
    }

    public boolean hasXRoadProperties() {
        return xRoadProperties.containsKey("endpoint");
    }

    public Map<String, String> getImpaxReportProperties() {
        return impaxReportProperties;
    }

    public void setImpaxReportProperty(String name, String value) {
        impaxReportProperties.put(name, value);
    }

    public void setImpaxReportProperties(String[] ss) {
        impaxReportProperties.clear();
        for (String s : ss) {
            int index = s.indexOf('=');
            if (index < 0)
                throw new IllegalArgumentException("Property in incorrect format : " + s);
            setImpaxReportProperty(s.substring(0, index), s.substring(index+1));
        }
    }

    public boolean hasImpaxReportProperties() {
        return impaxReportProperties.containsKey("endpoint");
    }

    public BulkDataDescriptor getBulkDataDescriptor(String bulkDataDescriptorID) {
        if (bulkDataDescriptorID == null)
            return BulkDataDescriptor.DEFAULT;

        BasicBulkDataDescriptor descriptor = bulkDataDescriptorMap.get(bulkDataDescriptorID);
        if (descriptor == null)
            throw new IllegalArgumentException("No Bulk Data Descriptor with ID " + bulkDataDescriptorID + " configured");

        return descriptor;
    }

    public BulkDataDescriptor getBulkDataDescriptor() {
        return getBulkDataDescriptor(bulkDataDescriptorID);
    }

    public void addBulkDataDescriptor(BasicBulkDataDescriptor descriptor) {
        bulkDataDescriptorMap.put(descriptor.getBulkDataDescriptorID(), descriptor);
    }

    public BasicBulkDataDescriptor removeBulkDataDescriptor(String bulkDataDescriptorID) {
        return bulkDataDescriptorMap.remove(bulkDataDescriptorID);
    }

    public void clearBulkDataDescriptors() {
        bulkDataDescriptorMap.clear();
    }

    public Map<String, BasicBulkDataDescriptor> getBulkDataDescriptors() {
        return bulkDataDescriptorMap;
    }

    private int greaterZero(int i, String prompt) {
        if (i <= 0)
            throw new IllegalArgumentException(prompt + ": " + i);
        return i;
    }

    private int greaterOrEqualsZero(int i, String prompt) {
        if (i < 0)
            throw new IllegalArgumentException(prompt + ": " + i);
        return i;
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

    public String[] getReturnRetrieveAETitles() {
        return returnRetrieveAETitles;
    }

    public void setReturnRetrieveAETitles(String... returnRetrieveAETitles) {
        this.returnRetrieveAETitles = returnRetrieveAETitles;
    }

    public String getAuditRecordRepositoryURL() {
        return auditRecordRepositoryURL;
    }

    public void setAuditRecordRepositoryURL(String auditRecordRepositoryURL) {
        this.auditRecordRepositoryURL = auditRecordRepositoryURL;
    }

    public String getAudit2JsonFhirTemplateURI() {
        return atna2JsonFhirTemplateURI;
    }

    public void setAudit2JsonFhirTemplateURI(String atna2JsonFhirTemplateURI) {
        this.atna2JsonFhirTemplateURI = atna2JsonFhirTemplateURI;
    }

    public String getAudit2XmlFhirTemplateURI() {
        return atna2XmlFhirTemplateURI;
    }

    public void setAudit2XmlFhirTemplateURI(String atna2XmlFhirTemplateURI) {
        this.atna2XmlFhirTemplateURI = atna2XmlFhirTemplateURI;
    }

    public Attributes.UpdatePolicy getCopyMoveUpdatePolicy() {
        return copyMoveUpdatePolicy;
    }

    public void setCopyMoveUpdatePolicy(Attributes.UpdatePolicy copyMoveUpdatePolicy) {
        this.copyMoveUpdatePolicy = copyMoveUpdatePolicy;
    }

    public Attributes.UpdatePolicy getLinkMWLEntryUpdatePolicy() {
        return linkMWLEntryUpdatePolicy;
    }

    public void setLinkMWLEntryUpdatePolicy(Attributes.UpdatePolicy linkMWLEntryUpdatePolicy) {
        this.linkMWLEntryUpdatePolicy = linkMWLEntryUpdatePolicy;
    }

    public boolean isHL7TrackChangedPatientID() {
        return hl7TrackChangedPatientID;
    }

    public void setHL7TrackChangedPatientID(boolean hl7TrackChangedPatientID) {
        this.hl7TrackChangedPatientID = hl7TrackChangedPatientID;
    }

    public boolean isAuditSoftwareConfigurationVerbose() {
        return auditSoftwareConfigurationVerbose;
    }

    public void setAuditSoftwareConfigurationVerbose(boolean auditSoftwareConfigurationVerbose) {
        this.auditSoftwareConfigurationVerbose = auditSoftwareConfigurationVerbose;
    }

    public String[] getHL7ADTReceivingApplication() {
        return hl7ADTReceivingApplication;
    }

    public void setHL7ADTReceivingApplication(String[] hl7ADTReceivingApplication) {
        this.hl7ADTReceivingApplication = hl7ADTReceivingApplication;
    }

    public String getHL7ADTSendingApplication() {
        return hl7ADTSendingApplication;
    }

    public void setHL7ADTSendingApplication(String hl7ADTSendingApplication) {
        this.hl7ADTSendingApplication = hl7ADTSendingApplication;
    }

    public String getAuditUnknownStudyInstanceUID() {
        return auditUnknownStudyInstanceUID;
    }

    public void setAuditUnknownStudyInstanceUID(String auditUnknownStudyInstanceUID) {
        this.auditUnknownStudyInstanceUID = auditUnknownStudyInstanceUID;
    }

    public String auditUnknownStudyInstanceUID() {
        return StringUtils.maskNull(auditUnknownStudyInstanceUID, "1.2.40.0.13.1.15.110.3.165.1");
    }

    public String getAuditUnknownPatientID() {
        return auditUnknownPatientID;
    }

    public void setAuditUnknownPatientID(String auditUnknownPatientID) {
        this.auditUnknownPatientID = auditUnknownPatientID;
    }

    public String auditUnknownPatientID() {
        return StringUtils.maskNull(auditUnknownPatientID, "<none>");
    }

    public boolean isHL7UseNullValue() {
        return hl7UseNullValue;
    }

    public void setHL7UseNullValue(boolean hl7UseNullValue) {
        this.hl7UseNullValue = hl7UseNullValue;
    }

    public int getQueueTasksFetchSize() {
        return queueTasksFetchSize;
    }

    public void setQueueTasksFetchSize(int queueTasksFetchSize) {
        this.queueTasksFetchSize = queueTasksFetchSize;
    }

    public String getRejectionNoteStorageAET() {
        return rejectionNoteStorageAET;
    }

    public void setRejectionNoteStorageAET(String rejectionNoteStorageAET) {
        this.rejectionNoteStorageAET = rejectionNoteStorageAET;
    }

    public String getUiConfigurationDeviceName() {
        return uiConfigurationDeviceName;
    }

    public void setUiConfigurationDeviceName(String uiConfigurationDeviceName) {
        this.uiConfigurationDeviceName = uiConfigurationDeviceName;
    }

    public boolean isStorageVerificationUpdateLocationStatus() {
        return storageVerificationUpdateLocationStatus;
    }

    public void setStorageVerificationUpdateLocationStatus(boolean storageVerificationUpdateLocationStatus) {
        this.storageVerificationUpdateLocationStatus = storageVerificationUpdateLocationStatus;
    }

    public String[] getStorageVerificationStorageIDs() {
        return storageVerificationStorageIDs;
    }

    public void setStorageVerificationStorageIDs(String... storageVerificationStorageIDs) {
        this.storageVerificationStorageIDs = storageVerificationStorageIDs;
    }

    public String getStorageVerificationAETitle() {
        return storageVerificationAETitle;
    }

    public void setStorageVerificationAETitle(String storageVerificationAETitle) {
        this.storageVerificationAETitle = storageVerificationAETitle;
    }

    public String getStorageVerificationBatchID() {
        return storageVerificationBatchID;
    }

    public void setStorageVerificationBatchID(String storageVerificationBatchID) {
        this.storageVerificationBatchID = storageVerificationBatchID;
    }

    public Period getStorageVerificationInitialDelay() {
        return storageVerificationInitialDelay;
    }

    public void setStorageVerificationInitialDelay(Period storageVerificationInitialDelay) {
        this.storageVerificationInitialDelay = storageVerificationInitialDelay;
    }

    public Period getStorageVerificationPeriod() {
        return storageVerificationPeriod;
    }

    public void setStorageVerificationPeriod(Period storageVerificationPeriod) {
        this.storageVerificationPeriod = storageVerificationPeriod;
    }

    public int getStorageVerificationMaxScheduled() {
        return storageVerificationMaxScheduled;
    }

    public void setStorageVerificationMaxScheduled(int storageVerificationMaxScheduled) {
        this.storageVerificationMaxScheduled = storageVerificationMaxScheduled;
    }

    public int getStorageVerificationFetchSize() {
        return storageVerificationFetchSize;
    }

    public void setStorageVerificationFetchSize(int storageVerificationFetchSize) {
        this.storageVerificationFetchSize = storageVerificationFetchSize;
    }

    public boolean isUpdateLocationStatusOnRetrieve() {
        return updateLocationStatusOnRetrieve;
    }

    public void setUpdateLocationStatusOnRetrieve(boolean updateLocationStatusOnRetrieve) {
        this.updateLocationStatusOnRetrieve = updateLocationStatusOnRetrieve;
    }

    public boolean isStorageVerificationOnRetrieve() {
        return storageVerificationOnRetrieve;
    }

    public void setStorageVerificationOnRetrieve(boolean storageVerificationOnRetrieve) {
        this.storageVerificationOnRetrieve = storageVerificationOnRetrieve;
    }

    public String getCompressionAETitle() {
        return compressionAETitle;
    }

    public void setCompressionAETitle(String compressionAETitle) {
        this.compressionAETitle = compressionAETitle;
    }

    public int getCompressionFetchSize() {
        return compressionFetchSize;
    }

    public void setCompressionFetchSize(int compressionFetchSize) {
        this.compressionFetchSize = greaterZero(compressionFetchSize, "CompressionFetchSize");
    }

    public int getCompressionThreads() {
        return compressionThreads;
    }

    public void setCompressionThreads(int compressionThreads) {
        this.compressionThreads = greaterZero(compressionThreads, "CompressionThreads");
    }

    public int getPatientVerificationFetchSize() {
        return patientVerificationFetchSize;
    }

    public void setPatientVerificationFetchSize(int patientVerificationFetchSize) {
        this.patientVerificationFetchSize = patientVerificationFetchSize;
    }

    public String getPatientVerificationPDQServiceID() {
        return patientVerificationPDQServiceID;
    }

    public Period getPatientVerificationPeriod() {
        return patientVerificationPeriod;
    }

    public void setPatientVerificationPeriod(Period patientVerificationPeriod) {
        this.patientVerificationPeriod = patientVerificationPeriod;
    }

    public Period getPatientVerificationPeriodOnNotFound() {
        return patientVerificationPeriodOnNotFound;
    }

    public void setPatientVerificationPeriodOnNotFound(Period patientVerificationPeriodOnNotFound) {
        this.patientVerificationPeriodOnNotFound = patientVerificationPeriodOnNotFound;
    }

    public int getPatientVerificationMaxRetries() {
        return patientVerificationMaxRetries;
    }

    public void setPatientVerificationMaxRetries(int patientVerificationMaxRetries) {
        this.patientVerificationMaxRetries = patientVerificationMaxRetries;
    }

    public boolean isPatientVerificationAdjustIssuerOfPatientID() {
        return patientVerificationAdjustIssuerOfPatientID;
    }

    public void setPatientVerificationAdjustIssuerOfPatientID(boolean patientVerificationAdjustIssuerOfPatientID) {
        this.patientVerificationAdjustIssuerOfPatientID = patientVerificationAdjustIssuerOfPatientID;
    }

    public Map<String, String> getImportReportTemplateParams() {
        return importReportTemplateParams;
    }

    public void setImportReportTemplateParam(String name, String value) {
        importReportTemplateParams.put(name, value);
    }

    public void setImportReportTemplateParams(String[] ss) {
        importReportTemplateParams.clear();
        for (String s : ss) {
            int index = s.indexOf('=');
            if (index < 0)
                throw new IllegalArgumentException("XSLT parameter in incorrect format : " + s);
            setImportReportTemplateParam(s.substring(0, index), s.substring(index+1));
        }
    }

    public String getHl7DicomCharacterSet() {
        return hl7DicomCharacterSet;
    }

    public void setHl7DicomCharacterSet(String hl7DicomCharacterSet) {
        this.hl7DicomCharacterSet = hl7DicomCharacterSet;
    }

    public boolean isHl7VeterinaryUsePatientName() {
        return hl7VeterinaryUsePatientName;
    }

    public void setHl7VeterinaryUsePatientName(boolean hl7VeterinaryUsePatientName) {
        this.hl7VeterinaryUsePatientName = hl7VeterinaryUsePatientName;
    }

    public int getCSVUploadChunkSize() {
        return csvUploadChunkSize;
    }

    public void setCSVUploadChunkSize(int csvUploadChunkSize) {
        this.csvUploadChunkSize = csvUploadChunkSize;
    }

    public boolean isValidateUID() {
        return validateUID;
    }

    public void setValidateUID(boolean validateUID) {
        this.validateUID = validateUID;
    }

    public boolean isRelationalQueryNegotiationLenient() {
        return relationalQueryNegotiationLenient;
    }

    public void setRelationalQueryNegotiationLenient(boolean relationalQueryNegotiationLenient) {
        this.relationalQueryNegotiationLenient = relationalQueryNegotiationLenient;
    }

    public boolean isRelationalRetrieveNegotiationLenient() {
        return relationalRetrieveNegotiationLenient;
    }

    public void setRelationalRetrieveNegotiationLenient(boolean relationalRetrieveNegotiationLenient) {
        this.relationalRetrieveNegotiationLenient = relationalRetrieveNegotiationLenient;
    }

    public int[] getRejectConflictingPatientAttribute() {
        return rejectConflictingPatientAttribute;
    }

    public void setRejectConflictingPatientAttribute(int[] rejectConflictingPatientAttribute) {
        Arrays.sort(this.rejectConflictingPatientAttribute = rejectConflictingPatientAttribute);
    }

    public int getSchedulerMinStartDelay() {
        return schedulerMinStartDelay;
    }

    public void setSchedulerMinStartDelay(int schedulerMinStartDelay) {
        this.schedulerMinStartDelay = schedulerMinStartDelay;
    }

    public boolean isStowRetiredTransferSyntax() {
        return stowRetiredTransferSyntax;
    }

    public void setStowRetiredTransferSyntax(boolean stowRetiredTransferSyntax) {
        this.stowRetiredTransferSyntax = stowRetiredTransferSyntax;
    }

    public boolean isStowExcludeAPPMarkers() {
        return stowExcludeAPPMarkers;
    }

    public void setStowExcludeAPPMarkers(boolean stowExcludeAPPMarkers) {
        this.stowExcludeAPPMarkers = stowExcludeAPPMarkers;
    }

    public boolean isRestrictRetrieveSilently() {
        return restrictRetrieveSilently;
    }

    public void setRestrictRetrieveSilently(boolean restrictRetrieveSilently) {
        this.restrictRetrieveSilently = restrictRetrieveSilently;
    }

    public int getRetrieveTaskFetchSize() {
        return retrieveTaskFetchSize;
    }

    public void setRetrieveTaskFetchSize(int retrieveTaskFetchSize) {
        this.retrieveTaskFetchSize = retrieveTaskFetchSize;
    }

    public boolean isStowQuicktime2MP4() {
        return stowQuicktime2MP4;
    }

    public void setStowQuicktime2MP4(boolean stowQuicktime2MP4) {
        this.stowQuicktime2MP4 = stowQuicktime2MP4;
    }

    public int getDeleteMWLFetchSize() {
        return deleteMWLFetchSize;
    }

    public void setDeleteMWLFetchSize(int deleteMWLFetchSize) {
        this.deleteMWLFetchSize = deleteMWLFetchSize;
    }

    public String[] getDeleteMWLDelay() {
        return deleteMWLDelay;
    }

    public void setDeleteMWLDelay(String[] deleteMWLDelay) {
        this.deleteMWLDelay = deleteMWLDelay;
    }

    @Override
    public void reconfigure(DeviceExtension from) {
        ArchiveDeviceExtension arcdev = (ArchiveDeviceExtension) from;
        defaultCharacterSet = arcdev.defaultCharacterSet;
        upsWorklistLabel = arcdev.upsWorklistLabel;
        upsEventSCUs = arcdev.upsEventSCUs;
        upsEventSCUKeepAlive = arcdev.upsEventSCUKeepAlive;
        fuzzyAlgorithmClass = arcdev.fuzzyAlgorithmClass;
        fuzzyStr = arcdev.fuzzyStr;
        bulkDataDescriptorID = arcdev.bulkDataDescriptorID;
        seriesMetadataStorageIDs = arcdev.seriesMetadataStorageIDs;
        seriesMetadataFetchSize = arcdev.seriesMetadataFetchSize;
        seriesMetadataThreads = arcdev.seriesMetadataThreads;
        purgeInstanceRecords = arcdev.purgeInstanceRecords;
        purgeInstanceRecordsFetchSize = arcdev.purgeInstanceRecordsFetchSize;
        deleteUPSFetchSize = arcdev.deleteUPSFetchSize;
        recordAttributeModification = arcdev.recordAttributeModification;
        bulkDataSpoolDirectory = arcdev.bulkDataSpoolDirectory;
        personNameComponentOrderInsensitiveMatching = arcdev.personNameComponentOrderInsensitiveMatching;
        validateCallingAEHostname = arcdev.validateCallingAEHostname;
        sendPendingCGet = arcdev.sendPendingCGet;
        wadoSupportedSRClasses.clear();
        wadoSupportedSRClasses.addAll(arcdev.wadoSupportedSRClasses);
        wadoSupportedPRClasses.clear();
        wadoSupportedPRClasses.addAll(arcdev.wadoSupportedPRClasses);
        wadoThumbnailViewPort = arcdev.wadoThumbnailViewPort;
        wadoZIPEntryNameFormat = arcdev.wadoZIPEntryNameFormat;
        wadoSR2HtmlTemplateURI = arcdev.wadoSR2HtmlTemplateURI;
        wadoSR2TextTemplateURI = arcdev.wadoSR2TextTemplateURI;
        wadoCDA2HtmlTemplateURI = arcdev.wadoCDA2HtmlTemplateURI;
        patientUpdateTemplateURI = arcdev.patientUpdateTemplateURI;
        importReportTemplateURI = arcdev.importReportTemplateURI;
        scheduleProcedureTemplateURI = arcdev.scheduleProcedureTemplateURI;
        outgoingPatientUpdateTemplateURI = arcdev.outgoingPatientUpdateTemplateURI;
        queryFetchSize = arcdev.queryFetchSize;
        queryMaxNumberOfResults = arcdev.queryMaxNumberOfResults;
        qidoMaxNumberOfResults = arcdev.qidoMaxNumberOfResults;
        mppsForwardDestinations = arcdev.mppsForwardDestinations;
        ianDestinations = arcdev.ianDestinations;
        ianOnTimeout = arcdev.ianOnTimeout;
        ianTaskFetchSize = arcdev.ianTaskFetchSize;
        spanningCFindSCP = arcdev.spanningCFindSCP;
        spanningCFindSCPRetrieveAETitles = arcdev.spanningCFindSCPRetrieveAETitles;
        fallbackCMoveSCP = arcdev.fallbackCMoveSCP;
        fallbackCMoveSCPDestination = arcdev.fallbackCMoveSCPDestination;
        fallbackCMoveSCPCallingAET = arcdev.fallbackCMoveSCPCallingAET;
        fallbackCMoveSCPLeadingCFindSCP = arcdev.fallbackCMoveSCPLeadingCFindSCP;
        fallbackCMoveSCPRetries = arcdev.fallbackCMoveSCPRetries;
        externalRetrieveAEDestination = arcdev.externalRetrieveAEDestination;
        xdsiImagingDocumentSourceAETitle = arcdev.xdsiImagingDocumentSourceAETitle;
        alternativeCMoveSCP = arcdev.alternativeCMoveSCP;
        exportTaskFetchSize = arcdev.exportTaskFetchSize;
        retrieveTaskFetchSize = arcdev.retrieveTaskFetchSize;
        deleteRejectedFetchSize = arcdev.deleteRejectedFetchSize;
        purgeStorageFetchSize = arcdev.purgeStorageFetchSize;
        deleteStudyBatchSize = arcdev.deleteStudyBatchSize;
        deletePatientOnDeleteLastStudy = arcdev.deletePatientOnDeleteLastStudy;
        failedToDeleteFetchSize = arcdev.failedToDeleteFetchSize;
        leadingCFindSCPQueryCacheSize = arcdev.leadingCFindSCPQueryCacheSize;
        auditSpoolDirectory = arcdev.auditSpoolDirectory;
        stowSpoolDirectory = arcdev.stowSpoolDirectory;
        wadoSpoolDirectory = arcdev.wadoSpoolDirectory;
        hl7LogFilePattern = arcdev.hl7LogFilePattern;
        hl7ErrorLogFilePattern = arcdev.hl7ErrorLogFilePattern;
        rejectExpiredStudiesFetchSize = arcdev.rejectExpiredStudiesFetchSize;
        rejectExpiredSeriesFetchSize = arcdev.rejectExpiredSeriesFetchSize;
        rejectExpiredStudiesAETitle = arcdev.rejectExpiredStudiesAETitle;
        fallbackCMoveSCPStudyOlderThan = arcdev.fallbackCMoveSCPStudyOlderThan;
        storePermissionServiceURL = arcdev.storePermissionServiceURL;
        storePermissionServiceResponse = arcdev.storePermissionServiceResponse;
        storePermissionServiceResponsePattern = arcdev.storePermissionServiceResponsePattern;
        storePermissionServiceExpirationDatePattern = arcdev.storePermissionServiceExpirationDatePattern;
        storePermissionServiceErrorCommentPattern = arcdev.storePermissionServiceErrorCommentPattern;
        storePermissionServiceErrorCodePattern = arcdev.storePermissionServiceErrorCodePattern;
        storePermissionCacheSize = arcdev.storePermissionCacheSize;
        mergeMWLCacheSize = arcdev.mergeMWLCacheSize;
        storeUpdateDBMaxRetries = arcdev.storeUpdateDBMaxRetries;
        storeUpdateDBMaxRetryDelay = arcdev.storeUpdateDBMaxRetryDelay;
        storeUpdateDBMinRetryDelay = arcdev.storeUpdateDBMinRetryDelay;
        retrieveAETitles = arcdev.retrieveAETitles;
        returnRetrieveAETitles = arcdev.returnRetrieveAETitles;
        remapRetrieveURL = arcdev.remapRetrieveURL;
        remapRetrieveURLClientHost = arcdev.remapRetrieveURLClientHost;
        hl7PSUSendingApplication = arcdev.hl7PSUSendingApplication;
        hl7PSUReceivingApplications = arcdev.hl7PSUReceivingApplications;
        hl7PSUOnTimeout = arcdev.hl7PSUOnTimeout;
        hl7PSUTaskFetchSize = arcdev.hl7PSUTaskFetchSize;
        hl7PSUMWL = arcdev.hl7PSUMWL;
        hl7PSUForRequestedProcedure = arcdev.hl7PSUForRequestedProcedure;
        hl7PSUPIDPV1 = arcdev.hl7PSUPIDPV1;
        hl7PSURequestedProcedureID = arcdev.hl7PSURequestedProcedureID;
        hl7PSUAccessionNumber = arcdev.hl7PSUAccessionNumber;
        hl7PSUFillerOrderNumber = arcdev.hl7PSUFillerOrderNumber;
        hl7PSUPlacerOrderNumber = arcdev.hl7PSUPlacerOrderNumber;
        auditRecordRepositoryURL = arcdev.auditRecordRepositoryURL;
        atna2JsonFhirTemplateURI = arcdev.atna2JsonFhirTemplateURI;
        atna2XmlFhirTemplateURI = arcdev.atna2XmlFhirTemplateURI;
        copyMoveUpdatePolicy = arcdev.copyMoveUpdatePolicy;
        linkMWLEntryUpdatePolicy = arcdev.linkMWLEntryUpdatePolicy;
        hl7TrackChangedPatientID = arcdev.hl7TrackChangedPatientID;
        hl7ADTReceivingApplication = arcdev.hl7ADTReceivingApplication;
        hl7ADTSendingApplication = arcdev.hl7ADTSendingApplication;
        auditUnknownStudyInstanceUID = arcdev.auditUnknownStudyInstanceUID;
        auditUnknownPatientID = arcdev.auditUnknownPatientID;
        auditSoftwareConfigurationVerbose = arcdev.auditSoftwareConfigurationVerbose;
        hl7UseNullValue = arcdev.hl7UseNullValue;
        queueTasksFetchSize = arcdev.queueTasksFetchSize;
        rejectionNoteStorageAET = arcdev.rejectionNoteStorageAET;
        uiConfigurationDeviceName = arcdev.uiConfigurationDeviceName;
        storageVerificationUpdateLocationStatus = arcdev.storageVerificationUpdateLocationStatus;
        storageVerificationStorageIDs = arcdev.storageVerificationStorageIDs;
        storageVerificationAETitle = arcdev.storageVerificationAETitle;
        storageVerificationBatchID = arcdev.storageVerificationBatchID;
        storageVerificationInitialDelay = arcdev.storageVerificationInitialDelay;
        storageVerificationPeriod = arcdev.storageVerificationPeriod;
        storageVerificationMaxScheduled = arcdev.storageVerificationMaxScheduled;
        storageVerificationFetchSize = arcdev.storageVerificationFetchSize;
        updateLocationStatusOnRetrieve = arcdev.updateLocationStatusOnRetrieve;
        storageVerificationOnRetrieve = arcdev.storageVerificationOnRetrieve;
        compressionAETitle = arcdev.compressionAETitle;
        compressionFetchSize = arcdev.compressionFetchSize;
        compressionThreads = arcdev.compressionThreads;
        patientVerificationPDQServiceID = arcdev.patientVerificationPDQServiceID;
        patientVerificationFetchSize = arcdev.patientVerificationFetchSize;
        patientVerificationPeriod = arcdev.patientVerificationPeriod;
        patientVerificationPeriodOnNotFound = arcdev.patientVerificationPeriodOnNotFound;
        patientVerificationMaxRetries = arcdev.patientVerificationMaxRetries;
        patientVerificationAdjustIssuerOfPatientID = arcdev.patientVerificationAdjustIssuerOfPatientID;
        csvUploadChunkSize = arcdev.csvUploadChunkSize;
        validateUID = arcdev.validateUID;
        hl7DicomCharacterSet = arcdev.hl7DicomCharacterSet;
        hl7VeterinaryUsePatientName = arcdev.hl7VeterinaryUsePatientName;
        relationalQueryNegotiationLenient = arcdev.relationalQueryNegotiationLenient;
        relationalRetrieveNegotiationLenient = arcdev.relationalRetrieveNegotiationLenient;
        rejectConflictingPatientAttribute = arcdev.rejectConflictingPatientAttribute;
        schedulerMinStartDelay = arcdev.schedulerMinStartDelay;
        stowRetiredTransferSyntax = arcdev.stowRetiredTransferSyntax;
        stowExcludeAPPMarkers = arcdev.stowExcludeAPPMarkers;
        restrictRetrieveSilently = arcdev.restrictRetrieveSilently;
        stowQuicktime2MP4 = arcdev.stowQuicktime2MP4;
        deleteMWLFetchSize = arcdev.deleteMWLFetchSize;
        deleteMWLDelay = arcdev.deleteMWLDelay;
        bulkDataDescriptorMap.clear();
        bulkDataDescriptorMap.putAll(arcdev.bulkDataDescriptorMap);
        hl7NoPatientCreateMessageTypes.clear();
        hl7NoPatientCreateMessageTypes.addAll(arcdev.hl7NoPatientCreateMessageTypes);
        attributeCoercions.clear();
        attributeCoercions.addAll(arcdev.attributeCoercions);
        xRoadProperties.clear();
        xRoadProperties.putAll(arcdev.xRoadProperties);
        impaxReportProperties.clear();
        impaxReportProperties.putAll(arcdev.impaxReportProperties);
        importReportTemplateParams.clear();
        importReportTemplateParams.putAll(arcdev.importReportTemplateParams);
    }
}
