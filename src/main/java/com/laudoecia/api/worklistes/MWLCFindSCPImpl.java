package com.laudoecia.api.worklistes;

import java.util.EnumSet;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.UID;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.QueryOption;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.ExtendedNegotiation;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicCFindSCP;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.net.service.QueryTask;

public class MWLCFindSCPImpl extends BasicCFindSCP {

	public MWLCFindSCPImpl() {
		super(UID.ModalityWorklistInformationModelFIND);
		System.out.println("ago vendo");
	}

	@Override
	protected QueryTask calculateMatches(Association as, PresentationContext pc, Attributes rq, Attributes keys)throws DicomServiceException {
		System.out.println("opa deu certo");
		try {
			String cuid = rq.getString(Tag.AffectedSOPClassUID);
			ExtendedNegotiation extNeg = as.getAAssociateAC().getExtNegotiationFor(cuid);
			// TODO consider default Issuer Of Patient ID of Source AE
			IDWithIssuer pid = IDWithIssuer.pidWithIssuer(keys, null);
			IDWithIssuer[] pids = pid != null ? new IDWithIssuer[] { pid } : null;
			// ArchiveDevice dev = (ArchiveDevice) as.getApplicationEntity().getDevice();
			EnumSet<QueryOption> queryOpts = QueryOption.toOptions(extNeg);
			QueryParam queryParam = new QueryParam();
			queryParam.setCombinedDatetimeMatching(true);
			queryParam.setFuzzySemanticMatching(queryOpts.contains(QueryOption.FUZZY));
			// queryParam.setFuzzyStr(dev.getFuzzyStr());
			try {
				//ModalityWorklistQuery query = (ModalityWorklistQuery) JNDIUtils.lookup(ModalityWorklistQuery.JNDI_NAME);
				ModalityWorklistQuery query = new ModalityWorklistQueryBean();
				query.findScheduledProcedureSteps(pids, keys, queryParam);
				return new MWLQueryTaskImpl(as, pc, rq, keys, query);
			} catch (Exception e) {
				throw new DicomServiceException(Status.UnableToProcess, e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
