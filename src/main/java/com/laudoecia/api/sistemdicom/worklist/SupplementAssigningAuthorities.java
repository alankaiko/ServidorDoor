package com.laudoecia.api.sistemdicom.worklist;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.AttributesCoercion;
import org.dcm4che3.data.Code;
import org.dcm4che3.data.ElementDictionary;
import org.dcm4che3.data.Issuer;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.net.Device;
import org.dcm4che3.util.TagUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupplementAssigningAuthorities implements AttributesCoercion {
	static final Logger LOG = LoggerFactory.getLogger(SupplementAssigningAuthorities.class);
	private static final ElementDictionary DICT = ElementDictionary.getStandardElementDictionary();

	private final Entity entity;
	private final Device device;
	private final AttributesCoercion next;

	private SupplementAssigningAuthorities(Entity entity, Device device, AttributesCoercion next) {
		this.entity = entity;
		this.device = device;
		this.next = next;
	}

	public static AttributesCoercion forInstance(Device device, AttributesCoercion next) {
		return device != null ? new SupplementAssigningAuthorities(Entity.Instance, device, next) : next;
	}

	public static AttributesCoercion forMPPS(Device device, AttributesCoercion next) {
		return device != null ? new SupplementAssigningAuthorities(Entity.MPPS, device, next) : next;
	}

	public static AttributesCoercion forMWL(Device device, AttributesCoercion next) {
		return device != null ? new SupplementAssigningAuthorities(Entity.MWL, device, next) : next;
	}

	public static AttributesCoercion forQuery(Device device, AttributesCoercion next) {
		return device != null ? new SupplementAssigningAuthorities(Entity.Query, device, next) : next;
	}

	@Override
	public String remapUID(String uid) {
		return next != null ? next.remapUID(uid) : uid;
	}

	@Override
	public void coerce(Attributes attrs, Attributes modified) {
		entity.supplement(this, attrs);
		if (next != null)
			next.coerce(attrs, modified);
	}

	private enum Entity {
		Instance {
			@Override
			void supplement(SupplementAssigningAuthorities coercion, Attributes attrs) {
				coercion.supplementInstance(attrs);
			}
		},
		MPPS {
			@Override
			void supplement(SupplementAssigningAuthorities coercion, Attributes attrs) {
				coercion.supplementMPPS(attrs);
			}
		},
		MWL {
			@Override
			void supplement(SupplementAssigningAuthorities coercion, Attributes attrs) {
				coercion.supplementMWL(attrs);
			}
		},
		Query {
			@Override
			void supplement(SupplementAssigningAuthorities coercion, Attributes attrs) {
				coercion.supplementQuery(attrs);
			}
		};

		abstract void supplement(SupplementAssigningAuthorities coercion, Attributes attrs);
	}

	private void supplementInstance(Attributes attrs) {
		supplementValue(attrs, Tag.Manufacturer, VR.LO, device.getManufacturer());
		supplementValue(attrs, Tag.ManufacturerModelName, VR.LO, device.getManufacturerModelName());
		supplementValue(attrs, Tag.StationName, VR.SH, device.getStationName());
		supplementValue(attrs, Tag.DeviceSerialNumber, VR.LO, device.getDeviceSerialNumber());
		supplementValues(attrs, Tag.SoftwareVersions, VR.LO, device.getSoftwareVersions());
		supplementValue(attrs, Tag.InstitutionName, VR.LO, device.getInstitutionNames());
		supplementCode(attrs, Tag.InstitutionCodeSequence, device.getInstitutionCodes());
		supplementValue(attrs, Tag.InstitutionalDepartmentName, VR.LO, device.getInstitutionalDepartmentNames());
		supplementIssuers(attrs);
		supplementRequestIssuers(attrs);
		supplementRequestIssuers(attrs.getSequence(Tag.RequestAttributesSequence));
		LOG.info("Supplement composite object from device: {}", device.getDeviceName());
	}

	private void supplementMPPS(Attributes attrs) {
		supplementIssuers(attrs);
		supplementRequestIssuers(attrs.getSequence(Tag.ScheduledStepAttributesSequence));
		LOG.info("Supplement MPPS from device: {}", device.getDeviceName());
	}

	private void supplementMWL(Attributes attrs) {
		supplementIssuers(attrs);
		supplementRequestIssuers(attrs);
		LOG.info("Supplement MWL from device: {}", device.getDeviceName());
	}

	private void supplementQuery(Attributes attrs) {
		supplementIssuers(attrs);
		supplementRequestIssuers(attrs);
		supplementRequestIssuers(attrs.getSequence(Tag.RequestAttributesSequence));
		LOG.info("Supplement composite query from device: {}", device.getDeviceName());
	}

	private void supplementValue(Attributes attrs, int tag, VR vr, String... values) {
		if (values.length == 0 || values[0] == null || attrs.containsValue(tag))
			return;

		attrs.setString(tag, vr, values[0]);
		log(tag, vr, values[0]);
	}

	private void supplementValues(Attributes attrs, int tag, VR vr, String... values) {
		if (values.length == 0 || attrs.containsValue(tag))
			return;

		attrs.setString(tag, vr, values);
		log(tag, vr, values);
	}

	private void supplementCode(Attributes attrs, int seqTag, Code... codes) {
		if (codes.length == 0 || codes[0] == null || attrs.containsValue(seqTag))
			return;

		Attributes item = new Attributes(attrs.bigEndian(), 4);
		item.setString(Tag.CodeValue, VR.SH, codes[0].getCodeValue());
		item.setString(Tag.CodingSchemeDesignator, VR.SH, codes[0].getCodingSchemeDesignator());
		String version = codes[0].getCodingSchemeVersion();
		if (version != null)
			item.setString(Tag.CodingSchemeVersion, VR.SH, version);
		item.setString(Tag.CodeMeaning, VR.LO, codes[0].getCodeMeaning());
		attrs.newSequence(seqTag, 1).add(item);
		log(seqTag, VR.SQ, item);
	}

	private void supplementIssuers(Attributes attrs) {
		supplementIssuerOfPatientID(attrs);
		supplementIssuer(attrs, Tag.AdmissionID, Tag.IssuerOfAdmissionIDSequence, device.getIssuerOfAdmissionID());
		supplementIssuer(attrs, Tag.ServiceEpisodeID, Tag.IssuerOfServiceEpisodeID,
				device.getIssuerOfServiceEpisodeID());
		supplementIssuer(attrs, Tag.ContainerIdentifier, Tag.IssuerOfTheContainerIdentifierSequence,
				device.getIssuerOfContainerIdentifier());
		supplementIssuer(attrs, Tag.SpecimenIdentifier, Tag.IssuerOfTheSpecimenIdentifierSequence,
				device.getIssuerOfSpecimenIdentifier());
	}

	private void supplementIssuerOfPatientID(Attributes attrs) {
		if (supplementIssuerOfPID(attrs)) {
			return;
		}

		Issuer issuer = device.getIssuerOfPatientID();
		String localNamespaceEntityID = issuer.getLocalNamespaceEntityID();
		if (localNamespaceEntityID != null) {
			attrs.setString(Tag.IssuerOfPatientID, VR.LO, localNamespaceEntityID);
			log(Tag.IssuerOfPatientID, VR.LO, localNamespaceEntityID);
		}
		String universalEntityID = issuer.getUniversalEntityID();
		if (universalEntityID != null) {
			Attributes item = new Attributes(attrs.bigEndian(), 2);
			item.setString(Tag.UniversalEntityID, VR.UT, universalEntityID);
			item.setString(Tag.UniversalEntityIDType, VR.CS, issuer.getUniversalEntityIDType());
			attrs.newSequence(Tag.IssuerOfPatientIDQualifiersSequence, 1).add(item);
			log(Tag.IssuerOfPatientIDQualifiersSequence, VR.SQ, item);
		}
	}

	private boolean supplementIssuerOfPID(Attributes attrs) {
		return !attrs.containsValue(Tag.PatientID) || device.getIssuerOfPatientID() == null
				|| attrs.containsValue(Tag.IssuerOfPatientID)
				|| attrs.containsValue(Tag.IssuerOfPatientIDQualifiersSequence);
	}

	private void supplementIssuer(Attributes attrs, int idTag, int seqTag, Issuer issuer) {
		if (issuer == null || !attrs.containsValue(idTag) || attrs.containsValue(seqTag))
			return;

		Attributes item = new Attributes(attrs.bigEndian(), 3);
		String localNamespaceEntityID = issuer.getLocalNamespaceEntityID();
		if (localNamespaceEntityID != null)
			item.setString(Tag.LocalNamespaceEntityID, VR.LO, localNamespaceEntityID);
		String universalEntityID = issuer.getUniversalEntityID();
		if (universalEntityID != null) {
			item.setString(Tag.UniversalEntityID, VR.UT, universalEntityID);
			item.setString(Tag.UniversalEntityIDType, VR.CS, issuer.getUniversalEntityIDType());
		}
		attrs.newSequence(seqTag, 1).add(item);
		log(seqTag, VR.SQ, item);
	}

	private void supplementRequestIssuers(Sequence rqSeq) {
		if (rqSeq != null)
			for (Attributes rq : rqSeq)
				supplementRequestIssuers(rq);
	}

	private void supplementRequestIssuers(Attributes rq) {
		supplementIssuer(rq, Tag.AccessionNumber, Tag.IssuerOfAccessionNumberSequence,
				device.getIssuerOfAccessionNumber());
		supplementIssuer(rq, Tag.PlacerOrderNumberImagingServiceRequest, Tag.OrderPlacerIdentifierSequence,
				device.getOrderPlacerIdentifier());
		supplementIssuer(rq, Tag.FillerOrderNumberImagingServiceRequest, Tag.OrderFillerIdentifierSequence,
				device.getOrderFillerIdentifier());
	}

	private void log(int tag, VR vr, Object value) {
		if (LOG.isDebugEnabled())
			LOG.debug("{}: Supplements {} {} [{}] {}", TagUtils.toString(tag), vr, value, DICT.keywordOf(tag));
	}
}