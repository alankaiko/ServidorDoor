package com.laudoecia.api.worklistes;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Code;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.UID;

public class QueryRetrieveView {

	private String viewID;
	private Code[] showInstancesRejectedByCode = {};
	private Code[] hideRejectionNotesWithCode = {};
	private boolean hideNotRejectedInstances = false;

	public final String getViewID() {
		return viewID;
	}

	public final void setViewID(String viewID) {
		this.viewID = viewID;
	}

	public final Code[] getShowInstancesRejectedByCodes() {
		return showInstancesRejectedByCode;
	}

	public final void setShowInstancesRejectedByCodes(Code... codes) {
		this.showInstancesRejectedByCode = codes;
	}

	public final Code[] getHideRejectionNotesWithCodes() {
		return hideRejectionNotesWithCode;
	}

	public final void setHideRejectionNotesWithCodes(Code... codes) {
		this.hideRejectionNotesWithCode = codes;
	}

	public final boolean isHideNotRejectedInstances() {
		return hideNotRejectedInstances;
	}

	public final void setHideNotRejectedInstances(boolean hideNotRejectedInstances) {
		this.hideNotRejectedInstances = hideNotRejectedInstances;
	}

	public boolean hideRejectionNote(Attributes attrs) {
		return UID.KeyObjectSelectionDocumentStorage.equals(attrs.getString(Tag.SOPClassUID))
				&& containsCode(hideRejectionNotesWithCode, attrs.getNestedDataset(Tag.ConceptNameCodeSequence));
	}

	public boolean hideRejectedInstance(Attributes codeItem) {
		return codeItem == null ? hideNotRejectedInstances : !containsCode(showInstancesRejectedByCode, codeItem);
	}

	private boolean containsCode(Code[] codes, Attributes codeItem) {
		Code code = new Code(codeItem);
		for (Code code1 : codes) {
			if (code1.equalsIgnoreMeaning(code))
				return true;
		}
		return false;
	}

}
