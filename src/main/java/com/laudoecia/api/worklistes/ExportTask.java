package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.csv.CSVPrinter;
import org.dcm4che3.util.StringUtils;

public class ExportTask {

	
	private long pk;

	private long version;

	private Date createdTime;

	private Date updatedTime;

	private Date scheduledTime;

	private String deviceName;

	private String exporterID;

	private String batchID;

	private String studyInstanceUID;

	private String seriesInstanceUID;

	private String sopInstanceUID;

	private Integer numberOfInstances;

	private String modalities;

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

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getExporterID() {
		return exporterID;
	}

	public void setExporterID(String exporterID) {
		this.exporterID = exporterID;
	}

	public String getBatchID() {
		return batchID;
	}

	public void setBatchID(String batchID) {
		this.batchID = batchID;
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

	public String getSopInstanceUID() {
		return sopInstanceUID;
	}

	public void setSopInstanceUID(String sopInstanceUID) {
		this.sopInstanceUID = sopInstanceUID;
	}

	public Integer getNumberOfInstances() {
		return numberOfInstances;
	}

	public void setNumberOfInstances(Integer numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}

	public String[] getModalities() {
		return StringUtils.split(modalities, '\\');
	}

	public void setModalities(String[] modalities) {
		this.modalities = StringUtils.concat(modalities, '\\');
	}

	public QueueMessage getQueueMessage() {
		return queueMessage;
	}

	public void setQueueMessage(QueueMessage queueMessage) {
		this.queueMessage = queueMessage;
	}

//    public void writeAsJSONTo(JsonGenerator gen, String localAET) {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//        JsonWriter writer = new JsonWriter(gen);
//        gen.writeStartObject();
//        writer.writeNotNullOrDef("pk", pk, null);
//        writer.writeNotNullOrDef("createdTime", df.format(createdTime), null);
//        writer.writeNotNullOrDef("updatedTime", df.format(updatedTime), null);
//        writer.writeNotNullOrDef("ExporterID", exporterID, null);
//        writer.writeNotNullOrDef("LocalAET", localAET, null);
//        writer.writeNotNullOrDef("StudyInstanceUID", studyInstanceUID, null);
//        writer.writeNotNullOrDef("SeriesInstanceUID", seriesInstanceUID, "*");
//        writer.writeNotNullOrDef("SOPInstanceUID", sopInstanceUID, "*");
//        writer.writeNotNullOrDef("NumberOfInstances", numberOfInstances, null);
//        writer.writeNotEmpty("Modality", getModalities());
//        if (queueMessage == null) {
//            writer.writeNotNullOrDef("batchID", batchID, null);
//            writer.writeNotNullOrDef("dicomDeviceName", deviceName, null);
//            writer.writeNotNullOrDef("status", QueueMessage.Status.TO_SCHEDULE.toString(), null);
//            writer.writeNotNullOrDef("scheduledTime", df.format(scheduledTime), null);
//        } else
//            queueMessage.writeStatusAsJSONTo(writer, df);
//        gen.writeEnd();
//        gen.flush();
//    }

	public static final String[] header = { "pk", "createdTime", "updatedTime", "ExporterID", "LocalAET",
			"StudyInstanceUID", "SeriesInstanceUID", "SOPInstanceUID", "NumberOfInstances", "Modality", "batchID",
			"dicomDeviceName", "scheduledTime", "status", "JMSMessageID", "queue", "failures", "processingStartTime",
			"processingEndTime", "errorMessage", "outcomeMessage" };

	public void printRecord(CSVPrinter printer, String localAET) throws IOException {
		if (queueMessage == null)
			printExportTask(printer, localAET);
		else
			printExportTaskWithQueueMsg(printer, localAET);
	}

	private void printExportTask(CSVPrinter printer, String localAET) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		printer.printRecord(String.valueOf(pk), df.format(createdTime), df.format(updatedTime), exporterID, localAET,
				studyInstanceUID, !seriesInstanceUID.equals("*") ? seriesInstanceUID : null,
				!sopInstanceUID.equals("*") ? sopInstanceUID : null,
				numberOfInstances != null ? numberOfInstances.toString() : null, modalities, batchID, deviceName,
				scheduledTime, "TO_SCHEDULE", null, null, null, null, null, null, null);
	}

	private void printExportTaskWithQueueMsg(CSVPrinter printer, String localAET) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		printer.printRecord(String.valueOf(pk), df.format(createdTime), df.format(updatedTime), exporterID, localAET,
				studyInstanceUID, !seriesInstanceUID.equals("*") ? seriesInstanceUID : null,
				!sopInstanceUID.equals("*") ? sopInstanceUID : null,
				numberOfInstances != null ? numberOfInstances.toString() : null, modalities, batchID, deviceName,
				scheduledTime, queueMessage.getStatus().toString(), queueMessage.getMessageID(),
				queueMessage.getQueueName(),
				queueMessage.getNumberOfFailures() > 0 ? String.valueOf(queueMessage.getNumberOfFailures()) : null,
				queueMessage.getProcessingStartTime() != null ? df.format(queueMessage.getProcessingStartTime()) : null,
				queueMessage.getProcessingEndTime() != null ? df.format(queueMessage.getProcessingEndTime()) : null,
				queueMessage.getErrorMessage(), queueMessage.getOutcomeMessage());
	}

	@Override
	public String toString() {
		return "ExportTask[pk=" + pk + ", exporterID=" + exporterID + ", studyUID=" + studyInstanceUID + ", seriesUID="
				+ seriesInstanceUID + ", objectUID=" + sopInstanceUID + "]";
	}
}
