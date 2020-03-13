package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.PrePersist;

import org.apache.commons.csv.CSVPrinter;


public class DiffTask {

	private long pk;

	private Date createdTime;

	private Date updatedTime;

	private String localAET;

	private String primaryAET;

	private String secondaryAET;

	private String queryString;

	private boolean checkMissing;

	private boolean checkDifferent;

	private String compareFields;

	private int matches;

	private int missing;

	private int different;

	private QueueMessage queueMessage;

	@PrePersist
	public void onPrePersist() {
		Date now = new Date();
		createdTime = now;
		updatedTime = now;
	}

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

	public String getPrimaryAET() {
		return primaryAET;
	}

	public void setPrimaryAET(String primaryAET) {
		this.primaryAET = primaryAET;
	}

	public String getSecondaryAET() {
		return secondaryAET;
	}

	public void setSecondaryAET(String secondaryAET) {
		this.secondaryAET = secondaryAET;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public boolean isCheckMissing() {
		return checkMissing;
	}

	public void setCheckMissing(boolean checkMissing) {
		this.checkMissing = checkMissing;
	}

	public boolean isCheckDifferent() {
		return checkDifferent;
	}

	public void setCheckDifferent(boolean checkDifferent) {
		this.checkDifferent = checkDifferent;
	}

	public String getCompareFields() {
		return compareFields;
	}

	public void setCompareFields(String compareFields) {
		this.compareFields = compareFields;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getMissing() {
		return missing;
	}

	public void setMissing(int missing) {
		this.missing = missing;
	}

	public int getDifferent() {
		return different;
	}

	public void setDifferent(int different) {
		this.different = different;
	}

	public void reset() {
		matches = 0;
		missing = 0;
		different = 0;
	}

	public QueueMessage getQueueMessage() {
		return queueMessage;
	}

	public void setQueueMessage(QueueMessage queueMessage) {
		this.queueMessage = queueMessage;
	}



	public static final String[] header = { "pk", "LocalAET", "PrimaryAET", "SecondaryAET", "QueryString",
			"checkMissing", "checkDifferent", "matches", "missing", "different", "comparefield", "createdTime",
			"updatedTime", "JMSMessageID", "queue", "dicomDeviceName", "status", "scheduledTime", "failures", "batchID",
			"processingStartTime", "processingEndTime", "errorMessage", "outcomeMessage" };

	public void printRecord(CSVPrinter printer) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		printer.printRecord(String.valueOf(pk), localAET, primaryAET, secondaryAET, queryString,
				String.valueOf(checkMissing), String.valueOf(checkDifferent), matches, missing, different,
				compareFields, df.format(createdTime), df.format(updatedTime), queueMessage.getMessageID(),
				queueMessage.getQueueName(), queueMessage.getDeviceName(), queueMessage.getStatus().toString(),
				queueMessage.getScheduledTime(),
				queueMessage.getNumberOfFailures() > 0 ? String.valueOf(queueMessage.getNumberOfFailures()) : null,
				queueMessage.getBatchID(),
				queueMessage.getProcessingStartTime() != null ? df.format(queueMessage.getProcessingStartTime()) : null,
				queueMessage.getProcessingEndTime() != null ? df.format(queueMessage.getProcessingEndTime()) : null,
				queueMessage.getErrorMessage(), queueMessage.getOutcomeMessage());
	}

	@Override
	public String toString() {
		return "DiffTask[pk=" + pk + ", PrimaryAET=" + primaryAET + ", DestinationAET=" + secondaryAET
				+ ", QueryString=" + queryString + "]";
	}
}
