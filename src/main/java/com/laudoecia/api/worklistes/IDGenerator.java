package com.laudoecia.api.worklistes;

public class IDGenerator {
	public enum Name {
		PatientID, AccessionNumber, RequestedProcedureID, ScheduledProcedureStepID, LocationMultiReference
	}

	private Name name;
	private String format;
	private int initialValue = 1;

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(int initialValue) {
		this.initialValue = initialValue;
	}
}
