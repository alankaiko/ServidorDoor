package com.laudoecia.api.sistemdicom.worklist;

import java.util.Arrays;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.AttributesCoercion;
import org.dcm4che3.data.Issuer;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.net.Device;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.TransferCapability;
import org.dcm4che3.util.AttributesFormat;

public class ArchiveAttributeCoercion {

    private String commonName;
    private int priority;
    private Dimse dimse;
    private TransferCapability.Role role;
    private String[] sopClasses = {};
    private boolean retrieveAsReceived;
    private String xsltStylesheetURI;
    private boolean noKeywords;
    private String leadingCFindSCP;
    private String mergeMWLTemplateURI;
    private Attributes.UpdatePolicy attributeUpdatePolicy = Attributes.UpdatePolicy.MERGE;
    private boolean trimISO2022CharacterSet;
    private int[] nullifyTags = {};
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

    public boolean isRetrieveAsReceived() {
        return retrieveAsReceived;
    }

    public ArchiveAttributeCoercion setRetrieveAsReceived(boolean retrieveAsReceived) {
        this.retrieveAsReceived = retrieveAsReceived;
        return this;
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

    public int[] getNullifyTags() {
        return nullifyTags;
    }

    public void setNullifyTags(int[] nullifyTags) {
        this.nullifyTags = nullifyTags;
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
        return this.role == role && this.dimse == dimse && isEmptyOrContains(sopClasses, sopClass);
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
                + ", retrieveAsReceived=" + retrieveAsReceived
                + ", xslturi=" + xsltStylesheetURI
                + ", noKeywords=" + noKeywords
                + ", leadingCFindSCP=" + leadingCFindSCP
                + ", mergeMWLTemplateURI=" + mergeMWLTemplateURI
                + ", attributeUpdatePolicy=" + attributeUpdatePolicy
                + ", trimISO2022CharacterSet=" + trimISO2022CharacterSet
                + ", nullifyTags=" + Arrays.toString(nullifyTags)
                + ", issuerOfPatientIDs=" + Arrays.toString(issuerOfPatientIDs)
                + ", issuerOfPatientIDFormat=" + issuerOfPatientIDFormat
                + ", supplementFromDeviceName="
                + (supplementFromDevice != null ? supplementFromDevice.getDeviceName() : null)
                + "]";
    }
}
