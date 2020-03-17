package com.laudoecia.api.worklistes;

import java.util.List;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.AttributesCoercion;
import org.dcm4che3.data.Tag;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CFindSCUAttributeCoercion implements AttributesCoercion {

	private final static Logger LOG = LoggerFactory.getLogger(CFindSCUAttributeCoercion.class);

	private final ApplicationEntity localAE;
	private final String leadingCFindSCP;
	private final Attributes.UpdatePolicy attributeUpdatePolicy;
	private final CFindSCU cfindSCU;
	private final LeadingCFindSCPQueryCache queryCache;
	private final AttributesCoercion next;

	public CFindSCUAttributeCoercion(ApplicationEntity localAE, String leadingCFindSCP, Attributes.UpdatePolicy attributeUpdatePolicy, CFindSCU cfindSCU, LeadingCFindSCPQueryCache queryCache, AttributesCoercion next) {
		this.localAE = localAE;
		this.leadingCFindSCP = leadingCFindSCP;
		this.attributeUpdatePolicy = attributeUpdatePolicy;
		this.cfindSCU = cfindSCU;
		this.queryCache = queryCache;
		this.next = next;
	}

	@Override
	public String remapUID(String uid) {
		return uid;
	}

	@Override
	public void coerce(Attributes attrs, Attributes modified) {
		String studyIUID = attrs.getString(Tag.StudyInstanceUID);
		Attributes newAttrs = queryStudy(studyIUID);
		if (newAttrs != null)
			attrs.update(attributeUpdatePolicy, newAttrs, modified);
		else
			LOG.warn("Failed to query Study[{}] from {} - do not coerce attributes", studyIUID, leadingCFindSCP);
		if (next != null)
			next.coerce(attrs, modified);
	}

	private Attributes queryStudy(String studyIUID) {
		LeadingCFindSCPQueryCache.Key key = new LeadingCFindSCPQueryCache.Key(leadingCFindSCP, studyIUID);
		Cache.Entry<Attributes> entry = queryCache.getEntry(key);
		if (entry != null)
			return entry.value();

		Attributes newAttrs = null;
		try {
			ArchiveDeviceExtension arcdev = localAE.getDevice().getDeviceExtensionNotNull(ArchiveDeviceExtension.class);
			List<Attributes> matches = cfindSCU.findStudy(localAE, leadingCFindSCP, Priority.NORMAL, studyIUID,
					arcdev.returnKeysForLeadingCFindSCP(leadingCFindSCP));
			if (!matches.isEmpty())
				newAttrs = matches.get(0);
		} catch (Exception e) {
		}
		queryCache.put(key, newAttrs);
		return newAttrs;
	}
}
