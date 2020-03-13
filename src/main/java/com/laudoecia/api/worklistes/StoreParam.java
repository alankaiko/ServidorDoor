package com.laudoecia.api.worklistes;

import java.util.Collections;
import java.util.List;

import org.dcm4che3.data.Attributes;

import com.laudoecia.api.utilities.FuzzyStr;

public class StoreParam {

	private FuzzyStr fuzzyStr;
	private AttributeFilter[] attributeFilters;
	private boolean storeOriginalAttributes;
	private String modifyingSystem;
	private String[] retrieveAETs;
	private String externalRetrieveAET;
	private List<StoreDuplicate> storeDuplicates = Collections.emptyList();
	private List<RejectionNote> rejectionNotes = Collections.emptyList();

	public final boolean isStoreOriginalAttributes() {
		return storeOriginalAttributes;
	}

	public final void setStoreOriginalAttributes(boolean storeOriginalAttributes) {
		this.storeOriginalAttributes = storeOriginalAttributes;
	}

	public final String getModifyingSystem() {
		return modifyingSystem;
	}

	public final void setModifyingSystem(String modifyingSystem) {
		this.modifyingSystem = modifyingSystem;
	}

	public final List<StoreDuplicate> getStoreDuplicates() {
		return storeDuplicates;
	}

	public final void setStoreDuplicates(List<StoreDuplicate> storeDuplicates) {
		this.storeDuplicates = storeDuplicates;
	}

	public final String[] getRetrieveAETs() {
		return retrieveAETs;
	}

	public final void setRetrieveAETs(String... retrieveAETs) {
		this.retrieveAETs = retrieveAETs;
	}

	public final String getExternalRetrieveAET() {
		return externalRetrieveAET;
	}

	public final void setExternalRetrieveAET(String externalRetrieveAET) {
		this.externalRetrieveAET = externalRetrieveAET;
	}

	public final void setFuzzyStr(FuzzyStr fuzzyStr) {
		this.fuzzyStr = fuzzyStr;
	}

	public final FuzzyStr getFuzzyStr() {
		return fuzzyStr;
	}

	public final void setAttributeFilters(AttributeFilter... attributeFilters) {
		this.attributeFilters = attributeFilters;
	}

	public AttributeFilter getAttributeFilter(Entity entity) {
		return attributeFilters[entity.ordinal()];
	}

	public final void setRejectionNotes(List<RejectionNote> rejectionNotes) {
		this.rejectionNotes = rejectionNotes;
	}

	public List<RejectionNote> getRejectionNotes() {
		return rejectionNotes;
	}

	public RejectionNote getRejectionNote(Code code) {
		if (code != null)
			for (RejectionNote rn : rejectionNotes)
				if (rn.matches(code))
					return rn;
		return null;
	}

	public RejectionNote getRejectionNote(Attributes codeItem) {
		if (codeItem != null)
			for (RejectionNote rn : rejectionNotes)
				if (rn.matches(codeItem))
					return rn;
		return null;
	}

	public StoreDuplicate.Action getStoreDuplicate(boolean noFiles, boolean eqChecksum, boolean eqFsGroup) {
		for (StoreDuplicate sd : storeDuplicates)
			if (sd.getCondition().matches(noFiles, eqChecksum, eqFsGroup))
				return sd.getAction();
		return StoreDuplicate.Action.IGNORE;
	}

}
