package com.laudoecia.api.worklistes;

public class ConfigurationException extends Exception {

	private static final long serialVersionUID = 1507953586524535616L;

	public ConfigurationException() {
	}

	public ConfigurationException(String message) {
		super(message);
	}

	public ConfigurationException(Throwable cause) {
		super(cause);
	}

	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}
