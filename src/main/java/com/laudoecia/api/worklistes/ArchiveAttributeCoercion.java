package com.laudoecia.api.worklistes;

import org.dcm4che3.data.*;
import org.dcm4che3.deident.DeIdentifier;
import org.dcm4che3.net.Device;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.TransferCapability;
import org.dcm4che3.util.AttributesFormat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArchiveAttributeCoercion {

    private String commonName;
    private int priority;
    private Dimse dimse;
    private TransferCapability.Role role;
    private String[] sopClasses = {};
    private Conditions conditions = new Conditions();
    private boolean retrieveAsReceived;
    private DeIdentifier.Option[] deIdentification = {};
    private String xsltStylesheetURI;
    private boolean noKeywords;
    private String leadingCFindSCP;
    private MergeMWLMatchingKey mergeMWLMatchingKey;
    private String mergeMWLTemplateURI;
    private Attributes.UpdatePolicy attributeUpdatePolicy = Attributes.UpdatePolicy.MERGE;
    private boolean trimISO2022CharacterSet;
    private UseCallingAETitleAsCoercion.Type useCallingAETitleAs;
    private int[] nullifyTags = {};
    private NullifyIssuer nullifyIssuerOfPatientID;
    private MergeAttribute[] mergeAttributes = {};
    private Issuer[] issuerOfPatientIDs = {};
    private Device supplementFromDevice;
    private String issuerOfPatientIDFormat;

    public ArchiveAttributeCoercion() {
    }

    public ArchiveAttributeCoercion(String commonName) {
        setCommonName(commonName);
    }

    public String getCommonName() {
        return commonName;
    }

    public ArchiveAttributeCoercion setCommonName(String commonName) {
        this.commonName = commonName;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public ArchiveAttributeCoercion setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public Dimse getDIMSE() {
        return dimse;
    }

    public ArchiveAttributeCoercion setDIMSE(Dimse dimse) {
        this.dimse = dimse;
        return this;
    }

    public TransferCapability.Role getRole() {
        return role;
    }

    public ArchiveAttributeCoercion setRole(TransferCapability.Role role) {
        this.role = role;
        return this;
    }

    public String[] getSOPClasses() {
        return sopClasses;
    }

    public ArchiveAttributeCoercion setSOPClasses(String... sopClasses) {
        this.sopClasses = sopClasses;
        return this;
    }

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    public ArchiveAttributeCoercion setSendingHostname(String hostname) {
        conditions.setSendingHostname(hostname);
        return this;
    }

    public ArchiveAttributeCoercion setSendingAETitle(String aet) {
        conditions.setSendingAETitle(aet);
        return this;
    }

    public ArchiveAttributeCoercion setReceivingHostname(String hostname) {
        conditions.setReceivingHostname(hostname);
        return this;
    }

    public ArchiveAttributeCoercion setReceivingAETitle(String aet) {
        conditions.setReceivingAETitle(aet);
        return this;
    }

    public boolean isRetrieveAsReceived() {
        return retrieveAsReceived;
    }

    public ArchiveAttributeCoercion setRetrieveAsReceived(boolean retrieveAsReceived) {
        this.retrieveAsReceived = retrieveAsReceived;
        return this;
    }

    public DeIdentifier.Option[] getDeIdentification() {
        return deIdentification;
    }

    public void setDeIdentification(DeIdentifier.Option[] deIdentification) {
        this.deIdentification = deIdentification;
    }

    public String getXSLTStylesheetURI() {
        return xsltStylesheetURI;
    }

    public ArchiveAttributeCoercion setXSLTStylesheetURI(String xsltStylesheetURI) {
        this.xsltStylesheetURI = xsltStylesheetURI;
        return this;
    }

    public boolean isNoKeywords() {
        return noKeywords;
    }

    public ArchiveAttributeCoercion setNoKeywords(boolean noKeywords) {
        this.noKeywords = noKeywords;
        return this;
    }

    public String getLeadingCFindSCP() {
        return leadingCFindSCP;
    }

    public ArchiveAttributeCoercion setLeadingCFindSCP(String leadingCFindSCP) {
        this.leadingCFindSCP = leadingCFindSCP;
        return this;
    }

    public MergeMWLMatchingKey getMergeMWLMatchingKey() {
        return mergeMWLMatchingKey;
    }

    public ArchiveAttributeCoercion setMergeMWLMatchingKey(MergeMWLMatchingKey mergeMWLMatchingKey) {
        this.mergeMWLMatchingKey = mergeMWLMatchingKey;
        return this;
    }

    public String getMergeMWLTemplateURI() {
        return mergeMWLTemplateURI;
    }

    public ArchiveAttributeCoercion setMergeMWLTemplateURI(String mergeMWLTemplateURI) {
        this.mergeMWLTemplateURI = mergeMWLTemplateURI;
        return this;
    }

    public Attributes.UpdatePolicy getAttributeUpdatePolicy() {
        return attributeUpdatePolicy;
    }

    public ArchiveAttributeCoercion setAttributeUpdatePolicy(Attributes.UpdatePolicy attributeUpdatePolicy) {
        this.attributeUpdatePolicy = attributeUpdatePolicy;
        return this;
    }

    public boolean isTrimISO2022CharacterSet() {
        return trimISO2022CharacterSet;
    }

    public void setTrimISO2022CharacterSet(boolean trimISO2022CharacterSet) {
        this.trimISO2022CharacterSet = trimISO2022CharacterSet;
    }

    public UseCallingAETitleAsCoercion.Type getUseCallingAETitleAs() {
        return useCallingAETitleAs;
    }

    public void setUseCallingAETitleAs(UseCallingAETitleAsCoercion.Type useCallingAETitleAs) {
        this.useCallingAETitleAs = useCallingAETitleAs;
    }

    public int[] getNullifyTags() {
        return nullifyTags;
    }

    public void setNullifyTags(int[] nullifyTags) {
        this.nullifyTags = nullifyTags;
    }

    public NullifyIssuer getNullifyIssuerOfPatientID() {
        return nullifyIssuerOfPatientID;
    }

    public void setNullifyIssuerOfPatientID(NullifyIssuer nullifyIssuerOfPatientID) {
        this.nullifyIssuerOfPatientID = nullifyIssuerOfPatientID;
    }

    public MergeAttribute[] getMergeAttributes() {
        return mergeAttributes;
    }

    public void setMergeAttributes(String... mergeAttributes) {
        this.mergeAttributes = MergeAttribute.of(mergeAttributes);
    }

    public Issuer[] getIssuerOfPatientIDs() {
        return issuerOfPatientIDs;
    }

    public void setIssuerOfPatientIDs(Issuer... issuerOfPatientIDs) {
        this.issuerOfPatientIDs = issuerOfPatientIDs;
    }

    public final Device getSupplementFromDevice() {
        return supplementFromDevice;
    }

    public String getSupplementFromDeviceName() {
        if (supplementFromDevice == null)
            throw new IllegalStateException("SupplementFromDevice not initialized");
        return supplementFromDevice.getDeviceName();
    }

    public ArchiveAttributeCoercion setSupplementFromDevice(Device supplementFromDevice) {
        this.supplementFromDevice = supplementFromDevice;
        return this;
    }

    public String getIssuerOfPatientIDFormat() {
        return issuerOfPatientIDFormat;
    }

    public void setIssuerOfPatientIDFormat(String issuerOfPatientIDFormat) {
        this.issuerOfPatientIDFormat = issuerOfPatientIDFormat;
    }

    public boolean match(Dimse dimse, TransferCapability.Role role, String sopClass,
            String sendingHost, String sendingAET, String receivingHost, String receivingAET, Attributes attrs) {
        return this.role == role && this.dimse == dimse && isEmptyOrContains(sopClasses, sopClass)
                && conditions.match(sendingHost, sendingAET, receivingHost, receivingAET, attrs);
    }

    private static boolean isEmptyOrContains(Object[] a, Object o) {
        if (a.length == 0)
            return true;

        if (o != null)
            for (Object o1 : a)
                if (o1.equals(o))
                    return true;
        return false;
    }

    public AttributesCoercion nullifyIssuerOfPatientID(Attributes attrs, final AttributesCoercion next) {
        if (!nullifyIssuerOfPatientID(attrs))
            return next;

        return new AttributesCoercion() {
            @Override
            public void coerce(Attributes attrs, Attributes modified) {
                String issuerOfPatientID = attrs.getString(Tag.IssuerOfPatientID);
                if (issuerOfPatientID != null && !issuerOfPatientID.isEmpty()) {
                    attrs.setNull(Tag.IssuerOfPatientID, VR.LO);
                    if (modified != null)
                        modified.setString(Tag.IssuerOfPatientID, VR.LO, issuerOfPatientID);
                }
                Attributes item = attrs.getNestedDataset(Tag.IssuerOfPatientIDQualifiersSequence);
                if (item != null) {
                    attrs.setNull(Tag.IssuerOfPatientIDQualifiersSequence, VR.SQ);
                    if (modified != null)
                        modified.newSequence(Tag.IssuerOfPatientIDQualifiersSequence, 1).add(item);
                }
                if (next != null)
                    next.coerce(attrs, modified);
            }

            @Override
            public String remapUID(String uid) {
                return next != null ? next.remapUID(uid) : uid;
            }
        };
    }

    private boolean nullifyIssuerOfPatientID(Attributes attrs) {
        return nullifyIssuerOfPatientID != null
                && nullifyIssuerOfPatientID.test(Issuer.fromIssuerOfPatientID(attrs), issuerOfPatientIDs);
    }

    public AttributesCoercion mergeAttributes(final AttributesCoercion next) {
        if (mergeAttributes == null)
            return next;

        return new AttributesCoercion() {
            @Override
            public void coerce(Attributes attrs, Attributes modified) {
                for (MergeAttribute mergeAttribute : mergeAttributes) {
                    mergeAttribute.merge(attrs, modified);
                }

                if (next != null)
                    next.coerce(attrs, modified);
            }

            @Override
            public String remapUID(String uid) {
                return next != null ? next.remapUID(uid) : uid;
            }
        };
    }

    public AttributesCoercion supplementIssuerOfPatientID(final AttributesCoercion next) {
        if (issuerOfPatientIDFormat == null)
            return next;

        return new AttributesCoercion() {
            @Override
            public void coerce(Attributes attrs, Attributes modified) {
                String issuerOfPatientID = attrs.getString(Tag.IssuerOfPatientID);
                String supplementIssuerOfPatientID = new AttributesFormat(issuerOfPatientIDFormat).format(attrs);

                attrs.setString(Tag.IssuerOfPatientID, VR.LO,
                        issuerOfPatientID != null && !issuerOfPatientID.isEmpty()
                                ? issuerOfPatientID + "-" + supplementIssuerOfPatientID
                                : supplementIssuerOfPatientID);
                if (modified != null)
                    modified.setString(Tag.IssuerOfPatientID, VR.LO, issuerOfPatientID);

                if (next != null)
                    next.coerce(attrs, modified);
            }

            @Override
            public String remapUID(String uid) {
                return next != null ? next.remapUID(uid) : uid;
            }
        };
    }

    @Override
    public String toString() {
        return "ArchiveAttributeCoercion[cn=" + commonName
                + ", priority=" + priority
                + ", DIMSE=" + dimse
                + ", role=" + role
                + ", cuids=" + Arrays.toString(sopClasses)
                + ", conditions=" + conditions.toString()
                + ", retrieveAsReceived=" + retrieveAsReceived
                + ", deIdentification=" + Arrays.toString(deIdentification)
                + ", xslturi=" + xsltStylesheetURI
                + ", noKeywords=" + noKeywords
                + ", leadingCFindSCP=" + leadingCFindSCP
                + ", mergeMWLMatchingKey=" + mergeMWLMatchingKey
                + ", mergeMWLTemplateURI=" + mergeMWLTemplateURI
                + ", attributeUpdatePolicy=" + attributeUpdatePolicy
                + ", trimISO2022CharacterSet=" + trimISO2022CharacterSet
                + ", useCallingAETitleAs=" + useCallingAETitleAs
                + ", nullifyTags=" + Arrays.toString(nullifyTags)
                + ", nullifyTags=" + Arrays.toString(mergeAttributes)
                + ", nullifyIssuerOfPatientID=" + nullifyIssuerOfPatientID
                + ", issuerOfPatientIDs=" + Arrays.toString(issuerOfPatientIDs)
                + ", issuerOfPatientIDFormat=" + issuerOfPatientIDFormat
                + ", supplementFromDeviceName="
                + (supplementFromDevice != null ? supplementFromDevice.getDeviceName() : null)
                + "]";
    }
}
