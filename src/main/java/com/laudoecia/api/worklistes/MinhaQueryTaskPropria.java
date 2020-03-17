package com.laudoecia.api.worklistes;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicQueryTask;
import org.dcm4che3.net.service.QueryTask;

public class MinhaQueryTaskPropria extends BasicQueryTask implements QueryTask {

	public MinhaQueryTaskPropria(Association as, PresentationContext pc, Attributes rq, Attributes keys) {
		super(as, pc, rq, keys);
	}

}
