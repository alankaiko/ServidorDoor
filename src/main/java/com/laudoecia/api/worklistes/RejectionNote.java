package com.laudoecia.api.worklistes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;

public class RejectionNote implements Serializable {

	private static final long serialVersionUID = -1299501804796953248L;

	public enum Action {
		HIDE_REJECTED_INSTANCES, HIDE_REJECTION_NOTE, NOT_ACCEPT_SUBSEQUENT_OCCURRENCE, NOT_REJECT_SUBSEQUENT_OCCURRENCE
	}

	private final String codeValue;
	private final String codingSchemeDesignator;
	private final String codingSchemeVersion;
	private final String codeMeaning;
	private final EnumSet<Action> actions = EnumSet.noneOf(Action.class);
	private Code code;

	public RejectionNote(String codeValue, String codingSchemeDesignator, String codingSchemeVersion,
			String codeMeaning) {
		this.codeValue = codeValue;
		this.codingSchemeDesignator = codingSchemeDesignator;
		this.codingSchemeVersion = codingSchemeVersion;
		this.codeMeaning = codeMeaning;
	}

	public final String getCodeValue() {
		return codeValue;
	}

	public final String getCodingSchemeDesignator() {
		return codingSchemeDesignator;
	}

	public final String getCodingSchemeVersion() {
		return codingSchemeVersion;
	}

	public final String getCodeMeaning() {
		return codeMeaning;
	}

	public final Code getCode() {
		return code;
	}

	public final void setCode(Code code) {
		this.code = code;
	}

	public final EnumSet<Action> getActions() {
		return actions;
	}

	public RejectionNote addAction(Action action) {
		actions.add(action);
		return this;
	}

	public boolean matches(Code code) {
		return matches(code.getCodeValue(), code.getCodingSchemeDesignator(), code.getCodingSchemeVersion());
	}

	public boolean matches(Attributes codeItem) {
		return matches(codeItem.getString(Tag.CodeValue), codeItem.getString(Tag.CodingSchemeDesignator),
				codeItem.getString(Tag.CodingSchemeVersion));
	}

	public boolean matches(RejectionNote other) {
		return matches(other.codeValue, other.codingSchemeDesignator, other.codingSchemeVersion);
	}

	public boolean matches(String codeValue, String codingSchemeDesignator, String codingSchemeVersion) {
		if (!this.codeValue.equals(codeValue))
			return false;
		if (!this.codingSchemeDesignator.equals(codingSchemeDesignator))
			return false;
		if (this.codingSchemeVersion != null ? !this.codingSchemeVersion.equals(codingSchemeVersion)
				: codingSchemeVersion != null)
			return false;
		return true;
	}

	public static List<RejectionNote> selectByAction(List<RejectionNote> rns, Action action) {
		List<RejectionNote> list = new ArrayList<RejectionNote>(rns.size());
		for (RejectionNote rn : rns)
			if (rn.getActions().contains(action))
				list.add(rn);
		return list;
	}

}
