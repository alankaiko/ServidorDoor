package com.laudoecia.api.worklistes;

import java.io.Closeable;
import java.util.stream.Stream;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.service.DicomServiceException;

public interface Query extends Closeable {
	boolean isOptionalKeysNotSupported();

	void close();

	void executeQuery(int fetchSize);

	void executeQuery(int fetchSize, int offset, int limit);

	long fetchCount();

	Stream<Long> withUnknownSize(int fetchSize);

	long fetchSize();

	boolean hasMoreMatches() throws DicomServiceException;

	Attributes nextMatch();

	Attributes adjust(Attributes match);

	QueryContext getQueryContext();
}
