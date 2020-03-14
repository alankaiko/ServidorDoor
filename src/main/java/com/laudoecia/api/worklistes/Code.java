package com.laudoecia.api.worklistes;

import org.dcm4che3.data.Attributes;

public class Code extends org.dcm4che3.data.Code {

	private Long codigo;

	public Code() {
	}

	public Code(org.dcm4che3.data.Code code) {
		super(code.getCodeValue(), code.getCodingSchemeDesignator(), code.getCodingSchemeVersion(),
				code.getCodeMeaning());
	}

	public Code(String codeValue, String codingSchemeDesignator, String codingSchemeVersion, String codeMeaning) {
		super(codeValue, codingSchemeDesignator, codingSchemeVersion, codeMeaning);
	}

	public Code(Attributes item) {
		super(item);
	}

	public Long getCodigo() {
		return codigo;
	}

	public boolean equals(Code other) {
		return other != null && other.codigo == codigo;
	}

}
