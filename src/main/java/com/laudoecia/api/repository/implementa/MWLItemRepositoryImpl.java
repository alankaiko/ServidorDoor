package com.laudoecia.api.repository.implementa;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.service.DicomServiceException;

public class MWLItemRepositoryImpl implements MWLItemRepositoryQuery {
	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean isOptionalKeysNotSupported() {

		return false;
	}

	@Override
	public void close() {

	}

	@Override
	public void executeQuery(int fetchSize) {

	}

	@Override
	public void executeQuery(int fetchSize, int offset, int limit) {

	}

	@Override
	public long fetchCount() {

		return 0;
	}

	@Override
	public Stream<Long> withUnknownSize(int fetchSize) {

		return null;
	}

	@Override
	public long fetchSize() {

		return 0;
	}

	@Override
	public boolean hasMoreMatches() throws DicomServiceException {

		return false;
	}

	@Override
	public Attributes nextMatch() {

		return null;
	}

	@Override
	public Attributes adjust(Attributes match) {

		return null;
	}

}
