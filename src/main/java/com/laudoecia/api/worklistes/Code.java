package com.laudoecia.api.worklistes;

import java.io.Serializable;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;

public class Code implements Serializable {

	private static final long serialVersionUID = -130090842318534124L;

	public static final String FIND_BY_CODE_VALUE_WITHOUT_SCHEME_VERSION = "Code.findByCodeValueWithoutSchemeVersion";
	public static final String FIND_BY_CODE_VALUE_WITH_SCHEME_VERSION = "Code.findByCodeValueWithSchemeVersion";

	private long pk;

	private String codeValue;

	private String codingSchemeDesignator;

	private String codingSchemeVersion;

	private String codeMeaning;

	public Code() {
	}

	public Code(String codeValue, String codingSchemeDesignator, String codingSchemeVersion, String codeMeaning) {
		this.codeValue = codeValue;
		this.codingSchemeDesignator = codingSchemeDesignator;
		this.codingSchemeVersion = codingSchemeVersion;
		this.codeMeaning = codeMeaning;
	}

	public Code(Attributes item) {
		this(item.getString(Tag.CodeValue, null), item.getString(Tag.CodingSchemeDesignator, null),
				item.getString(Tag.CodingSchemeVersion, null), item.getString(Tag.CodeMeaning, null));
	}

	public long getPk() {
		return pk;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public String getCodingSchemeDesignator() {
		return codingSchemeDesignator;
	}

	public String getCodingSchemeVersion() {
		return codingSchemeVersion;
	}

	public String getCodeMeaning() {
		return codeMeaning;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append('(').append(codeValue).append(", ")
				.append(codingSchemeDesignator);
		if (codingSchemeVersion != null) {
			sb.append(';').append(codingSchemeVersion);
		}
		sb.append(", \"").append(codeMeaning).append("\")");
		return sb.toString();
	}

	public Attributes toItem() {
		Attributes codeItem = new Attributes(codingSchemeVersion != null ? 4 : 3);
		codeItem.setString(Tag.CodeValue, VR.SH, codeValue);
		codeItem.setString(Tag.CodingSchemeDesignator, VR.SH, codingSchemeDesignator);
		if (codingSchemeVersion != null)
			codeItem.setString(Tag.CodingSchemeVersion, VR.SH, codingSchemeVersion);
		codeItem.setString(Tag.CodeMeaning, VR.LO, codeMeaning);
		return codeItem;
	}
}
