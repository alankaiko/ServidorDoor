package com.laudoecia.api.servidor;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicQueryTask;

public class PesquisaTask extends BasicQueryTask{

	public PesquisaTask(Association as, PresentationContext pc, Attributes rq, Attributes keys) {
		super(as, pc, rq, keys);
	}


}
