package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface ArchiveDeviceMBean {

	String getDeviceName();

	void bindConnections() throws IOException, GeneralSecurityException;

	void unbindConnections();

	void reloadConfiguration() throws ConfigurationException, GeneralSecurityException, IOException;

}
