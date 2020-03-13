package com.laudoecia.api.worklistes;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.UID;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicCFindSCP;
import org.dcm4che3.net.service.BasicQueryTask;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.net.service.QueryTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MWLCFindSCPImpl extends BasicCFindSCP {
	private static final Logger LOG = LoggerFactory.getLogger(MWLCFindSCPImpl.class);
	
	
	public MWLCFindSCPImpl() {
		super(UID.ModalityWorklistInformationModelFIND);
		System.out.println("ago vendo");
	}

	@Override
	protected QueryTask calculateMatches(Association as, PresentationContext pc, Attributes rq, Attributes keys)throws DicomServiceException {
		try {
//			LOG.info("{}: Process MWL C-FIND RQ:\n{}", as, keys);
			try {
				return new BasicQueryTask(as, pc, rq, keys);
			} catch (Exception e) {
				throw new DicomServiceException(Status.UnableToProcess, e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
