package com.laudoecia.api.worklistes;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.util.StringUtils;



public class IDWithIssuer {

	public static final IDWithIssuer[] EMPTY = {};

	public final String id;
	public final Issuer issuer;

	public IDWithIssuer(String id, Issuer issuer) {
		this.id = id;
		this.issuer = issuer;
	}

	public IDWithIssuer(String cx) {
		String[] ss = StringUtils.split(cx, '^');
		this.id = ss[0];
		this.issuer = ss.length > 3 ? new Issuer() : null;
	}

	@Override
	public String toString() {
		return issuer == null ? id : id + "^^^" + issuer.toString();
	}

	public Attributes toPIDWithIssuer(Attributes attrs) {
		if (attrs == null)
			attrs = new Attributes(3);

		attrs.setString(Tag.PatientID, VR.LO, id);
		if (issuer == null)
			return attrs;
		String issuerOfPatientID = issuer.getLocalNamespaceEntityID();
		if (issuerOfPatientID != null)
			attrs.setString(Tag.IssuerOfPatientID, VR.LO, issuerOfPatientID);
		String universalEntityID = issuer.getUniversalEntityID();
		if (universalEntityID != null) {
			Attributes item = new Attributes(2);
			item.setString(Tag.UniversalEntityID, VR.UT, universalEntityID);
			item.setString(Tag.UniversalEntityIDType, VR.UT, issuer.getUniversalEntityIDType());
			attrs.newSequence(Tag.IssuerOfPatientIDQualifiersSequence, 1).add(item);
		}
		return attrs;
	}

	public static IDWithIssuer pidWithIssuer(Attributes keys, Issuer defaultIssuerWithPatientID) {
		String id = keys.getString(Tag.PatientID);
		if (id == null)
			return null;

		Issuer issuerOfPatientID = Issuer.issuerOfPatientIDOf(keys);
		return new IDWithIssuer(id, issuerOfPatientID != null ? issuerOfPatientID : defaultIssuerWithPatientID);
	}

	public static void addOtherPatientIDs(Attributes attrs, IDWithIssuer... pids) {
		Sequence seq = attrs.newSequence(Tag.OtherPatientIDsSequence, pids.length);
		for (IDWithIssuer pid : pids)
			if (pid.issuer != null)
				seq.add(pid.toPIDWithIssuer(null));
		if (seq.isEmpty())
			attrs.remove(Tag.OtherPatientIDsSequence);
	}
}
