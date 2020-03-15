package com.laudoecia.api.worklistes;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicQueryTask;
import org.dcm4che3.net.service.DicomServiceException;

class MWLQueryTaskImpl extends BasicQueryTask {

	private final ModalityWorklistQuery query;

	public MWLQueryTaskImpl(Association as, PresentationContext pc, Attributes rq, Attributes keys,
			ModalityWorklistQuery query) throws DicomServiceException {
		super(as, pc, rq, keys);
		this.query = query;
	}

	@Override
	protected void close() {
		query.close();
	}

	@Override
	protected boolean hasMoreMatches() throws DicomServiceException {
		try {
			return query.hasMoreMatches();
		} catch (Exception e) {
			// throw wrapException(Status.UnableToProcess, e);
			e.printStackTrace();
			return canceled;
		}

	}

	@Override
	protected Attributes nextMatch() throws DicomServiceException {
		try {
			return query.nextMatch();
		} catch (Exception e) {
			// throw wrapException(Status.UnableToProcess, e);
			e.printStackTrace();
			return null;
		}
	}

	protected boolean optionalKeyNotSupported(Attributes match) {
		return query.optionalKeyNotSupported();
	}
}
