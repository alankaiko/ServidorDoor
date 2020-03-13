package com.laudoecia.api.worklistes;

import org.dcm4che3.data.Attributes;

public interface ModalityWorklistQuery {

	public static final String JNDI_NAME = "ModalityWorklistQueryBean/local";

	void findScheduledProcedureSteps(IDWithIssuer[] pids, Attributes keys, QueryParam queryParam);

	boolean optionalKeyNotSupported();

	boolean hasMoreMatches();

	Attributes nextMatch();

	void close();
}
