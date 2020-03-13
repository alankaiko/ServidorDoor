package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Commands;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.net.service.QueryTask;


public class BasicQueryTask implements QueryTask {
	protected final Association as;
	protected final PresentationContext pc;
	protected final Attributes rq;
	protected final Attributes keys;
	protected volatile boolean canceled;
	protected boolean optionalKeysNotSupported = false;

	public BasicQueryTask(Association as, PresentationContext pc, Attributes rq, Attributes keys) {
		this.as = as;
		this.pc = pc;
		this.rq = rq;
		this.keys = keys;
	}

	public boolean isOptionalKeysNotSupported() {
		return optionalKeysNotSupported;
	}

	public void setOptionalKeysNotSupported(boolean optionalKeysNotSupported) {
		this.optionalKeysNotSupported = optionalKeysNotSupported;
	}

	@Override
	public void onCancelRQ(Association as) {
		canceled = true;
	}

	@Override
	public void run() {
		try {
			int msgId = rq.getInt(Tag.MessageID, -1);
			as.addCancelRQHandler(msgId, this);
			try {
				while (!canceled && hasMoreMatches()) {
					Attributes match = adjust(nextMatch());
					if (match != null) {
						int status = optionalKeysNotSupported ? Status.PendingWarning : Status.Pending;
						as.writeDimseRSP(pc, Commands.mkCFindRSP(rq, status), match);
					}
				}
				int status = canceled ? Status.Cancel : Status.Success;
				as.writeDimseRSP(pc, Commands.mkCFindRSP(rq, status));
			} catch (DicomServiceException e) {
				Attributes rsp = e.mkRSP(0x8020, msgId);
				as.writeDimseRSP(pc, rsp, e.getDataset());
			} finally {
				as.removeCancelRQHandler(msgId);
				close();
			}
		} catch (IOException e) {
			// handled by Association
		}
	}

	protected void close() {
	}

	protected Attributes nextMatch() throws DicomServiceException {
		throw new NoSuchElementException();
	}

	protected boolean hasMoreMatches() throws DicomServiceException {
		return false;
	}

	protected Attributes adjust(Attributes match) {
		if (match == null)
			return null;

		Attributes filtered = new Attributes(match.size());
		// include SpecificCharacterSet also if not in keys
		if (!keys.contains(Tag.SpecificCharacterSet)) {
			String[] ss = match.getStrings(Tag.SpecificCharacterSet);
			if (ss != null)
				filtered.setString(Tag.SpecificCharacterSet, VR.CS, ss);
		}
		filtered.addSelected(match, keys);
		return filtered;
	}
}