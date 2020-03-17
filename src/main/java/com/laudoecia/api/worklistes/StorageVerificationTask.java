package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.csv.CSVPrinter;
import org.dcm4che3.util.StringUtils;

public class StorageVerificationTask {
	public static final String UPDATE_RESULT_BY_PK = "StorageVerificationTask.UpdateResultByPk";

	private long pk;

	private Date createdTime;

	private Date updatedTime;

	private String localAET;

	private StorageVerificationPolicy storageVerificationPolicy;

	private Boolean updateLocationStatus;

	private String storageIDs;

	private String studyInstanceUID;

	private String seriesInstanceUID;

	private String sopInstanceUID;

	private int completed;

	private int failed;

	private QueueMessage queueMessage;

	public void setUpdatedTime() {
		updatedTime = new Date();
	}

	public long getPk() {
		return pk;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public String getLocalAET() {
		return localAET;
	}

	public void setLocalAET(String localAET) {
		this.localAET = localAET;
	}

	public StorageVerificationPolicy getStorageVerificationPolicy() {
		return storageVerificationPolicy;
	}

	public void setStorageVerificationPolicy(StorageVerificationPolicy storageVerificationPolicy) {
		this.storageVerificationPolicy = storageVerificationPolicy;
	}

	public Boolean getUpdateLocationStatus() {
		return updateLocationStatus;
	}

	public void setUpdateLocationStatus(Boolean updateLocationStatus) {
		this.updateLocationStatus = updateLocationStatus;
	}

	public String getStorageIDsAsString() {
		return storageIDs;
	}

	public String[] getStorageIDs() {
		return StringUtils.split(storageIDs, '\\');
	}

	public void setStorageIDs(String[] storageIDs) {
		if (storageIDs != null && storageIDs.length > 0) {
			Arrays.sort(storageIDs);
			this.storageIDs = StringUtils.concat(storageIDs, '\\');
		} else {
			this.storageIDs = null;
		}
	}

	public String getStudyInstanceUID() {
		return studyInstanceUID;
	}

	public void setStudyInstanceUID(String studyInstanceUID) {
		this.studyInstanceUID = studyInstanceUID;
	}

	public String getSeriesInstanceUID() {
		return seriesInstanceUID;
	}

	public void setSeriesInstanceUID(String seriesInstanceUID) {
		this.seriesInstanceUID = seriesInstanceUID;
	}

	public String getSOPInstanceUID() {
		return sopInstanceUID;
	}

	public void setSOPInstanceUID(String sopInstanceUID) {
		this.sopInstanceUID = sopInstanceUID;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getFailed() {
		return failed;
	}

	public void setFailed(int failed) {
		this.failed = failed;
	}

	public QueueMessage getQueueMessage() {
		return queueMessage;
	}

	public void setQueueMessage(QueueMessage queueMessage) {
		this.queueMessage = queueMessage;
	}

	public static final String[] header = { "pk", "createdTime", "updatedTime", "LocalAET", "StgCmtPolicy",
			"UpdateLocationStatus", "StorageID", "StudyInstanceUID", "SeriesInstanceUID", "SOPInstanceUID", "completed",
			"failed", "JMSMessageID", "queue", "dicomDeviceName", "status", "scheduledTime", "failures", "batchID",
			"processingStartTime", "processingEndTime", "errorMessage", "outcomeMessage" };

	public void printRecord(CSVPrinter printer) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		printer.printRecord(String.valueOf(pk), df.format(createdTime), df.format(updatedTime), localAET,
				storageVerificationPolicy, String.valueOf(updateLocationStatus), storageIDs, studyInstanceUID,
				seriesInstanceUID, sopInstanceUID, String.valueOf(completed), String.valueOf(failed),
				queueMessage.getMessageID(), queueMessage.getQueueName(), queueMessage.getDeviceName(),
				queueMessage.getStatus().toString(), queueMessage.getScheduledTime(),
				queueMessage.getNumberOfFailures() > 0 ? String.valueOf(queueMessage.getNumberOfFailures()) : null,
				queueMessage.getBatchID(),
				queueMessage.getProcessingStartTime() != null ? df.format(queueMessage.getProcessingStartTime()) : null,
				queueMessage.getProcessingEndTime() != null ? df.format(queueMessage.getProcessingEndTime()) : null,
				queueMessage.getErrorMessage(), queueMessage.getOutcomeMessage());
	}

	@Override
	public String toString() {
		return "StgVerTask[pk=" + pk + ", LocalAET=" + localAET + ", StudyIUID=" + studyInstanceUID + ", SeriesIUID="
				+ seriesInstanceUID + "]";
	}
}
