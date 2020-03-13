package com.laudoecia.api.worklistes;

import java.util.List;

import com.laudoecia.api.utilities.FuzzyStr;



public class QueryParam {

	private FuzzyStr fuzzyStr;
    private AttributeFilter[] attributeFilters;
    private boolean combinedDatetimeMatching;
    private boolean fuzzySemanticMatching;
    private boolean matchUnknown;
    private List<Code> hideConceptNameCodes;
    private List<Code> hideRejectionCodes;
    private String[] roles;
    private boolean showEmptyStudy;
    private boolean showEmptySeries;
    private boolean returnOtherPatientIDs;
    private Issuer defaultIssuerOfPatientID;
    private Issuer defaultIssuerOfAccessionNumber;

    public final boolean isCombinedDatetimeMatching() {
        return combinedDatetimeMatching;
    }

    public final void setCombinedDatetimeMatching(boolean combinedDatetimeMatching) {
        this.combinedDatetimeMatching = combinedDatetimeMatching;
    }

    public final boolean isFuzzySemanticMatching() {
        return fuzzySemanticMatching;
    }

    public final void setFuzzySemanticMatching(boolean fuzzySemanticMatching) {
        this.fuzzySemanticMatching = fuzzySemanticMatching;
    }

    public final boolean isMatchUnknown() {
        return matchUnknown;
    }

    public final void setMatchUnknown(boolean matchUnknown) {
        this.matchUnknown = matchUnknown;
    }

    public final String[] getRoles() {
        return roles != null ? roles.clone() : null;
    }

    public final void setRoles(String... roles) {
        this.roles = roles != null ? roles.clone() : null;
    }

    public final void setFuzzyStr(FuzzyStr fuzzyStr) {
        this.fuzzyStr = fuzzyStr;
    }

    public final FuzzyStr getFuzzyStr() {
        return fuzzyStr;
    }

    public final void setAttributeFilters(AttributeFilter[] attributeFilters) {
        this.attributeFilters = attributeFilters;
    }

    public final AttributeFilter[] getAttributeFilters() {
        return attributeFilters;
    }

    public List<Code> getHideConceptNameCodes() {
        return hideConceptNameCodes;
    }

    public void setHideConceptNameCodes(List<Code> hideConceptNameCodes) {
        this.hideConceptNameCodes = hideConceptNameCodes;
    }

    public List<Code> getHideRejectionCodes() {
        return hideRejectionCodes;
    }

    public void setHideRejectionCodes(List<Code> hideRejectionCodes) {
        this.hideRejectionCodes = hideRejectionCodes;
    }

    public boolean isShowEmptyStudy() {
        return showEmptyStudy;
    }

    public void setShowEmptyStudy(boolean showEmptyStudy) {
        this.showEmptyStudy = showEmptyStudy;
    }

    public boolean isShowEmptySeries() {
        return showEmptySeries;
    }

    public void setShowEmptySeries(boolean showEmptySeries) {
        this.showEmptySeries = showEmptySeries;
    }

    public boolean isReturnOtherPatientIDs() {
        return returnOtherPatientIDs;
    }

    public void setReturnOtherPatientIDs(boolean returnOtherPatientIDs) {
        this.returnOtherPatientIDs = returnOtherPatientIDs;
    }

    public Issuer getDefaultIssuerOfPatientID() {
        return defaultIssuerOfPatientID;
    }

    public void setDefaultIssuerOfPatientID(Issuer issuer) {
        this.defaultIssuerOfPatientID = issuer;
    }

    public Issuer getDefaultIssuerOfAccessionNumber() {
        return defaultIssuerOfAccessionNumber;
    }

    public void setDefaultIssuerOfAccessionNumber(Issuer issuer) {
        this.defaultIssuerOfAccessionNumber = issuer;
    }

}
