package com.laudoecia.api.worklistes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.ObjectMessage;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.dcm4che3.util.StringUtils;

public class QueueMessage {


	public enum Status {
		SCHEDULED, IN_PROCESS, COMPLETED, WARNING, FAILED, CANCELED, TO_SCHEDULE;

		public static Status fromString(String s) {
			return Status.valueOf(s.replace(' ', '_'));
		}

		@Override
		public String toString() {
			return name().replace('_', ' ');
		}
	}

	private long pk;
	private long version;
	private Date createdTime;
	private Date updatedTime;
	private String deviceName;
	private String queueName;
	private int priority;
	private String messageID;
	private String messageProperties;
	private byte[] messageBody;
	private Status status;
	private Date scheduledTime;
	private Date processingStartTime;
	private Date processingEndTime;
	private int numberOfFailures;
	private String batchID;
	private String errorMessage;
	private String outcomeMessage;
	private ExportTask exportTask;
	private RetrieveTask retrieveTask;
	private DiffTask diffTask;
	private StorageVerificationTask storageVerificationTask;

	public QueueMessage() {
	}

	public QueueMessage(String deviceName, String queueName, ObjectMessage msg, long delay) {
		try {
			this.deviceName = deviceName;
			this.queueName = queueName;
			this.messageID = msg.getJMSMessageID();
			this.priority = msg.getJMSPriority();
			this.scheduledTime = new Date(System.currentTimeMillis() + delay);
			this.messageProperties = propertiesOf(msg);
			this.messageBody = serialize(msg.getObject());
			this.status = Status.SCHEDULED;
		} catch (JMSException e) {
			throw toJMSRuntimeException(e);
		}
	}

	public long getPk() {
		return pk;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getPriority() {
		return priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getProcessingStartTime() {
		return processingStartTime;
	}

	public void setProcessingStartTime(Date processingStartTime) {
		this.processingStartTime = processingStartTime;
	}

	public Date getProcessingEndTime() {
		return processingEndTime;
	}

	public void setProcessingEndTime(Date processingEndTime) {
		this.processingEndTime = processingEndTime;
	}

	public int getNumberOfFailures() {
		return numberOfFailures;
	}

	public int incrementNumberOfFailures() {
		return ++numberOfFailures;
	}

	public void setNumberOfFailures(int numberOfFailures) {
		this.numberOfFailures = numberOfFailures;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage != null ? StringUtils.truncate(errorMessage, 255) : null;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public String getOutcomeMessage() {
		return outcomeMessage;
	}

	public void setOutcomeMessage(String outcomeMessage) {
		this.outcomeMessage = outcomeMessage != null ? StringUtils.truncate(outcomeMessage, 255) : null;
	}

	public ExportTask getExportTask() {
		return exportTask;
	}

	public RetrieveTask getRetrieveTask() {
		return retrieveTask;
	}

	public DiffTask getDiffTask() {
		return diffTask;
	}

	public StorageVerificationTask getStorageVerificationTask() {
		return storageVerificationTask;
	}

	public String getBatchID() {
		return batchID;
	}

	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}

	private String propertiesOf(ObjectMessage msg) throws JMSException {
		StringBuilder sb = new StringBuilder(512);
		Enumeration<String> names = msg.getPropertyNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			if (!name.startsWith("JMS")) {
				Object o = msg.getObjectProperty(name);
				sb.append('"').append(name).append('"').append(':');
				if (o instanceof String)
					sb.append('"').append(((String) o).replace("\"", "\\\"")).append('"');
				else
					sb.append(o);
				if (names.hasMoreElements())
					sb.append(',');
			}
		}
		return sb.toString();
	}

	public ObjectMessage initProperties(ObjectMessage msg) {
		try {
			int len = messageProperties.length();
			char[] buf = new char[len + 2];
			buf[0] = '{';
			messageProperties.getChars(0, len, buf, 1);
			buf[len + 1] = '}';
			try (JsonParser parser = Json.createParser(new CharArrayReader(buf))) {
				parser.next();
				while (parser.next() == JsonParser.Event.KEY_NAME) {
					String key = parser.getString();
					switch (parser.next()) {
					case VALUE_STRING:
						msg.setStringProperty(key, parser.getString());
						break;
					case VALUE_NUMBER:
						msg.setIntProperty(key, parser.getInt());
						break;
					case VALUE_FALSE:
						msg.setBooleanProperty(key, false);
						break;
					case VALUE_TRUE:
						msg.setBooleanProperty(key, true);
						break;
					case VALUE_NULL:
						msg.setStringProperty(key, null);
						break;
					default:
						throw new IllegalStateException(messageProperties);
					}
				}
			}
			return msg;
		} catch (JMSException e) {
			throw toJMSRuntimeException(e);
		}
	}

	public Serializable getMessageBody() {
		ByteArrayInputStream bais = new ByteArrayInputStream(messageBody);
		try (ObjectInputStream ois = new ObjectInputStream(bais)) {
			return (Serializable) ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException("Unexpected Exception", e);
		}
	}

	private byte[] serialize(Serializable obj) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		try {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(obj);
			}
		} catch (IOException e) {
			throw new RuntimeException("Unexpected Exception", e);
		}
		return baos.toByteArray();
	}

	@PrePersist
	public void onPrePersist() {
		Date now = new Date();
		createdTime = now;
		updatedTime = now;
	}

	@PreUpdate
	public void onPreUpdate() {
		updatedTime = new Date();
	}

	@Override
	public String toString() {
		StringWriter w = new StringWriter(256);
		w.write("QueueMessage");
		try {
			System.out.println("retirei o metodo jsonwriter");
		} catch (Exception e) {
			return "" + e;
		}
		return w.toString();
	}

	public static JMSRuntimeException toJMSRuntimeException(JMSException e) {
		return new JMSRuntimeException(e.getMessage(), e.getErrorCode(), e.getCause());
	}

}
