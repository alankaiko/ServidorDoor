package com.laudoecia.api.worklistes;

import java.io.IOException;

import javax.persistence.Persistence;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.AttributesCoercion;
import org.dcm4che3.data.Tag;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicQueryTask;
import org.dcm4che3.net.service.DicomServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MWLQueryTask extends BasicQueryTask {
	private static final Logger LOG = LoggerFactory.getLogger(MWLQueryTask.class);

	private final Query query;
	private final AttributesCoercion coercion;
	private final RunInTransaction runInTx;

	public MWLQueryTask(Association as, PresentationContext pc, Attributes rq, Attributes keys, Query query, AttributesCoercion coercion, RunInTransaction runInTx) {
		super(as, pc, rq, keys);
		this.query = query;
		this.coercion = coercion;
		this.runInTx = runInTx;
		setOptionalKeysNotSupported(query.isOptionalKeysNotSupported());
	}

	@Override
	public void run() {
		runInTx.execute(this::run0);
	}

	private void run0() {
		try {
			QueryContext ctx = query.getQueryContext();
			ArchiveAEExtension arcAE = ctx.getArchiveAEExtension();
			ArchiveDeviceExtension arcdev = arcAE.getArchiveDeviceExtension();
			query.executeQuery(arcdev.getQueryFetchSize());
			super.run();
		} catch (Exception e) {
			writeDimseRSP(new DicomServiceException(Status.UnableToProcess, e));
		} finally {
			query.close();
		}
	}

	private void writeDimseRSP(DicomServiceException e) {
		int msgId = rq.getInt(Tag.MessageID, -1);
		Attributes rsp = e.mkRSP(Dimse.C_FIND_RSP.commandField(), msgId);
		try {
			as.writeDimseRSP(pc, rsp, null);
		} catch (IOException e1) {
			// handled by Association
		}
	}

	@Override
	protected boolean hasMoreMatches() throws DicomServiceException {
		try {
			return query.hasMoreMatches();
		} catch (DicomServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new DicomServiceException(Status.UnableToProcess, e);
		}
	}

	@Override
	protected Attributes nextMatch() throws DicomServiceException {
		try {
			return query.nextMatch();
		} catch (Exception e) {
			throw new DicomServiceException(Status.UnableToProcess, e);
		}
	}

	@Override
	protected Attributes adjust(Attributes match) {
		if (match == null)
			return null;

		if (coercion != null)
			coercion.coerce(match, null);

		return query.adjust(match);
	}
}
